
  public final void read(Directory directory) throws IOException {
    generation = lastGeneration = -1;

    new FindSegmentsFile(directory) {

      @Override
      protected Object doBody(String segmentFileName) throws IOException {
        read(directory, segmentFileName);
        return null;
      }
    }.run();
  }

  // Only non-null after prepareCommit has been called and
  // before finishCommit is called
  ChecksumIndexOutput pendingSegnOutput;

  private static final String SEGMENT_INFO_UPGRADE_CODEC = "SegmentInfo3xUpgrade";
  private static final int SEGMENT_INFO_UPGRADE_VERSION = 0;

  private void write(Directory directory) throws IOException {

    String segmentsFileName = getNextSegmentFileName();
    
    // Always advance the generation on write:
    if (generation == -1) {
      generation = 1;
    } else {
      generation++;
    }
    
    ChecksumIndexOutput segnOutput = null;
    boolean success = false;

    final Set<String> upgradedSIFiles = new HashSet<String>();

    try {
      segnOutput = new ChecksumIndexOutput(directory.createOutput(segmentsFileName, IOContext.DEFAULT));
      CodecUtil.writeHeader(segnOutput, "segments", VERSION_40);
      segnOutput.writeLong(version); 
      segnOutput.writeInt(counter); // write counter
      segnOutput.writeInt(size()); // write infos
      for (SegmentInfoPerCommit siPerCommit : this) {
        SegmentInfo si = siPerCommit.info;
        segnOutput.writeString(si.name);
        segnOutput.writeString(si.getCodec().getName());
        segnOutput.writeLong(siPerCommit.getDelGen());
        segnOutput.writeInt(siPerCommit.getDelCount());
        assert si.dir == directory;

        assert siPerCommit.getDelCount() <= si.getDocCount();

        // If this segment is pre-4.x, perform a one-time
        // "ugprade" to write the .si file for it:
        String version = si.getVersion();
        if (version == null || StringHelper.getVersionComparator().compare(version, "4.0") < 0) {

          if (!segmentWasUpgraded(directory, si)) {

            String markerFileName = IndexFileNames.segmentFileName(si.name, "upgraded", Lucene3xSegmentInfoFormat.UPGRADED_SI_EXTENSION);
            si.addFile(markerFileName);

            final String segmentFileName = write3xInfo(directory, si, IOContext.DEFAULT);
            upgradedSIFiles.add(segmentFileName);
            directory.sync(Collections.singletonList(segmentFileName));

            // Write separate marker file indicating upgrade
            // is completed.  This way, if there is a JVM
            // kill/crash, OS crash, power loss, etc. while
            // writing the upgraded file, the marker file
            // will be missing:
            si.addFile(markerFileName);
            IndexOutput out = directory.createOutput(markerFileName, IOContext.DEFAULT);
            try {
              CodecUtil.writeHeader(out, SEGMENT_INFO_UPGRADE_CODEC, SEGMENT_INFO_UPGRADE_VERSION);
            } finally {
              out.close();
            }
            upgradedSIFiles.add(markerFileName);
            directory.sync(Collections.singletonList(markerFileName));
          }
        }
      }
      segnOutput.writeStringStringMap(userData);
      pendingSegnOutput = segnOutput;
      success = true;
    } finally {
      if (!success) {
        // We hit an exception above; try to close the file
        // but suppress any exception:
        IOUtils.closeWhileHandlingException(segnOutput);

        for(String fileName : upgradedSIFiles) {
          try {
            directory.deleteFile(fileName);
          } catch (Throwable t) {
            // Suppress so we keep throwing the original exception
          }
        }

        try {
          // Try not to leave a truncated segments_N file in
          // the index:
          directory.deleteFile(segmentsFileName);
        } catch (Throwable t) {
          // Suppress so we keep throwing the original exception
        }
      }
    }
  }

  private static boolean segmentWasUpgraded(Directory directory, SegmentInfo si) {
    // Check marker file:
    String markerFileName = IndexFileNames.segmentFileName(si.name, "upgraded", Lucene3xSegmentInfoFormat.UPGRADED_SI_EXTENSION);
    IndexInput in = null;
    try {
      in = directory.openInput(markerFileName, IOContext.READONCE);
      if (CodecUtil.checkHeader(in, SEGMENT_INFO_UPGRADE_CODEC, SEGMENT_INFO_UPGRADE_VERSION, SEGMENT_INFO_UPGRADE_VERSION) == 0) {
        return true;
      }
    } catch (IOException ioe) {
      // Ignore: if something is wrong w/ the marker file,
      // we will just upgrade again
    } finally {
      if (in != null) {
        IOUtils.closeWhileHandlingException(in);
      }
    }
    return false;
  }


  @Deprecated
  public static String write3xInfo(Directory dir, SegmentInfo si, IOContext context) throws IOException {

    // NOTE: this is NOT how 3.x is really written...
    String fileName = IndexFileNames.segmentFileName(si.name, "", Lucene3xSegmentInfoFormat.UPGRADED_SI_EXTENSION);
    si.addFile(fileName);

    //System.out.println("UPGRADE write " + fileName);
    boolean success = false;
    IndexOutput output = dir.createOutput(fileName, context);
    try {
      // we are about to write this SI in 3.x format, dropping all codec information, etc.
      // so it had better be a 3.x segment or you will get very confusing errors later.
      assert si.getCodec() instanceof Lucene3xCodec : "broken test, trying to mix preflex with other codecs";
      CodecUtil.writeHeader(output, Lucene3xSegmentInfoFormat.UPGRADED_SI_CODEC_NAME, 
                                    Lucene3xSegmentInfoFormat.UPGRADED_SI_VERSION_CURRENT);
      // Write the Lucene version that created this segment, since 3.1
      output.writeString(si.getVersion());
      output.writeInt(si.getDocCount());

      output.writeStringStringMap(si.attributes());

      output.writeByte((byte) (si.getUseCompoundFile() ? SegmentInfo.YES : SegmentInfo.NO));
      output.writeStringStringMap(si.getDiagnostics());
      output.writeStringSet(si.files());

      output.close();

      success = true;
    } finally {
      if (!success) {
        IOUtils.closeWhileHandlingException(output);
        try {
          si.dir.deleteFile(fileName);
        } catch (Throwable t) {
          // Suppress so we keep throwing the original exception
        }
      }
    }

    return fileName;
  }

  
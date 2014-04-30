
  public Collection<String> files(Directory dir, boolean includeSegmentsFile) throws IOException {
    HashSet<String> files = new HashSet<String>();
    if (includeSegmentsFile) {
      final String segmentFileName = getSegmentsFileName();
      if (segmentFileName != null) {
        files.add(segmentFileName);
      }
    }
    final int size = size();
    for(int i=0;i<size;i++) {
      final SegmentInfoPerCommit info = info(i);
      assert info.info.dir == dir;
      if (info.info.dir == dir) {
        files.addAll(info.files());
      }
    }
    return files;
  }

  final void finishCommit(Directory dir) throws IOException {
    if (pendingSegnOutput == null) {
      throw new IllegalStateException("prepareCommit was not called");
    }
    boolean success = false;
    try {
      pendingSegnOutput.finishCommit();
      success = true;
    } finally {
      if (!success) {
        // Closes pendingSegnOutput & deletes partial segments_N:
        rollbackCommit(dir);
      } else {
        success = false;
        try {
          pendingSegnOutput.close();
          success = true;
        } finally {
          if (!success) {
            // Closes pendingSegnOutput & deletes partial segments_N:
            rollbackCommit(dir);
          } else {
            pendingSegnOutput = null;
          }
        }
      }
    }

    // NOTE: if we crash here, we have left a segments_N
    // file in the directory in a possibly corrupt state (if
    // some bytes made it to stable storage and others
    // didn't).  But, the segments_N file includes checksum
    // at the end, which should catch this case.  So when a
    // reader tries to read it, it will throw a
    // CorruptIndexException, which should cause the retry
    // logic in SegmentInfos to kick in and load the last
    // good (previous) segments_N-1 file.

    final String fileName = IndexFileNames.fileNameFromGeneration(IndexFileNames.SEGMENTS, "", generation);
    success = false;
    try {
      dir.sync(Collections.singleton(fileName));
      success = true;
    } finally {
      if (!success) {
        try {
          dir.deleteFile(fileName);
        } catch (Throwable t) {
          // Suppress so we keep throwing the original exception
        }
      }
    }

    lastGeneration = generation;
    writeSegmentsGen(dir, generation);
  }

  

  public String getNextSegmentFileName() {
    long nextGeneration;

    if (generation == -1) {
      nextGeneration = 1;
    } else {
      nextGeneration = generation+1;
    }
    return IndexFileNames.fileNameFromGeneration(IndexFileNames.SEGMENTS,
                                                 "",
                                                 nextGeneration);
  }

  
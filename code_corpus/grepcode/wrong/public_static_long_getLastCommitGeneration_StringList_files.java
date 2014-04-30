
  public static long getLastCommitGeneration(String[] files) {
    if (files == null) {
      return -1;
    }
    long max = -1;
    for (String file : files) {
      if (file.startsWith(IndexFileNames.SEGMENTS) && !file.equals(IndexFileNames.SEGMENTS_GEN)) {
        long gen = generationFromSegmentsFileName(file);
        if (gen > max) {
          max = gen;
        }
      }
    }
    return max;
  }

  

  public static long getLastCommitGeneration(Directory directory) throws IOException {
    try {
      return getLastCommitGeneration(directory.listAll());
    } catch (NoSuchDirectoryException nsde) {
      return -1;
    }
  }

  

  final void prepareCommit(Directory dir) throws IOException {
    if (pendingSegnOutput != null) {
      throw new IllegalStateException("prepareCommit was already called");
    }
    write(dir);
  }

  
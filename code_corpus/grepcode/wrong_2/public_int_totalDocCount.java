
  public int totalDocCount() {
    int count = 0;
    for(SegmentInfoPerCommit info : this) {
      count += info.info.getDocCount();
    }
    return count;
  }

  
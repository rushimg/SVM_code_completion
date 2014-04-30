
  void replace(SegmentInfos other) {
    rollbackSegmentInfos(other.asList());
    lastGeneration = other.lastGeneration;
  }

  
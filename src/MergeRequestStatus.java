enum MergeRequestStatus {
  AVAILABLE_TO_AUTOMATIC_MERGE,
  HAS_CONFLICTS_TO_MERGE;

  public boolean canBeMergedAutomatically() {
    return this.equals(MergeRequestStatus.AVAILABLE_TO_AUTOMATIC_MERGE);
  }
}

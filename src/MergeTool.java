class MergeTool {
  MergeTool() {}

  static MergeRequestStatus createNewMergeRequest(Branch source, Branch taret) {
    if (source.hasNoConflictsWith(taret)) {
      return MergeRequestStatus.AVAILABLE_TO_AUTOMATIC_MERGE;
    }
    return MergeRequestStatus.HAS_CONFLICTS_TO_MERGE;
  }

  static Branch merge(final Branch source, final Branch target) {
    return new Branch(target.getBranchName());
  }
}

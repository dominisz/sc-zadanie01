class Branch {
  private final String branchName;

  Branch(final String develop) {
    this.branchName = develop;
  }

  boolean hasNoConflictsWith(final Branch taret) {
    return true;
  }

  String getBranchName() {
    return branchName;
  }
}

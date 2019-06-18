import java.util.logging.Level;
import java.util.logging.LogRecord;

import static java.lang.String.format;
import static java.util.logging.Logger.getAnonymousLogger;

public class ApplicationFlow {

  public static void main(String[] args) {
    Branch master = new Branch("master");
    Branch develop = new Branch("develop");
    MergeRequestStatus mergeRequestStatus = MergeTool.createNewMergeRequest(develop, master);
    if (mergeRequestStatus.canBeMergedAutomatically()) {
      Branch mergedMaster = MergeTool.merge(develop, master);
      logSuccess(master, develop);
    } else {
      getAnonymousLogger().log(new LogRecord(Level.WARNING, mergeRequestStatus.name()));
    }
  }

  private static void logSuccess(final Branch master, final Branch develop) {
    getAnonymousLogger()
        .log(
            new LogRecord(
                Level.INFO,
                format("Merged branch %s to %s", develop.getBranchName(), master.getBranchName())));
  }
}

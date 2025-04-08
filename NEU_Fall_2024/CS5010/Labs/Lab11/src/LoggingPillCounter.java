import java.io.FileOutputStream;


/**
 * This implementation of a pill counter has a built-in logging capability.
 * Every change to the count of pills is written to a log file.
 * THIS FILE EXISTS IN LEGACY CODE. YOU DO NOT HAVE THE CORRECT
 * PRIVILEGES TO EDIT THIS FILE TO IMPROVE IT.
 */

public class LoggingPillCounter implements PillCounter {
  private int count;

  /**
   * Create a pill counter initialized to a zero count.
   */
  public LoggingPillCounter() {
    count = 0;

  }

  /**
   * Add the specific number of pills to this counter. This method
   * is general enough to work with machines with different pill-filling
   * capacities.
   * @param count the amount of pills to be added
   */
  @Override
  public void addPill(int count) {
    if (count > 0) {
      this.count += count;
      log("Added " + count + "\n");
    }
  }

  /**
   * Remove a pill from this counter. This method is called in case
   * a malfunction in the hardware is detected, and it dispenses too
   * many pills. Only one pill may be removed at a time.
   */
  @Override
  public void removePill() {
    if (count > 0) {
      this.count -= 1;
      log("Removed 1\n");
    }
  }

  /**
   * Reset the counter to 0.
   */
  @Override
  public void reset() {
    count = 0;
    log("Reset\n");
  }

  /**
   * Return how many pills have been counted so far.
   * @return the pill count
   */
  @Override
  public int getPillCount() {
    return this.count;
  }

  private void log(String logMessage) {
    FileOutputStream out = null;

    try {
      //open file to append, this will not overwrite file if present
      out = new FileOutputStream("log.txt", true);
      out.write(logMessage.getBytes());
      out.close();
    } catch (Exception e) {
      System.out.println("Log cannot be opened");
    }

  }
}

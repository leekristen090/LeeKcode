
/**
 * Batch adding pill counter class which extends the pill counter decorator.
 */
public class PillBatchAddCounter extends PillCounterDecorator {
  private int pendingPills = 0;

  public PillBatchAddCounter(PillCounter pillCounter) {
    super(pillCounter);
  }

  /**
   * Accumulate the count instead of immediately forwarding to the delegate.
   * @param count the amount of pills to be added
   */
  @Override
  public void addPill(int count) {
    pendingPills += count;
  }

  /**
   * Forward all accumulated pills to the delegate and reset pending count.
   * @return the delegate get pill count
   */
  @Override
  public int getPillCount() {
    flush();
    return super.getPillCount();
  }

  /**
   * Reset the counter to 0.
   */
  @Override
  public void reset() {
    flush();
    super.reset();
  }

  /**
   * give pending pills to the delegate.
   */
  private void flush() {
    if (pendingPills > 0) {
      super.addPill(pendingPills);
      pendingPills = 0;
    }
  }

}
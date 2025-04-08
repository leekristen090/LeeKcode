
/**
 * This class is the decorator for the pill counter.
 */
public class PillCounterDecorator implements PillCounter {
  private PillCounter pillCounter;

  /**
   * Constructing the pill counter decorator which takes parameter PillCounter.
   * @param pillCounter our pill counter
   */
  public PillCounterDecorator(PillCounter pillCounter) {
    this.pillCounter = pillCounter;
  }

  /**
   * Add the specific number of pills to this counter. This method
   * is general enough to work with machines with different pill-filling
   * capacities.
   * @param count the amount of pills to be added
   */
  @Override
  public void addPill(int count) {
    pillCounter.addPill(count);
  }

  /**
   * Remove a pill from this counter. This method is called in case
   * a malfunction in the hardware is detected, and it dispenses too
   * many pills. Only one pill may be removed at a time.
   */
  @Override
  public void removePill() {
    pillCounter.removePill();
  }

  /**
   * Reset the counter to 0.
   */
  @Override
  public void reset() {
    pillCounter.reset();
  }

  /**
   * Return how many pills have been counted so far.
   * @return the pill count
   */
  @Override
  public int getPillCount() {
    return pillCounter.getPillCount();
  }
}
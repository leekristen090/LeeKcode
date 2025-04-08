
/**
 * Logging pill decorator.
 */
public class LoggingDecorator extends PillCounterDecorator {

  public LoggingDecorator(PillCounter pillCounter) {
    super(pillCounter);
  }

  /**
   * Add pills to the counter.
   * @param count the amount of pills to be added
   */
  @Override
  public void addPill(int count) {
    super.addPill(count);
  }

  /**
   * Get the pill count.
   * @return the count
   */
  @Override
  public int getPillCount() {
    int count = super.getPillCount();
    System.out.println("Current pill count: " + count);
    return count;
  }

  /**
   * Reset the counter to 0.
   */
  @Override
  public void reset() {
    super.reset();
  }
}
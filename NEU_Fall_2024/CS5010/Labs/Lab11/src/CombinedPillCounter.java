
import java.util.ArrayList;
import java.util.List;

/**
 * This is the combination of the batch-adding decorator with the add-monitoring decorator.
 */
public class CombinedPillCounter extends PillCounterDecorator {

  private final List<Integer> addCounts = new ArrayList<>();
  private int pendingPills = 0;

  /**
   * Construct the combined pill counter.
   * @param delegate the delegate
   */
  public CombinedPillCounter(PillCounter delegate) {
    super(delegate);
  }

  /**
   * Add the pills to the pill counter.
   * @param count the amount of pills to be added
   */
  @Override
  public void addPill(int count) {
    pendingPills += count;
  }

  /**
   * Get the pill counter.
   * @return the count
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
   * Get the count of times pills have been added to the counter.
   * @return the count
   */
  public List<Integer> getAddCounts() {
    //return new ArrayList<>(addCounts);
    return addCounts;
  }

  private void flush() {
    if (pendingPills > 0) {
      super.addPill(pendingPills);
      //addCounts.add(1);
      addCounts.add(pendingPills);
      pendingPills = 0;
    }
  }

}
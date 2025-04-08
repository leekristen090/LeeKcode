import java.util.ArrayList;
import java.util.List;

/**
 * This is the pill counter monitor.
 * This class will monitor the number of times pills are added to a pill counter before it
 * is reset (this would monitor how many times the pill counter is used while filling one bottle).
 * The same pill counter may be reset many times, so this decorator saves all add counts.
 * Each time the pill counter is reset, this new class will start a new count.
 */
public class PillAddMonitor extends PillCounterDecorator {

  private int currentAddCount = 0;
  private final List<Integer> addCounts = new ArrayList<>();

  public PillAddMonitor(PillCounter pillCounter) {
    super(pillCounter);
  }

  /**
   * Add the specific number of pills to this counter. This method
   * is general enough to work with machines with different pill-filling
   * capacities. Updates the number of times pills are added to the pill counter.
   * @param count the amount of pills to be added
   */
  @Override
  public void addPill(int count) {
    super.addPill(count);
    currentAddCount++;
  }

  /**
   * Reset the counter to 0.
   */
  @Override
  public void reset() {
    super.reset();
    addCounts.add(currentAddCount);
    currentAddCount = 0;
  }

  public List<Integer> getAddCounts() {
    return new ArrayList<>(addCounts);
  }
}
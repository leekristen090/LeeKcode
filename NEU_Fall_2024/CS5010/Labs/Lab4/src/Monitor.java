/**
 * This is the Monitor interface.
 */
public interface Monitor<T> {

  /**
   * Add a given BP record to the PatientMonitor.
   * @param t record to be added
   */
  public void add(T t);

  /**
   * Remove a given record from the PatientMonitor.
   * @param t record to be removed
   */
  public void remove(T t);

  /**
   * Gets the number of records in a PatientMonitor.
   * @return number of records
   */
  public int getNumberOfRecords();

  /**
   * A record indicates a hypertensive crisis if the systolic reading is above 180 or a diastolic
   * reading is above 120. An emergency is when more than one patient records indicate a
   * hypertensive crisis.
   *
   * @return true if emergency condition is met
   */
  public boolean emergency();
}
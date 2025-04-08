/**
 * This is the Blood pressure record interface.
 */
public interface BloodPressureRecord {

  /**
   * This is a method to get the unique ID of a blood pressure record.
   * @return the ID
   */
  String getID();

  /**
   * A method to get the systolic reading.
   * @return systolic
   */
  double getSystolicReading();

  /**
   * A method to get the diastolic reading.
   * @return diastolic
   */
  double getDiastolicReading();

  /**
   * A method to update the systolic blood pressure. It should throw an IllegalArgumentException if
   * the new systolic reading is lower than the existing diastolic reading.
   * @param sys systolic to update
   */
  void updateSystolicReading(double sys) throws IllegalArgumentException;

  /**
   * A method to update the systolic blood pressure. It should throw an IllegalArgumentException if
   * the new diastolic reading is higher than the existing systolic reading.
   * @param dias diastolic to update
   */
  void updateDiastolicReading(double dias) throws IllegalArgumentException;
}
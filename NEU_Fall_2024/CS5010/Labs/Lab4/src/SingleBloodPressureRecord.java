import java.util.Objects;

/**
 * This is the single blood pressure record class which implements the BloodPressureRecord
 * interface.
 */
public class SingleBloodPressureRecord implements BloodPressureRecord {
  private double systolic;
  private double diastolic;
  private final String id;

  /**
   * This is a constructor for a single blood pressure record. It consists of a systolic and
   * diastolic reading. A blood pressure measurement has two numbers: systolic and diastolic. The
   * systolic is the higher number and the diastolic is the low number. For example, in a
   * blood pressure reading of 120/80, 120 and 80 are the systolic and diastolic readings
   * respectively. The systolic reading can never be lesser than the diastolic reading.
   * @param sys systolic reading
   * @param dias diastolic reading
   */
  public SingleBloodPressureRecord(String id, double sys, double dias)
          throws IllegalArgumentException {
    if (sys < 0 || dias < 0) {
      throw new IllegalArgumentException("no negative values!");
    }
    if (sys < dias) {
      throw new IllegalArgumentException("Systolic can't be less than diastolic!");
    }
    if (id == null || id.trim().isEmpty()) {
      throw new IllegalArgumentException("ID can't be empty!");
    }
    this.id = id;
    this.systolic = sys;
    this.diastolic = dias;
  }

  /**
   * This is a method to get the unique ID of a blood pressure record.
   * @return the ID
   */
  @Override
  public String getID() {
    return this.id;
  }

  /**
   * A method to get the systolic reading.
   * @return systolic
   */
  @Override
  public double getSystolicReading() {
    return this.systolic;
  }

  /**
   * A method to get the diastolic reading.
   * @return diastolic
   */
  @Override
  public double getDiastolicReading() {
    return this.diastolic;
  }

  /**
   * A method to update the systolic blood pressure. It should throw an IllegalArgumentException if
   * the new systolic reading is lower than the existing diastolic reading.
   * @param sys systolic to update
   */
  @Override
  public void updateSystolicReading(double sys) throws IllegalArgumentException {
    if (sys < this.diastolic) {
      throw new IllegalArgumentException("Sys not allowed to be lower than dias!");
    }
    if (sys < 0) {
      throw new IllegalArgumentException("No negative systolic reading!");
    }
    this.systolic = sys;
  }

  /**
   * A method to update the systolic blood pressure. It should throw an IllegalArgumentException if
   * the new diastolic reading is higher than the existing systolic reading.
   * @param dias diastolic to update
   */
  @Override
  public void updateDiastolicReading(double dias) throws IllegalArgumentException {
    if (dias > this.systolic) {
      throw new IllegalArgumentException("Dias not allowed to be higher than sys!");
    }
    if (dias < 0) {
      throw new IllegalArgumentException("No negative diastolic reading!");
    }
    this.diastolic = dias;
  }

  /**
   * Two blood pressure records are the same if they have the same ID (case-sensitive), and their
   * respective readings are less than 1 apart.
   * @param o object to be compared
   * @return true if they are equal
   */
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof SingleBloodPressureRecord)) {
      return false;
    }
    SingleBloodPressureRecord other = (SingleBloodPressureRecord) o;
    if (this.getID().equals(other.getID())) {
      return (Math.round(this.getSystolicReading()) == Math.round(other.getSystolicReading()))
              && (Math.round(this.getDiastolicReading())
              == Math.round(other.getDiastolicReading()));
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, (int) systolic, (int) diastolic);
  }
}
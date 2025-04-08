import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a patient monitor. It monitors blood pressure records,
 * specifically to see how many of them are going into hypertensive crisis.
 */
public class PatientMonitor implements Monitor<BloodPressureRecord> {
  private List<BloodPressureRecord> bpRecordList;

  /**
   * Constructing a PatientMonitor that is empty.
   */
  public PatientMonitor() {
    this.bpRecordList = new ArrayList<>();
  }

  /**
   * Add a given BP record to the PatientMonitor.
   * @param t record to be added
   */
  @Override
  public void add(BloodPressureRecord t) {
    bpRecordList.add(t);
  }

  /**
   * Remove a given record from the PatientMonitor.
   * @param t record to be removed
   */
  @Override
  public void remove(BloodPressureRecord t) {
    bpRecordList.remove(t);
  }

  /**
   * Gets the number of records in a PatientMonitor.
   * @return number of records
   */
  @Override
  public int getNumberOfRecords() {
    return bpRecordList.size();
  }

  /**
   * A record indicates a hypertensive crisis if the systolic reading is above 180 or a diastolic
   * reading is above 120. An emergency is when more than one patient records indicate a
   * hypertensive crisis.
   *
   * @return true if emergency condition is met
   */
  @Override
  public boolean emergency() {
    List<String> uniquePatient = new ArrayList<>();
    // int count = 0;
    for (BloodPressureRecord t: bpRecordList) {
      if ((t.getSystolicReading() > 180) || (t.getDiastolicReading() > 120)) {
        // count += 1;
        if (!uniquePatient.contains(t.getID())) {
          uniquePatient.add(t.getID());
        }
      }

    }
    // return count > 1;
    return uniquePatient.size() > 1;
  }

}

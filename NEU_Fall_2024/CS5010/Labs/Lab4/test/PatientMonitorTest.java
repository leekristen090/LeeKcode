import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * This is the JUNIT test class for PatientMonitor.
 */
public class PatientMonitorTest {
  private PatientMonitor monitor;

  @Before
  public void setUp() {
    monitor = new PatientMonitor();
  }

  /**
   * Testing the constructor of PatientMonitor with 0 records.
   */
  @Test
  public void testConstructor() {
    assertEquals(0,monitor.getNumberOfRecords());
  }

  /**
   * Testing overzealous emergency reporting.
   */
  @Test
  public void testOverzealous() {
    // revised
    BloodPressureRecord record = new SingleBloodPressureRecord("record 1", 190,80);
    BloodPressureRecord dupe = new SingleBloodPressureRecord("record 1", 190,80);
    monitor.add(record);
    monitor.add(dupe);
    assertFalse("Emergency detected", monitor.emergency());
  }

  /**
   * Testing the add(BloodPressureRecord t) method.
   */
  @Test
  public void testAdd() {
    BloodPressureRecord record = new SingleBloodPressureRecord("record 1", 120,80);
    BloodPressureRecord record2 = new SingleBloodPressureRecord("record 2", 110,70);
    monitor.add(record);
    monitor.add(record2);
    assertEquals(2, monitor.getNumberOfRecords());
    assertFalse("Emergency detected", monitor.emergency()); // no emergency
  }

  /**
   * Testing the remove(BloodPressureRecord t) method.
   */
  @Test
  public void testRemove() {
    BloodPressureRecord record = new SingleBloodPressureRecord("record 1", 120,80);
    BloodPressureRecord record2 = new SingleBloodPressureRecord("record 2", 110,70);
    monitor.add(record);
    monitor.add(record2);
    assertEquals(2, monitor.getNumberOfRecords());
    monitor.remove(record2);
    assertEquals(1, monitor.getNumberOfRecords());
  }

  /**
   * Testing emergency along with updates and removals.
   */
  @Test
  public void testEmergency() {
    BloodPressureRecord record = new SingleBloodPressureRecord("record 1", 190,80);
    BloodPressureRecord record2 = new SingleBloodPressureRecord("record 2", 182,98);
    BloodPressureRecord record3 = new SingleBloodPressureRecord("record 3", 142,135);
    BloodPressureRecord record4 = new SingleBloodPressureRecord("record 4", 156,142);
    BloodPressureRecord record5 = new SingleBloodPressureRecord("record 5", 120,80);

    monitor.add(record);
    monitor.add(record2);
    monitor.add(record3);
    monitor.add(record4);
    monitor.add(record5);
    assertEquals(5, monitor.getNumberOfRecords());
    assertTrue("Emergency detected", monitor.emergency());
    monitor.remove(record);
    assertEquals(4, monitor.getNumberOfRecords());
    assertTrue("Emergency detected", monitor.emergency());
    record3.updateDiastolicReading(80);
    assertTrue("Emergency detected", monitor.emergency());
    monitor.remove(record2);
    assertFalse("Emergency detected", monitor.emergency());
    record5.updateSystolicReading(198);
    assertTrue("Emergency detected", monitor.emergency());
  }

}
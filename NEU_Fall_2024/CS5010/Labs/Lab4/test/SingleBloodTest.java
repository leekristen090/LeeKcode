import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * This is the test class for SingleBloodPressureRecord and its methods.
 */
public class SingleBloodTest {

  /**
   * This is testing the methods with a BP of 120/80.
   */
  @Test
  public void testConstructor() {
    BloodPressureRecord newSingle = new SingleBloodPressureRecord("One",120,80);
    assertEquals("One", newSingle.getID());
    assertEquals(120.0, newSingle.getSystolicReading(),.01);
    assertEquals(80.0, newSingle.getDiastolicReading(),.01);
  }

  /**
   * This is testing the illegal argument exceptions with sys < dias, negative sys and
   * dias, and an empty ID string.
   */
  @Test
  public void testInvalidBP() {
    try {
      SingleBloodPressureRecord newSingle =
              new SingleBloodPressureRecord("One", 80, 120);
      fail("This should have thrown an exception");
    } catch (IllegalArgumentException e) {
      // do nothing
    }
    try {
      SingleBloodPressureRecord newSingle =
              new SingleBloodPressureRecord("One", -120, -80);
      fail("This should have thrown an exception");
    } catch (IllegalArgumentException e) {
      // do nothing
    }
    try {
      SingleBloodPressureRecord newSingle =
              new SingleBloodPressureRecord("", 120, 80);
      fail("This should have thrown an exception");
    } catch (IllegalArgumentException e) {
      // do nothing
    }
  }

  /**
   * This is testing updateSystolicReading(). This is a valid update on systolic reading.
   */
  @Test
  public void testUpdateSys() {
    SingleBloodPressureRecord newSingle = new SingleBloodPressureRecord("One",120,80);
    newSingle.updateSystolicReading(100.0);
    assertEquals(100.0, newSingle.getSystolicReading(),.01);
  }

  /**
   * This is testing updateDiastolicReading(). This is a valid update on diastolic reading.
   */
  @Test
  public void testUpdateDias() {
    SingleBloodPressureRecord newSingle = new SingleBloodPressureRecord("One",120,80);
    newSingle.updateDiastolicReading(60.0);
    assertEquals(60.0, newSingle.getDiastolicReading(),.01);
  }

  /**
   * Testing update with a negative systolic reading. Also tests that we can't update systolic
   * because it is less than diastolic.
   */
  @Test
  public void testUpdateNegSys() {
    try {
      SingleBloodPressureRecord newSingle =
              new SingleBloodPressureRecord("One", 120, 80);
      newSingle.updateSystolicReading(-100.0);
      fail("This should have thrown an exception");
    } catch (IllegalArgumentException e) {
      // do nothing
    }
  }

  /**
   * This is testing update with a negative diastolic reading.
   */
  @Test
  public void testUpdateNegDias() {
    try {
      SingleBloodPressureRecord newSingle =
              new SingleBloodPressureRecord("One", 120, 80);
      newSingle.updateDiastolicReading(-60);
      fail("This should have thrown an exception");
    } catch (IllegalArgumentException e) {
      // do nothing
    }
  }

  /**
   * This is testing the equals() method.
   */
  @Test
  public void testEquals() {
    SingleBloodPressureRecord newSingle =
            new SingleBloodPressureRecord("One", 120, 80);
    SingleBloodPressureRecord newSingle2 =
            new SingleBloodPressureRecord("One", 120, 80.4);
    SingleBloodPressureRecord newSingle3 =
            new SingleBloodPressureRecord("One", 120, 80);
    SingleBloodPressureRecord newSingle4 =
            new SingleBloodPressureRecord("One", 120, 79.6);
    SingleBloodPressureRecord newSingle5 =
            new SingleBloodPressureRecord("two", 120.4, 79.6);
    SingleBloodPressureRecord newSingle6 =
            new SingleBloodPressureRecord("two", 119.7, 80.4);
    assertTrue(newSingle.equals(newSingle2));
    assertTrue(newSingle.equals(newSingle3));
    assertTrue(newSingle2.equals(newSingle4));
    assertTrue(newSingle5.equals(newSingle6));
  }

  /**
   * Testing BPs that aren't equal then updating them.
   */
  @Test
  public void testNotEquals() {
    SingleBloodPressureRecord newSingle =
            new SingleBloodPressureRecord("One", 120, 80);
    SingleBloodPressureRecord newSingle2 =
            new SingleBloodPressureRecord("One", 120, 70);
    SingleBloodPressureRecord newSingle3 =
            new SingleBloodPressureRecord("Two", 120, 80);
    SingleBloodPressureRecord newSingle4 =
            new SingleBloodPressureRecord("One", 110, 80);
    SingleBloodPressureRecord newSingle5 =
            new SingleBloodPressureRecord("One", 120, 80);
    SingleBloodPressureRecord newSingle6 =
            new SingleBloodPressureRecord("one", 120, 80);
    assertFalse(newSingle.equals(newSingle2));
    assertFalse(newSingle.equals(newSingle3));
    assertFalse(newSingle.equals(newSingle4));
    newSingle4.updateSystolicReading(120);
    assertTrue(newSingle.equals(newSingle4));
    newSingle2.updateDiastolicReading(80);
    assertTrue(newSingle.equals(newSingle2));
    assertFalse(newSingle5.equals(newSingle6));
  }

  /**
   * This is testing the hashCode() method.
   */
  @Test
  public void testHashCode() {
    SingleBloodPressureRecord newSingle =
            new SingleBloodPressureRecord("One", 120, 80);
    SingleBloodPressureRecord newSingle2 =
            new SingleBloodPressureRecord("One", 120, 80.4);
    SingleBloodPressureRecord newSingle3 =
            new SingleBloodPressureRecord("One", 120, 80);
    assertEquals(newSingle.hashCode(), newSingle2.hashCode());
    assertEquals(newSingle.hashCode(), newSingle3.hashCode());
  }

  /**
   * Testing BPs that don't have the same hash code then updating.
   */
  @Test
  public void testHashCodeNot() {
    SingleBloodPressureRecord newSingle =
            new SingleBloodPressureRecord("One", 120, 80);
    SingleBloodPressureRecord newSingle2 =
            new SingleBloodPressureRecord("One", 120, 70);
    SingleBloodPressureRecord newSingle3 =
            new SingleBloodPressureRecord("Two", 120, 80);
    SingleBloodPressureRecord newSingle4 =
            new SingleBloodPressureRecord("One", 110, 80);
    SingleBloodPressureRecord newSingle5 =
            new SingleBloodPressureRecord("one", 120, 80);
    assertNotEquals(newSingle.hashCode(), newSingle2.hashCode());
    assertNotEquals(newSingle.hashCode(), newSingle3.hashCode());
    assertNotEquals(newSingle.hashCode(), newSingle4.hashCode());
    assertNotEquals(newSingle.hashCode(), newSingle5.hashCode());
    newSingle4.updateSystolicReading(120);
    assertEquals(newSingle.hashCode(), newSingle4.hashCode());
    newSingle2.updateDiastolicReading(80);
    assertEquals(newSingle.hashCode(), newSingle2.hashCode());
  }

}
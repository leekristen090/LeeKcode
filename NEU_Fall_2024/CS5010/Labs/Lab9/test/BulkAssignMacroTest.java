import org.junit.Before;
import org.junit.Test;

import spreadsheet.BulkAssignMacro;
import spreadsheet.SparseSpreadSheet;
import spreadsheet.SpreadSheet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * This is the testing class for bulk assignment.
 */
public class BulkAssignMacroTest {
  private SpreadSheet spreadSheet;
  private double testValue = 5.0;

  /**
   * Construct spreadsheet.
   */
  @Before
  public void setup() {
    spreadSheet = new SparseSpreadSheet();
  }

  /**
   * Testing bulk assignment with a valid range.
   */
  @Test
  public void testBulkAssignValidRange() {
    BulkAssignMacro macro = new BulkAssignMacro(0, 0, 2, 2,
            testValue);
    macro.execute(spreadSheet);

    for (int row = 0; row <= 2; row++) {
      for (int col = 0; col <= 2; col++) {
        assertEquals(testValue, spreadSheet.get(row, col), 0.001);
      }
    }
  }

  /**
   * Testing invalid row.
   */
  @Test
  public void testBulkAssignOutOfBounds() {
    try {
      new BulkAssignMacro(-1, 0, 2, 2, testValue);
      fail("Expected IllegalArgumentException for negative startRow");
    } catch (IllegalArgumentException e) {
      // expected so we do nothing
    }
  }

  /**
   * Invalid range where start is larger than end.
   */
  @Test
  public void testBulkAssignInvalidRange() {
    try {
      new BulkAssignMacro(2, 2, 1, 1, testValue);
      fail("Expected IllegalArgumentException for invalid range");
    } catch (IllegalArgumentException e) {
      // expected so we do nothing
    }
  }
}

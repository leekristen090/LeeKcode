
import org.junit.Test;
import spreadsheet.MacroSpreadSheetController;
import spreadsheet.SpreadSheetWithMacro;
import spreadsheet.SpreadSheetWithMacroImpl;

import static org.junit.Assert.assertEquals;

/**
 * This is the test class for the macro spreadsheet controller class. We test the bulk assign
 * command using the input command.
 */
public class MacroSpreadSheetControllerTest {

  /**
   * Testing bulk assign through the input command.
   */
  @Test
  public void testBulkAssignCommand() {
    String input = "bulk-assign-value A 1 B 2 10.0";
    SpreadSheetWithMacro model = new SpreadSheetWithMacroImpl();

    Readable inputStream = new java.io.StringReader(input);
    Appendable outputStream = new StringBuilder();
    MacroSpreadSheetController controller = new MacroSpreadSheetController(model, inputStream,
            outputStream);

    controller.control();

    assertEquals(10.0, model.get(0, 0), 0.01);
    assertEquals(10.0, model.get(1, 1), 0.01);
  }

  /**
   * This is testing average with an empty spreadsheet.
   */
  @Test
  public void testZeroAverageCommand() {
    String input = "average A 1 B 10 C 2";
    SpreadSheetWithMacro model = new SpreadSheetWithMacroImpl();
    Readable inputStream = new java.io.StringReader(input);
    Appendable outputStream = new StringBuilder();
    MacroSpreadSheetController controller = new MacroSpreadSheetController(model, inputStream,
            outputStream);

    controller.control(); // Run the controller's logic with the input command

    // Check that the average is calculated correctly and stored in the destination cell
    assertEquals(0.0, model.get(2, 1), 0.01);
  }

  /**
   * Testing average of a spreadsheet and storing the average in C1 (2, 0).
   */
  @Test
  public void testAverageCommandWithFilledCells() {
    String input = "bulk-assign-value A 1 B 2 10.0\naverage A 1 B 2 C 1";

    SpreadSheetWithMacro model = new SpreadSheetWithMacroImpl();

    Readable inputStream = new java.io.StringReader(input);
    Appendable outputStream = new StringBuilder();

    MacroSpreadSheetController controller = new MacroSpreadSheetController(model, inputStream,
            outputStream);

    controller.control();

    // check if cells in A1:B2 were assigned correctly
    //System.out.println("value A1: " + model.get(0, 0));
    //System.out.println("value B2: " + model.get(1, 1));
    assertEquals(10.0, model.get(0, 0), 0.01);
    assertEquals(10.0, model.get(1, 1), 0.01);

    assertEquals(10.0, model.get(2, 0), 0.01);
  }

  /**
   * Testing the range assignment input command.
   */
  @Test
  public void testRangeAssignmentCommand() {
    String input = "range-assign A 1 A 10 2.0 10.0";
    SpreadSheetWithMacro model = new SpreadSheetWithMacroImpl();
    Readable inputStream = new java.io.StringReader(input);
    Appendable outputStream = new StringBuilder();

    MacroSpreadSheetController controller = new MacroSpreadSheetController(model, inputStream,
            outputStream);
    controller.control();
    //System.out.println("value A1: " + model.get(0, 0));
    assertEquals(2.0, model.get(0, 0), 0.01);
    assertEquals(12.0, model.get(0, 1), 0.01);
    assertEquals(22.0, model.get(0, 2), 0.01);
    assertEquals(32.0, model.get(0, 3), 0.01);
    assertEquals(42.0, model.get(0, 4), 0.01);
    assertEquals(92.0, model.get(0, 9), 0.01);
  }

}

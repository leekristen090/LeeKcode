
import org.junit.Before;
import org.junit.Test;

import spreadsheet.BetterSparseSpreadSheet;
import spreadsheet.BetterSpreadSheet;
import spreadsheet.MockSpreadSheet;
import spreadsheet.SpreadSheetController;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This is the test class for the spreadsheet program.
 */
public class SpreadSheetTest {

  private BetterSpreadSheet mockSheet;
  private MockSpreadSheet mock2Sheet;
  private MockAppendable mockOutput;

  /**
   * setting things up.
   */
  @Before
  public void setUp() {
    // Initialize a sparse spreadsheet as the mock sheet (assuming it's already tested)
    mockSheet = new BetterSparseSpreadSheet() {
    };
    // Initialize the mock appendable to capture output
    mockOutput = new MockAppendable();

    mock2Sheet = new MockSpreadSheet();
  }

  /**
   * Testing the welcome message.
   */
  @Test
  public void testWelcomeMessage() {
    // Prepare a readable that only supplies a quit command
    Readable mockInput = new StringReader("q");

    // Initialize controller with the mocks
    SpreadSheetController controller = new SpreadSheetController(mockSheet, mockInput, mockOutput);

    // Run the controller
    controller.goMethod();

    // Expected welcome message
    String expectedWelcomeMessage = "Welcome to the spreadsheet program!\n" +
            "Supported user instructions are: \n" +
            "assign-value row-num col-num value (set a cell to a value)\n" +
            "print-value row-num col-num (print the value at a given cell)\n" +
            "menu (Print supported instruction list)\n" +
            "q or quit (quit the program) \n";

    // Assert that the output starts with the welcome message
    String[] outputLines = mockOutput.getContent().split(System.lineSeparator());
    String actualWelcome = String.join(System.lineSeparator(), outputLines[0], outputLines[1],
            outputLines[2], outputLines[3], outputLines[4], outputLines[5]);
    assertEquals(expectedWelcomeMessage.trim(), actualWelcome.trim());
  }

  /**
   * testing the farewell message.
   */
  @Test
  public void testFarewellMessage() {
    // Prepare a readable that only supplies a quit command
    Readable mockInput = new StringReader("q");

    // Initialize controller with the mocks
    SpreadSheetController controller = new SpreadSheetController(mockSheet, mockInput, mockOutput);

    // Run the controller
    controller.goMethod();

    // Expected farewell message
    String expectedFarewellMessage = "Thank you for using this program!";

    // Check if the farewell message is present in the output
    assertTrue(mockOutput.getContent().contains(expectedFarewellMessage));
  }

  /**
   * testing setting the value.
   */
  @Test
  public void testSetValueInteraction() {
    // Prepare input for setting a value
    Readable mockInput = new StringReader("assign-value A 1 42\nq");
    SpreadSheetController controller = new SpreadSheetController(mock2Sheet, mockInput, mockOutput);

    // Run the controller
    controller.goMethod();

    // Check the log to verify the interactions
    List<String> expectedLog = List.of("set(0, 0, 42.0)");
    assertEquals(expectedLog, mock2Sheet.getLog());
  }

  /**
   * testing printing.
   */
  @Test
  public void testPrintValueInteraction() {
    // Prepare input for printing a value
    Readable mockInput = new StringReader("print-value A 1\nq");
    SpreadSheetController controller = new SpreadSheetController(mock2Sheet, mockInput, mockOutput);

    // Run the controller
    controller.goMethod();

    // Check the log to verify the interactions
    List<String> expectedLog = List.of("get(0, 0)");
    assertEquals(expectedLog, mock2Sheet.getLog());
  }

  /**
   * testing an empty spreadsheet.
   */
  @Test
  public void testIsEmptyInteraction() {
    // Prepare input for checking if a cell is empty
    Readable mockInput = new StringReader("print-value A 1\nq");
    SpreadSheetController controller = new SpreadSheetController(mock2Sheet, mockInput, mockOutput);

    // Run the controller
    controller.goMethod();

    // Check the log to verify the interactions
    List<String> expectedLog = List.of("get(0, 0)");
    assertEquals(expectedLog, mock2Sheet.getLog());
  }

  /**
   * testing the bulk assign command.
   */
  @Test
  public void testBulkAssign() {
    MockSpreadSheet mockSheet = new MockSpreadSheet();
    String input = "bulk-assign A 1 B 4 100\nq\n"; // User input
    Readable readable = new StringReader(input);
    Appendable appendable = new StringBuilder(); // Capture output

    SpreadSheetController controller = new SpreadSheetController(mockSheet, readable, appendable);
    controller.goMethod();

    // Check if the log contains the correct method call
    assertEquals(1, mockSheet.getLog().size());
    assertEquals("setRegion(0, 0, 1, 3, 100.0)", mockSheet.getLog().get(0));
  }

  // Mock Appendable class to capture the output in a StringBuilder
  private static class MockAppendable implements Appendable {
    private final StringBuilder content = new StringBuilder();

    @Override
    public Appendable append(CharSequence csq) throws IOException {
      content.append(csq);
      return this;
    }

    @Override
    public Appendable append(CharSequence csq, int start, int end) throws IOException {
      content.append(csq, start, end);
      return this;
    }

    @Override
    public Appendable append(char c) throws IOException {
      content.append(c);
      return this;
    }

    public String getContent() {
      return content.toString();
    }
  }
}

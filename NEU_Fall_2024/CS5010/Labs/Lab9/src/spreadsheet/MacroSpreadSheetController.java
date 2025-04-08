package spreadsheet;

import java.util.Scanner;

/**
 * This is the macro spreadsheet controller which extends the spreadsheet controller.
 * This class represents the controller of an interactive spreadsheet application.
 * This controller offers a simple text interface in which the user can
 * type instructions to manipulate a spreadsheet.
 *
 * <p>This controller works with any Readable to read its inputs and
 * any Appendable to transmit output. This controller directly uses
 * the Appendable object (i.e. there is no official "view")
 *
 * <p>A cell in the spreadsheet is referred to using a row-letter and a column number.
 * The row letter starts from A-Z and then AA-ZZ, then AAA-ZZZ and so on.
 * The column numbers begin with 1.
 *
 * <p>For example, the cell in the first row and column is A 1.
 * The cell in the 30th row and 26th column is AD 26.
 *
 * <p>In this way it tries to simulate how Microsoft Excel works (except that
 * it uses letters for rows, not columns).
 */
public class MacroSpreadSheetController extends SpreadSheetController {
  private final SpreadSheetWithMacro sheetWithMacro;

  /**
   * Construct a macro spreadsheet controller to work with the specified sheet (model),
   * readable (to take inputs) and appendable (to transmit output).
   * @param sheet the sheet to work with (the model)
   * @param readable the Readable object for inputs
   * @param appendable the Appendable objects to transmit any output
   */
  public MacroSpreadSheetController(SpreadSheetWithMacro sheet, Readable readable,
                                    Appendable appendable) {
    super(sheet, readable, appendable);
    //this.sheetWithMacro = new SpreadSheetWithMacroImpl();
    this.sheetWithMacro = sheet;
  }

  /**
   * We process the command from input and modify the spreadsheet accordingly.
   * @param userInstruction command
   * @param sc scan the string command
   * @param sheet the given sheet
   */
  @Override
  protected void processCommand(String userInstruction, Scanner sc, SpreadSheet sheet) {
    //SpreadSheetWithMacro sheetWithMacro = new SpreadSheetWithMacroImpl(sheet);

    if (userInstruction.equals("bulk-assign-value")) {

      String startRowStr = sc.next();
      int startRow = getRowNum(startRowStr);
      int startCol = sc.nextInt() - 1;
      String endRowStr = sc.next();
      int endRow = getRowNum(endRowStr);
      int endCol = sc.nextInt() - 1;
      double val = sc.nextDouble();

      BulkAssignMacro macro = new BulkAssignMacro(startRow, startCol, endRow, endCol, val);
      sheetWithMacro.executeMacro(macro);

    } else if (userInstruction.equals("average")) {

      String fromRowStr = sc.next();
      int fromRow = getRowNum(fromRowStr);
      int fromCol = sc.nextInt() - 1;
      int toRow = getRowNum(sc.next());
      int toCol = sc.nextInt() - 1;
      int destRow = getRowNum(sc.next());
      int destCol = sc.nextInt() - 1;

      AverageMacro macro = new AverageMacro(fromRow, fromCol, toRow, toCol, destRow, destCol);
      sheetWithMacro.executeMacro(macro);

    } else if (userInstruction.equals("range-assign")) {

      String startRowStr = sc.next();
      int startRow = getRowNum(startRowStr);
      int startCol = sc.nextInt() - 1;
      String endRowStr = sc.next();
      int endRow = getRowNum(endRowStr);
      int endCol = sc.nextInt() - 1;
      double startValue = sc.nextDouble();
      double increment = sc.nextDouble();
      RangeAssignMacro macro = new RangeAssignMacro(startRow, startCol, endRow, endCol, startValue,
              increment);
      sheetWithMacro.executeMacro(macro);

    } else {
      super.processCommand(userInstruction, sc, sheet);
    }
  }

  /**
   * Printing the menu to the user.
   * @throws IllegalStateException if the controller is unable to print
   */
  @Override
  protected void printMenu() throws IllegalStateException {
    writeMessage("Supported user instructions are: " + System.lineSeparator());
    writeMessage("assign-value row-num col-num value (set a cell to a value)"
            + System.lineSeparator());
    writeMessage("print-value row-num col-num (print the value at a given cell)"
            + System.lineSeparator());

    writeMessage("bulk-assign-value from-row-num from-col-num to-row-num to-col-num value (set a "
            + "range of cells to a value)\n");
    writeMessage("range-assign from-row-num from-col-num to-row-num to-col-num start-value "
            + "increment (set a row or column of cells to a range of values starting at the "
            + "given value and advancing by the increment)\n");
    writeMessage("average from-row-num from-col-num to-row-num to-col-num dest-row-num "
            + "dest-col-num (compute the average of a range of cells and put it at the "
            + "given location)\n");
    writeMessage("menu (Print supported instruction list)" + System.lineSeparator());
    writeMessage("q or quit (quit the program) " + System.lineSeparator());
    //super.printMenu();
  }
}

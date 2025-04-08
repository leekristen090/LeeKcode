package spreadsheet;

import java.io.InputStreamReader;

/**
 * This is the class which holds the main for the lab 8 spreadsheet program.
 */
public class SpreadSheetProgram {
  /**
   * the main program that runs the user interactive part.
   * @param args arguments
   */
  public static void main(String []args) {
    BetterSpreadSheet model = new BetterSparseSpreadSheet();
    Readable rd = new InputStreamReader(System.in);
    Appendable ap = System.out;
    SpreadSheetController controller = new SpreadSheetController(model,rd,ap);
    controller.goMethod();
  }
}

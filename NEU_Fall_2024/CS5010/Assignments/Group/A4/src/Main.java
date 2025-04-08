/**
 * This is the main class.
 */
public class Main {
  /**
   * Main class where we have interactive user input.
   * @param args args
   */
  public static void main(String[] args) {
    ImageController controller = new ImCont();

    //controller.goScript("Script.txt");
    controller.interactive();
  }
}
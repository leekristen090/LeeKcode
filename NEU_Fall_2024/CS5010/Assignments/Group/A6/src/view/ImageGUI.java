package view;

import javax.swing.SwingUtilities;

import controller.ImageController;

/**
 * The ImageGUI class contains the main entry point for the image processing application.
 * It initializes the necessary controllers and launches the graphical user interface (GUI).
 */
public class ImageGUI  {

  /**
   * The main method is the entry point for the application.
   * It sets up the necessary controllers and initializes the GUI by running the event dispatch
   * thread.
   * @param args Command line arguments (not used in this implementation).
   */
  public static void main(String[] args) {

    SwingUtilities.invokeLater(() -> {
      ImageController imageController = new ImageController();
      ImageGUIFrame gui = new ImageGUIFrame(null);
      FeatureController featureController = new FeatureController(imageController, gui);
      gui.setFeatureController(featureController);

      gui.setVisible(true);
    });


  }

}
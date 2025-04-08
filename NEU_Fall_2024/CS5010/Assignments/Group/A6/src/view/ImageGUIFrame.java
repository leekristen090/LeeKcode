package view;

import java.awt.GridLayout;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JSlider;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.image.BufferedImage;
import  java.awt.event.WindowEvent;
import  java.awt.event.WindowAdapter;
import java.io.IOException;

/**
 * ImageGUIFrame is the graphical user interface (GUI) frame that displays images and histograms,
 * and allows the user to apply various effects and manipulate images.
 * It extends JFrame and implements the IView interface to handle the user interactions and updates
 * to the view when the underlying image or histogram changes. The frame allows users to load,
 * save, apply effects to images, and control the display of the image using various options.
 */
public class ImageGUIFrame extends JFrame implements IView {
  private final JLabel imageLabel;
  private final JLabel histogramLabel;
  private final JButton loadButton;
  private final JButton saveButton;
  private final JButton clearButton;
  private JCheckBox splitToggle;
  private JSlider splitSlider;
  private FeatureController featCont;
  private final JScrollPane imageScrollPane;
  private int[] levels;

  /**
   * Constructs a new ImageGUIFrame with the given FeatureController to manage image effects.
   * @param controller the FeatureController instance used to manage image actions and effects
   */
  public ImageGUIFrame(FeatureController controller) {
    super("Image Loader and Manipulator");
    this.imageLabel = new JLabel("No Image Loaded", JLabel.CENTER);
    this.histogramLabel = new JLabel("Histogram", JLabel.CENTER);
    this.loadButton = new JButton("Load Image");
    this.saveButton = new JButton("Save Image");
    this.clearButton = new JButton("Clear Effects");
    this.imageScrollPane = new JScrollPane(imageLabel);
    this.featCont = controller;
    initUI();
  }

  /**
   * Sets the feature controller for the frame, allowing the GUI to interact with the controller.
   * @param controller the FeatureController instance to set
   */
  public void setFeatureController(FeatureController controller) {
    this.featCont = controller;
  }

  /**
   * Initializes the user interface by setting up the layout, buttons, sliders, and
   * action listeners.
   */
  private void initUI() {
    final JButton sepiaButton;
    final JButton redButton;
    final JButton greenButton;
    final JButton blueButton;
    final JButton blurButton;
    final JButton sharpenButton;
    final JButton flipHorButton;
    final JButton flipVertButton;
    final JButton lumaGreyButton;
    final JButton compressionButton;
    final JButton colorCorrectButton;
    final JButton adjustLevelsButton;

    setSize(1000, 600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout());

    addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        // Check if an image is loaded
        if (featCont.visibleAlias != null && !featCont.visibleAlias.isEmpty()) {
          // Show a confirmation dialog
          int option = JOptionPane.showConfirmDialog(
                  ImageGUIFrame.this,
                  "Do you want to save the current image before exiting?",
                  "Save Image",
                  JOptionPane.YES_NO_CANCEL_OPTION,
                  JOptionPane.QUESTION_MESSAGE
          );

          if (option == JOptionPane.YES_OPTION) {
            // Save the image
            featCont.saveImage();
          } else if (option == JOptionPane.CANCEL_OPTION) {
            // Cancel closing the window
            setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            return;
          }
        }
        // If no image is loaded or user chose not to save, exit the application
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      }
    });

    // Image and histogram display
    JPanel mainPanel = new JPanel(new GridLayout(1, 2));
    //mainPanel.add(imageLabel);
    mainPanel.add(imageScrollPane);
    mainPanel.add(histogramLabel);
    add(mainPanel, BorderLayout.CENTER);

    // Button panel
    JPanel buttonPanel = new JPanel();
    loadButton.addActionListener(e -> featCont.loadImage());
    saveButton.addActionListener(e -> featCont.saveImage());

    splitToggle = new JCheckBox("Split View");
    splitSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
    splitSlider.setMajorTickSpacing(25);
    splitSlider.setPaintTicks(true);
    splitSlider.setPaintLabels(true);
    splitSlider.setBorder(BorderFactory.createTitledBorder("Split Percentage"));
    splitSlider.setEnabled(false);

    splitToggle.addActionListener(e -> {
      boolean enabled = splitToggle.isSelected();
      splitSlider.setEnabled(enabled); // Enable the slider based on checkbox state
      featCont.splitViewEnabled = enabled; // Update split view state in FeatureController

      // Apply split view effect immediately with the current slider value
      try {
        if (enabled) {
          featCont.updateSplit(splitSlider.getValue());
        } else {
          featCont.updateImageAndHistogram(featCont.visibleAlias);
        }
      } catch (IOException ex) {
        JOptionPane.showMessageDialog(ImageGUIFrame.this,
                "Error applying split view effect: " + ex.getMessage());
      }
    });

    splitSlider.addChangeListener(e -> {
      if (splitToggle.isSelected()) {  // Only apply split view if it's enabled
        try {
          int splitPercentage = splitSlider.getValue();  // Get new split percentage
          // Apply split view effect with the updated percentage
          featCont.updateSplit(splitPercentage);
        } catch (IOException ex) {
          JOptionPane.showMessageDialog(ImageGUIFrame.this,
                  "Error applying split view effect: " + ex.getMessage());
        }
      }
    });

    clearButton.addActionListener(e -> {
      featCont.clearEffects();  // Calls the clearEffects method in FeatureController
    });

    buttonPanel.add(loadButton);
    buttonPanel.add(saveButton);
    buttonPanel.add(clearButton);
    buttonPanel.add(splitToggle);
    buttonPanel.add(splitSlider);
    add(buttonPanel, BorderLayout.SOUTH);

    JPanel effectPanel = new JPanel();
    effectPanel.setLayout(new GridLayout(0, 1, 10, 10));
    effectPanel.setBorder(BorderFactory.createTitledBorder("Effects"));

    adjustLevelsButton = new JButton("Adjust Levels Effect");
    adjustLevelsButton.addActionListener(e -> {
      try {
        levelsPopup();
        featCont.applyLevels("adjust-levels", levels[0], levels[1], levels[2]);
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });
    effectPanel.add(adjustLevelsButton);
    blueButton = new JButton("Blue Effect");
    blueButton.addActionListener(e -> {
      try {
        featCont.applyEffect("blue-component");
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });
    effectPanel.add(blueButton);
    blurButton = new JButton("Blur Effect");
    blurButton.addActionListener(e -> {
      try {
        featCont.applyEffect("blur");
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });
    effectPanel.add(blurButton);
    colorCorrectButton = new JButton("Color Correct Effect");
    colorCorrectButton.addActionListener(e -> {
      try {
        featCont.applyEffect("color-correct");
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });
    effectPanel.add(colorCorrectButton);
    compressionButton = new JButton("Compression Effect");
    compressionButton.addActionListener(e -> {
      try {
        int percent = compressionPopup();
        featCont.applyCompression("compression", percent);
        //featCont.applyEffect("compression");
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });
    effectPanel.add(compressionButton);
    flipHorButton = new JButton("Flip Horizontal Effect");
    flipHorButton.addActionListener(e -> {
      try {
        featCont.applyEffect("horizontal-flip");
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });
    effectPanel.add(flipHorButton);
    flipVertButton = new JButton("Flip Vertical Effect");
    flipVertButton.addActionListener(e -> {
      try {
        featCont.applyEffect("vertical-flip");
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });
    effectPanel.add(flipVertButton);
    greenButton = new JButton("Green Effect");
    greenButton.addActionListener(e -> {
      try {
        featCont.applyEffect("green-component");
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });
    effectPanel.add(greenButton);
    lumaGreyButton = new JButton("Greyscale-Luma Effect");
    lumaGreyButton.addActionListener(e -> {
      try {
        featCont.applyEffect("luma-component");
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });
    effectPanel.add(lumaGreyButton);
    redButton = new JButton("Red Effect");
    redButton.addActionListener(e -> {
      try {
        featCont.applyEffect("red-component");
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });
    effectPanel.add(redButton);
    sepiaButton = new JButton("Sepia Effect");
    sepiaButton.addActionListener(e -> {
      try {
        featCont.applyEffect("sepia");
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });
    effectPanel.add(sepiaButton);
    sharpenButton = new JButton("Sharpen Effect");
    sharpenButton.addActionListener(e -> {
      try {
        featCont.applyEffect("sharpen");
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });
    effectPanel.add(sharpenButton);

    JScrollPane scrollPane = new JScrollPane(effectPanel);
    add(scrollPane, BorderLayout.WEST);
  }

  /**
   * Update the image in the gui.
   * This method is used to refresh the image in the view, typically after a new image is loaded
   * or an effect is applied.
   * @param image given image
   */
  @Override
  public void updateImage(BufferedImage image) {
    if (image != null) {
      imageLabel.setIcon(new ImageIcon(image));
      imageLabel.setText("");
    } else {
      imageLabel.setText("Failed to load image.");
      imageLabel.setIcon(null);
    }
  }

  /**
   * Update the histogram in the gui.
   * This method is used to refresh the histogram view, which visually represents the distribution
   * of pixel intensities in the image.
   * @param histogram the histogram
   */
  @Override
  public void updateHistogram(BufferedImage histogram) {
    if (histogram != null) {
      histogramLabel.setIcon(new ImageIcon(histogram));
      histogramLabel.setText("");
    } else {
      histogramLabel.setText("Failed to generate histogram.");
      histogramLabel.setIcon(null);
    }
  }

  /**
   * This method is used to update both the image and the histogram at the same time, using the
   * alias to fetch the relevant image and histogram data for display.
   * @param alias image alias
   */
  @Override
  public void updateImageAndHistogram(String alias) {
    featCont.updateImageAndHistogram(alias);
  }


  private int compressionPopup() {
    JSlider percentSlider = new JSlider(0, 100, 50);
    percentSlider.setMajorTickSpacing(25);
    percentSlider.setPaintTicks(true);
    percentSlider.setPaintLabels(true);

    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());
    panel.add(new JLabel("Select Compression Percentage:"), BorderLayout.NORTH);
    panel.add(percentSlider, BorderLayout.CENTER);

    Object[] options = {"OK"};

    int option = JOptionPane.showOptionDialog(
            null,
            panel,
            "Compression Percentage",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.PLAIN_MESSAGE,
            null,
            options,
            options[0]
    );

    // Return the value of the slider when the OK button is pressed
    return percentSlider.getValue();
  }


  private void levelsPopup() {

    JSlider blackSlider = new JSlider(0, 255, 125);
    blackSlider.setMajorTickSpacing(50);
    blackSlider.setPaintTicks(true);
    blackSlider.setPaintLabels(true);

    JSlider midSlider = new JSlider(0, 255, 125);
    midSlider.setMajorTickSpacing(50);
    midSlider.setPaintTicks(true);
    midSlider.setPaintLabels(true);

    JSlider whiteSlider = new JSlider(0, 255, 125);
    whiteSlider.setMajorTickSpacing(50);
    whiteSlider.setPaintTicks(true);
    whiteSlider.setPaintLabels(true);

    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(6, 1));

    // Add labels and sliders to the panel
    panel.add(new JLabel("Select Black Value:"));
    panel.add(blackSlider);
    panel.add(new JLabel("Select Mid Value:"));
    panel.add(midSlider);
    panel.add(new JLabel("Select White Value:"));
    panel.add(whiteSlider);

    Object[] options = {"OK"};

    JOptionPane.showOptionDialog(
            null,
            panel,
            "Adjust Levels",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.PLAIN_MESSAGE,
            null,
            options,
            options[0]
    );

    if (blackSlider.getValue() >= midSlider.getValue()) {
      JOptionPane.showMessageDialog(ImageGUIFrame.this,
              "Error: The black level must be less than the mid level. Please try again");

    }
    else if (blackSlider.getValue() >= whiteSlider.getValue()) {
      JOptionPane.showMessageDialog(ImageGUIFrame.this,
              "Error: The black level must be less than "
                      + "the white level. Please try again");
    }
    else if (midSlider.getValue() >= whiteSlider.getValue()) {
      JOptionPane.showMessageDialog(ImageGUIFrame.this,
              "Error: The mid level must be less than the white level. Please try again");
    }
    else {
      levels = new int[3];

      levels[0] = blackSlider.getValue();
      levels[1] = midSlider.getValue();
      levels[2] = whiteSlider.getValue();
    }
  }

  public JButton getLoadButton() {
    return loadButton;
  }
}
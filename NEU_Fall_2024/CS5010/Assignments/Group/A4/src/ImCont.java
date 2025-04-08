import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import javax.imageio.ImageIO;

/**
 * This is the image controller class which implements the ImageController interface and its
 * methods.
 */
public class ImCont implements ImageController {

  private Image[] images; // An array to store image models
  private String[] aliases; // An array to store the corresponding aliases
  private int imageCount;
  Image myImage;
  private HashMap<String, Command> commandMap;

  /**
   * Constructing image controller.
   */
  public ImCont() {
    this.images = new Image[30];
    this.aliases = new String[30];
    imageCount = 0;
    commandMap = new HashMap<>();
    commandMap.put("sepia", new SepiaCommand(this));
    commandMap.put("blur", new BlurCommand(this));
    commandMap.put("sharpen", new SharpenCommand(this));
    commandMap.put("brighten", new AdjustCommand(this));
    commandMap.put("horizontal-flip", new FlipCommand(this));
    commandMap.put("vertical-flip", new FlipCommand(this));
    commandMap.put("rgb-split", new SplitCommand(this));
    commandMap.put("rgb-combine", new CombineCommand(this));
    commandMap.put("red-component", new ColorCommand(this));
    commandMap.put("green-component", new ColorCommand(this));
    commandMap.put("blue-component", new ColorCommand(this));
    commandMap.put("value-component", new GreyCommand(this));
    commandMap.put("luma-component", new GreyCommand(this));
    commandMap.put("intensity-component", new GreyCommand(this));
  }

  /**
   * Load a given image file from path with a given alias.
   * @param path image path
   * @param alias image alias to reference
   */
  @Override
  public void load(String path, String alias) {
    String fileExt = getFileExt(path).toLowerCase();

    switch (fileExt) {
      case "ppm":
        loadPPM(path, alias);
        break;
      case "png":
      case "jpeg":
      case "jpg":
        loadOther(path, alias);
        break;
      default:
        throw new IllegalArgumentException("Unsupported file format: " + fileExt);
    }
  }

  /**
   * Load a file with extension ppm.
   * @param path image path
   * @param alias image alias
   */
  private void loadPPM(String path, String alias) {
    //int imWidth;
    //int imHeight;
    Scanner sc;
    try {
      sc = new Scanner(new FileInputStream(path));
    } catch (FileNotFoundException e) {
      System.out.println("File " + path + " not found!");
      return;
    }

    StringBuilder builder = new StringBuilder();
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    sc = new Scanner(builder.toString());

    String token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3. Yours is: "
              + token);
      return;
    }

    int imWidth = sc.nextInt();
    int imHeight = sc.nextInt();
    int maxValue = sc.nextInt();

    myImage = new ConcreteImage(alias, imHeight, imWidth);
    // Read pixel data
    for (int i = 0; i < imHeight; i++) {
      for (int j = 0; j < imWidth; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        //System.out.println("Color of pixel (" + j + "," + i + "): " + r + "," + g + "," + b);
        // Set the pixel in the images object

        myImage.setPixel(i, j, r, g, b); // Update this line

      }
    }
    addImage(myImage, alias);
    System.out.println("Image loaded successfully with dimensions: " + imWidth + "x" + imHeight);
  }

  /**
   * Load a file with extensions jpg, jpeg, or png.
   * @param path image path
   * @param alias image alias
   */
  private void loadOther(String path, String alias) {

    try {

      BufferedImage im = ImageIO.read(new File(path));

      if (im == null) {
        throw new IllegalArgumentException("Could not load image: " + path);
      }

      int imHeight = im.getHeight();
      int imWidth = im.getWidth();
      Image myIm = new ConcreteImage(alias, imHeight, imWidth);

      for (int row = 0; row < imHeight; row++) {
        for (int col = 0; col < imWidth; col++) {
          int rgb = im.getRGB(col, row);
          int r = (rgb >> 16) & 0x000000ff;
          int g = (rgb >> 8) & 0x000000ff;
          int b = rgb & 0x000000ff;
          myIm.setPixel(row, col, r, g, b);
        }
      }

      addImage(myIm, alias);  // Add image to your controller
      System.out.println("Image loaded successfully with dimensions: " + imWidth + "x" + imHeight);

    } catch (IOException e) {
      System.out.println("Error loading image: " + e.getMessage());
    }

  }

  /**
   * Save an image with a specified alias to a given destination path.
   * @param alias image alias to be saved
   * @param dest destination path
   */
  @Override
  public void save(String dest, String alias) {
    String fileExt = getFileExt(dest).toLowerCase();
    switch (fileExt) {
      case "ppm":
        savePPM(dest, alias);
        break;
      case "jpg":
      case "jpeg":
      case "png":
        saveOther(dest, alias, fileExt);
        break;
      default:
        throw new IllegalArgumentException("Unsupported file format: " + fileExt);
    }

  }

  /**
   * Save file with extension ppm.
   * @param dest destination to save
   * @param alias alias
   */
  private void savePPM(String dest, String alias) {
    Image image = findImageByAlias(alias); // Find the image by alias
    if (image == null) {
      throw new IllegalArgumentException("Image with alias '" + alias + "' not found.");
    }
    try (FileOutputStream fos = new FileOutputStream(dest)) {
      StringBuilder sb = new StringBuilder();

      // Write PPM header
      sb.append("P3\n");
      sb.append(image.getWidth()).append(" ").append(image.getHeight()).append("\n");
      sb.append("255\n");  // Max color value

      // Write pixel data
      for (int row = 0; row < image.getHeight(); row++) {
        for (int col = 0; col < image.getWidth(); col++) {
          int r = image.getPixel(row, col, 0);  // Get Red component
          int g = image.getPixel(row, col, 1);  // Get Green component
          int b = image.getPixel(row, col, 2);  // Get Blue component

          sb.append(r).append(" ").append(g).append(" ").append(b).append(" ");
        }
        sb.append("\n");
      }

      // Write to file
      fos.write(sb.toString().getBytes());
      System.out.println("Image saved successfully to " + dest + "!");

    } catch (IOException e) {
      System.err.println("Error saving image: " + e.getMessage());
    }
  }

  /**
   * Save file with extensions jpg, jpeg, or png.
   * @param path path to save
   * @param alias alias
   * @param format file ext
   */
  private void saveOther(String path, String alias, String format) {
    Image image = findImageByAlias(alias);
    if (image == null) {
      throw new IllegalArgumentException("Image with alias '" + alias + "' not found.");
    }
    try {
      BufferedImage im = new BufferedImage(image.getWidth(), image.getHeight(),
              BufferedImage.TYPE_INT_RGB);
      for (int row = 0; row < image.getHeight(); row++) {
        for (int col = 0; col < image.getWidth(); col++) {
          int r = image.getPixel(row, col, 0);
          int g = image.getPixel(row, col, 1);
          int b = image.getPixel(row, col, 2);
          int rgb = (r << 16) | (g << 8) | b;
          im.setRGB(col, row, rgb);
        }
      }
      ImageIO.write(im, format, new File(path));
    } catch (IOException e) {
      System.err.println("Error saving image: " + e.getMessage());
    }
  }

  /**
   * Helper function to fine file extension.
   * @param path image path
   * @return dot
   */
  private String getFileExt(String path) {
    int dot = path.lastIndexOf('.');
    return (dot == -1) ? "" : path.substring(dot + 1);
  }

  /**
   * Helper function to find aliases stored in an array.
   * @param alias alias to find
   * @return the corresponding image
   */
  public Image findImageByAlias(String alias) {
    for (int i = 0; i < imageCount; i++) {
      if (aliases[i].equals(alias)) {
        return images[i]; // Return the image directly
      }
    }
    return null; // Not found
  }

  /**
   * Helper function to create a copy of an image so we don't overwrite an image when doing multiple
   * image manipulations.
   * @param original original image to be manipulated
   * @param newAlias the alias after manipulation
   * @return copy of original image
   */
  public Image createImageCopy(Image original, String newAlias) {
    int height = original.getHeight();
    int width = original.getWidth();
    Image copy = new ConcreteImage(newAlias, height, width); // Adjust constructor if necessary

    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        int r = original.getPixel(row, col, 0);
        int g = original.getPixel(row, col, 1);
        int b = original.getPixel(row, col, 2);
        copy.setPixel(row, col, r, g, b);
      }
    }

    return copy;
  }

  /**
   * Helper function to add image aliases to an array to be accessed later for image manipulation.
   * @param image the image
   * @param alias the image's alias
   */
  public void addImage(Image image, String alias) {
    if (imageCount >= images.length) {
      throw new IllegalStateException("Image array is full.");
    }
    images[imageCount] = image;
    aliases[imageCount] = alias;
    imageCount++;
  }

  /**
   * This runs a separate script file with command lines and comments.
   * @param scriptPath path of the script file
   */
  @Override
  public void goScript(String scriptPath) {

    try (Scanner scanner = new Scanner(new File(scriptPath))) {
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine().trim();

        // comments or nothing there
        if (line.startsWith("#") || line.isEmpty()) {
          continue;
        }

        String[] tokens = line.split(" ");
        String commandName = tokens[0];

        if (commandName.equals("load")) {
          load(tokens[1], tokens[2]);
        }
        else if (commandName.equals("save")) {
          save(tokens[1], tokens[2]);
        } else {
          Command command = commandMap.get(commandName);
          if (command != null) {

            command.execute(tokens); // Execute command on the image

          } else {
            System.out.println("Unknown command: " + commandName);
          }
        }

      }

    } catch (FileNotFoundException e) {
      System.out.println("Script file not found: " + scriptPath);
    }
  }

  /**
   * This runs commands based on user keyboard input.
   */
  public void interactive() {
    System.out.println("** Welcome to the image manipulation program! **");

    Scanner cin = new Scanner(System.in);

    while (true) {
      System.out.println("\nEnter 'run-script <script_path>' to run a script or enter commands "
              + "interactively.");
      System.out.println("Please enter your command (type 'exit' to quit): ");
      String commandLine = cin.nextLine().trim();
      if (commandLine.equalsIgnoreCase("exit")) {
        System.out.println("Exiting program. Bye!");
        break;
      }
      if (commandLine.startsWith("run-script")) {
        String scriptPath = commandLine.substring(10).trim();
        goScript(scriptPath);
      } else {
        System.out.println("Running '" + commandLine + "'");
        executeCommand(commandLine);
      }

    }
  }


  /**
   * Executes a single command entered interactively.
   */
  public void executeCommand(String input) {
    String[] tokens = input.split(" ");
    String commandName = tokens[0];

    if (commandName.equals("load")) {
      load(tokens[1], tokens[2]);
    } else if (commandName.equals("save")) {
      save(tokens[1], tokens[2]);
    } else {
      Command command = commandMap.get(commandName);
      if (command != null) {
        command.execute(tokens);
      } else {
        System.out.println("Unknown command: " + commandName);
      }
    }
  }

}

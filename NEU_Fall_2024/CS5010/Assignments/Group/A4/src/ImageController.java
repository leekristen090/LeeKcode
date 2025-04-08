/**
 * This is the ImageController interface. We have load, save, and goScript methods.
 */
public interface ImageController {

  /**
   * Load a given image file from path with a given alias.
   * @param path image path
   * @param alias image alias to reference
   */
  void load(String path, String alias);

  /**
   * Save an image with a specified alias to a given destination path.
   * @param alias image alias to be saved
   * @param dest destination path
   */
  void save(String alias,String dest);

  /**
   * Add an image to the alias array so we can access it later.
   * @param image image to add to array
   * @param alias alias
   */
  void addImage(Image image, String alias);

  /**
   * Fina an image by its alias in the array of aliases.
   * @param alias given alias
   * @return the image
   */
  Image findImageByAlias(String alias);

  /**
   * This runs a script file that contains image commands and comments.
   * @param scriptPath path of the script file
   */
  void goScript(String scriptPath);

  /**
   * This runs commands based on user keyboard input.
   */
  void interactive();

  void executeCommand(String input);
}
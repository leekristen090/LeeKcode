package controller;

import java.io.IOException;

/**
 * This is the controller interface which holds load and save methods.
 */
public interface Controller {

  /**
   * Load an image.
   * @param filepath image file path
   * @param alias alias for image
   * @throws IOException exception
   */
  void loadImage(String filepath, String alias) throws IOException;

  /**
   * save the given image.
   * @param filepath given image file path
   * @param alias alias to save
   * @throws IOException exception
   */
  void saveImage(String filepath, String alias) throws IOException;

}
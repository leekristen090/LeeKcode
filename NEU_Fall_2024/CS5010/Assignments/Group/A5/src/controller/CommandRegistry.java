package controller;

import java.util.HashMap;
import java.util.Map;

/**
 * This is the class where we register the commands that can be used into a hash map.
 */
public class CommandRegistry {
  protected static final Map<String, Command> commandMap = new HashMap<>();

  /**
   * We are setting the commands that our program runs and when seen in the script or command line
   * input will call the corresponding command class.
   */
  public CommandRegistry() {
    commandMap.put("sepia", new SepiaCommand());
    commandMap.put("blur", new BlurCommand());
    commandMap.put("sharpen", new SharpenCommand());
    commandMap.put("grey-scale", new GreyCommand());
    commandMap.put("luma-component", new GreyCommand());
    commandMap.put("intensity-component", new GreyCommand());
    commandMap.put("value-component", new GreyCommand());
    commandMap.put("red-component", new ColorCommand());
    commandMap.put("green-component", new ColorCommand());
    commandMap.put("blue-component", new ColorCommand());
    commandMap.put("horizontal-flip", new FlipCommand());
    commandMap.put("vertical-flip", new FlipCommand());
    commandMap.put("brighten", new BrightnessCommand());
    commandMap.put("rgb-combine" , new CombineCommand());
    commandMap.put("rgb-split"  , new SplitCommand());
    commandMap.put("histogram" , new HistogramCommand());
    commandMap.put("levels-adjust", new LevelsCommand());
    commandMap.put("color-correct", new ColorCorrectCommand());
    commandMap.put("compress", new CompressCommand());

  }

}
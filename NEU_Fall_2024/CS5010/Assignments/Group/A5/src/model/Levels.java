package model;

/**
 * This class holds the logic for implementing levels adjust. We use the mathematical formula from
 * canvas to compute coefficients a, b, and c and plugging into the quadratic curve formula.
 */
public class Levels {
  private final int b; //black
  private final int m; //mid
  private final int w; //white

  /**
   * Construct levels with black, mid, and white values.
   * @param b black
   * @param m mid
   * @param w white
   */
  public Levels(int b, int m, int w) {
    this.b = b;
    this.m = m;
    this.w = w;
  }

  /**
   * Execute levels adjustment logic using math from canvas description.
   * @param image given image
   * @param splitPercentage optional split preview percentage
   * @return adjusted levels image
   */
  public Image execute(Image image, Integer splitPercentage) {
    int splitPoint = splitPercentage != null ? (image.getWidth() * splitPercentage / 100) :
            image.getWidth();
    int height = image.getHeight();
    int width = image.getWidth();
    // coefficients for the quadratic curve
    double[] coefficients = calculateQuadraticCoefficients();

    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        if (col < splitPoint) {
          int red = clamp(applyLevels(image.getPixel(row, col, 0), coefficients));
          int green = clamp(applyLevels(image.getPixel(row, col, 1), coefficients));
          int blue = clamp(applyLevels(image.getPixel(row, col, 2), coefficients));

          image.setPixel(row, col, red, green, blue);
        }
      }
    }

    return image;
  }

  /**
   * Calc coeff for quadratic formula.
   * @return array of coeff
   */
  private double[] calculateQuadraticCoefficients() {
    // calc A, Aa, Ab, Ac using formulas from canvas
    double a = (Math.pow(b, 2) * (m - w)) - (b * (Math.pow(m, 2) - Math.pow(w, 2)))
            + (w * Math.pow(m, 2)) - (m * Math.pow(w, 2));
    double aA = -b * (128 - 225) + (128 * w) - (255 * m);
    double bA = (Math.pow(b, 2)) * (128 - 255) + (255 * Math.pow(m, 2)) - (128 * Math.pow(w, 2));
    double cA = (Math.pow(b, 2)) * ((225 * m) - (128 * w)) - (b * (255 * Math.pow(m, 2))
            - (128 * Math.pow(w, 2)));

    double aCoeff = aA / a;
    double bCoeff = bA / a;
    double cCoeff = cA / a;

    return new double[] {aCoeff, bCoeff, cCoeff};
  }

  /**
   * Apply quadratic formula y = ax^2 + bx + c.
   * @param x input from image channel
   * @param coefficients a,b,c
   * @return adjusted value
   */
  private int applyLevels(int x, double[] coefficients) {
    double aCoeff = coefficients[0];
    double bCoeff = coefficients[1];
    double cCoeff = coefficients[2];

    return (int) (aCoeff * Math.pow(x, 2) + bCoeff * x + cCoeff);
  }

  /**
   * Clamps the RGB value to the valid range [0, 255].
   *
   * @param value The value to clamp.
   * @return The clamped value.
   */
  private int clamp(int value) {
    return Math.max(0, Math.min(255, value));
  }

}
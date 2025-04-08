

/**
 * This is the Money interface which represents US money.
 */

public interface Money {
  /**
   * From description on Canvas:
   * This is a method to add two money amounts together.
   * @param other the money amount to add
   * @return the sum of money (non-negative)
   */
  Money add(Money other);

  /**
   * From assignment description on Canvas:
   * This is a helper function for Money add(Money other). This is a method to add a money amount
   * with another given as a separate dollar and cent value.
   * @param dollar the given dollar amount to be added
   * @param cents the given cent amount to be added.
   * @return the money amount
   * @throws IllegalArgumentException if given an invalid money amount
   */
  Money add(int dollar, int cents);

  /**
   * From description on Canvas:
   * This method returns the decimal value of money in the format "xx.yy" and the cent value
   * should be padded with leading zeros if necessary.
   * @return the decimal value of money with leading zeros (non-negative)
   */
  double getDecimalValue();

  /**
   * This method gets the dollar amount.
   * @return dollar
   */
  int getDollar();

  /**
   * This method gets the cents amount.
   * @return cents
   */
  int getCents();
}

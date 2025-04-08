

/**
 * This is the SimpleMoney class which implements the Money interface.
 */

public class SimpleMoney implements Money {
  private final int dollar;
  private final int cents;

  /**
   * This class can only represent valid money (i.e., money that can be dispensed). This class
   * should also have a single public constructor that takes the dollar and cent amount of the
   * money as integers as its only arguments. Any attempt to create a negative amount of money, or
   * using a negative amount of dollars or cents should throw an IllegalArgumentException.
   * @param dollar the dollar amount
   * @param cents the cent amount
   * @throws IllegalArgumentException if any negative amount of money is given
   */
  public SimpleMoney(int dollar, int cents)
          throws IllegalArgumentException {
    if ((dollar < 0) || (cents < 0)) {
      throw new IllegalArgumentException("No negative dollar or cent amounts allowed!");
    }
    if (cents >= 100) {
      dollar += cents / 100;
      cents = cents % 100;
    }
    this.dollar = dollar;
    this.cents = cents;
  }

  /**
   * From assignment description on Canvas:
   * This is a method to add two money amounts together.
   * @param other the money amount to add
   * @return the sum of money (non-negative)
   */
  @Override
  public Money add(Money other)
          throws IllegalArgumentException {
    if ((dollar < 0) || (cents < 0)) {
      throw new IllegalArgumentException("No negative dollar or cent amounts allows!");
    }
    int totCent = this.cents + other.getCents();
    int totDol = this.dollar + other.getDollar() + totCent / 100;
    totCent %= 100;
    return new SimpleMoney(totDol, totCent);
  }

  /**
   * From assignment description on Canvas:
   * This is a helper function for Money add(Money other). This is a method to add a money amount
   * with another given as a separate dollar and cent value.
   * @param dollar the given dollar amount to be added
   * @param cents the given cent amount to be added.
   * @return the money amount
   * @throws IllegalArgumentException if given an invalid money amount
   */
  public Money add(int dollar, int cents)
          throws IllegalArgumentException {
    if ((dollar < 0) || (cents < 0)) {
      throw new IllegalArgumentException("No negative dollar or cent amounts allows!");
    }
    int totalCent = this.cents + cents;
    int totalDollar = this.dollar + dollar + totalCent / 100;
    totalCent %= 100;
    return new SimpleMoney(totalDollar, totalCent);
    //return null;
  }

  /**
   * From assignment description on Canvas:
   * This method returns the decimal value of money in the format "xx.yy" and the cent value
   * should be padded with leading zeros if necessary.
   * @return the decimal value of money with leading zeros (non-negative)
   */
  @Override
  public double getDecimalValue() {
    return dollar + cents / 100.0;
    //return 0;
  }

  /**
   * From assignment description on Canvas:
   * This method returns a string of the form "$xx.yy". There should be exactly two digits after
   * the decimal point, padded with leading zeroes if necessary.
   * @return money amount in "$xx.yy" format
   */
  @Override
  public String toString() {
    return String.format("$%d.%02d",dollar, cents);
  }

  /**
   * This method gets the dollar amount.
   * @return dollar
   */
  public int getDollar() {
    return dollar;
  }

  /**
   * This method gets the cents amount.
   * @return cents
   */
  public int getCents() {
    return cents;
  }
}
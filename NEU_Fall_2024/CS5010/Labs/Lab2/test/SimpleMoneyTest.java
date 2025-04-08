import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Random;

/**
 * This is the JUnit test for the Money interface in lab 2.
 */
public class SimpleMoneyTest {

  /**
   * We are testing a money object that has dollars and cents.
   */
  private Money m1_17_13;
  private Money m2_17_03;
  private Money m4_00_30;
  private Money m;

  /**
   * Creating money that has a given dollar and cent amount.
   */
  @Before
  public void setUp() {
    m1_17_13 = new SimpleMoney(17,13);
    m2_17_03 = new SimpleMoney(17,3);
    m4_00_30 = new SimpleMoney(0,30);
  }

  /**
   * This is testing the construction of valid money amounts.
   */
  @Test
  public void testConstructorValidMoney() {
    m = new SimpleMoney(5,8);
    assertEquals(5.08, m.getDecimalValue(), .01);
    assertEquals(17.13, m1_17_13.getDecimalValue(),.01);
    assertEquals(17.03, m2_17_03.getDecimalValue(), .01);
    assertEquals(0.30, m4_00_30.getDecimalValue(), .01);
  }

  /**
   * THis is testing the constructor with Illegal argument exception with a valid money amount.
   */
  @Test
  public void testValidPosMoney() {
    try {
      m = new SimpleMoney(5,99);
    } catch (IllegalArgumentException e) {
      fail("The above should have thrown an exception.");
    }
  }

  /**
   * This is testing a method with IllegalArgumentException. Here we are using a negative dollar
   * amount.
   */
  @Test
  public void testNegDollarConstructor() {
    try {
      m = new SimpleMoney(-4, 10);
      fail("The above should have thrown an exception.");
    } catch (IllegalArgumentException e) {
      // do nothing
    }
  }

  /**
   * This is testing a method with IllegalArgumentException. Here we are using a negative cents
   * amount.
   */
  @Test
  public void testNegCentConstructor() {
    try {
      m = new SimpleMoney(4, -10);
      fail("The above should have thrown an exception.");
    } catch (IllegalArgumentException e) {
      // do nothing
    }
  }

  /**
   * This is testing a method with IllegalArgumentException. Here we are using a cents amount of
   * 100.
   */
  @Test
  public void testInvalidCentConstructor() {
    try {
      m = new SimpleMoney(4, -10);
      fail("This should have thrown an exception");
    } catch (IllegalArgumentException e) {
      // do nothing
    }
  }

  /**
   * This is testing the addition of one money amount to another.
   */
  @Test
  public void testAddValid() {
    Money m1 = new SimpleMoney(17,13);
    Money m2 = new SimpleMoney(17,90);
    Money addition = m1.add(m2);
    assertEquals("$35.03", addition.toString());
    assertEquals(35.03,addition.getDecimalValue(), .01);
  }

  /**
   * This is testing the addition of cents that is greater than 100.
   */
  @Test
  public void testAddInvalidCents() {
    Money m1 = new SimpleMoney(17,130);
    Money m2 = new SimpleMoney(17,90);
    Money addition = m1.add(m2);
    assertEquals(36.20, addition.getDecimalValue(), .01);
  }

  /**
   * This is testing the getDecimalValue() method.
   */
  @Test
  public void testGetDecimalValue() {
    assertEquals(17.13, m1_17_13.getDecimalValue(), .01);
  }

  /**
   * This is testing the getDollar() method.
   */
  @Test
  public void testGetDollars() {
    assertEquals(17, m1_17_13.getDollar());
  }

  /**
   * This is testing the getCents() method.
   */
  @Test
  public void testGetCents() {
    assertEquals(13, m1_17_13.getCents());
  }

  /**
   * This is testing the add function with fuzzy testing/random sample testing.
   */
  @Test
  public void testAddFuzzy() {
    Random rand = new Random(50);
    for (int i = 0; i < 1000; i++) {
      int dollarValue1 = rand.nextInt(100);
      int dollarValue2 = rand.nextInt(100);
      int centValue1 = rand.nextInt(99);
      int centValue2 = rand.nextInt(99);

      Money moneyAmount1 = new SimpleMoney(dollarValue1, centValue1);
      Money moneyAmount2 = new SimpleMoney(dollarValue2, centValue2);
      Money addition = moneyAmount1.add(moneyAmount2);

      double expected = (dollarValue1 + dollarValue2) + (centValue1 + centValue2) / 100.0;
      assertEquals(expected, addition.getDecimalValue(), .01);
    }
  }
}
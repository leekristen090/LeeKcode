import polynomial.Polynomial;
import polynomial.SimplePolynomial;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * This is a JUnit test for the methods in SimplePolynomial.
 */
public class SimplePolyTest {

  private SimplePolynomial poly1;
  private SimplePolynomial poly2;
  private SimplePolynomial poly3;
  private SimplePolynomial poly4;

  @Before
  public void setUp() {
    poly1 = new SimplePolynomial();
    poly2 = new SimplePolynomial();
    poly3 = new SimplePolynomial();
    poly4 = new SimplePolynomial();

    // 5x^3-3x^2
    poly1.addTerm(5,3);
    poly1.addTerm(-3,2);

    // 2x^2+3x^1
    poly2.addTerm(2,2);
    poly2.addTerm(3,1);

    // 3x^1
    poly3.addTerm(3,1);

    // 2x^2+3x^1
    poly4.addTerm(2,2);
    poly4.addTerm(3,1);

  }

  /**
   * This is testing the constructor.
   */
  @Test
  public void testConstructor() {
    SimplePolynomial newPoly = new SimplePolynomial();
    assertEquals("0", newPoly.toString());
    assertEquals(0, newPoly.getDegree());
    assertEquals(0, newPoly.getCoefficient(0));
    assertEquals(0.0, newPoly.evaluate(2),.001 );
    //newPoly.derivative();
    assertEquals("0", newPoly.derivative().toString());
    newPoly.addTerm(0,0);
    assertEquals("", newPoly.toString());
  }

  @Test
  public void testMultByZero() {
    Polynomial simple1 = new SimplePolynomial();
    Polynomial simple2 = new SimplePolynomial();
    simple1.addTerm(3,2);
    Polynomial mult = simple1.multiply(simple2);
    assertEquals("0", mult.toString());
    assertEquals("0", simple2.derivative().toString());
    assertEquals(0, simple1.evaluate(0),.01);
    assertEquals(12.0, simple1.evaluate(-2.0),.01);
  }

  /**
   * This is testing the toString() method.
   */
  @Test
  public void testToString() {
    assertEquals("5x^3 -3x^2", poly1.toString());
    assertEquals("2x^2 +3x^1", poly2.toString());
    assertEquals("3x^1", poly3.toString());
  }

  /**
   * This is testing the addTerm() method.
   */
  @Test
  public void testAddTerm() {
    assertEquals("5x^3 -3x^2", poly1.toString());
    poly1.addTerm(6,0);
    assertEquals("5x^3 -3x^2 +6", poly1.toString());
  }

  /**
   * This is testing the equals() method.
   */
  @Test
  public void testEquals() {
    assertNotEquals(poly1, poly2);
    assertTrue(poly2.equals(poly4));
  }

  /**
   * This is testing the hashCode() method.
   */
  @Test
  public void testHashCode() {
    assertEquals(poly2.hashCode(), poly4.hashCode());
  }

  /**
   * This is testing the derivative() method.
   */
  @Test
  public void testDerivative() {
    Polynomial deriv = poly1.derivative();
    assertEquals("15x^2 -6x^1", deriv.toString());
  }

  /**
   * This is testing the evaluate() method.
   */
  @Test
  public void testEvaluate() {
    assertEquals(28.0, poly1.evaluate(2.0),.001);
  }

  /**
   * This is testing the add() method.
   */
  @Test
  public void testAddPoly() {
    Polynomial addition = poly1.add(poly2);
    assertEquals("5x^3 -1x^2 +3x^1", addition.toString());
  }

  /**
   * This is testing the multiply() method.
   */
  @Test
  public void testMultiplyPoly() {
    Polynomial mult = poly1.multiply(poly3);
    assertEquals("15x^4 -9x^3", mult.toString());
  }

  /**
   * This is testing addTerm() with a negative power.
   */
  @Test
  public void testNegPower() {
    try {
      //Polynomial polyNeg = new SimplePolynomial();
      poly1.addTerm(5, -2);
      fail("This should have thrown an exception");
    } catch (IllegalArgumentException e) {
      // do nothing
    }
    try { // normal test
      //Polynomial poly = new SimplePolynomial();
      poly3.addTerm(5,2);
    } catch (IllegalArgumentException e) {
      fail("This should have thrown an exception!");
    }
  }

  /**
   * This is testing the getCoefficient() method.
   */
  @Test
  public void testGetCoefficient() {
    assertEquals(5,poly1.getCoefficient(3));
    assertEquals(2,poly2.getCoefficient(2));
    assertEquals(3,poly3.getCoefficient(1));
  }

  /**
   * This is testing the getDegree() method.
   */
  @Test
  public void testGetDegree() {
    assertEquals(3, poly1.getDegree());
  }

}
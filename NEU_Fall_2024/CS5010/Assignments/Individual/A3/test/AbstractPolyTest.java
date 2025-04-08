import org.junit.Test;

import polynomial.Polynomial;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * This is an abstract test class for Assignment 3. Completing tests that can be done for both
 * SimplePolynomial and SparsePolynomial.
 */
public abstract class AbstractPolyTest {
  protected abstract Polynomial create();

  /**
   * Testing the constructor.
   */
  @Test
  public void testConstructor() {
    Polynomial poly = create();
    assertEquals("0", poly.toString());
    assertEquals(0, poly.getDegree());
    assertEquals(0,poly.getCoefficient(0));
    assertEquals("0", poly.derivative().toString());
    assertEquals(0.0, poly.evaluate(2.0),.001);

    poly.addTerm(0,0);
    assertEquals("0", poly.toString());
  }

  /**
   * Testing negative power.
   */
  @Test
  public void testInvalidConstructor() {
    try {
      Polynomial poly = create();
      poly.addTerm(3,-20);
      fail("This should have thrown an exception");
    } catch (IllegalArgumentException e) {
      // do nothing
    }
  }

  /**
   * This is testing the addTerm() method.
   */
  @Test
  public void testAddTerm() {
    Polynomial poly = create();
    poly.addTerm(3,2);
    assertEquals(3,poly.getCoefficient(2));
    assertEquals("3x^2", poly.toString());
  }

  /**
   * This is testing the addTerm() method with multiple terms.
   */
  @Test
  public void testAddMultipleTerms() {
    Polynomial poly = create();
    poly.addTerm(2,3);
    poly.addTerm(-3,2);
    poly.addTerm(6,0);
    assertEquals("2x^3 -3x^2 +6", poly.toString());
  }

  /**
   * This is testing the addTerm() method with multiple terms with same power.
   */
  @Test
  public void testAddTermSame() {
    Polynomial poly = create();
    poly.addTerm(2,3);
    poly.addTerm(3,3);
    poly.addTerm(-6,0);
    poly.addTerm(5,0);
    assertEquals("5x^3 -1", poly.toString());
  }

  /**
   * Testing the toString() method.
   */
  @Test
  public void testToString() {
    Polynomial poly = create();
    poly.addTerm(22,31);
    poly.addTerm(-3,3);
    poly.addTerm(-19,0);
    assertEquals("22x^31 -3x^3 -19", poly.toString());
  }

  /**
   * Testing getCoefficient() and getDegree() methods.
   */
  @Test
  public void testGetCoeffAndGetDegree() {
    Polynomial poly = create();
    poly.addTerm(12,3);
    poly.addTerm(-9,2);
    poly.addTerm(-6,0);
    assertEquals(12, poly.getCoefficient(3));
    assertEquals(-9, poly.getCoefficient(2));
    assertEquals(-6, poly.getCoefficient(0));
    assertEquals(3,poly.getDegree());
  }

  /**
   * This is testing the derivative() method with a poly with more than one term, poly 0, and a
   * constant.
   */
  @Test
  public void testDerivative() {
    Polynomial poly = create();
    poly.addTerm(5,3);
    poly.addTerm(-3,2);
    Polynomial deriv1 = poly.derivative();
    assertEquals("15x^2 -6x^1", deriv1.toString());

    Polynomial zeroPoly = create();
    Polynomial deriv2 = zeroPoly.derivative();
    assertEquals("0", deriv2.toString());

    Polynomial constant = create();
    constant.addTerm(5,0);
    Polynomial deriv3 = constant.derivative();
    assertEquals("0", deriv3.toString());
  }

  /**
   * Testing evaluate method with x = 2.0.
   */
  @Test
  public void testEval() {
    Polynomial poly = create();
    poly.addTerm(-3,2);
    poly.addTerm(2,4);
    poly.addTerm(2,2);
    assertEquals("2x^4 -1x^2", poly.toString());
    assertEquals(28,poly.evaluate(2.0),.01);
  }

  /**
   * Test evaluate with 0.
   */
  @Test
  public void testEvaluateWithZero() {
    Polynomial poly = create();
    poly.addTerm(3,2);
    poly.addTerm(2,4);
    poly.addTerm(-5,3);
    poly.addTerm(7,5);
    poly.addTerm(8,1);
    poly.addTerm(13,0);
    assertEquals(13.0,poly.evaluate(0.0),.001);
  }

  /**
   * Testing evaluate method with negative x-value, where x = -2.0.
   */
  @Test
  public void testEvalWithNeg() {
    Polynomial poly = create();
    poly.addTerm(-3,2);
    poly.addTerm(2,3);
    poly.addTerm(2,2);
    assertEquals("2x^3 -1x^2", poly.toString());
    assertEquals(-20, poly.evaluate(-2.0),.01);
  }

  @Test
  public void testAddDiffPowPoly() {
    Polynomial poly1 = create();
    Polynomial poly2 = create();

    poly1.addTerm(3,2);
    poly1.addTerm(2,4);
    poly2.addTerm(-5,3);
    poly2.addTerm(7,5);
    poly2.addTerm(7,1);
    assertEquals("7x^5 -5x^3 +7x^1", poly2.toString());
    Polynomial addition = poly1.add(poly2);
    assertEquals("7x^5 +2x^4 -5x^3 +3x^2 +7x^1", addition.toString());
  }

  /**
   * Adding polys with the same power.
   */
  @Test
  public void testAddSamePower() {
    Polynomial poly1 = create();
    Polynomial poly2 = create();
    poly1.addTerm(3,2);
    poly1.addTerm(2,4);
    poly2.addTerm(5,4);
    poly2.addTerm(1,2);
    poly2.addTerm(1,1);
    Polynomial addition1 = poly1.add(poly2);
    assertEquals("7x^4 +4x^2 +1x^1", addition1.toString());
  }

  /**
   * Testing add() by adding poly 0.
   */
  @Test
  public void testAddPolyZero() {
    Polynomial poly1 = create();
    Polynomial poly2 = create();
    poly1.addTerm(3,2);
    poly1.addTerm(1,1);
    assertEquals("0", poly2.toString());
    Polynomial addition1 = poly1.add(poly2);
    assertEquals("3x^2 +1x^1", addition1.toString());
  }

  /**
   * This is testing the multiply() method and multiplying by poly 0.
   */
  @Test
  public void testMultiplyPoly() {
    Polynomial poly1 = create();
    Polynomial poly2 = create();
    poly1.addTerm(5,3);
    poly1.addTerm(3,2);
    poly2.addTerm(2,2);
    poly2.addTerm(3,1);
    Polynomial mult1 = poly1.multiply(poly2);
    assertEquals("10x^5 +21x^4 +9x^3", mult1.toString());
    Polynomial zeroPoly = create();
    Polynomial mult2 = poly1.multiply(zeroPoly);
    assertEquals("0", mult2.toString());
  }

  /**
   * Testing the equal() method.
   */
  @Test
  public void testEqual() {
    Polynomial poly1 = create();
    Polynomial poly2 = create();
    poly1.addTerm(5,3);
    poly1.addTerm(3,2);
    poly2.addTerm(2,3);
    poly2.addTerm(3,3);
    poly2.addTerm(3,2);
    assertTrue(poly1.equals(poly2));
  }

  /**
   * Testing not equal polynomials.
   */
  @Test
  public void testEqualsNot() {
    Polynomial poly1 = create();
    Polynomial poly2 = create();

    poly1.addTerm(3,7);
    poly1.addTerm(1,0);
    poly2.addTerm(3,7);

    assertFalse(poly1.equals(poly2));
  }

  /**
   * Testing hashCode() method.
   */
  @Test
  public void testHash() {
    Polynomial poly1 = create();
    Polynomial poly2 = create();
    assertEquals(poly1.hashCode(), poly2.hashCode());
    poly1.addTerm(3,7);
    poly2.addTerm(3,7);
    assertEquals(poly1.hashCode(), poly2.hashCode());
  }

}
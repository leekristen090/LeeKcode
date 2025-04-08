import org.junit.Test;

import polynomial.Polynomial;
import polynomial.SparsePolynomial;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * This is the JUnit test class for the methods in SparsePolynomial. The additional tests in this
 * class are using polynomial terms that have larger powers than in the abstract to test methods in
 * Sparse. We also have the sampleTest() that was given to us.
 */
public class SparseTest extends AbstractPolyTest {

  @Override
  protected Polynomial create() {
    return new SparsePolynomial();
  }

  /**
   * We are testing the addTerm method with a larger power.
   */
  @Test
  public void testAddTermLargishPow() {
    Polynomial newSparse = new SparsePolynomial();
    newSparse.addTerm(3,2000);
    assertEquals("3x^2000", newSparse.toString());
    newSparse.addTerm(2,40000);
    newSparse.addTerm(2,2000);
    newSparse.addTerm(0,0);
    assertEquals("2x^40000 +5x^2000", newSparse.toString());
    newSparse.addTerm(-2,40000);
    assertEquals("5x^2000", newSparse.toString());
  }

  /**
   * This is testing the getCoefficient() method with terms with large powers.
   */
  @Test
  public void testGetCoeffLargePow() {
    Polynomial newSparse = new SparsePolynomial();
    newSparse.addTerm(3,2000);
    newSparse.addTerm(2,40000);
    newSparse.addTerm(2,2000);
    newSparse.addTerm(1,1000);
    newSparse.addTerm(-1,0);
    assertEquals("2x^40000 +5x^2000 +1x^1000 -1", newSparse.toString());
    assertEquals(5, newSparse.getCoefficient(2000));
    assertEquals(-1, newSparse.getCoefficient(0));
    assertEquals(2, newSparse.getCoefficient(40000));
  }

  /**
   * Testing the getDegree() method.
   */
  @Test
  public void testGetLargeDegree() {
    Polynomial sparse1 = new SparsePolynomial();
    sparse1.addTerm(3,2);
    sparse1.addTerm(2,49999999);
    sparse1.addTerm(-5,3);
    sparse1.addTerm(7,50000000);
    sparse1.addTerm(8,1);
    sparse1.addTerm(13,0);
    assertEquals("7x^50000000 +2x^49999999 -5x^3 +3x^2 +8x^1 +13", sparse1.toString());
    assertEquals(50000000, sparse1.getDegree());
  }

  /**
   * Testing add() with largish power.
   */
  @Test
  public void testAddDiffPowerLarge() {
    Polynomial sparse1 = new SparsePolynomial();
    Polynomial sparse2 = new SparsePolynomial();
    sparse1.addTerm(3,2000);
    sparse1.addTerm(2,4001);
    sparse2.addTerm(-5,3333);
    sparse2.addTerm(7,5555);
    sparse2.addTerm(7,1111);
    assertEquals("7x^5555 -5x^3333 +7x^1111", sparse2.toString());
    Polynomial addition = sparse1.add(sparse2);
    assertEquals("7x^5555 +2x^4001 -5x^3333 +3x^2000 +7x^1111", addition.toString());
  }

  /**
   * Adding things with the same power.
   */
  @Test
  public void testAddSamePowerLarge() {
    Polynomial sparse1 = new SparsePolynomial();
    Polynomial sparse2 = new SparsePolynomial();
    sparse1.addTerm(3,222);
    sparse1.addTerm(2,4444);
    sparse2.addTerm(5,4444);
    sparse2.addTerm(1,222);
    sparse2.addTerm(1,1);
    Polynomial addition1 = sparse1.add(sparse2);
    assertEquals("7x^4444 +4x^222 +1x^1", addition1.toString());
  }

  /**
   * Adding poly 0 to a largish poly.
   */
  @Test
  public void testAddPolyZeroToLarge() {
    Polynomial sparse1 = new SparsePolynomial();
    Polynomial sparse2 = new SparsePolynomial();
    sparse1.addTerm(3,22222);
    sparse1.addTerm(1,1);
    Polynomial addition1 = sparse1.add(sparse2);
    assertEquals("3x^22222 +1x^1", addition1.toString());
  }

  /**
   * This is testing multiply() with largish power.
   */
  @Test
  public void testMultiplyLarge() {
    Polynomial sparse1 = new SparsePolynomial();
    Polynomial sparse2 = new SparsePolynomial();
    sparse1.addTerm(1,200);
    sparse1.addTerm(1,400);
    //sparse2.addTerm(-5,3);
    sparse2.addTerm(1,500);
    sparse2.addTerm(1,1);
    Polynomial mult = sparse1.multiply(sparse2);
    assertEquals("1x^900 +1x^700 +1x^401 +1x^201", mult.toString());
  }

  /**
   * Testing multiplying by zero.
   */
  @Test
  public void testMultByZero() {
    Polynomial sparse1 = new SparsePolynomial();
    Polynomial sparse2 = new SparsePolynomial();
    Polynomial sparse3 = new SparsePolynomial();
    sparse1.addTerm(3,1000);
    // one polynomial is 0/empty
    Polynomial mult1 = sparse1.multiply(sparse2);
    assertEquals("0", mult1.toString());
    // both polynomials are 0/empty
    Polynomial mult2 = sparse2.multiply(sparse3);
    assertEquals("0", mult2.toString());
  }

  /**
   * Test derive with a positive and negative coefficient and a largish power for one of the terms.
   */
  @Test
  public void testDeriveLarge() {
    Polynomial sparse1 = new SparsePolynomial();
    sparse1.addTerm(4,2000);
    sparse1.addTerm(-3,2);
    Polynomial derive3 = sparse1.derivative();
    assertEquals("8000x^1999 -6x^1", derive3.toString());
  }

  /**
   * This is testing equals() with two largish sparse polynomials.
   */
  @Test
  public void testEqualsTwoLargeSparse() {
    Polynomial sparse1 = new SparsePolynomial();
    Polynomial sparse2 = new SparsePolynomial();

    sparse1.addTerm(3,7000);
    sparse1.addTerm(1,0);
    sparse2.addTerm(3,7000);
    sparse2.addTerm(1,0);
    assertTrue(sparse1.equals(sparse2));
  }

  /**
   * Testing not equal large sparse polynomials.
   */
  @Test
  public void testLargeEqualsNot() {
    Polynomial sparse1 = new SparsePolynomial();
    Polynomial sparse2 = new SparsePolynomial();

    sparse1.addTerm(3,7001);
    sparse1.addTerm(1,0);
    sparse2.addTerm(3,7000);
    sparse2.addTerm(1,0);

    assertFalse(sparse1.equals(sparse2));
  }

  /**
   * Testing hashCode() with polynomials with largish powers.
   */
  @Test
  public void testHashLarge() {
    Polynomial sparse1 = new SparsePolynomial();
    Polynomial sparse2 = new SparsePolynomial();
    Polynomial sparse3 = new SparsePolynomial();

    sparse1.addTerm(3,321);
    sparse1.addTerm(1,0);
    sparse2.addTerm(3,321);
    sparse2.addTerm(1,0);
    sparse3.addTerm(5,321);
    sparse3.addTerm(1,0);

    assertEquals(sparse1.hashCode(), sparse2.hashCode());
    assertNotEquals(sparse1.hashCode(), sparse3.hashCode());
  }


  /**
   * Sample test from given SampleTest.java.
   */
  @Test
  public void sampleTest() {
    int degree_a = 20000000;
    int degree_b = 30000000;

    Polynomial a = new SparsePolynomial();
    Polynomial b = new SparsePolynomial();
    Polynomial d = new SparsePolynomial();
    a.addTerm(1,degree_a);
    a.addTerm(1,0);

    b.addTerm(2,degree_b);
    b.addTerm(1,0);

    d.addTerm(1,degree_a);
    d.addTerm(1,0);

    Polynomial c = a.multiply(b);
    Polynomial e = a.add(d);

    assertTrue(a.equals(d));
    assertFalse(a.equals(b));
    assertEquals("Coefficient does not match",2,
            c.getCoefficient(degree_a + degree_b));
    assertEquals("Coefficient does not match",2,c.getCoefficient(degree_b));
    assertEquals("Coefficient does not match",1,c.getCoefficient(degree_a));
    assertEquals("Coefficient does not match",1,c.getCoefficient(0));

    assertEquals(2,e.getCoefficient(degree_a));
    assertEquals("1x^20000000 +1", a.toString());
  }

}
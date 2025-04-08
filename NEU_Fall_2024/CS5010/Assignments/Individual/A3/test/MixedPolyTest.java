import org.junit.Test;

import polynomial.Polynomial;
import polynomial.SimplePolynomial;
import polynomial.SparsePolynomial;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This is the JUnit test class for mixed polynomials.
 */
public class MixedPolyTest {

  /**
   * This is testing the construction of a polynomial with no terms. Also testing multiply(), add(),
   * derivative(), getDegree(), and getCoefficient() when both terms are 0.
   */
  @Test
  public void testConstructor() {
    Polynomial sparse = new SparsePolynomial();
    Polynomial simple = new SimplePolynomial();

    assertEquals("0", sparse.toString());
    assertEquals(0, sparse.getDegree());
    sparse.addTerm(0,0);
    assertEquals("0", sparse.toString());
    assertEquals("0", sparse.derivative().toString());

    assertEquals("0", simple.toString());
    assertEquals(0, simple.getDegree());
    sparse.addTerm(0,0);
    assertEquals("0", simple.toString());
    assertEquals("0", simple.derivative().toString());

    assertEquals(sparse.hashCode(), sparse.hashCode());
    assertTrue(sparse.equals(simple));

    Polynomial addition = sparse.add(simple);
    assertEquals("0", addition.toString());
    assertEquals(0, addition.getDegree());
    assertEquals("0", addition.derivative().toString());
    assertEquals(0, addition.getCoefficient(0));
    assertEquals(0.0, addition.evaluate(0), .01);

    Polynomial multiply = sparse.multiply(simple);
    assertEquals("0", multiply.toString());
    assertEquals(0, multiply.getDegree());
    assertEquals("0", multiply.derivative().toString());
  }

  /**
   * Here we are testing addTerm() and toString().
   */
  @Test
  public void testAddTermSamePowAndToString() {
    Polynomial sparse1 = new SparsePolynomial();
    Polynomial simple = new SimplePolynomial();

    sparse1.addTerm(2,673);
    sparse1.addTerm(1,673);
    sparse1.addTerm(1,0);
    simple.addTerm(3,673);
    simple.addTerm(1,0);

    assertEquals("3x^673 +1", sparse1.toString());
    assertEquals("3x^673 +1", simple.toString());
  }

  /**
   * Here we are testing addTerm() and toString().
   */
  @Test
  public void testAddTermDiffPowAndToString() {
    Polynomial sparse1 = new SparsePolynomial();
    Polynomial simple = new SimplePolynomial();

    sparse1.addTerm(2,60000);
    sparse1.addTerm(1,5000);
    sparse1.addTerm(1,0);
    simple.addTerm(3,4000);
    simple.addTerm(1,0);

    assertEquals("2x^60000 +1x^5000 +1", sparse1.toString());
    assertEquals("3x^4000 +1", simple.toString());
  }

  /**
   * This is testing equals() with one sparse polynomial and one simple polynomial.
   */
  @Test
  public void testSparseEqualsSimple() {
    Polynomial sparse1 = new SparsePolynomial();
    Polynomial simple = new SimplePolynomial();

    sparse1.addTerm(2,673);
    sparse1.addTerm(1,673);
    sparse1.addTerm(1,0);
    simple.addTerm(3,673);
    simple.addTerm(1,0);

    assertEquals("3x^673 +1", sparse1.toString());
    assertEquals("3x^673 +1", simple.toString());
    assertTrue(sparse1.equals(simple));
  }

  /**
   * Testing equals when one poly is a mixed and comparing it to a sparse or simple other.
   */
  @Test
  public void testMixedEqualsOther() {
    Polynomial sparse1 = new SparsePolynomial();
    Polynomial simple1 = new SimplePolynomial();
    Polynomial sparse2 = new SparsePolynomial();
    Polynomial simple2 = new SimplePolynomial();

    sparse1.addTerm(2,10000);
    sparse1.addTerm(1,0);
    simple1.addTerm(1, 10000);
    simple1.addTerm(1, 0);
    sparse2.addTerm(3,10000);
    sparse2.addTerm(2,0);
    simple2.addTerm(3,10000);
    simple2.addTerm(2,0);

    Polynomial mixed = sparse1.add(simple1);
    assertEquals("3x^10000 +2", mixed.toString());
    assertEquals("3x^10000 +2", sparse2.toString());
    assertEquals("3x^10000 +2", simple2.toString());

    assertTrue(mixed.equals(sparse2));
    assertTrue(mixed.equals(simple2));
  }

  /**
   * We are testing hashCode() for two mixed polynomials.
   */
  @Test
  public void testHashCodeMixed() {
    Polynomial sparse1 = new SparsePolynomial();
    Polynomial simple1 = new SimplePolynomial();
    sparse1.addTerm(2,673);
    sparse1.addTerm(1,673);
    sparse1.addTerm(1,0);
    simple1.addTerm(3,673);
    simple1.addTerm(1,0);

    Polynomial mixed1 = sparse1.add(simple1);

    Polynomial sparse2 = new SparsePolynomial();
    Polynomial simple2 = new SimplePolynomial();
    sparse2.addTerm(3,673);
    sparse2.addTerm(1,0);
    simple2.addTerm(3,673);
    simple2.addTerm(1,0);
    Polynomial mixed2 = sparse2.add(simple2);

    assertEquals(mixed1.hashCode(), mixed2.hashCode());
  }

  /**
   * This is testing add() with mixed polynomials, one sparse and one simple with the same power.
   * Also testing getDegree() and getCoefficient() in this test.
   */
  @Test
  public void testAddMixedSame() {
    Polynomial sparse = new SparsePolynomial();
    Polynomial simple = new SimplePolynomial();
    sparse.addTerm(3,1000);
    sparse.addTerm(3,198);
    simple.addTerm(3,198);
    simple.addTerm(3,0);

    assertEquals(198, simple.getDegree());

    Polynomial mixAdd1 = sparse.add(simple);
    assertEquals("3x^1000 +6x^198 +3", mixAdd1.toString());
    assertEquals(1000, mixAdd1.getDegree());
    assertEquals(3, mixAdd1.getCoefficient(1000));
    assertEquals(6, mixAdd1.getCoefficient(198));
    assertEquals(3, mixAdd1.getCoefficient(0));

    Polynomial sparse2 = new SparsePolynomial();
    Polynomial simple2 = new SimplePolynomial();
    sparse2.addTerm(3,1000);
    sparse2.addTerm(3,198);
    simple2.addTerm(3,198);
    simple2.addTerm(3,0);
    Polynomial mixAdd2 = sparse2.add(simple2);
    assertEquals("3x^1000 +6x^198 +3", mixAdd2.toString());

    assertTrue(mixAdd1.equals(mixAdd2));

    // adding two mixed polynomials together
    Polynomial mixedAddMixed = mixAdd1.add(mixAdd2);
    assertEquals("6x^1000 +12x^198 +6", mixedAddMixed.toString());
    assertEquals(6, mixedAddMixed.getCoefficient(1000));
  }

  /**
   * This is testing add() with mixed polynomials, one sparse and one simple with different powers.
   * Also testing getDegree() and getCoefficient() in this test.
   */
  @Test
  public void testAddMixedDiff() {
    Polynomial sparse = new SparsePolynomial();
    Polynomial simple = new SimplePolynomial();

    sparse.addTerm(1,10000);
    sparse.addTerm(3,900);
    simple.addTerm(2,1000);
    simple.addTerm(4,0);

    assertEquals("1x^10000 +3x^900", sparse.toString());
    assertEquals("2x^1000 +4", simple.toString());

    Polynomial addition = sparse.add(simple);
    assertEquals("1x^10000 +2x^1000 +3x^900 +4", addition.toString());
    assertEquals(10000, addition.getDegree());
    assertEquals(1, addition.getCoefficient(10000));
    assertEquals(2, addition.getCoefficient(1000));
    assertEquals(3, addition.getCoefficient(900));
    assertEquals(4, addition.getCoefficient(0));

    Polynomial sparse2 = new SparsePolynomial();
    Polynomial simple2 = new SimplePolynomial();
    sparse2.addTerm(1,1001);
    sparse2.addTerm(3,199);
    simple2.addTerm(2,198);
    simple2.addTerm(4,1);
    Polynomial mixAdd2 = sparse2.add(simple2);
    assertEquals("1x^1001 +3x^199 +2x^198 +4x^1", mixAdd2.toString());

    Polynomial mixedAddMixed = addition.add(mixAdd2);
    assertEquals("1x^10000 +1x^1001 +2x^1000 +3x^900 +3x^199 +2x^198 +4x^1 +4",
            mixedAddMixed.toString());
    assertEquals(1, mixedAddMixed.getCoefficient(10000));
  }

  /**
   * Multiplying sparse and simple polynomial.
   */
  @Test
  public void testMultMixedDiff() {
    Polynomial sparse = new SparsePolynomial();
    Polynomial simple = new SimplePolynomial();
    sparse.addTerm(3,1000);
    sparse.addTerm(1,0);
    simple.addTerm(3,198);
    simple.addTerm(1,0);
    Polynomial mixedMult = sparse.multiply(simple);
    assertEquals("9x^1198 +3x^1000 +3x^198 +1", mixedMult.toString());
  }

  /**
   * This is testing the derivative of a mixed polynomial.
   */
  @Test
  public void testDerivativeMixed() {
    Polynomial sparse = new SparsePolynomial();
    Polynomial simple = new SimplePolynomial();

    sparse.addTerm(1,10000);
    sparse.addTerm(-3,900);
    simple.addTerm(2,1000);
    simple.addTerm(4,0);

    Polynomial mixed = sparse.add(simple);
    assertEquals("1x^10000 +2x^1000 -3x^900 +4", mixed.toString());

    assertEquals("10000x^9999 +2000x^999 -2700x^899", mixed.derivative().toString());
  }

  /**
   * Testing evaluate() with x = positive number.
   */
  @Test
  public void testEvalMixed() {
    Polynomial sparse = new SparsePolynomial();
    Polynomial simple = new SimplePolynomial();

    sparse.addTerm(1,10000);
    sparse.addTerm(-5,900);
    simple.addTerm(2,1000);
    simple.addTerm(4,0);

    Polynomial addition = sparse.add(simple);
    assertEquals("1x^10000 +2x^1000 -5x^900 +4", addition.toString());

    assertEquals(2.0, addition.evaluate(1),.01);
  }

  /**
   * Testing evaluate() with x = 0.
   */
  @Test
  public void testEvalMixedWithZero() {
    Polynomial sparse = new SparsePolynomial();
    Polynomial simple = new SimplePolynomial();

    sparse.addTerm(1,10000);
    sparse.addTerm(-3,900);
    simple.addTerm(2,1000);
    simple.addTerm(4,0);

    Polynomial addition = sparse.add(simple);
    assertEquals("1x^10000 +2x^1000 -3x^900 +4", addition.toString());

    assertEquals(4.0, addition.evaluate(0),.01);
  }

  /**
   * Testing evaluate() with x = negative number.
   */
  @Test
  public void evalMixedWithNeg() {
    Polynomial sparse = new SparsePolynomial();
    Polynomial simple = new SimplePolynomial();

    sparse.addTerm(1,10000);
    sparse.addTerm(-3,901);
    simple.addTerm(2,1000);
    simple.addTerm(4,0);

    Polynomial addition = sparse.add(simple);
    assertEquals("1x^10000 +2x^1000 -3x^901 +4", addition.toString());

    assertEquals(10.0, addition.evaluate(-1),.01);
  }

  /**
   * Sample test from given SampleTest.java with a simple and sparse polynomial.
   */
  @Test
  public void sampleTest() {
    int degree_a = 20000000;
    int degree_b = 30000000;

    Polynomial a = new SparsePolynomial();
    Polynomial b = new SimplePolynomial();

    a.addTerm(1,degree_a);
    a.addTerm(1,0);

    b.addTerm(2,degree_b);
    b.addTerm(1,0);

    Polynomial c = a.multiply(b);
    //assertFalse(a.equals(b));

    assertEquals("Coefficient does not match",2,
            c.getCoefficient(degree_a + degree_b));
    assertEquals("Coefficient does not match",2,c.getCoefficient(degree_b));
    assertEquals("Coefficient does not match",1,c.getCoefficient(degree_a));
    assertEquals("Coefficient does not match",1,c.getCoefficient(0));
  }

}
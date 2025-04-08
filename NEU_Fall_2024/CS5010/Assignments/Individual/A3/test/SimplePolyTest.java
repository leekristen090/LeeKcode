import polynomial.Polynomial;
import polynomial.SimplePolynomial;


/**
 * This is a JUnit test for the methods in SimplePolynomial.
 */
public class SimplePolyTest extends AbstractPolyTest {

  @Override
  protected Polynomial create() {
    return new SimplePolynomial();
  }

}
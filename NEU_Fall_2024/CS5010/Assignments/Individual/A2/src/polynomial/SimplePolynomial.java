package polynomial;

import java.util.ArrayList;

/**
 * This class represents a SimplePolynomial which implements the Polynomial interface.
 */
public class SimplePolynomial implements Polynomial {

  private final ArrayList<Integer> numbers;

  /**
   * This class is an implementation of the Polynomial interface which represents all the operations
   * offered by a polynomial.
   * From Canvas assignment description:
   * A polynomial is made of several terms, each term having a
   * coefficient and a variable raised to a power. Polynomials are one-variable if all their terms
   * contain only one variable.
   */
  public SimplePolynomial() {
    numbers = new ArrayList<>();
  }

  /**
   * Add this polynomial with another and return the result as another
   * polynomial. All implementations must ensure that neither of the two operand
   * polynomials are changed as a result of this operation.
   *
   * @param other the other polynomial to be added
   * @return the resulting polynomial
   */
  @Override
  public Polynomial add(Polynomial other) {
    // create a new polynomial for our return value
    SimplePolynomial result = new SimplePolynomial();
    for (int i = 0; i < numbers.size(); i++) {
      int thisCoeff = this.getCoefficient(i);
      // inserting the current, or first poly into the result one
      // we will use the addTerm() logic
      result.addTerm(thisCoeff, i);
    }
    for (int i = 0; i < other.getDegree() + 1; i++ ) {
      int otherCoeff = other.getCoefficient(i);
      // adding the other poly into the result that it to be added to the first poly
      result.addTerm(otherCoeff, i);
    }
    return result;
  }

  /**
   * Multiply this polynomial to another and return the result as another
   * polynomial. All implementations must ensure that neither of the two operand
   * polynomials are changed as a result of this operation.
   *
   * @param other the other polynomial to be multiplied
   * @return the resulting polynomial
   */
  @Override
  public Polynomial multiply(Polynomial other) {
    SimplePolynomial result = new SimplePolynomial();
    if (this.getDegree() == 0 || other.getDegree() == 0) {
      return result;
    }
    for (int i = 0; i < numbers.size(); i++) {
      int thisCoeff = this.getCoefficient(i);
      for (int j = 0; j < other.getDegree() + 1; j++) {
        int otherCoeff = other.getCoefficient(j);
        result.addTerm(thisCoeff * otherCoeff, i + j);
      }
    }
    return result;
    //return null;
  }

  /**
   * Compute and return the first derivative of this polynomial. All implementations
   * must ensure that the calling polynomial is not changed as a result of this
   * operation.
   *
   * @return the polynomial that is the first derivative of this polynomial
   */
  @Override
  public Polynomial derivative() {
    SimplePolynomial result = new SimplePolynomial();
    // start at 1 because derivative of constant is 0 so we don't need to start there
    for (int i = 1; i < numbers.size(); i++) {
      int thisCoeff = numbers.get(i);
      int newPower = i - 1;
      //result.addTerm(thisCoeff * i, newPower);
      if (newPower == 0) {
        result.addTerm(thisCoeff * i,newPower);
      }
      if (newPower != 0) {
        result.addTerm(thisCoeff * i, newPower);
      }
    }
    return result;
  }

  /**
   * Add a term to this polynomial with the specified coefficient and power.
   *
   * @param coefficient the coefficient of the term to be added
   * @param power       the power of the term to be added
   * @throws IllegalArgumentException if the power is negative
   */
  @Override
  public void addTerm(int coefficient, int power) throws IllegalArgumentException {
    if (power < 0) {
      throw new IllegalArgumentException("No negative exponents!");
    }

    while (numbers.size() <= power) {
      numbers.add(0);
    }

    int newCoeff = numbers.get(power) + coefficient;
    if (newCoeff == 0) {
      numbers.set(power, 0);
    } else {
      numbers.set(power, newCoeff);
    }
  }

  /**
   * Get the degree of this polynomial. The degree of a polynomial is defined as the
   * highest power in the polynomial.
   *
   * @return the degree of this polynomial as a whole number
   */
  @Override
  public int getDegree() {
    int largest = 0; // at first assume the largest exponent is 0
    for (int i = 0; i < numbers.size(); i++) {
      // scan array for larger items
      if (i > largest) {
        largest = i;
      }
    }
    //return 0;
    return largest;
  }

  /**
   * Return a string representation of the polynomial. If there is a constructor with no parameters
   * that creates a polynomial with no terms, i.e. the polynomial 0.
   * @return string format of polynomial
   */
  @Override
  public String toString() {
    // polynomial with no parameters is polynomial 0
    if (numbers.isEmpty()) {
      return "0";
    }

    StringBuilder entireString = new StringBuilder();

    for (int power = numbers.size() - 1; power >= 0; power--) {
      int coeff = numbers.get(power);
      if (coeff != 0) {
        if (entireString.length() > 0 && coeff > 0) {
          entireString.append(" +");
        }
        if (coeff < 0) {
          entireString.append(" ");
        }
        if (power == 0) {
          entireString.append(coeff);
        } else {
          entireString.append(coeff).append("x^").append(power);
        }
      }
    }
    return entireString.toString();
  }

  /**
   * Evaluate the value of this polynomial at the given value of the variable. For example, if
   * x = 2 and f(x) = 5x^2, then F(2) = 5(2)^2 = 20
   *
   * @param x the value at which the polynomial is to be evaluated.
   * @return the value of the polynomial at x
   */
  @Override
  public double evaluate(double x) {
    double result = 0;
    for (int power = 0; power < numbers.size(); power++) {
      result += numbers.get(power) * Math.pow(x,power);
    }
    return result;
    //return 0;
  }

  /**
   * Return the coefficient of the term with the given power.
   *
   * @param power the power whose coefficient is sought
   * @return the coefficient at the given power
   */
  @Override
  public int getCoefficient(int power) {
    if (power < 0 || power >= numbers.size()) {
      return 0;
    }
    return numbers.get(power);
    //return 0;
  }

  /**
   * I did this method based on the equals() method in HmsDuration.
   * @param o polynomial to be compared
   * @return check if they are equal
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof SimplePolynomial)) {
      return false;
    }

    SimplePolynomial that = (SimplePolynomial) o;
    if (this.getDegree() != that.getDegree()) {
      return false;
    }
    for (int i = 0; i < this.getDegree(); i++) {
      if (this.getCoefficient(i) != that.getCoefficient(i)) {
        return false;
      }
    }
    return true;
  }

  /**
   * From HmsDuration hashCode() javadoc comment:
   * One must override the hashCode() methods when one overrides equals. The rationale is that if
   * two objects are equal to each other (as deemed by the equals method) then their hashCode
   * methods should return the same value.
   */
  @Override
  public int hashCode() { // hashing and stuff
    int hash = 7; // reduce number of collisions
    hash = 31 * hash + this.getDegree();
    for (int i = 0; i <= this.getDegree(); i++) { // get all coefficients
      hash = 31 * hash + this.getCoefficient(i);
    }
    return hash;
  }
}
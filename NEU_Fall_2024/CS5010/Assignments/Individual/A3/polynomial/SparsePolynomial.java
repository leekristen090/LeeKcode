package polynomial;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * This is SparsePolynomial. It uses linked lists and implements the Polynomial interface which
 * represents all the operations offered by a polynomial.
 */
public class SparsePolynomial implements Polynomial {

  private final LinkedList<Term> numbers;

  private static class Term {
    int coeff;
    int pow;

    Term(int coefficient, int power) {
      this.coeff = coefficient;
      this.pow = power;
    }
  }

  /**
   * We use a linked list to represent a polynomial.
   */
  public SparsePolynomial() {
    numbers = new LinkedList<>();
  }

  /**
   * Add this polynomial with another and return the result as another polynomial. All
   * implementations must ensure that neither of the two operand polynomials are changed as a
   * result of this operation. Binary operations on polynomials should be "optimized".
   *
   * @param other the other polynomial to be added
   * @return the resulting polynomial
   */
  @Override
  public Polynomial add(Polynomial other) {
    SparsePolynomial result = new SparsePolynomial();
    // first we add 'this' poly
    for (Term thisTerm : numbers) {
      result.addTerm(thisTerm.coeff, thisTerm.pow);
    }

    if (other instanceof SparsePolynomial) {
      SparsePolynomial otherSparse = (SparsePolynomial) other;
      for (Term otherTerm : otherSparse.numbers) {
        result.addTerm(otherTerm.coeff, otherTerm.pow);
      }
    }
    else if (other instanceof SimplePolynomial) {
      SimplePolynomial otherSimple = (SimplePolynomial) other;
      for (int i = 0; i <= otherSimple.getDegree(); i++) {
        int otherCoeff = otherSimple.getCoefficient(i);
        if (otherCoeff != 0) {
          result.addTerm(otherCoeff, i);
        }
      }
    }

    return result;
  }

  /**
   * Multiply this polynomial to another and return the result as another polynomial. All
   * implementations must ensure that neither of the two operand polynomials are changed as a result
   * of this operation. Binary operations on polynomials should be "optimized".
   *
   * @param other the other polynomial to be multiplied
   * @return the resulting polynomial
   */
  @Override
  public Polynomial multiply(Polynomial other) {
    SparsePolynomial result = new SparsePolynomial();
    if (this.getDegree() == 0 || other.getDegree() == 0) {
      return result; // return result bc result is empty therefore 0
    }
    for (Term thisTerm : this.numbers) {
      if (other instanceof SparsePolynomial) {
        for (Term otherTerm : ((SparsePolynomial) other).numbers) {
          int newCoeff = thisTerm.coeff * otherTerm.coeff;
          int newPow = thisTerm.pow + otherTerm.pow;
          result.addTerm(newCoeff, newPow);
        }

      } else {
        for (int i = 0; i <= other.getDegree(); i++) {
          int newC = thisTerm.coeff * other.getCoefficient(i);
          int newP = thisTerm.pow + i;
          result.addTerm(newC, newP);
        }
      }
    }

    return result;
  }

  /**
   * Compute and return the first derivative of this polynomial. All implementations must ensure
   * that the calling polynomial is not changed as a result of this operation. Binary operations on
   * polynomials should be "optimized".
   *
   * @return the polynomial that is the first derivative of this polynomial
   */
  @Override
  public Polynomial derivative() {
    Polynomial result = new SparsePolynomial();
    for (Term thisTerm : this.numbers) {
      int newPow = thisTerm.pow - 1; // new power is current power - 1
      if (thisTerm.pow > 0) {
        // mult current coeff with current power and then use newPow
        result.addTerm(thisTerm.coeff * thisTerm.pow, newPow);
      } else {
        // derivative of constant is 0
        result.addTerm(0, 0);
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
      throw new IllegalArgumentException("No negative power!");
    }

    if (coefficient == 0) {
      return; // we ignore
    }

    //Term newTerm = new Term(coefficient, power);
    ListIterator<Term> iterator = numbers.listIterator();
    while (iterator.hasNext()) {
      Term currentTerm = iterator.next();
      // if the new term has the same power then add the coefficients together
      if (currentTerm.pow == power) {
        currentTerm.coeff += coefficient;
        // if the sum is 0 we get rid of the term
        if (currentTerm.coeff == 0) {
          iterator.remove();
        }
        return; // exit while loop
      }
      if (currentTerm.pow < power) {
        iterator.previous();
        iterator.add(new Term(coefficient, power));
        return; // exit while loop
      }
    }
    numbers.add(new Term(coefficient, power));
  }

  /**
   * Get the degree of this polynomial. The degree of a polynomial is defined as the highest power
   * in the polynomial.
   *
   * @return the degree of this polynomial as a whole number
   */
  @Override
  public int getDegree() {
    if (numbers.isEmpty()) {
      return 0;
    }
    return numbers.getFirst().pow;
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
    //for (int power = 0; power < numbers.size(); power++)
    for (Term terms : numbers) {
      //Term terms = numbers.get(power);
      result += terms.coeff * Math.pow(x, terms.pow);
    }
    return result;
  }

  /**
   * Return the coefficient of the term with the given power.
   *
   * @param power the power whose coefficient is sought
   * @return the coefficient at the given power
   */
  @Override
  public int getCoefficient(int power) {
    //if (power < 0 || numbers.isEmpty()) {return 0;}
    //for (int i = 0; i < numbers.size(); i++)
    for (Term terms : numbers) {
      //Term terms = numbers.get(i);
      if (terms.pow == power) {
        return terms.coeff;
      }
      if (terms.pow < power) {
        break;
      }
    }
    return 0;
  }

  /**
   * Return a string representation of the polynomial. If there is a constructor with no parameters
   * that creates a polynomial with no terms, i.e. the polynomial 0.
   * @return string format of polynomial
   */
  @Override
  public String toString() {

    if (numbers.isEmpty()) {
      return "0";
    }
    //return "";

    StringBuilder entireString = new StringBuilder();
    ListIterator<Term> iterator = numbers.listIterator();
    if (iterator.hasNext()) {
      do {
        Term currentTerm = iterator.next();
        if (entireString.length() > 0) {
          if (currentTerm.coeff > 0) {
            entireString.append(" +");
          }
          if (currentTerm.coeff < 0) {
            entireString.append(" ");
          }
        }
        if (currentTerm.pow == 0) {
          entireString.append(currentTerm.coeff);
        } else {
          entireString.append(currentTerm.coeff).append("x^").append(currentTerm.pow);
        }
      }
      while (iterator.hasNext());
    }
    return entireString.toString();
  }

  /**
   * This compares polynomial objects to see if they are the same type and/or have the same terms
   * and degree.
   * @param o object to be compared
   * @return true if polynomials are equal
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Polynomial)) {
      return false;
    }
    Polynomial other = (Polynomial) o;
    if (this.getDegree() != other.getDegree()) {
      return false;
    }
    for (Term terms : this.numbers) {
      if (terms.coeff != other.getCoefficient(terms.pow)) {
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
   * @return hash value
   */
  @Override
  public int hashCode() {
    int hash = 1;
    for (Term thisTerm : numbers) {
      //hash += Objects.hash(thisTerm.coeff, thisTerm.pow);
      hash = 31 * hash + thisTerm.coeff;
      hash = 31 * hash + thisTerm.pow;
    }
    return hash;
  }

}
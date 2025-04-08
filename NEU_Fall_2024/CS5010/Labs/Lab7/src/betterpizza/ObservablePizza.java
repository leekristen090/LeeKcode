package betterpizza;

import pizza.ToppingName;
import pizza.ToppingPortion;

/**
 * This class represents the operations offered by a single pizza in the better pizza package.
 */
public interface ObservablePizza {
  /**
   * Get the cost of this pizza.
   *
   * @return the cost of this pizza in MM.CC format
   */
  double cost();

  /**
   * Determines if the specified topping is on this pizza and if so, return its portion.
   *
   * @param name the name of the topping
   * @return portion of topping on this pizza, or null if topping is not on this pizza
   */
  ToppingPortion hasTopping(ToppingName name);
}
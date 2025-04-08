package betterpizza;

import java.util.HashMap;
import java.util.Map;

import pizza.Crust;
import pizza.Size;
import pizza.ToppingName;
import pizza.ToppingPortion;

/**
 * This class represents an ala carte pizza (i.e. a pizza that can
 * have an arbitrary number of ingredients.
 */
public class AlaCartePizza implements ObservablePizza {
  protected Crust crust;
  protected Size size;
  protected Map<ToppingName, ToppingPortion> toppings;

  /**
   * Create a pizza given its crust type, size and toppings.
   */
  protected AlaCartePizza(Size size, Crust crust,Map<ToppingName, ToppingPortion> toppings) {
    if (size == null || crust == null) {
      throw new IllegalArgumentException("null size and crust not allowed");
    }
    this.crust = crust;
    this.size = size;
    this.toppings = new HashMap<>(toppings);
  }

  /**
   * Determines if the specified topping is on this pizza and if so, return its portion.
   *
   * @param name the name of the topping
   * @return portion of topping on this pizza, or null if topping is not on this pizza
   */
  @Override
  public ToppingPortion hasTopping(ToppingName name) {
    return this.toppings.getOrDefault(name, null);
  }

  /**
   * Get the cost of this pizza.
   *
   * @return the cost of this pizza in MM.CC format
   */
  @Override
  public double cost() {
    double cost = 0.0;
    for (Map.Entry<ToppingName, ToppingPortion> item : this.toppings.entrySet()) {
      cost += item.getKey().getCost() * item.getValue().getCostMultiplier();
    }
    return cost + this.size.getBaseCost();
  }

  /**
   * This is my public static class of AlaCartePizzaBuilder which extends the abstract class
   * PizzaBuilder.
   */
  public static class AlaCartePizzaBuilder extends PizzaBuilder<AlaCartePizzaBuilder> {

    /**
     * Build my pizza.
     * @return return my built pizza
     */
    @Override
    public ObservablePizza build() {
      if (size == null) {
        throw new IllegalStateException("no null sizes!!");
      }
      return new AlaCartePizza(size, crust, toppings);
      //return returnBuilder();
    }

    /**
     * return our pizza.
     * @return pizza of correct type
     */
    @Override
    protected AlaCartePizzaBuilder returnBuilder() {
      return this;
    }

  }

}



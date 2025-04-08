package betterpizza;

import java.util.HashMap;
import java.util.Map;

import pizza.Crust;
import pizza.Size;
import pizza.ToppingName;
import pizza.ToppingPortion;

/**
 * This class represents a vegetarian pizza which implements the observable pizza interface.
 */
public class VeggiePizza implements ObservablePizza {
  protected Crust crust;
  protected Size size;
  protected Map<ToppingName, ToppingPortion> toppings;

  /**
   * Create a veggie pizza with all vegetarian toppings, of the specified
   * size with the specified crust.
   * @param size the size of this pizza
   * @param crust the crust of this pizza
   */
  public VeggiePizza(Size size, Crust crust, Map<ToppingName, ToppingPortion> toppings) {
    if (size == null || crust == null) {
      throw new IllegalArgumentException("veggie with null size or crust not allowed here!");
    }
    this.size = size;
    this.crust = crust;
    this.toppings = toppings != null ? toppings : addVeggieDefault();
  }

  private Map<ToppingName, ToppingPortion> addVeggieDefault() {
    Map<ToppingName, ToppingPortion> defaultToppings = new HashMap<>();
    defaultToppings.put(ToppingName.Cheese, ToppingPortion.Full);
    defaultToppings.put(ToppingName.Sauce,ToppingPortion.Full);
    defaultToppings.put(ToppingName.BlackOlive,ToppingPortion.Full);
    defaultToppings.put(ToppingName.GreenPepper,ToppingPortion.Full);
    defaultToppings.put(ToppingName.Onion,ToppingPortion.Full);
    defaultToppings.put(ToppingName.Jalapeno,ToppingPortion.Full);
    defaultToppings.put(ToppingName.Tomato,ToppingPortion.Full);
    return defaultToppings;
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
   * This is the veggie builder class which extends the abstract class veggie pizza builder.
   */
  public static class VeggiePizzaBuilder extends PizzaBuilder<VeggiePizzaBuilder> {

    /**
     * Build initial veggie pizza.
     */
    public VeggiePizzaBuilder() {
      this.toppings = new HashMap<>();
      this.toppings.put(ToppingName.Cheese, ToppingPortion.Full);
      this.toppings.put(ToppingName.Sauce, ToppingPortion.Full);
      this.toppings.put(ToppingName.BlackOlive, ToppingPortion.Full);
      this.toppings.put(ToppingName.GreenPepper, ToppingPortion.Full);
      this.toppings.put(ToppingName.Onion, ToppingPortion.Full);
      this.toppings.put(ToppingName.Jalapeno, ToppingPortion.Full);
      this.toppings.put(ToppingName.Tomato, ToppingPortion.Full);
    }

    /**
     * We build our pizza.
     * @return return our pizza
     */
    @Override
    public ObservablePizza build() {
      if (size == null) {
        throw new IllegalArgumentException("no null sizes!!");
      }
      return new VeggiePizza(size, crust, toppings);
    }

    @Override
    protected VeggiePizzaBuilder returnBuilder() {
      return this;
    }

  }
}

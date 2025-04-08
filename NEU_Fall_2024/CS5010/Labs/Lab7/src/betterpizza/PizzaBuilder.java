package betterpizza;

import java.util.HashMap;
import java.util.Map;

import pizza.Crust;
import pizza.Size;
import pizza.ToppingName;
import pizza.ToppingPortion;

/**
 * THis is the abstract pizza builder class for alacarte pizza.
 * @param <T> generic parameter
 */
public abstract class PizzaBuilder<T extends PizzaBuilder<T>> {
  protected Crust crust;
  protected Size size;
  protected Map<ToppingName, ToppingPortion> toppings = new HashMap<>();

  /**
   * This is our crust type.
   * @param crust type
   * @return return pizza
   */
  public T crust(Crust crust) {
    this.crust = crust;
    //return this;
    return returnBuilder();
  }

  /**
   * This is our pizza size.
   * @param size size
   * @return return pizza
   */
  public T size(Size size) {
    this.size = size;
    //return this;
    return returnBuilder();
  }

  /**
   * Add the toppings to our pizza.
   * @param name topping name
   * @param portion portion size
   * @return return our pizza
   */
  public T addTopping(ToppingName name, ToppingPortion portion) {
    toppings.put(name, portion);
    //return this;
    return returnBuilder();
  }

  /**
   * return our pizza.
   * @return pizza of correct type
   */
  protected abstract T returnBuilder();

  /**
   * Build our pizza.
   * @return return our built pizza
   */
  public abstract ObservablePizza build();

  /**
   * Cheese pizza with cheese removed.
   * @return return the pizza
   */
  public T noCheese() {
    toppings.remove(ToppingName.Cheese);
    return returnBuilder();
  }

  /**
   * Cheese pizza with sauce removed.
   * @return return the pizza
   */
  public T noSauce() {
    toppings.remove(ToppingName.Sauce);
    return returnBuilder();
  }

  /**
   * Cheese pizza with black olive removed.
   * @return return the pizza
   */
  public T noBlackOlive() {
    toppings.remove(ToppingName.BlackOlive);
    return returnBuilder();
  }

  /**
   * Cheese pizza with green pepper removed.
   * @return return the pizza
   */
  public T noGreenPepper() {
    toppings.remove(ToppingName.GreenPepper);
    return returnBuilder();
  }

  /**
   * Cheese pizza with onion removed.
   * @return return the pizza
   */
  public T noOnion() {
    toppings.remove(ToppingName.Onion);
    return returnBuilder();
  }

  /**
   * Cheese pizza with jalapeno removed.
   * @return return the pizza
   */

  public T noJalapeno() {
    toppings.remove(ToppingName.Jalapeno);
    return returnBuilder();
  }

  /**
   * Cheese pizza with tomato removed.
   * @return return the pizza
   */
  public T noTomato() {
    toppings.remove(ToppingName.Tomato);
    return returnBuilder();
  }

  /**
   * Cheese pizza with cheese on only the left half.
   *
   * @return return the pizza
   */
  public T leftHalfCheese() {
    toppings.put(ToppingName.Cheese, ToppingPortion.LeftHalf);
    return returnBuilder();
  }

  /**
   * Cheese pizza with cheese on only the right half.
   *
   * @return return the pizza
   */
  public T rightHalfCheese() {
    toppings.put(ToppingName.Cheese, ToppingPortion.RightHalf);
    return returnBuilder();
  }

}
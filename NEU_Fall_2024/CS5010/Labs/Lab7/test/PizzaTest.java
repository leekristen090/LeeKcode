import org.junit.Before;
import org.junit.Test;

import pizza.AlaCartePizza;
import pizza.CheesePizza;
import pizza.Pizza;
import pizza.Crust;
import pizza.Size;
import pizza.ToppingName;
import pizza.ToppingPortion;

import static org.junit.Assert.assertEquals;

/**
 * This is the test class given to us in the starter code for pizzas in pizza package.
 */
public class PizzaTest {
  private Pizza alacarte;
  private Pizza cheese;
  private Pizza halfCheese;

  /**
   * Setting up the pizzas to test.
   */
  @Before
  public void setup() {
    alacarte = new AlaCartePizza(Size.Medium,Crust.Classic);
    alacarte.addTopping(ToppingName.Cheese, ToppingPortion.Full);
    alacarte.addTopping(ToppingName.Sauce,ToppingPortion.Full);
    alacarte.addTopping(ToppingName.GreenPepper,ToppingPortion.Full);
    alacarte.addTopping(ToppingName.Onion,ToppingPortion.Full);
    alacarte.addTopping(ToppingName.Jalapeno,ToppingPortion.LeftHalf);

    cheese = new CheesePizza(Size.Large,Crust.Thin);

    halfCheese = new CheesePizza(Size.Large,Crust.Thin);
    //put cheese only on left half
    halfCheese.addTopping(ToppingName.Cheese,ToppingPortion.LeftHalf);
  }

  /**
   * Testing cost of pizza.
   */
  @Test
  public void testCost() {
    assertEquals(8.25,alacarte.cost(),0.01);
    assertEquals(9,cheese.cost(),0.01);
    assertEquals(8.5,halfCheese.cost(),0.01);

  }

}
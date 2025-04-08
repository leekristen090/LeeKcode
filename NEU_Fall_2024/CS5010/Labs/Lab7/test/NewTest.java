import org.junit.Before;
import org.junit.Test;

import betterpizza.AlaCartePizza;
import betterpizza.CheesePizza;
import betterpizza.ObservablePizza;
import betterpizza.VeggiePizza;
import pizza.Crust;
import pizza.Size;
import pizza.ToppingName;
import pizza.ToppingPortion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * This is the test class using better pizza package.
 */
public class NewTest {
  private ObservablePizza alacarte;
  private ObservablePizza cheese;
  private ObservablePizza veggie;

  /**
   * Setting up pizzas to be tested.
   */
  @Before
  public void setUp() {
    alacarte = new AlaCartePizza.AlaCartePizzaBuilder()
            .crust(Crust.Classic)
            .size(Size.Medium)
            .addTopping(ToppingName.Cheese, ToppingPortion.Full)
            .addTopping(ToppingName.Sauce,ToppingPortion.Full)
            .addTopping(ToppingName.GreenPepper,ToppingPortion.Full)
            .addTopping(ToppingName.Onion,ToppingPortion.Full)
            .addTopping(ToppingName.Jalapeno,ToppingPortion.LeftHalf)
            .build();
    //cheese = new CheesePizza.CheeseBuilder()
    cheese = new CheesePizza.CheesePizzaBuilder()
            .crust(Crust.Thin)
            .size(Size.Large)
            .build();
    //veggie = new VeggiePizza.VeggieBuilder()
    veggie = new VeggiePizza.VeggiePizzaBuilder()
            .crust(Crust.Classic)
            .size(Size.Large)
            .build();
  }

  /**
   * Testing cost of pizza of pizzas from set up.
   */
  @Test
  public void testCost() {
    assertEquals(8.25,alacarte.cost(),0.01);
    assertEquals(9,cheese.cost(),0.01);
    assertEquals(11.5, veggie.cost(), 0.001);
  }

  /**
   * Testing medium, stuffed crust veggie pizza.
   */
  @Test
  public void testVeggieOne() {
    ObservablePizza veggie1 = new VeggiePizza.VeggiePizzaBuilder()
            .crust(Crust.Stuffed)
            .size(Size.Medium)
            .build();
    ObservablePizza veggie2 = new VeggiePizza.VeggiePizzaBuilder()
            .crust(Crust.Thin)
            .size(Size.Large)
            .build();
    assertEquals(9.5, veggie1.cost(), 0.01);
    assertEquals(11.5, veggie2.cost(), 0.01);
  }

  /**
   * Test no cheese with full sauce.
   */
  @Test
  public void testNoCheese() {
    ObservablePizza cheese1 = new CheesePizza.CheesePizzaBuilder()
            .crust(Crust.Thin)
            .size(Size.Large)
            .noCheese()
            .build();
    assertNull(cheese1.hasTopping(ToppingName.Cheese));
    assertEquals(8,cheese1.cost(),0.01);
  }

  /**
   * Test left cheese with full sauce.
   */
  @Test
  public void testLeftCheese() {
    ObservablePizza cheese = new CheesePizza.CheesePizzaBuilder()
            .crust(Crust.Thin)
            .size(Size.Medium)
            .leftHalfCheese()
            .build();
    assertEquals(6.5,cheese.cost(),0.01);
  }

  /**
   * Test right cheese with full sauce.
   */
  @Test
  public void testRightCheese() {
    ObservablePizza cheese = new CheesePizza.CheesePizzaBuilder()
            .crust(Crust.Thin)
            .size(Size.Medium)
            .rightHalfCheese()
            .build();
    assertEquals(6.5,cheese.cost(),0.01);
  }

  /**
   * Testing veggie pizza with no cheese and no sauce.
   */
  @Test
  public void testVeggieNoCheese() {
    ObservablePizza veg = new VeggiePizza.VeggiePizzaBuilder()
            .crust(Crust.Thin)
            .size(Size.Large)
            .noCheese()
            .noSauce()
            .build();
    assertNull(veg.hasTopping(ToppingName.Cheese));
    assertNull(veg.hasTopping(ToppingName.Sauce));
    assertEquals(9.5, veg.cost(),0.01);
  }

  /**
   * Testing removal of all toppings on a veggie pizza.
   */
  @Test
  public void testRemoveAllToppings() {
    ObservablePizza veg = new VeggiePizza.VeggiePizzaBuilder()
            .crust(Crust.Thin)
            .size(Size.Large)
            .noCheese()
            .noSauce()
            .noBlackOlive()
            .noGreenPepper()
            .noBlackOlive()
            .noOnion()
            .noJalapeno()
            .noTomato()
            .build();
    assertNull(veg.hasTopping(ToppingName.Cheese));
    assertNull(veg.hasTopping(ToppingName.Sauce));
    assertNull(veg.hasTopping(ToppingName.BlackOlive));
    assertNull(veg.hasTopping(ToppingName.GreenPepper));
    assertNull(veg.hasTopping(ToppingName.Onion));
    assertNull(veg.hasTopping(ToppingName.Jalapeno));
    assertNull(veg.hasTopping(ToppingName.Tomato));
    assertEquals(7,veg.cost(), 0.01);
  }

}
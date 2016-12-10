package com.acabra.mp;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;

import static org.mockito.internal.verification.VerificationModeFactory.times;

/**
 * Created by Agustin on 12/10/2016.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({Order.class, PizzaUtils.class})
public class OrderValidationTest {

    private static Map<String ,Pizza> TEST_PIZZAS =  new HashMap<String, Pizza>(){{
        put("1", new Pizza("1", 1, "1"));
        put("2", new Pizza("2", 2, "2"));
        put("3", new Pizza("3", 3, "3"));
    }};

    @Test
    public void test_for_valid_order_return_true() {
        PowerMockito.mockStatic(PizzaUtils.class);
        Mockito.when(PizzaUtils.loadPizzasFromMockFile()).thenReturn(TEST_PIZZAS);

        Order order = PizzaTestUtils.createOrder("1", "2", "Peter", "SomeStreet 3", "+485552223");
        Assert.assertTrue(OrderValidation.validate(order));

        PowerMockito.verifyStatic(times(1));
        PizzaUtils.loadPizzasFromMockFile();
    }

    @Test @Ignore
    public void test_for_invalid_order_noTelephone_return_false() {
        PowerMockito.mockStatic(PizzaUtils.class);
        Mockito.when(PizzaUtils.loadPizzasFromMockFile()).thenReturn(TEST_PIZZAS);

        Order order = PizzaTestUtils.createOrder("1", "2", "Peter", "SomeStreet 3", "   ");
        Assert.assertFalse(OrderValidation.validate(order));

        PowerMockito.verifyStatic(times(1));
        PizzaUtils.loadPizzasFromMockFile();
    }

    @Test
    public void test_for_invalid_order_wrongPizzaId_return_false() {
        PowerMockito.mockStatic(PizzaUtils.class);
        Mockito.when(PizzaUtils.loadPizzasFromMockFile()).thenReturn(TEST_PIZZAS);

        Order order = PizzaTestUtils.createOrder("4", "2", "Peter", "SomeStreet 3", "+48444");
        Assert.assertFalse(OrderValidation.validate(order));

        PowerMockito.verifyStatic(times(1));
        PizzaUtils.loadPizzasFromMockFile();
    }

    @Test(expected = InputMismatchException.class)
    public void test_errorInPizzaOrder_wrong_quantity_return_false() {
        PowerMockito.mockStatic(PizzaUtils.class);
        Mockito.when(PizzaUtils.loadPizzasFromMockFile()).thenReturn(TEST_PIZZAS);

        Order order = PizzaTestUtils.createOrder("1", "asda", "Peter", "SomeStreet 3", "+48444");

        PowerMockito.verifyStatic(times(1));
        PizzaUtils.loadPizzasFromMockFile();

        OrderValidation.validate(order);
    }

    @Test(expected = InputMismatchException.class)
    public void test_errorInPizzaOrder_wrong_quantity2_return_false() {
        PowerMockito.mockStatic(PizzaUtils.class);
        Mockito.when(PizzaUtils.loadPizzasFromMockFile()).thenReturn(TEST_PIZZAS);

        Order order = PizzaTestUtils.createOrder("1", "", "Peter", "SomeStreet 3", "+48444");

        PowerMockito.verifyStatic(times(1));
        PizzaUtils.loadPizzasFromMockFile();

        OrderValidation.validate(order);
    }

    @Test
    public void test_errorInPizzaOrder_wrong_quantity_negative_return_false() {
        PowerMockito.mockStatic(PizzaUtils.class);
        Mockito.when(PizzaUtils.loadPizzasFromMockFile()).thenReturn(TEST_PIZZAS);

        Order order = PizzaTestUtils.createOrder("1", "-1", "Peter", "SomeStreet 3", "+48444");

        PowerMockito.verifyStatic(times(1));
        PizzaUtils.loadPizzasFromMockFile();

        Assert.assertFalse(OrderValidation.validate(order));
    }

    @Test
    public void test_errorInPizzaOrder_wrong_quantity_zero_return_false() {
        PowerMockito.mockStatic(PizzaUtils.class);
        Mockito.when(PizzaUtils.loadPizzasFromMockFile()).thenReturn(TEST_PIZZAS);

        Order order = PizzaTestUtils.createOrder("1", "0", "Peter", "SomeStreet 3", "+48444");

        PowerMockito.verifyStatic(times(1));
        PizzaUtils.loadPizzasFromMockFile();

        Assert.assertFalse(OrderValidation.validate(order));
    }

    //-- Acceptance defined Tests for Order Validation
    @Test(expected = InputMismatchException.class)
    public void test_errorInPizzaOrder_return_false() {
        PowerMockito.mockStatic(PizzaUtils.class);
        Mockito.when(PizzaUtils.loadPizzasFromMockFile()).thenReturn(TEST_PIZZAS);

        Order order = PizzaTestUtils.createOrder("1", "NaN", "Peter", "SomeStreet 3", "+48444");

        PowerMockito.verifyStatic(times(1));
        PizzaUtils.loadPizzasFromMockFile();

        OrderValidation.validate(order);
    }

}

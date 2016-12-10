package com.acabra.mp;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.StrutsTestCase;
import org.junit.Assert;

/**
 * Created by Agustin on 12/10/2016.
 */
public class OrderTest extends StrutsTestCase {

    public void test_render_input_forDefaultOrder() throws Exception {
        Order order = new Order();
        String result = order.execute();
        Assert.assertTrue("Should Return success", ActionSupport.INPUT.equals(result));
    }

    public void test_should_calculate_totalOrder_for_valid_order() throws Exception {
        Order validOder = getValidOrder();

        String result = validOder.execute();

        Assert.assertTrue("Should Return Success", ActionSupport.SUCCESS.equals(result));
        Assert.assertEquals(15.0, validOder.getOrderTotal(), 0.001);

    }

    //-- Acceptance defined Tests for Order
    public void test_NominalPizzaOrder() throws Exception {
        Order nominalPizzaOrder = getNominalPrizeOrder("1", "2", "Smith", "NYC", "12345678");
        String result = nominalPizzaOrder.execute();
        Assert.assertTrue("Should Return Success", ActionSupport.SUCCESS.equals(result));
        Assert.assertEquals(10.0, nominalPizzaOrder.getOrderTotal(), 0.001);

    }

    private Order getNominalPrizeOrder(String pizzaId, String quantity, String name, String address, String telephone) {
        return PizzaTestUtils.createOrder(pizzaId, quantity, name, address, telephone);
    }


    public Order getValidOrder() {
        return PizzaTestUtils.createOrder("1", "3", "PersonsName", "Address", "+48555");
    }
}

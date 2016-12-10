package com.acabra.mp;

/**
 * Created by Agustin on 12/10/2016.
 */
public class PizzaTestUtils {

    static Order createOrder(String pizzaId, String quantity, String name, String address, String telephone) {
        Order order = new Order();
        order.setPizzaId(pizzaId);
        order.setQuantity(quantity);
        order.setName(name);
        order.setAddress(address);
        order.setTelephone(telephone);
        return order;
    }
}

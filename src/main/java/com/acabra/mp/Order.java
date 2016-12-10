package com.acabra.mp;

import com.opensymphony.xwork2.ActionSupport;

import java.util.Collection;
import java.util.InputMismatchException;
import java.util.Map;

/**
 * Created by Agustin on 12/10/2016.
 */
public class Order extends ActionSupport {

    private static final String DEFAULT_PIZZA_KEY = "1";
    private final Map<String, Pizza> pizzasMap;
    private final Collection<Pizza> pizzas;
    private boolean quantityError;

    private String quantity;
    private String name;
    private String address;
    private String telephone;
    private String pizzaId;
    private boolean orderProcessed;
    private int orderTotal;

    public Order() {
        pizzasMap = PizzaUtils.loadPizzasFromMockFile();
        pizzas = pizzasMap.values();
        pizzaId = null;
        quantity = null;
        name = null;
        address = null;
        telephone = null;
        orderProcessed = false;
        orderTotal = 0;
        quantityError = false;
    }

    @Override
    public String execute() throws Exception {
        orderProcessed = false;
        quantityError = false;
        try {
            boolean validate = OrderValidation.validate(this);
            if(validate) {
                Pizza pizza = pizzasMap.get(pizzaId);
                orderTotal = Integer.parseInt(quantity) * pizza.getPrice();
                orderProcessed = true;
                return SUCCESS;
            }
            return INPUT;
        } catch (InputMismatchException ime) {
            quantityError = true;
            return INPUT;
        }
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getDefaultPizzaValue(){
        return pizzasMap.get(DEFAULT_PIZZA_KEY).getId();
    }

    public String getPizzaId() {
        return pizzaId;
    }

    public void setPizzaId(String pizzaId) {
        this.pizzaId = pizzaId;
    }

    public int getOrderTotal() {
        return orderTotal;
    }

    public Collection<Pizza> getPizzas() {
        return pizzas;
    }

    public boolean isPizzaIdAvailable(String pizzaId) {
        return pizzasMap.containsKey(pizzaId);
    }

    public boolean getOrderProcessed() {
        return orderProcessed;
    }

    public boolean getQuantityError() {
        return quantityError;
    }
}

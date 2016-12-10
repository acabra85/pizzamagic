package com.acabra.mp;

import com.opensymphony.xwork2.ActionSupport;

import java.util.Collection;
import java.util.InputMismatchException;
import java.util.Map;

/**
 * This class represents the action Order which receives the parameters from the view
 * and validates them finally calculates the total order price to pay or an error.
 * @author Agustin Cabra.
 */
public class Order extends ActionSupport {

    private static final String DEFAULT_PIZZA_KEY = "1";

    /**
     * Map Containing the pizzas indexed by pizzaId
     */
    private final Map<String, Pizza> pizzasMap;

    /**
     * A collection representation of all pizzas available for the purpose of easy representation to view.
     */
    private final Collection<Pizza> pizzas;

    /**
     * Represents a pizzaId selected on the view
     */
    private String pizzaId;

    /**
     * Represents the amount of pizzas to order
     */
    private String quantity;

    /**
     * Customers delivery name
     */
    private String name;

    /**
     * Customers delivery address
     */
    private String address;

    /**
     * Customers telephone number
     */
    private String telephone;

    /**
     * Indicates whether the order was successfully processed or not
     */
    private boolean orderProcessed;

    /**
     * Indicates if an error related to the quantity field was detected (When the quantity is not a number)
     */
    private boolean quantityError;

    /**
     * The total amount to pay calculated as (quantity * pizzaPrice)
     */
    private int orderTotal;

    public Order() {
        pizzasMap = PizzaUtils.loadPizzasFromMockFile();
        pizzas = pizzasMap.values();
    }

    /**
     * This method calls validation for the parameters received from view
     * and calculates the total order price
     * @return String representing Strut response "success" if pizzaId and quantity are valid
     * or "input" indicating wrong input was placed.
     * @throws Exception
     */
    @Override
    public String execute() throws Exception {
        orderProcessed = false;
        quantityError = false;
        try {
            if(OrderValidation.validate(this)) {
                Pizza pizza = pizzasMap.get(pizzaId);
                orderTotal = Double.valueOf(quantity).intValue() * pizza.getPrice();
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

package com.acabra.mp;

/**
 * @author Agustin Cabra.
 */
public class Pizza {

    /**
     * Pizza Name
     */
    private final String name;

    /**
     * Pizza Price
     */
    private final int price;

    /**
     * Pizza unique Id in the system.
     */
    private final String id;

    public Pizza(String name, int price, String id) {
        this.name = name;
        this.price = price;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getId() {
        return id;
    }
}

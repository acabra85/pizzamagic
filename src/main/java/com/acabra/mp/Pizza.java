package com.acabra.mp;

/**
 * Created by Agustin on 12/10/2016.
 */
public class Pizza {
    private final String name;
    private final int price;
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

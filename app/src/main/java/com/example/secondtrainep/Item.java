package com.example.secondtrainep;

public class Item {
    private String name;
    private int imageResource;
    private int quantity;

    public Item(String name, int imageResource) {
        this.name = name;
        this.imageResource = imageResource;
        this.quantity = 0;
    }

    public String getName() {
        return name;
    }

    public int getImageResource() {
        return imageResource;
    }

    public int getQuantity() {
        return quantity;
    }

    public void incrementQuantity() {
        quantity++;
    }

    public void decrementQuantity() {
        if (quantity >0) {
            quantity--;
        }
    }
}

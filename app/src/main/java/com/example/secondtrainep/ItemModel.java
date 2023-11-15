package com.example.secondtrainep;

public class ItemModel {
    public String name;

    public int quantity;

    ItemModel(String name ) {
        this.name = name;

        this.quantity = 0;
    }

    String getName() {
        return name;
    }



    int getQuantity() {
        return quantity;
    }

    void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
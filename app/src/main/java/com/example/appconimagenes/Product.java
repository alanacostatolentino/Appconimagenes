package com.example.appconimagenes;

/**
 * Created by BFAL on 6/15/2017.
 */

public class Product {
    private String image;
    private String name;
    private String price;
    private String detalle;

    public Product(String image, String name, String price) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.detalle= detalle;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}


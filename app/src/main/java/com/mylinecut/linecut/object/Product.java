package com.mylinecut.linecut.object;

import java.util.ArrayList;

public class Product {
    private String name, description, productId;
    private ArrayList<Double> priceperunit;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Double> getPriceperunit() {
        return priceperunit;
    }

    public void setPriceperunit(ArrayList<Double> priceperunit) {
        this.priceperunit = priceperunit;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}

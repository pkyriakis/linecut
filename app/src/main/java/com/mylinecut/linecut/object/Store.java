package com.mylinecut.linecut.object;

import java.util.ArrayList;

public class Store {
    String storeid, name, street, city, postcode, mainimage;
    ArrayList<String> openninghours, deliverytype, deliverylocations_counter, deliverylocations_table;

    public Store(){

    }

    public String getStoreid() {
        return storeid;
    }

    public void setStoreid(String storeid) {
        this.storeid = storeid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getMainimage() {
        return mainimage;
    }

    public void setMainimage(String mainimage) {
        this.mainimage = mainimage;
    }

    public ArrayList<String> getOpenninghours() {
        return openninghours;
    }

    public void setOpenninghours(ArrayList<String> openninghours) {
        this.openninghours = openninghours;
    }

    public ArrayList<String> getDeliverytype() {
        return deliverytype;
    }

    public void setDeliverytype(ArrayList<String> deliverytype) {
        this.deliverytype = deliverytype;
    }

    public ArrayList<String> getDeliverylocations_counter() {
        return deliverylocations_counter;
    }

    public void setDeliverylocations_counter(ArrayList<String> deliverylocations_counter) {
        this.deliverylocations_counter = deliverylocations_counter;
    }

    public ArrayList<String> getDeliverylocations_table() {
        return deliverylocations_table;
    }

    public void setDeliverylocations_table(ArrayList<String> deliverylocations_table) {
        this.deliverylocations_table = deliverylocations_table;
    }
}

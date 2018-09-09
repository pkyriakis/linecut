package com.mylinecut.linecut.object;

import java.util.ArrayList;

public class Store {
    String storeId;
    String name;
    String description;
    String address;
    String mainimage;
    String contactPhone;
    String website;
    ArrayList<String> openninghours, deliverytype, deliverylocations_counter, deliverylocations_table;
    Double location_lat, location_long;

    public Store(){

    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public Double getLocation_lat() {
        return location_lat;
    }

    public void setLocation_lat(Double location_lat) {
        this.location_lat = location_lat;
    }

    public Double getLocation_long() {
        return location_long;
    }

    public void setLocation_long(Double location_long) {
        this.location_long = location_long;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

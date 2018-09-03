package com.mylinecut.linecut.object;

import java.util.ArrayList;

public class User {
    private String uid;
    private String firstname;
    private String lastname;
    private String email;
    private String birthDay;
    private String phone;
    private Integer phoneVerCode;
    private String type;
    private ArrayList<String> favoriteStoresIDs;

    public User(){

    }

    public ArrayList<String> getFavoriteStoresIDs() {
        return favoriteStoresIDs;
    }

    public void setFavoriteStoresIDs(ArrayList<String> favoriteStoresIDs) {
        this.favoriteStoresIDs = favoriteStoresIDs;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPhoneVerCode() {
        return phoneVerCode;
    }

    public void setPhoneVerCode(Integer phoneVerCode) {
        this.phoneVerCode = phoneVerCode;
    }

    public void removeStoreFromFav(String sid){this.favoriteStoresIDs.remove(sid);}

    public void addStoreToFav(String sid){this.favoriteStoresIDs.add(sid);}
}

package com.mylinecut.linecut.object;

import android.util.Log;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;

import javax.annotation.Nullable;

public class User {
    private String userId;
    private String firstname;
    private String lastname;
    private String email;
    private String birthDay;
    private String phone;
    private Integer phoneVerCode;
    private String type;
    private ArrayList<String> favoriteStoresIDs;
    private ArrayList<String> favoriteProductsIDs;

    public User(){
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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


    public ArrayList<String> getFavoriteProductsIDs() {
        return favoriteProductsIDs;
    }

    public void setFavoriteProductsIDs(ArrayList<String> favoriteProductsIDs) {
        this.favoriteProductsIDs = favoriteProductsIDs;
    }

    public void setFavoriteStoresIDs(ArrayList<String> favoriteStoresIDs) {
        this.favoriteStoresIDs = favoriteStoresIDs;
    }

    public ArrayList<String> getFavoriteStoresIDs() {
        return favoriteStoresIDs;
    }

    public void removeStoreFromFav(String sid){this.favoriteStoresIDs.remove(sid);}

    public void addStoreToFav(String sid){this.favoriteStoresIDs.add(sid);}

    public void removeProductFromFav(String pid){this.favoriteProductsIDs.remove(pid);}

    public void addProductToFav(String pid){this.favoriteProductsIDs.add(pid);}

}

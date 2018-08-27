package com.panaceasoft.awesomematerial.object;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Panacea-Soft on 5/8/18.
 * Contact Email : teamps.is.cool@gmail.com
 */


public class MainViewObject {
    @SerializedName("id")
    public String id;

    @SerializedName("name")
    public String name;

    @SerializedName("image_name")
    public String imageName;

    @SerializedName("is_new")
    public String isNew;

    @SerializedName("second_level_objects")
    public List<MainViewSecondLevelObject> secondLevelObjectList;

    public MainViewObject(String id, String name, List<MainViewSecondLevelObject> secondLevelObjectList) {
        this.id = id;
        this.name = name;
        this.secondLevelObjectList = secondLevelObjectList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MainViewSecondLevelObject> getSecondLevelObjectList() {
        return secondLevelObjectList;
    }

}

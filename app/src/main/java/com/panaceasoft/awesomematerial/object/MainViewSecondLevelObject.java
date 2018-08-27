package com.panaceasoft.awesomematerial.object;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Panacea-Soft on 5/8/18.
 * Contact Email : teamps.is.cool@gmail.com
 */


public class MainViewSecondLevelObject {

    @SerializedName("id")
    public String id;

    @SerializedName("name")
    public String name;

    @SerializedName("image_name")
    public String imageName;

    @SerializedName("is_new")
    public String isNew;

    @SerializedName("third_level_objects")
    public List<MainViewThirdLevelObject> thirdLevelObjectList;

    public MainViewSecondLevelObject(String id, String name, List<MainViewThirdLevelObject> thirdLevelObjectList) {
        this.id = id;
        this.name = name;
        this.thirdLevelObjectList = thirdLevelObjectList;
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

    public List<MainViewThirdLevelObject> getThirdLevelObjectList() {
        return thirdLevelObjectList;
    }

}

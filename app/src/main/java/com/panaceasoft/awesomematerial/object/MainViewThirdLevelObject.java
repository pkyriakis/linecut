package com.panaceasoft.awesomematerial.object;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Panacea-Soft on 5/8/18.
 * Contact Email : teamps.is.cool@gmail.com
 */


public class MainViewThirdLevelObject {

    @SerializedName("id")
    public String id;

    @SerializedName("name")
    public String name;

    @SerializedName("image_name")
    public String imageName;

    @SerializedName("is_new")
    public String isNew;

    public MainViewThirdLevelObject() {}
    public MainViewThirdLevelObject(String id, String name) {
        this.id = id;
        this.name = name;
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
}

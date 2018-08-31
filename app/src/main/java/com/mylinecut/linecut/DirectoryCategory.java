package com.mylinecut.linecut;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Panacea-Soft on 6/10/18.
 * Contact Email : teamps.is.cool@gmail.com
 */


public class DirectoryCategory {

    @SerializedName("id")
    public String id;

    @SerializedName("name")
    public String name;

    @SerializedName("desc")
    public String desc;

    @SerializedName("image")
    public String image;

    public DirectoryCategory(String id, String name, String desc, String image) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.image = image;
    }

}

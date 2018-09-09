package com.mylinecut.linecut.object;

import java.util.ArrayList;

public class ProductCategory {
    private String comment, description, name,
            serveddays, servedhours, categoryId;
    ArrayList<String> unitlabels;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getServeddays() {
        return serveddays;
    }

    public void setServeddays(String serveddays) {
        this.serveddays = serveddays;
    }

    public String getServedhours() {
        return servedhours;
    }

    public void setServedhours(String servedhours) {
        this.servedhours = servedhours;
    }

    public ArrayList<String> getUnitlabels() {
        return unitlabels;
    }

    public void setUnitlabels(ArrayList<String> unitlabels) {
        this.unitlabels = unitlabels;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}

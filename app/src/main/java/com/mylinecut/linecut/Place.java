package com.mylinecut.linecut;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Place {

    @SerializedName("name")
    public String name;

    @SerializedName("image")
    public String imageName;

    @SerializedName("logo_image")
    public String logoImage;

    @SerializedName("type")
    public String type;

    @SerializedName("city")
    public String city;

    @SerializedName("rating_count")
    public String ratingCount;

    @SerializedName("total_rating")
    public String totalRating;

    @SerializedName("distance")
    public String distance;

    @SerializedName("discount")
    public String discount;

    @SerializedName("total_like")
    public String totalLike;

    @SerializedName("total_comment")
    public String totalComment;

    @SerializedName("lat")
    public String lat;

    @SerializedName("lng")
    public String lng;

    @SerializedName("total_review")
    public String totalReview;

    @SerializedName("opening")
    public String opening;

    @SerializedName("booking_time")
    public String bookingTime;

    @SerializedName("address")
    public String address;

    @SerializedName("website")
    public String website;

    @SerializedName("phone")
    public String phone;

    @SerializedName("email")
    public String email;

    @SerializedName("desc")
    public String desc;

    @SerializedName("marker_color")
    public String markerColor;

    @SerializedName("image_list")
    public List<Place.Image> imageList;

    public Place(String name, String imageName, String type, String city, String ratingCount, String totalRating, String distance, String discount, String totalLike, String totalComment, String lat, String lng, String totalReview, String opening, String bookingTime, String address, String website, String phone, String email, String desc, String markerColor, List<Image> imageList) {
        this.name = name;
        this.imageName = imageName;
        this.type = type;
        this.city = city;
        this.ratingCount = ratingCount;
        this.totalRating = totalRating;
        this.distance = distance;
        this.discount = discount;
        this.totalLike = totalLike;
        this.totalComment = totalComment;
        this.lat = lat;
        this.lng = lng;
        this.totalReview = totalReview;
        this.opening = opening;
        this.bookingTime = bookingTime;
        this.address = address;
        this.website = website;
        this.phone = phone;
        this.email = email;
        this.desc = desc;
        this.markerColor = markerColor;
        this.imageList = imageList;
    }

    public Place(String name, String imageName, String ratingCount, String totalRating) {
        this.name = name;
        this.imageName = imageName;
        this.ratingCount = ratingCount;
        this.totalRating = totalRating;
    }

    public class Image {
        @SerializedName("image_name")
        public String imageName;
    }

//    public String getName() {
//        return name;
//    }
//
//    public String getImageName() {
//        return imageName;
//    }
//
//    public String getLogoImage() {
//        return logoImage;
//    }
//
//    public String getType() {
//        return type;
//    }
//
//    public String getCity() {
//        return city;
//    }
//
//    public String getRatingCount() {
//        return ratingCount;
//    }
//
//    public String getTotalRating() {
//        return totalRating;
//    }
//
//    public String getDistance() {
//        return distance;
//    }
//
//    public String getDiscount() {
//        return discount;
//    }
//
//    public String getTotalLike() {
//        return totalLike;
//    }
//
//    public String getTotalComment() {
//        return totalComment;
//    }
//
//    public String getLat() {
//        return lat;
//    }
//
//    public String getLng() {
//        return lng;
//    }
//
//    public String getTotalReview() {
//        return totalReview;
//    }
//
//    public String getOpening() {
//        return opening;
//    }
//
//    public String getBookingTime() {
//        return bookingTime;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public String getWebsite() {
//        return website;
//    }
//
//    public String getPhone() {
//        return phone;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public String getDesc() {
//        return desc;
//    }
//
//    public String getMarkerColor() {
//        return markerColor;
//    }

    public List<Image> getImageList() {
        return imageList;
    }

//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setImageName(String imageName) {
//        this.imageName = imageName;
//    }
//
//    public void setRatingCount(String ratingCount) {
//        this.ratingCount = ratingCount;
//    }
//
//    public void setTotalRating(String totalRating) {
//        this.totalRating = totalRating;
//    }
}

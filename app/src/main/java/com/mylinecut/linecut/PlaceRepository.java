package com.mylinecut.linecut;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mylinecut.linecut.Place;

import java.util.ArrayList;

/**
 * Created by Panacea-Soft on 31/5/18.
 * Contact Email : teamps.is.cool@gmail.com
 * Website : http://www.panacea-soft.com
 */
public class PlaceRepository {

    public static Place getPlace() {

        ArrayList<Place> placeArrayList = new Gson().fromJson(json, new  TypeToken<ArrayList<Place>>(){}.getType());

        return placeArrayList.get(0);
    }

    public static ArrayList<Place> getPlaceList() {
        return new Gson().fromJson(json, new  TypeToken<ArrayList<Place>>(){}.getType());
    }

    static String json = "[\n" +
            "  {\n" +
            "    \"id\":\"GardensByTheBay\",\n" +
            "    \"name\":\"Gardens By the Bay\",\n" +
            "    \"city\":\"Singapore\",\n" +
            "    \"type\":\"Gardens\",\n" +
            "    \"image\":\"sg_gardens_by_the_bay_singapore\",\n" +
            "    \"logo_image\":\"dir_shop_logo_1\",\n" +
            "    \"rating_count\":\"80\",\n" +
            "    \"total_rating\":\"5.0\",\n" +
            "    \"distance\":\"1.4 km\",\n" +
            "    \"discount\":\"10\",\n" +
            "    \"total_like\":\"100\",\n" +
            "    \"total_comment\":\"5\",\n" +
            "    \"total_review\":\"80\",\n" +
            "    \"lat\":\"1.283424\",\n" +
            "    \"lng\":\"103.860631\",\n" +
            "    \"opening\":\"09:00 - 21:00, Open Now\",\n" +
            "    \"booking_time\":\"Friday, 10th October, 7:30 PM\",\n" +
            "    \"website\":\"http://www.panacea-soft.com\",\n" +
            "    \"phone\":\"73737373\",\n" +
            "    \"email\":\"teamps.is.cool@gmail.com\",\n" +
            "    \"desc\":\"Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.\",\n" +
            "    \"address\":\"824 High Point Street Ottawa, IL 61350\",\n" +
            "    \"marker_color\":\"#D50000\",\n" +
            "    \"image_list\":[\n" +
            "      {\n" +
            "        \"image_name\":\"sg_gardens_by_the_bay_singapore\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"image_name\":\"sg_zoo\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"image_name\":\"sg_orchard_garden\"\n" +
            "      }\n" +
            "    ]\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\":\"SingaporeZoo\",\n" +
            "    \"name\":\"Singapore Zoo\",\n" +
            "    \"city\":\"Singapore\",\n" +
            "    \"type\":\"Parks\",\n" +
            "    \"image\":\"sg_zoo\",\n" +
            "    \"logo_image\":\"dir_shop_logo_2\",\n" +
            "    \"rating_count\":\"90\",\n" +
            "    \"total_rating\":\"3.5\",\n" +
            "    \"distance\":\"2.4 km\",\n" +
            "    \"discount\":\"0\",\n" +
            "    \"total_like\":\"80\",\n" +
            "    \"total_comment\":\"2\",\n" +
            "    \"total_review\":\"80\",\n" +
            "    \"lat\":\"1.405314\",\n" +
            "    \"lng\":\"103.788753\",\n" +
            "    \"opening\":\"09:00 - 21:00, Open Now\",\n" +
            "    \"booking_time\":\"Friday, 10th October, 7:30 PM\",\n" +
            "    \"website\":\"http://www.panacea-soft.com\",\n" +
            "    \"phone\":\"73737373\",\n" +
            "    \"email\":\"teamps.is.cool@gmail.com\",\n" +
            "    \"desc\":\"Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse\",\n" +
            "    \"address\":\"824 High Point Street Ottawa, IL 61350\",\n" +
            "    \"marker_color\":\"#512DA8\",\n" +
            "    \"image_list\":[\n" +
            "      {\n" +
            "        \"image_name\":\"sg_zoo\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"image_name\":\"sg_gardens_by_the_bay_singapore\"\n" +
            "      }\n" +
            "    ]\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\":\"NationalOrchidGarden\",\n" +
            "    \"name\":\"National Orchid Garden\",\n" +
            "    \"city\":\"Singapore\",\n" +
            "    \"type\":\"Gardens\",\n" +
            "    \"image\":\"sg_orchard_garden\",\n" +
            "    \"logo_image\":\"dir_shop_logo_3\",\n" +
            "    \"rating_count\":\"50\",\n" +
            "    \"total_rating\":\"4.5\",\n" +
            "    \"distance\":\"1.5 km\",\n" +
            "    \"discount\":\"50\",\n" +
            "    \"total_like\":\"70\",\n" +
            "    \"total_comment\":\"10\",\n" +
            "    \"total_review\":\"80\",\n" +
            "    \"lat\":\"1.311866\",\n" +
            "    \"lng\":\"103.814687\",\n" +
            "    \"opening\":\"09:00 - 21:00, Open Now\",\n" +
            "    \"booking_time\":\"Friday, 10th October, 7:30 PM\",\n" +
            "    \"website\":\"http://www.panacea-soft.com\",\n" +
            "    \"phone\":\"73737373\",\n" +
            "    \"email\":\"teamps.is.cool@gmail.com\",\n" +
            "    \"desc\":\"Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse\",\n" +
            "    \"address\":\"824 High Point Street Ottawa, IL 61350\",\n" +
            "    \"marker_color\":\"#1976D2\",\n" +
            "    \"image_list\":[\n" +
            "      {\n" +
            "        \"image_name\":\"sg_orchard_garden\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"image_name\":\"sg_gardens_by_the_bay_singapore\"\n" +
            "      }\n" +
            "    ]\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\":\"ClarkeQuey\",\n" +
            "    \"name\":\"Clarke Quey\",\n" +
            "    \"type\":\"Points of Interest\",\n" +
            "    \"city\":\"Singapore\",\n" +
            "    \"image\":\"sg_clarke_quay\",\n" +
            "    \"logo_image\":\"dir_shop_logo_4\",\n" +
            "    \"rating_count\":\"100\",\n" +
            "    \"total_rating\":\"5.0\",\n" +
            "    \"distance\":\"3.6 km\",\n" +
            "    \"discount\":\"20\",\n" +
            "    \"total_like\":\"150\",\n" +
            "    \"total_comment\":\"80\",\n" +
            "    \"total_review\":\"80\",\n" +
            "    \"lat\":\"1.290656\",\n" +
            "    \"lng\":\"103.846270\",\n" +
            "    \"opening\":\"09:00 - 21:00, Open Now\",\n" +
            "    \"booking_time\":\"Friday, 10th October, 7:30 PM\",\n" +
            "    \"website\":\"http://www.panacea-soft.com\",\n" +
            "    \"phone\":\"73737373\",\n" +
            "    \"email\":\"teamps.is.cool@gmail.com\",\n" +
            "    \"desc\":\"Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse\",\n" +
            "    \"address\":\"824 High Point Street Ottawa, IL 61350\",\n" +
            "    \"marker_color\":\"#1976D2\",\n" +
            "    \"image_list\":[\n" +
            "      {\n" +
            "        \"image_name\":\"sg_clarke_quay\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"image_name\":\"sg_gardens_by_the_bay_singapore\"\n" +
            "      }\n" +
            "    ]\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\":\"SentosaIsland\",\n" +
            "    \"name\":\"Sentosa Island\",\n" +
            "    \"city\":\"Singapore\",\n" +
            "    \"type\":\"Landmarks\",\n" +
            "    \"image\":\"sg_sentosa\",\n" +
            "    \"logo_image\":\"dir_shop_logo_5\",\n" +
            "    \"rating_count\":\"250\",\n" +
            "    \"total_rating\":\"4.0\",\n" +
            "    \"distance\":\"1.4 km\",\n" +
            "    \"total_like\":\"210\",\n" +
            "    \"total_comment\":\"50\",\n" +
            "    \"total_review\":\"80\",\n" +
            "    \"lat\":\"1.252010\",\n" +
            "    \"lng\":\"103.829873\",\n" +
            "    \"opening\":\"09:00 - 21:00, Open Now\",\n" +
            "    \"booking_time\":\"Friday, 10th October, 7:30 PM\",\n" +
            "    \"website\":\"http://www.panacea-soft.com\",\n" +
            "    \"phone\":\"73737373\",\n" +
            "    \"email\":\"teamps.is.cool@gmail.com\",\n" +
            "    \"desc\":\"Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse\",\n" +
            "    \"address\":\"824 High Point Street Ottawa, IL 61350\",\n" +
            "    \"marker_color\":\"#D50000\",\n" +
            "    \"image_list\":[\n" +
            "      {\n" +
            "        \"image_name\":\"sg_sentosa\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"image_name\":\"sg_gardens_by_the_bay_singapore\"\n" +
            "      }\n" +
            "    ]\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\":\"JurongBirdPark\",\n" +
            "    \"name\":\"Jurong Bird Park\",\n" +
            "    \"city\":\"Singapore\",\n" +
            "    \"type\":\"Parks\",\n" +
            "    \"image\":\"sg_jurong_bird_park\",\n" +
            "    \"logo_image\":\"dir_shop_logo_6\",\n" +
            "    \"rating_count\":\"150\",\n" +
            "    \"total_rating\":\"4.0\",\n" +
            "    \"distance\":\"0.3 km\",\n" +
            "    \"discount\":\"30\",\n" +
            "    \"total_like\":\"10\",\n" +
            "    \"total_comment\":\"1\",\n" +
            "    \"total_review\":\"80\",\n" +
            "    \"opening\":\"09:00 - 21:00, Open Now\",\n" +
            "    \"booking_time\":\"Friday, 10th October, 7:30 PM\",\n" +
            "    \"website\":\"http://www.panacea-soft.com\",\n" +
            "    \"phone\":\"73737373\",\n" +
            "    \"email\":\"teamps.is.cool@gmail.com\",\n" +
            "    \"desc\":\"Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.\",\n" +
            "    \"address\": \"824 High Point Street Ottawa, IL 61350\",\n" +
            "    \"lat\":\"1.319028\",\n" +
            "    \"lng\":\"103.706570\",\n" +
            "    \"marker_color\":\"#512DA8\",\n" +
            "    \"image_list\":[\n" +
            "      {\n" +
            "        \"image_name\":\"sg_jurong_bird_park\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"image_name\":\"sg_gardens_by_the_bay_singapore\"\n" +
            "      }\n" +
            "    ]\n" +
            "  }\n" +
            "]";
    /*
    static String json = "[\n" +
            "  {\n" +
            "    \"id\":\"GardensByTheBay\",\n" +
            "    \"name\":\"Gardens By the Bay\",\n" +
            "    \"city\":\"Singapore\",\n" +
            "    \"type\":\"Gardens\",\n" +
            "    \"image\":\"sg_gardens_by_the_bay_singapore\",\n" +
            "    \"image2\":\"sg_zoo\",\n" +
            "    \"image3\":\"sg_orchard_garden\",\n" +
            "    \"rating_count\":\"80\",\n" +
            "    \"total_rating\":\"5.0\",\n" +
            "    \"distance\":\"1.4 km\",\n" +
            "    \"discount\":\"10\",\n" +
            "    \"total_like\":\"100\",\n" +
            "    \"total_comment\":\"5\",\n" +
            "    \"total_review\":\"80\",\n" +
            "    \"opening\":\"09:00 - 21:00, Open Now\",\n" +
            "    \"booking_time\":\"Friday, 10th October, 7:30 PM\"," +
            "    \"website\": \"http://www.panacea-soft.com\",\n" +
            "    \"phone\": \"73737373\",\n" +
            "    \"email\": \"teamps.is.cool@gmail.com\",\n" +
            "    \"desc\": \"Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.\",\n"+
            "    \"address\": \"824 High Point Street Ottawa, IL 61350\",\n" +
            "    \"cmt1_name\":\"Coco’s Cafe\",\n" +
            "    \"cmt1_info\":\"West University, Cafe\",\n" +
            "    \"cmt1_comment\":\"Very Nice Cafe. Try Guys.\",\n" +
            "    \"cmt1_date\":\"Sep 4, 2017\",\n" +
            "    \"cmt2_name\":\"PanaceSoft\",\n" +
            "    \"cmt2_info\":\"web and mobile development, Yangon\",\n" +
            "    \"cmt2_comment\":\"Lorem ipsum dolor sit amet\",\n" +
            "    \"cmt2_date\":\"Jun 4, 2018\",\n" +
            "    \"lat\" : \"1.283424\",\n" +
            "    \"lng\" : \"103.860631\",\n" +
            "    \"marker_color\" : \"#D50000\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\":\"SingaporeZoo\",\n" +
            "    \"name\":\"Singapore Zoo\",\n" +
            "    \"city\":\"Singapore\",\n" +
            "    \"type\":\"Parks\",\n" +
            "    \"image\":\"sg_zoo\",\n" +
            "    \"rating_count\":\"90\",\n" +
            "    \"total_rating\":\"3.5\",\n" +
            "    \"distance\":\"2.4 km\",\n" +
            "    \"discount\":\"0\",\n" +
            "    \"total_like\":\"80\",\n" +
            "    \"total_comment\":\"2\",\n" +
            "    \"lat\" : \"1.405314\",\n" +
            "    \"lng\" : \"103.788753\",\n" +
            "    \"website\" : \"http://www.panacea-soft.com\",\n" +
            "    \"email\" : \"teamps.is.cool@gmail.com\",\n" +
            "    \"desc\" : \"Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse\",\n" +
            "    \"address\" : \"824 High Point Street Ottawa, IL 61350\",\n" +
            "    \"marker_color\" : \"#512DA8\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\":\"NationalOrchidGarden\",\n" +
            "    \"name\":\"National Orchid Garden\",\n" +
            "    \"city\":\"Singapore\",\n" +
            "    \"type\":\"Gardens\",\n" +
            "    \"image\":\"sg_orchard_garden\",\n" +
            "    \"rating_count\":\"50\",\n" +
            "    \"total_rating\":\"4.5\",\n" +
            "    \"distance\":\"1.5 km\",\n" +
            "    \"discount\":\"50\",\n" +
            "    \"total_like\":\"70\",\n" +
            "    \"total_comment\":\"10\",\n" +
            "    \"lat\" : \"1.311866\",\n" +
            "    \"lng\" : \"103.814687\",\n" +
            "    \"website\" : \"http://www.panacea-soft.com\",\n" +
            "    \"email\" : \"teamps.is.cool@gmail.com\",\n" +
            "    \"desc\" : \"Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse\",\n" +
            "    \"address\" : \"824 High Point Street Ottawa, IL 61350\",\n" +
            "    \"marker_color\" : \"#1976D2\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\":\"ClarkeQuey\",\n" +
            "    \"name\":\"Clarke Quey\",\n" +
            "    \"type\":\"Points of Interest\",\n" +
            "    \"city\":\"Singapore\",\n" +
            "    \"image\":\"sg_clarke_quay\",\n" +
            "    \"rating_count\":\"100\",\n" +
            "    \"total_rating\":\"5.0\",\n" +
            "    \"distance\":\"3.6 km\",\n" +
            "    \"discount\":\"20\",\n" +
            "    \"total_like\":\"150\",\n" +
            "    \"total_comment\":\"80\",\n" +
            "    \"lat\" : \"1.290656\",\n" +
            "    \"lng\" : \"103.846270\",\n" +
            "    \"website\" : \"http://www.panacea-soft.com\",\n" +
            "    \"email\" : \"teamps.is.cool@gmail.com\",\n" +
            "    \"desc\" : \"Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse\",\n" +
            "    \"address\" : \"824 High Point Street Ottawa, IL 61350\",\n" +
            "    \"marker_color\" : \"#1976D2\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\":\"SentosaIsland\",\n" +
            "    \"name\":\"Sentosa Island\",\n" +
            "    \"city\":\"Singapore\",\n" +
            "    \"type\":\"Landmarks\",\n" +
            "    \"image\":\"sg_sentosa\",\n" +
            "    \"rating_count\":\"250\",\n" +
            "    \"total_rating\":\"4.0\",\n" +
            "    \"distance\":\"1.4 km\",\n" +
            "    \"total_like\":\"210\",\n" +
            "    \"total_comment\":\"50\",\n" +
            "    \"lat\" : \"1.252010\",\n" +
            "    \"lng\" : \"103.829873\",\n" +
            "    \"website\" : \"http://www.panacea-soft.com\",\n" +
            "    \"email\" : \"teamps.is.cool@gmail.com\",\n" +
            "    \"desc\" : \"Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse\",\n" +
            "    \"address\" : \"824 High Point Street Ottawa, IL 61350\",\n" +
            "    \"marker_color\" : \"#D50000\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\":\"JurongBirdPark\",\n" +
            "    \"name\":\"Jurong Bird Park\",\n" +
            "    \"city\":\"Singapore\",\n" +
            "    \"type\":\"Parks\",\n" +
            "    \"image\":\"sg_jurong_bird_park\",\n" +
            "    \"rating_count\":\"150\",\n" +
            "    \"total_rating\":\"4.0\",\n" +
            "    \"distance\":\"0.3 km\",\n" +
            "    \"discount\":\"30\",\n" +
            "    \"total_like\":\"10\",\n" +
            "    \"total_comment\":\"1\",\n" +
            "    \"total_review\":\"80\",\n" +
            "    \"website\": \"http://www.panacea-soft.com\",\n" +
            "    \"phone\": \"73737373\",\n" +
            "    \"email\": \"teamps.is.cool@gmail.com\",\n" +
            "    \"desc\": \"Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.\",\n"+
            "    \"address\": \"824 High Point Street Ottawa, IL 61350\",\n" +
            "    \"lat\" : \"1.319028\",\n" +
            "    \"lng\" : \"103.706570\",\n" +
            "     \"marker_color\" : \"#512DA8\"\n" +
            "  }\n" +
            "]";*/
}

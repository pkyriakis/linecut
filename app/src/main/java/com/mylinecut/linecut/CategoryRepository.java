package com.mylinecut.linecut;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mylinecut.linecut.DirectoryCategory;

import java.util.List;

/**
 * Created by Panacea-Soft on 6/10/18.
 * Contact Email : teamps.is.cool@gmail.com
 */


public class CategoryRepository {

    public static List<DirectoryCategory> getCategoryList() {
        return new Gson().fromJson(json, new TypeToken<List<DirectoryCategory>>() {
        }.getType());
    }

    private static String json = "[\n" +
            "  {\n" +
            "    \"id\":\"Breakfast\",\n" +
            "    \"name\":\"Breakfast\",\n" +
            "    \"desc\":\"Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo\",\n" +
            "    \"image\":\"dir_cat_1\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\":\"Lunch\",\n" +
            "    \"name\":\"Lunch\",\n" +
            "    \"desc\":\"Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo\",\n" +
            "    \"image\":\"dir_cat_2\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\":\"Dinner\",\n" +
            "    \"name\":\"Dinner\",\n" +
            "    \"desc\":\"Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo\",\n" +
            "    \"image\":\"dir_cat_3\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\":\"CoffeeAndTea\",\n" +
            "    \"name\":\"Coffee & Tea\",\n" +
            "    \"desc\":\"Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo\",\n" +
            "    \"image\":\"dir_cat_4\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\":\"NightLife\",\n" +
            "    \"name\":\"Nightlife\",\n" +
            "    \"desc\":\"Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo\",\n" +
            "    \"image\":\"dir_cat_5\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\":\"ThingsToDo\",\n" +
            "    \"name\":\"Things To Do\",\n" +
            "    \"desc\":\"Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo\",\n" +
            "    \"image\":\"dir_cat_6\"\n" +
            "  }\n" +
            "]";


}

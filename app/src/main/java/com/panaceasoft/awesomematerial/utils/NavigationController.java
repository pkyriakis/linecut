package com.panaceasoft.awesomematerial.utils;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by Panacea-Soft on 5/10/18.
 * Contact Email : teamps.is.cool@gmail.com
 */


public class NavigationController {

    public static void openActivity(Activity activity, Intent intent) {
        if(activity != null) {
            activity.startActivity(intent);
        }
    }

}

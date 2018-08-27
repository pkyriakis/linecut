package com.panaceasoft.awesomematerial;

import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

/**
 * Created by Panacea-Soft on 7/10/18.
 * Contact Email : teamps.is.cool@gmail.com
 */

public class AwesomeMaterials extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        MultiDex.install(this);

//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            // This process is dedicated to LeakCanary for heap analysis.
//            // You should not init your app in this process.
//            return;
//        }
//
//        LeakCanary.install(this);
//
//        setupLeakCanary();

    }

//    protected RefWatcher setupLeakCanary() {
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            return RefWatcher.DISABLED;
//        }
//
//        ExcludedRefs excludedRefs = AndroidExcludedRefs.createAppDefaults()
//                .instanceField("android.view.inputmethod.InputMethodManager", "sInstance")
//                .instanceField("android.view.inputmethod.InputMethodManager", "mLastSrvView")
//                .instanceField("com.android.internal.policy.PhoneWindow$DecorView", "mContext")
//                .instanceField("android.support.v7.widget.SearchView$SearchAutoComplete", "mContext")
//                .build();
//
//        return LeakCanary.refWatcher(this).excludedRefs(excludedRefs).buildAndInstall();
//                //.install(this);
//    }

}


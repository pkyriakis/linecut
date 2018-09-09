package com.mylinecut.linecut.activity.client;

import android.graphics.PorterDuff;
import android.net.Uri;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.gson.Gson;
import com.mylinecut.linecut.fragment.client.ViewStoreMenuFragment;
import com.mylinecut.linecut.object.Product;
import com.mylinecut.linecut.object.ProductCategory;
import com.mylinecut.linecut.object.Store;
import com.mylinecut.linecut.object.User;
import com.panaceasoft.awesomematerial.utils.Utils;
import com.mylinecut.linecut.R;
import com.panaceasoft.awesomematerial.utils.common_adapter.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Shows the menu of the store using a collapsable appbar and a
 * scrollable tablayout. user and store obejects need to be
 * pass through gson
 * */
public class ViewStoreMenuActivity extends AppCompatActivity
            implements ViewStoreMenuFragment.OnFragmentInteractionListener{

    private User user;
    private Store store;
    private FirebaseFirestore mDatabase;

    ViewPager viewPager;
    TabLayout tabLayout;

    HashMap<String, ProductCategory> cateogyNameProductCategoryHashMap;

    // map containing the category name as key
    // and the product list corresponding to that category
    HashMap<String, ArrayList<Product>> categoryNameProductHashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_store_menu);

        // Get user and store object passed from the signup activity
        Gson gson = new Gson();
        user = gson.fromJson(getIntent().getStringExtra("userJson"), User.class);
        store = gson.fromJson(getIntent().getStringExtra("storeJson"), Store.class);

        getProductList();

        setupUI();
    }

    public void onFragmentInteraction(Uri uri){

    }
    /*
    * Gets products from firestore and saves them to map
    * */
    private void getProductList(){
        // init
        mDatabase = FirebaseFirestore.getInstance();
        categoryNameProductHashMap = new HashMap<>();
        cateogyNameProductCategoryHashMap = new HashMap<>();

        // get category list
        mDatabase.collection("products/" + store.getStoreId() + "/categorylist").get().addOnCompleteListener(task -> {
           if (task.isSuccessful()){
               // loop over all categories
               for (QueryDocumentSnapshot categoryDoc :task.getResult()) {
                   // get category from document snapshot
                   ProductCategory category = categoryDoc.toObject(ProductCategory.class);
                   // set the category id
                   category.setCategoryId(categoryDoc.getId());
                   // put it into the hashmap using category name as key
                   cateogyNameProductCategoryHashMap.put(category.getName(), category);
                   // get the path to productslist
                   String pro_path = "products/" + store.getStoreId() + "/categorylist/" + category.getCategoryId() + "/productslist";
                   // get products on the above path
                   mDatabase.collection(pro_path).get().addOnCompleteListener(task1 -> {
                       if (task1.isSuccessful()){
                           ArrayList<Product> productList = new ArrayList<>();
                           // loop over all product under this catefory
                           for(QueryDocumentSnapshot productDoc : task1.getResult()){
                               // get product object
                               Product product = productDoc.toObject(Product.class);
                               // set product id
                               product.setProductId(productDoc.getId());
                               // add to list
                               productList.add(product);
                           }
                           //put the category and the product list to map
                           categoryNameProductHashMap.put(category.getName(),productList);
                           // create the tab layout
                           setupViewPager();
                       }else{
                           Log.e("Task", "getProductList: " + task1.getException().toString());
                       }
                   });
               }
           }else{
               Log.e("Task", "getProductList: " + task.getException().toString());
           }
        });

    }

    // setup the page adapter for the tab layout
    private void setupViewPager() {
        Gson json = new Gson();
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        // iterate over all categories, convert objects to json and create a new
        // fragment for each category adding categoty and productlist objects as arguments
        for (String categoryName : categoryNameProductHashMap.keySet()){
            ArrayList<Product> productList = categoryNameProductHashMap.get(categoryName);
            // use the static new instance method to pass the productlist
            ViewStoreMenuFragment fragment = ViewStoreMenuFragment.newInstance(user, cateogyNameProductCategoryHashMap.get(categoryName),productList);
            adapter.addFragment(fragment, categoryName);
        }
        viewPager.setAdapter(adapter);
    }

    //region setup UI
    private void setupUI() {

        // toolbar region
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_black_24);

        if(toolbar.getNavigationIcon() != null) {
            toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.md_white_1000), PorterDuff.Mode.SRC_ATOP);
        }
        toolbar.setTitle(store.getName() + " Menu");

        try {
            toolbar.setTitleTextColor(getResources().getColor(R.color.md_white_1000));
        }catch (Exception e){
            Log.e("TEAMPS","Can't set color.");
        }
        try {
            setSupportActionBar(toolbar);
        }catch (Exception e) {
            Log.e("TEAMPS","Error in set support action bar.");
        }

        try {
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        }catch (Exception e) {
            Log.e("TEAMPS","Error in set display home as up enabled.");
        }

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingToolbar);

        if (Utils.isRTL()) {
            collapsingToolbarLayout.setCollapsedTitleGravity(Gravity.END | Gravity.BOTTOM);
            collapsingToolbarLayout.setExpandedTitleGravity(Gravity.END | Gravity.BOTTOM);
        } else {
            collapsingToolbarLayout.setCollapsedTitleGravity(Gravity.START);
            collapsingToolbarLayout.setExpandedTitleGravity(Gravity.START | Gravity.BOTTOM);
        }

        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);


    }

}

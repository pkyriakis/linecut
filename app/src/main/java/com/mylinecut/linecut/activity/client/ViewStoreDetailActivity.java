package com.mylinecut.linecut.activity.client;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.mylinecut.linecut.R;
import com.mylinecut.linecut.object.Store;

import com.google.gson.*;
import com.mylinecut.linecut.object.User;
import com.panaceasoft.awesomematerial.utils.Utils;

import java.util.ArrayList;

public class ViewStoreDetailActivity extends AppCompatActivity implements OnMapReadyCallback {

    final long MAX_SIZE = 1024*1024;

    // data
    Store store;
    String storeid;
    User user;
    FirebaseFirestore mDatabase;
    FirebaseStorage mStorage;
    // Map
    GoogleMap googleMap;

    // ui
    MapView mapView;
    TextView placeNameTextView;
    TextView phoneTextView;
    TextView websiteTextView;
    TextView openingTimeTextView;
    TextView addressTextView;
    TextView descriptionTextView;
    ImageView storeImageView;
    ImageView backImageView;
    ImageView moreImageView;
    ImageView favImageView;
    Button viewStoreMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_store_detail);

        // Get user object passed from the signup activity
        Gson gson = new Gson();
        storeid = gson.fromJson(getIntent().getStringExtra("storeid"), String.class);
        user = gson.fromJson(getIntent().getStringExtra("userJson"), User.class);
        //initData();

        initUI(savedInstanceState);

        //initDataBindings();

        initActions();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        initData();
    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    private void initData() {
       mDatabase = FirebaseFirestore.getInstance();
       mDatabase.collection("store").document(storeid).get().addOnCompleteListener(task -> {
           if(task.isSuccessful()){
               store = task.getResult().toObject(Store.class);
               initMap();
               initDataBindings();
               mStorage = FirebaseStorage.getInstance();
               mStorage.getReference().child("store/" + store.getStoreid() + "/" + store.getMainimage()).getBytes(MAX_SIZE).addOnCompleteListener(task1 -> {
                   if (task1.isSuccessful()){
                       byte[] byteArray = task1.getResult();
                       Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                       storeImageView.setImageBitmap(bmp);
                   }else{
                       Log.e("FAILED_DATA_RETRIEVAL", "initData: " + task1.getException().toString() );
                   }
               });
           }else{
               Log.e("FAILED_DATA_RETRIEVAL", "initData: " + task.getException().toString() );
           }

       });
    }

    private void initUI(Bundle savedInstanceState) {

        mapView = findViewById(R.id.storeMapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        placeNameTextView = findViewById(R.id.placeNameTextView);
        phoneTextView = findViewById(R.id.phoneTextView);
        websiteTextView = findViewById(R.id.websiteTextView);
        openingTimeTextView = findViewById(R.id.openingTimeTextView);
        addressTextView = findViewById(R.id.addressTextView);
        storeImageView = findViewById(R.id.storeImageView);
        backImageView = findViewById(R.id.backImageView);
        moreImageView = findViewById(R.id.moreImageView);
        descriptionTextView = findViewById(R.id.descriptionTextView);
        favImageView = findViewById(R.id.favImageView);
        viewStoreMenu = findViewById(R.id.viewMenuButton);

        int id = R.drawable.baseline_heart_grey; // assume store does not belong to users favs
        // check if it does
        if (user.getFavoriteStoresIDs().contains(storeid)){
            id = R.drawable.baseline_heart;
        }
        // set the appropriate heart
        Utils.setImageToImageView(getApplicationContext(),favImageView,id);
    }

    private void initMap()
    {
        googleMap.setMinZoomPreference(15);
        LatLng ny = new LatLng(store.getLocation_lat(),store.getLocation_long());

        Log.d("", "initMap: "+String.valueOf(store.getLocation_lat()));
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(ny);
        googleMap.addMarker(markerOptions);

        googleMap.moveCamera(CameraUpdateFactory.newLatLng(ny));
    }

    private void initDataBindings() {

        placeNameTextView.setText(store.getName());
        phoneTextView.setText(store.getContactPhone());
        websiteTextView.setText(store.getWebsite());
        addressTextView.setText(store.getAddress());
        descriptionTextView.setText(store.getDescription());
        //openingTimeTextView.setText(place.opening);
    }

    public void favImageClickHandler(){
        // check if store belogs to favs
        if (user.getFavoriteStoresIDs().contains(storeid)){
            //if yes remove it
            user.removeStoreFromFav(storeid);
            // update db
            mDatabase.collection("users").document(user.getUid()).update("favoriteStoresIDs",user.getFavoriteStoresIDs()).addOnCompleteListener(task -> {
                if (task.isSuccessful()){
                    //show toast and change
                    Toast.makeText(ViewStoreDetailActivity.this,"Removed from favorites",Toast.LENGTH_SHORT).show();
                    int id = R.drawable.baseline_heart_grey;
                    Utils.setImageToImageView(getApplicationContext(),favImageView,id);
                }else{
                    Log.e("FAILED_DATA_RETRIEVAL", "favImageClickHandler: " + task.getException().toString() );
                }
            });
        }else{
            // does not belong; add it
            user.addStoreToFav(storeid);
            // update db
            mDatabase.collection("users").document(user.getUid()).update("favoriteStoresIDs",user.getFavoriteStoresIDs()).addOnCompleteListener(task -> {
                if (task.isSuccessful()){
                    //show toast and change
                    Toast.makeText(ViewStoreDetailActivity.this,"Added to" +
                            " favorites",Toast.LENGTH_SHORT).show();
                    int id = R.drawable.baseline_heart;
                    Utils.setImageToImageView(getApplicationContext(),favImageView,id);
                }else{
                    Log.e("FAILED_DATA_RETRIEVAL", "favImageClickHandler: " + task.getException().toString() );
                }
            });

        }

    }

    private void initActions() {
        //need actions for more an back

        viewStoreMenu.setOnClickListener(v -> {
            Gson json = new Gson();
            String storeJson = json.toJson(store);
            String userJson = json.toJson(user);
            Intent intent = new Intent(ViewStoreDetailActivity.this, ViewStoreMenuActivity.class);
            intent.putExtra("userJson",userJson);
            intent.putExtra("storeJson",storeJson);
            startActivity(intent);
        });

        favImageView.setOnClickListener(v -> {
            favImageClickHandler();
        });
    }
}

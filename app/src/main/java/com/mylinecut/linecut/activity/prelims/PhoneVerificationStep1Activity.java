package com.mylinecut.linecut.activity.prelims;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.mylinecut.linecut.R;
import com.mylinecut.linecut.object.User;
import com.mylinecut.linecut.utils.CommonUI;
import com.panaceasoft.awesomematerial.utils.Utils;

import com.google.gson.*;

import com.google.firebase.firestore.*;
import java.util.Random;


public class PhoneVerificationStep1Activity extends AppCompatActivity {

    ImageView bgImageView;
    EditText phoneEditText, countryCodeEditText;
    Button continueButton;

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.mylinecut.linecut.R.layout.phone_verification_step_1_activity);

        // Get user object passed from the signup activity
        Gson gson = new Gson();
        user = gson.fromJson(getIntent().getStringExtra("userJson"), User.class);

        initUI();

        initActions();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initUI() {
        initToolbar();

        bgImageView = findViewById(com.mylinecut.linecut.R.id.bgImageView);
        int id = Utils.getDrawableInt(getApplicationContext(), "verification1");
        Utils.setImageToImageView(getApplicationContext(), bgImageView, id);
        phoneEditText = findViewById(com.mylinecut.linecut.R.id.codeEditText);
        countryCodeEditText = findViewById(com.mylinecut.linecut.R.id.countryCodeEditText);
        continueButton = findViewById(com.mylinecut.linecut.R.id.continueButton);

    }

    private void initActions(){
        continueButton.setOnClickListener((View v) ->{
            String countryCode = countryCodeEditText.getText().toString(),
                    phone = phoneEditText.getText().toString();

            if (phone.length() == 10) {
                updatePhone(countryCode + phone);
            }else{
                phoneEditText.setError("Please enter a valid phone number.");
                (new CommonUI()).removeErrorsOnTextChange(phoneEditText);
            }
        });
    }

    private void updatePhone(String phone){
        FirebaseFirestore mDatabase = FirebaseFirestore.getInstance();
        CollectionReference usersRef = mDatabase.collection("users");
        Query query = usersRef.whereEqualTo("phone",phone);
        query.get().addOnSuccessListener(queryDocumentSnapshots -> {
            if (queryDocumentSnapshots.isEmpty()){
                user.setPhone(phone);
                user.setPhoneVerCode((new Random()).nextInt(8999)+1000);
                DocumentReference userRef = mDatabase.collection("users").document(user.getUid());
                userRef.set(user).addOnSuccessListener(aVoid -> {
                    Gson gson = new Gson();
                    String userJson = gson.toJson(user);
                    Intent intent = new Intent(PhoneVerificationStep1Activity.this,PhoneVerificationStep2Activity.class);
                    intent.putExtra("userJson",userJson);
                    startActivity(intent);
                    finish();
                }).addOnFailureListener(e -> {

                });
            }else{
                phoneEditText.setError("Phone number already exists.");
                (new CommonUI()).removeErrorsOnTextChange(phoneEditText);
            }

        });

    }


    private void initToolbar() {

        Toolbar toolbar = findViewById(com.mylinecut.linecut.R.id.toolbar);

        toolbar.setNavigationIcon(com.mylinecut.linecut.R.drawable.baseline_menu_black_24);

        if (toolbar.getNavigationIcon() != null) {
            toolbar.getNavigationIcon().setColorFilter(getResources().getColor(com.mylinecut.linecut.R.color.md_white_1000), PorterDuff.Mode.SRC_ATOP);
        }

        toolbar.setTitle("Phone Verification");

        try {
            toolbar.setTitleTextColor(getResources().getColor(R.color.md_white_1000));
        } catch (Exception e) {
            Log.e("TEAMPS", "Can't set color.");
        }

        try {
            setSupportActionBar(toolbar);
        } catch (Exception e) {
            Log.e("TEAMPS", "Error in set support action bar.");
        }

        try {
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        } catch (Exception e) {
            Log.e("TEAMPS", "Error in set display home as up enabled.");
        }

    }
}

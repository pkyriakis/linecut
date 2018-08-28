package com.mylinecut.linecut;

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
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;
import com.panaceasoft.awesomematerial.utils.Utils;
import com.mylinecut.linecut.utils.CommonUI;


public class PhoneVerificationStep2Activity extends AppCompatActivity {

    ImageView bgImageView;
    Button continueButton;
    TextView resendTextView;
    EditText codeEditText;

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phone_verification_step_2_activity);

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

        bgImageView = findViewById(R.id.bgImageView);
        int id = Utils.getDrawableInt(getApplicationContext(), "verification2");
        Utils.setImageToImageView(getApplicationContext(), bgImageView, id);

        continueButton = findViewById(R.id.continueButton);
        codeEditText = findViewById(R.id.codeEditText);
        resendTextView = findViewById(R.id.resendTextView);
    }

    private void initActions(){
        continueButton.setOnClickListener((View v) ->{
            verifyPhone();
        });
        resendTextView.setOnClickListener((View v) ->{
            resendSMS();
        });
    }

    private void verifyPhone(){
        String codeString = codeEditText.getText().toString();
        if (codeString.length() != 4){
            codeEditText.setError("Please enter a 4-digit number");
            (new CommonUI()).removeErrorsOnTextChange(codeEditText);
        }else {
            Integer code = Integer.parseInt(codeString);
            if (!code.equals(user.getPhoneVerCode())){
                codeEditText.setError("Invalid Verification Code");
                (new CommonUI()).removeErrorsOnTextChange(codeEditText);
            }else{
                user.setPhoneVerCode(-1);
                FirebaseFirestore mDatabase = FirebaseFirestore.getInstance();
                mDatabase.collection("users").document(user.getUid()).update("phoneVerCode",-1)
                        .addOnSuccessListener(aVoid -> {
                            Gson gson = new Gson();
                            String userJson = gson.toJson(user);
                            Intent intent = new Intent(PhoneVerificationStep2Activity.this,AppDirectoryHome2Activity.class);
                            intent.putExtra("userJson",userJson);
                            startActivity(intent);
                            finish();
                        }).addOnFailureListener(e -> {

                });
            }
        }
    }
    private void resendSMS(){

    }
    private void initToolbar() {

        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setNavigationIcon(R.drawable.baseline_menu_black_24);

        if (toolbar.getNavigationIcon() != null) {
            toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.md_white_1000), PorterDuff.Mode.SRC_ATOP);
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

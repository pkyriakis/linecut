package com.mylinecut.linecut;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mylinecut.linecut.R;

public class LoginActivity extends AppCompatActivity {

    TextView forgotTextView, signUpTextView;
    Button loginButton;
    CardView facebookCardView, twitterCardView;
    ImageView bgImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout._linecut_login_activity);

        initUI();

        initDataBindings();

        initActions();
    }

    //region Init Functions
    private void initUI() {
        forgotTextView = findViewById(R.id.forgotTextView);
        signUpTextView = findViewById(R.id.signuptTextView);

        loginButton = findViewById(R.id.loginButton);
        facebookCardView = findViewById(R.id.facebookCardView);
        twitterCardView = findViewById(R.id.twitterCardView);
        bgImageView = findViewById(R.id.bgImageView);
    }

    private void initDataBindings() {
        int id = R.drawable.login_background_3;
        //Utils.setImageToImageView(getApplicationContext(), bgImageView, id);
    }

    private void initActions() {
        forgotTextView.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this,ForgotPasswordActivity.class);
            startActivity(intent);
        });

        signUpTextView.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this,SignupActivity.class);
            startActivity(intent);
        });

        loginButton.setOnClickListener(view -> {
            Toast.makeText(getApplicationContext(), "Clicked Login.", Toast.LENGTH_SHORT).show();
        });

        facebookCardView.setOnClickListener(view -> {
            Toast.makeText(getApplicationContext(), "Clicked Facebook.", Toast.LENGTH_SHORT).show();
        });

        twitterCardView.setOnClickListener(view -> {
            Toast.makeText(getApplicationContext(), "Clicked Twitter.", Toast.LENGTH_SHORT).show();
        });
    }


    //endregion
}

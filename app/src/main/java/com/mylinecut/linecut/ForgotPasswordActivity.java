package com.mylinecut.linecut;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ForgotPasswordActivity extends AppCompatActivity {

    Button resetButton;
    TextView signInTextView;
    ImageView bgImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password_activity);

        initUI();

        initDataBindings();

        initActions();

    }

    //region Init Functions
    private void initUI() {
        signInTextView = findViewById(R.id.signinTextView);
        resetButton = findViewById(R.id.resetButton);
        bgImageView = findViewById(R.id.bgImageView);
    }

    private void initDataBindings() {
        int id = R.drawable.login_background_3;
        //Utils.setImageToImageView(getApplicationContext(), bgImageView, id);
    }

    private void initActions() {

        signInTextView.setOnClickListener(view -> {
            Intent intent = new Intent(ForgotPasswordActivity.this,LoginActivity.class);
            startActivity(intent);
        });


        resetButton.setOnClickListener(view -> {
            resetPassword();
        });

    }

    // to be written; reset password method
    private void resetPassword(){


    }
    //endregion
}

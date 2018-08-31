package com.mylinecut.linecut.activity.prelims;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import com.google.gson.Gson;
import com.mylinecut.linecut.R;
import com.mylinecut.linecut.activity.client.AppDirectoryHome1Activity;
import com.mylinecut.linecut.utils.*;
import com.mylinecut.linecut.object.User;

public class LoginActivity extends AppCompatActivity {

    TextView forgotTextView, signUpTextView;
    EditText emailEditText, passEditText;
    Button loginButton;
    CardView facebookCardView, twitterCardView;
    ImageView bgImageView;
    FirebaseAuth mAuth;
    FirebaseFirestore mDatabasse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        initUI();

        initDataBindings();

        initActions();
    }

    //region Init Functions
    private void initUI() {
        mAuth = FirebaseAuth.getInstance();
        mDatabasse = FirebaseFirestore.getInstance();

        forgotTextView = findViewById(R.id.forgotTextView);
        signUpTextView = findViewById(R.id.signuptTextView);

        loginButton = findViewById(R.id.loginButton);
        facebookCardView = findViewById(R.id.facebookCardView);
        twitterCardView = findViewById(R.id.twitterCardView);
        bgImageView = findViewById(R.id.bgImageView);
        emailEditText = findViewById(R.id.emailEditText);
        passEditText = findViewById(R.id.passwordEditText);
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
            login();
        });

        facebookCardView.setOnClickListener(view -> {
            Toast.makeText(getApplicationContext(), "Clicked Facebook.", Toast.LENGTH_SHORT).show();
        });

        twitterCardView.setOnClickListener(view -> {
            Toast.makeText(getApplicationContext(), "Clicked Twitter.", Toast.LENGTH_SHORT).show();
        });
    }

    private void login(){
        String email = emailEditText.getText().toString(),
                pass = passEditText.getText().toString();
        // make sure email has valid format
        if (!(new Validator()).isValidEmail(email))
        {
            emailEditText.setError("The email address is badly formatted.");
            emailEditText.requestFocus();
            (new CommonUI()).removeErrorsOnTextChange(emailEditText);
        }else {
            //attempt login
            mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {// user log in successful
                    // grab profile from database; needs to be passed to next activity
                    mDatabasse.collection("users").document(mAuth.getUid()).get().addOnCompleteListener(task1 -> {
                        if (task1.isSuccessful()){// profile successfully retrieved
                            DocumentSnapshot userDoc = task1.getResult();
                            User user = userDoc.toObject(User.class);
                            Gson gson = new Gson();
                            String userJson = gson.toJson(user);
                            Intent intent = new Intent(LoginActivity.this,AppDirectoryHome1Activity.class);
                            intent.putExtra("userJson",userJson);
                            startActivity(intent);
                            finish();

                        }
                    });
                } else {
                    String errorCode = ((FirebaseAuthException) task.getException()).getErrorCode();

                    switch (errorCode) {

                        case "ERROR_INVALID_CUSTOM_TOKEN":
                            Toast.makeText(LoginActivity.this, "The custom token format is incorrect. Please check the documentation.", Toast.LENGTH_LONG).show();
                            break;

                        case "ERROR_CUSTOM_TOKEN_MISMATCH":
                            Toast.makeText(LoginActivity.this, "The custom token corresponds to a different audience.", Toast.LENGTH_LONG).show();
                            break;

                        case "ERROR_INVALID_CREDENTIAL":
                            Toast.makeText(LoginActivity.this, "The supplied auth credential is malformed or has expired.", Toast.LENGTH_LONG).show();
                            break;

                        case "ERROR_INVALID_EMAIL":
                            emailEditText.setError("The email address is badly formatted.");
                            emailEditText.requestFocus();
                            (new CommonUI()).removeErrorsOnTextChange(emailEditText);
                            break;

                        case "ERROR_WRONG_PASSWORD":
                            passEditText.setError("Password is incorrect");
                            passEditText.requestFocus();
                            passEditText.setText("");
                            (new CommonUI()).removeErrorsOnTextChange(passEditText);
                            break;

                        case "ERROR_USER_MISMATCH":
                            Toast.makeText(LoginActivity.this, "The supplied credentials do not correspond to the previously signed in user.", Toast.LENGTH_LONG).show();
                            break;

                        case "ERROR_REQUIRES_RECENT_LOGIN":
                            Toast.makeText(LoginActivity.this, "This operation is sensitive and requires recent authentication. Log in again before retrying this request.", Toast.LENGTH_LONG).show();
                            break;

                        case "ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL":
                            Toast.makeText(LoginActivity.this, "An account already exists with the same email address but different sign-in credentials. Sign in using a provider associated with this email address.", Toast.LENGTH_LONG).show();
                            break;

                        case "ERROR_EMAIL_ALREADY_IN_USE":
                            emailEditText.setError("The email address is already in use by another account.");
                            emailEditText.requestFocus();
                            break;

                        case "ERROR_CREDENTIAL_ALREADY_IN_USE":
                            Toast.makeText(LoginActivity.this, "This credential is already associated with a different user account.", Toast.LENGTH_LONG).show();
                            break;

                        case "ERROR_USER_DISABLED":
                            Toast.makeText(LoginActivity.this, "The user account has been disabled by an administrator.", Toast.LENGTH_LONG).show();
                            break;

                        case "ERROR_USER_TOKEN_EXPIRED":
                            Toast.makeText(LoginActivity.this, "The user\\'s credential is no longer valid. The user must sign in again.", Toast.LENGTH_LONG).show();
                            break;

                        case "ERROR_USER_NOT_FOUND":
                            Toast.makeText(LoginActivity.this, "There is no user record corresponding to this identifier. The user may have been deleted.", Toast.LENGTH_LONG).show();
                            break;

                        case "ERROR_INVALID_USER_TOKEN":
                            Toast.makeText(LoginActivity.this, "The user\\'s credential is no longer valid. The user must sign in again.", Toast.LENGTH_LONG).show();
                            break;

                        case "ERROR_OPERATION_NOT_ALLOWED":
                            Toast.makeText(LoginActivity.this, "This operation is not allowed. You must enable this service in the console.", Toast.LENGTH_LONG).show();
                            break;

                        case "ERROR_WEAK_PASSWORD":
                            passEditText.setError("Password must be at least 6 characters.");
                            passEditText.requestFocus();
                            break;

                    }
                }

            });
        }

    }
    //endregion
}

package com.mylinecut.linecut;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.DatePickerDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.support.annotation.NonNull;
import android.widget.Toast;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.view.View;
import android.view.View.OnClickListener;

import com.google.android.gms.tasks.OnSuccessListener;
import com.mylinecut.linecut.R;
import com.mylinecut.linecut.utils.Validator;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.firestore.*;

public class SignupActivity extends AppCompatActivity {

    private EditText firstnameEditText, lastnameEditText,
            birthdayEditText, emailEditText,
            passEditText, reppassEditText;

    private String firstname, lastname,
            birthday, email,
            pass, reppass;

    private TextView forgotTextView, loginTextView;
    private  Button registerButton, datePickButton;

    private ImageView bgImageView;

    private User user;

    private String DATE_FORMAT ="MM/dd/yyyy";

    private FirebaseAuth mAuth;
    private FirebaseFirestore mDatabase;

    Calendar dateTime = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener datePickerDialog = (view, year, monthOfYear, dayOfMonth) -> {
        dateTime.set(Calendar.YEAR, year);
        dateTime.set(Calendar.MONTH, monthOfYear);
        dateTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        updateDate();
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout._linecut_signup_activity);

        initUI();

        initDataBindings();

        initActions();

    }

    //region Init Functions
    private void initUI() {
        firstnameEditText = findViewById(R.id.firstnameEditText);
        lastnameEditText = findViewById(R.id.lastnameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        birthdayEditText = findViewById(R.id.birthdayEditText);
        passEditText = findViewById(R.id.passwordEditText);
        reppassEditText = findViewById(R.id.reppasswordEditText);
        forgotTextView = findViewById(R.id.forgotTextView);
        loginTextView = findViewById(R.id.loginTextView);
        registerButton = findViewById(R.id.registerButton);
        bgImageView = findViewById(R.id.bgImageView);
        datePickButton = findViewById(R.id.datePickButton);
    }

    private void initDataBindings() {
        int id = R.drawable.login_background_3;
        //Utils.setImageToImageView(getApplicationContext(), bgImageView, id);
    }
    private void initActions() {
        forgotTextView.setOnClickListener(view -> {
            Intent intent = new Intent(SignupActivity.this,ForgotPasswordActivity.class);
            startActivity(intent);
        });

        loginTextView.setOnClickListener(view -> {
            Intent intent = new Intent(SignupActivity.this,LoginActivity.class);
            startActivity(intent);
        });

        registerButton.setOnClickListener(view -> {
            if (validateInput()) {
                register();
            }
        });
        datePickButton.setOnClickListener(view -> openDatePicker());

    }

    private void openDatePicker(){
        new DatePickerDialog(this, datePickerDialog, dateTime.get(Calendar.YEAR),dateTime.get(Calendar.MONTH),dateTime.get(Calendar.DAY_OF_MONTH)).show();
    }
    private void updateDate(){
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT, Locale.US);
        String shortTimeStr = sdf.format(dateTime.getTime());
        birthdayEditText.setText(shortTimeStr);
    }

    // validate user input
    private boolean validateInput(){
        boolean isValid = true;
        user = new User();
        Validator validator = new Validator();

        firstname = firstnameEditText.getText().toString().trim();
        lastname = lastnameEditText.getText().toString().trim();
        birthday = birthdayEditText.getText().toString().trim();
        email = emailEditText.getText().toString().trim();
        pass = passEditText.getText().toString().trim();
        reppass = reppassEditText.getText().toString().trim();

        if(!firstname.isEmpty()){
            user.setFirstname(firstname);
        }else{
            firstnameEditText.setError("Please enter your first name.");
            removeErrorsOnTextChange(firstnameEditText);
            isValid = false;
        }
        if(!lastname.isEmpty()){
            user.setLastname(lastname);
        }else{
            lastnameEditText.setError("Please enter your last name.");
            removeErrorsOnTextChange(lastnameEditText);
            isValid = false;
        }
        if(validator.isThisDateValid(birthday,DATE_FORMAT)){
            user.setBirthDay(birthday);
        }else{
            birthdayEditText.setError("Please enter a valid date.");
            removeErrorsOnTextChange(birthdayEditText);
            isValid = false;
        }
        if(validator.isValidEmail(email)){
            user.setEmail(email);
        }else{
            emailEditText.setError("Please enter a valid email address.");
            removeErrorsOnTextChange(emailEditText);
            isValid = false;
        }
        if(!pass.equals(reppass)){
            passEditText.setError("Passwords do not match.");
            reppassEditText.setError("Passwords do not match.");
            removeErrorsOnTextChange(passEditText);
            removeErrorsOnTextChange(reppassEditText);
            isValid = false;
        }
        if(pass.length()<6){
            passEditText.setError("Must be at least 6 characters.");
            removeErrorsOnTextChange(passEditText);
            isValid = false;
        }

        return isValid;
    }

    // Removes the error when the text in the given edittext is changed
    private void removeErrorsOnTextChange(final EditText itemText)
    {

        itemText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                itemText.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    // to be written; register user
    private void register(){
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseFirestore.getInstance();

        mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser mUser = mAuth.getCurrentUser();
                            if (mUser != null) {
                                String uid = mUser.getUid();
                                user.setUid(uid);

                                Log.d("uid", user.getUid());
                                mDatabase.collection("users").document(user.getUid()).set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        //Intent intent = new Intent(SignupActivity.this,UserDashboard.class);
                                        //startActivity(intent);
                                        finish();
                                    }
                                });
                            }
                        } else {

                            String errorCode = ((FirebaseAuthException) task.getException()).getErrorCode();

                            switch (errorCode) {

                                case "ERROR_INVALID_CUSTOM_TOKEN":
                                    Toast.makeText(SignupActivity.this, "The custom token format is incorrect. Please check the documentation.", Toast.LENGTH_LONG).show();
                                    break;

                                case "ERROR_CUSTOM_TOKEN_MISMATCH":
                                    Toast.makeText(SignupActivity.this, "The custom token corresponds to a different audience.", Toast.LENGTH_LONG).show();
                                    break;

                                case "ERROR_INVALID_CREDENTIAL":
                                    Toast.makeText(SignupActivity.this, "The supplied auth credential is malformed or has expired.", Toast.LENGTH_LONG).show();
                                    break;

                                case "ERROR_INVALID_EMAIL":
                                    emailEditText.setError("The email address is badly formatted.");
                                    emailEditText.requestFocus();
                                    break;

                                case "ERROR_WRONG_PASSWORD":
                                    Toast.makeText(SignupActivity.this, "The password is invalid.", Toast.LENGTH_LONG).show();
                                    passEditText.setError("Password is incorrect ");
                                    passEditText.requestFocus();
                                    passEditText.setText("");
                                    break;

                                case "ERROR_USER_MISMATCH":
                                    Toast.makeText(SignupActivity.this, "The supplied credentials do not correspond to the previously signed in user.", Toast.LENGTH_LONG).show();
                                    break;

                                case "ERROR_REQUIRES_RECENT_LOGIN":
                                    Toast.makeText(SignupActivity.this, "This operation is sensitive and requires recent authentication. Log in again before retrying this request.", Toast.LENGTH_LONG).show();
                                    break;

                                case "ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL":
                                    Toast.makeText(SignupActivity.this, "An account already exists with the same email address but different sign-in credentials. Sign in using a provider associated with this email address.", Toast.LENGTH_LONG).show();
                                    break;

                                case "ERROR_EMAIL_ALREADY_IN_USE":
                                    emailEditText.setError("The email address is already in use by another account.");
                                    emailEditText.requestFocus();
                                    break;

                                case "ERROR_CREDENTIAL_ALREADY_IN_USE":
                                    Toast.makeText(SignupActivity.this, "This credential is already associated with a different user account.", Toast.LENGTH_LONG).show();
                                    break;

                                case "ERROR_USER_DISABLED":
                                    Toast.makeText(SignupActivity.this, "The user account has been disabled by an administrator.", Toast.LENGTH_LONG).show();
                                    break;

                                case "ERROR_USER_TOKEN_EXPIRED":
                                    Toast.makeText(SignupActivity.this, "The user\\'s credential is no longer valid. The user must sign in again.", Toast.LENGTH_LONG).show();
                                    break;

                                case "ERROR_USER_NOT_FOUND":
                                    Toast.makeText(SignupActivity.this, "There is no user record corresponding to this identifier. The user may have been deleted.", Toast.LENGTH_LONG).show();
                                    break;

                                case "ERROR_INVALID_USER_TOKEN":
                                    Toast.makeText(SignupActivity.this, "The user\\'s credential is no longer valid. The user must sign in again.", Toast.LENGTH_LONG).show();
                                    break;

                                case "ERROR_OPERATION_NOT_ALLOWED":
                                    Toast.makeText(SignupActivity.this, "This operation is not allowed. You must enable this service in the console.", Toast.LENGTH_LONG).show();
                                    break;

                                case "ERROR_WEAK_PASSWORD":
                                    passEditText.setError("Password must be at least 6 characters.");
                                    passEditText.requestFocus();
                                    break;

                            }
                        }
                    }
                });


    }


    //endregion
}

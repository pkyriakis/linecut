package com.mylinecut.linecut;

import android.animation.Animator;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mylinecut.linecut.R;
import com.panaceasoft.awesomematerial.utils.Utils;

public class SplashScreen extends AppCompatActivity implements View.OnClickListener {

    ImageView iconImageView;
    TextView nameTextView;
    Button loginButton, signupButton;
    Boolean isRunning = false;
    ImageView bgImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout._linecut_splash_screen_activity);

        initUI();

        initDataBindings();


    }

    private void initUI() {
        iconImageView = findViewById(R.id.iconImageView);
        nameTextView = findViewById(R.id.nameTextView);
        loginButton = findViewById(R.id.loginButton);
        signupButton = findViewById(R.id.signupButton);
        bgImageView = findViewById(R.id.s2bgImageView);

        loginButton.setOnClickListener(this);
        signupButton.setOnClickListener(this);
    }

    private void initDataBindings() {
        Utils.setImageToImageView(this, bgImageView, R.drawable.star_bg);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId()){
            case R.id.loginButton:
                Intent loginIntent = new Intent(SplashScreen.this, LoginActivity.class);
                startActivity(loginIntent);
                break;
            case R.id.signupButton:
                Intent signpIntent = new Intent(SplashScreen.this, SignupActivity.class);
                startActivity(signpIntent);
                break;
        }

    }
    @Override
    public void onBackPressed()
    {

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        //Here you can get the size!

        if(!isRunning) {
            isRunning = true;
            int width = Resources.getSystem().getDisplayMetrics().widthPixels;
            int height = Resources.getSystem().getDisplayMetrics().heightPixels;

            int halfWidth = width / 2;
            int halfHeight = height / 2;

            int px50 = Utils.dpToPx(this, 50);
            int px20 = Utils.dpToPx(this, 20);
            int px90 = Utils.dpToPx(this, 90);

            int iconToX = halfWidth - px50;
            int iconToY = halfHeight - px90;

            iconImageView.animate().scaleX(4).scaleY(4).alpha(0).setDuration(0).setListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {

                    iconImageView.animate().scaleX(1).scaleY(1).alpha(1).setDuration(1500).setListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animator) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animator) {
                            nameTextView.animate().alpha(1).setDuration(800).setListener(new Animator.AnimatorListener() {
                                @Override
                                public void onAnimationStart(Animator animator) {

                                }

                                @Override
                                public void onAnimationEnd(Animator animator) {
                                    loginButton.animate().alpha(1).setDuration(400).start();
                                    signupButton.animate().alpha(1).setDuration(400).start();
                                }

                                @Override
                                public void onAnimationCancel(Animator animator) {

                                }

                                @Override
                                public void onAnimationRepeat(Animator animator) {

                                }
                            }).start();

                        }

                        @Override
                        public void onAnimationCancel(Animator animator) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animator) {

                        }
                    }).start();

                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            }).start();

//            loginButton.animate().translationY(height).setDuration(0).setListener(new Animator.AnimatorListener() {
//                @Override
//                public void onAnimationStart(Animator animator) {
//
//                }
//
//                @Override
//                public void onAnimationEnd(Animator animator) {
//                    loginButton.animate().translationY(halfHeight + px90).alpha(1).setDuration(800).start();
//                }
//
//                @Override
//                public void onAnimationCancel(Animator animator) {
//
//                }
//
//                @Override
//                public void onAnimationRepeat(Animator animator) {
//
//                }
//            }).start();
//
//            int textToX = halfWidth - (nameTextView.getWidth() / 2);
//            int textToY = halfHeight + px20;
//
//
//            nameTextView.animate().alpha(0).translationY(height).translationX(width).setDuration(0).setListener(new Animator.AnimatorListener() {
//                @Override
//                public void onAnimationStart(Animator animator) {
//
//                }
//
//                @Override
//                public void onAnimationEnd(Animator animator) {
//                    nameTextView.animate().alpha(1).translationX(textToX).translationY(textToY).setDuration(1500).start();
//                }
//
//                @Override
//                public void onAnimationCancel(Animator animator) {
//
//                }
//
//                @Override
//                public void onAnimationRepeat(Animator animator) {
//
//                }
//            }).start();

        }
    }


}


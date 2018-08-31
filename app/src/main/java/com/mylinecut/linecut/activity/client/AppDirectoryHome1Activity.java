package com.mylinecut.linecut.activity.client;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.mylinecut.linecut.R;
import com.mylinecut.linecut.fragment.AppDirectoryHome1Fragment;
import com.mylinecut.linecut.fragment.AppDirectoryHome2Fragment;

import com.panaceasoft.awesomematerial.utils.Utils;

public class AppDirectoryHome1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_directory_home_1_activity);

        initData();

        initUI();

        initDataBinding();

        initActions();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initData() {

    }

    private void initUI() {

        // Init Toolbar
        initToolbar();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        Utils.removeShiftMode(bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {

            switch (item.getItemId()) {
                case R.id.searchMenu:
                    loadFragment(new AppDirectoryHome1Fragment());
                    break;
               case R.id.listMenu:
                    loadFragment(new AppDirectoryHome2Fragment());
                    break;
//                case R.id.historyMenu:
//                    loadFragment(new AppDirectoryHome3Fragment());
//                    break;
//                case R.id.profileMenu:
//                    loadFragment(new AppDirectoryHome4Fragment());
//                    break;
                default:
                    loadFragment(new AppDirectoryHome1Fragment());
                    break;
            }

            Toast.makeText(getApplicationContext(), "Clicked " + item.getTitle(), Toast.LENGTH_SHORT).show();

            return false;
        });

        loadFragment(new AppDirectoryHome1Fragment());

    }

    private void initDataBinding() {

    }

    private void initActions() {

    }

    private void initToolbar() {

        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setNavigationIcon(R.drawable.baseline_menu_black_24);

        if (toolbar.getNavigationIcon() != null) {
            toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.md_white_1000), PorterDuff.Mode.SRC_ATOP);
        }

        toolbar.setTitle("Home 1");

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

    private void loadFragment(android.support.v4.app.Fragment fragment) {
        this.getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commitAllowingStateLoss();
    }

}

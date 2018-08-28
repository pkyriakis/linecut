package com.mylinecut.linecut;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mylinecut.linecut.R;
import com.mylinecut.linecut.AppDirectoryHome2Fragment;

import com.panaceasoft.awesomematerial.utils.Utils;

public class AppDirectoryHome2Activity extends AppCompatActivity {

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_directory_home_2_activity);

        // Get user object passed from the signup activity
        Gson gson = new Gson();
        user = gson.fromJson(getIntent().getStringExtra("userJson"), User.class);
        initData();

        initUI();

        initDataBinding();

        initActions();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }else {
            Toast.makeText(this, "Clicked "+ item.getTitle() , Toast.LENGTH_SHORT).show();
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

        bottomNavigationView.setSelectedItemId(R.id.listMenu);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {

            switch (item.getItemId()) {
//              case R.id.searchMenu:
//                    loadFragment(new AppDirectoryHome1Fragment());
//                    break;
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
                    Toast.makeText(getApplicationContext(), "Clicked " + item.getTitle(), Toast.LENGTH_SHORT).show();
                    break;
            }




            return false;
        });

        loadFragment(new AppDirectoryHome2Fragment());

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

        toolbar.setTitle("Home 2");

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

package com.mylinecut.linecut.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.*;
import com.google.gson.Gson;
import com.mylinecut.linecut.activity.client.AppDirectoryHome1Activity;
import com.mylinecut.linecut.activity.prelims.SplashScreenActivity;
import com.mylinecut.linecut.object.Product;
import com.mylinecut.linecut.object.Store;
import com.mylinecut.linecut.object.User;



public class MainActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;
    private FirebaseFirestore mDatabase;
    private User user;
    private Product product;
    private Store store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String sid="MZyV2VuZBXmTanWbr9gY";

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseFirestore.getInstance();

        mDatabase.collection("products/MZyV2VuZBXmTanWbr9gY/categorylist/18wXovyJShMEivBlUFWi/productslist").
                document("7GneJaiDfzJwNSH8obEl").get().addOnCompleteListener(task->{
           if (task.isSuccessful()){
               Log.d("store", "DocumentSnapshot data: " + task.getResult().getData());
               product=task.getResult().toObject(Product.class);
           }else {
               Log.d("failed", "get failed with ", task.getException());
           }
        });
        mDatabase.collection("store").
                document(sid).get().addOnCompleteListener(task->{
            if (task.isSuccessful()){
                Log.d("store", "DocumentSnapshot data: " + task.getResult().getData());
                store=task.getResult().toObject(Store.class);
            }else {
                Log.d("failed", "get failed with ", task.getException());
            }
        });

        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser == null){ //not signed in; show splash screen
            Intent intent = new Intent(MainActivity.this, SplashScreenActivity.class);
            startActivity(intent);
            finish();
        }
        else {// user is signed in; redirect to user dashboard
            mDatabase.collection("users").document(mAuth.getUid()).get().addOnCompleteListener(task1 -> {
                if (task1.isSuccessful()){// profile successfully retrieved
                    DocumentSnapshot userDoc = task1.getResult();
                    user = userDoc.toObject(User.class);
                    redirect();
                }
            });
        }
    }

    private void redirect(){
        Gson gson = new Gson();
        String userJson = gson.toJson(user);
        Intent intent =  new Intent();
        if(user.getType().equals("client")) {
            intent = new Intent(MainActivity.this, AppDirectoryHome1Activity.class);
        }
        intent.putExtra("userJson",userJson);
        startActivity(intent);
        //finish();

    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_info, menu);

        Utils.updateMenuIconColor(menu, getResources().getColor(R.color.md_white_1000));

        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            new AlertDialog.Builder(this)
                    .setTitle(getResources().getString(R.string.app_name))
                    .setMessage(R.string.message__want_to_quit)
                    .setIcon(R.drawable.baseline_info_line_24)
                    .setNegativeButton(android.R.string.no, null)
                    .setPositiveButton(android.R.string.yes, (DialogInterface dialog, int whichButton) -> {
                        finish();
                        System.exit(0);
                    }).show();

        }
        return true;
    }


    //region Init Toolbar

    private void initToolbar() {

        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setNavigationIcon(R.drawable.app_icon);

        if (toolbar.getNavigationIcon() != null) {
            toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.md_white_1000), PorterDuff.Mode.SRC_ATOP);
        }

        toolbar.setTitle(getString(R.string.app_name));

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Toast.makeText(this, getString(R.string.app_name), Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.action_app_info) {
            NavigationController.openActivity(this, new Intent(this, MainAboutUs.class));
        }
        return super.onOptionsItemSelected(item);
    }

    //endregion


    private void initUI() {
        ViewPager view_pager = findViewById(R.id.view_pager);
        TabLayout tab_layout = findViewById(R.id.tab_layout);
        setupViewPager(view_pager);

        tab_layout.setupWithViewPager(view_pager);

        try {

            // set icon color pre-selected
            TabLayout.Tab tab1 = tab_layout.getTabAt(0);
            if (tab1 != null) {
                tab1.setIcon(R.drawable.baseline_layers_white_24);
                if (tab1.getIcon() != null) {
                    tab1.getIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
                }
            }

            TabLayout.Tab tab2 = tab_layout.getTabAt(1);
            if (tab2 != null) {
                tab2.setIcon(R.drawable.baseline_widgets_black_24);
                if (tab2.getIcon() != null) {
                    tab2.getIcon().setColorFilter(getResources().getColor(R.color.md_grey_200), PorterDuff.Mode.SRC_IN);
                }
            }

            TabLayout.Tab tab3 = tab_layout.getTabAt(2);
            if (tab3 != null) {
                tab3.setIcon(R.drawable.baseline_book_white_24);
                if (tab3.getIcon() != null) {
                    tab3.getIcon().setColorFilter(getResources().getColor(R.color.md_grey_200), PorterDuff.Mode.SRC_IN);
                }
            }

            tab_layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    if (tab.getIcon() != null) {
                        tab.getIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
                    }
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {
                    if (tab.getIcon() != null) {
                        tab.getIcon().setColorFilter(getResources().getColor(R.color.md_grey_200), PorterDuff.Mode.SRC_IN);
                    }
                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {
                    if (tab.getIcon() != null) {
                        tab.getIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
                    }
                }
            });

        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPagerAdapter viewPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(MainFeatureListFragment.newInstance(), getString(R.string.tab_features));    // index 0
        viewPagerAdapter.addFragment(MainApplicationListFragment.newInstance(), getString(R.string.tab_application));   // index 1
        viewPagerAdapter.addFragment(MainUIFragment.newInstance(), getString(R.string.tab_ui_collection));    // index 2
        viewPager.setAdapter(viewPagerAdapter);
    }

    private class SectionsPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        SectionsPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        public String getTitle(int position) {
            return mFragmentTitleList.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }*/
}

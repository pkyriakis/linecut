package com.mylinecut.linecut;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mylinecut.linecut.R;
import com.mylinecut.linecut.AppDirectoryHome2PagerAdapter;
import com.mylinecut.linecut.Place;
import com.mylinecut.linecut.PlaceRepository;
import com.panaceasoft.awesomematerial.utils.Utils;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AppDirectoryHome2Fragment extends Fragment {

    private AppDirectoryHome2PagerAdapter pagerAdapter;
    private ViewPager imageViewPager;

    private LinearLayout pager_indicator;
    private ImageView[] dots;
    private int dotsCount;

    private ConstraintLayout savedConstraintLayout;
    private ConstraintLayout likedConstraintLayout;
    private TextView discoverMoreTextView;

    // data variables
    List<Place> placeList;


    public AppDirectoryHome2Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.app_directory_home_2_fragment, container, false);

        initData();

        initUI(view);

        initDataBinding();

        initActions();

        return view;

    }

    private void initData() {
        // get featured Item List
        placeList = PlaceRepository.getPlaceList();

        pagerAdapter = new AppDirectoryHome2PagerAdapter(getContext(), placeList);
    }

    private void initUI(View view) {

        imageViewPager = view.findViewById(R.id.imageViewPager);

        pager_indicator = view.findViewById(R.id.viewPagerCountDots);

        discoverMoreTextView = view.findViewById(R.id.discoverMoreTextView);

        // Saved Cell Group
        ImageView savedImageView1 = view.findViewById(R.id.savedImageView1);
        int logoId = R.drawable.dir_shop_logo_1;
        Utils.setCornerRadiusImageToImageView(view.getContext(), savedImageView1, logoId, 10, 2, R.color.md_grey_200);

        ImageView savedImageView2 = view.findViewById(R.id.savedImageView2);
        logoId = R.drawable.dir_shop_logo_2;
        Utils.setCornerRadiusImageToImageView(view.getContext(), savedImageView2, logoId, 10, 2, R.color.md_grey_200);

        ImageView savedImageView3 = view.findViewById(R.id.savedImageView3);
        logoId = R.drawable.dir_shop_logo_3;
        Utils.setCornerRadiusImageToImageView(view.getContext(), savedImageView3, logoId, 10, 2, R.color.md_grey_200);

        ImageView savedImageView4 = view.findViewById(R.id.savedImageView4);
        logoId = R.drawable.dir_shop_logo_4;
        Utils.setCornerRadiusImageToImageView(view.getContext(), savedImageView4, logoId, 10, 2, R.color.md_grey_200);

        savedConstraintLayout = view.findViewById(R.id.savedConstraintLayout);


        // Liked Cell Group
        ImageView likedImageView1 = view.findViewById(R.id.likedImageView1);
        logoId = R.drawable.dir_shop_logo_5;
        Utils.setCornerRadiusImageToImageView(view.getContext(), likedImageView1, logoId, 10, 2, R.color.md_grey_200);

        ImageView likedImageView2 = view.findViewById(R.id.likedImageView2);
        logoId = R.drawable.dir_shop_logo_4;
        Utils.setCornerRadiusImageToImageView(view.getContext(), likedImageView2, logoId, 10, 2, R.color.md_grey_200);

        ImageView likedImageView3 = view.findViewById(R.id.likedImageView3);
        logoId = R.drawable.dir_shop_logo_3;
        Utils.setCornerRadiusImageToImageView(view.getContext(), likedImageView3, logoId, 10, 2, R.color.md_grey_200);

        ImageView likedImageView4 = view.findViewById(R.id.likedImageView4);
        logoId = R.drawable.dir_shop_logo_2;
        Utils.setCornerRadiusImageToImageView(view.getContext(), likedImageView4, logoId, 10, 2, R.color.md_grey_200);

        likedConstraintLayout = view.findViewById(R.id.likedConstraintLayout);

        // Shape Image View
        ImageView shapedImageView = view.findViewById(R.id.savedImageView5);
        ImageView shapedImageView2 = view.findViewById(R.id.likedImageView5);
        if (Utils.isRTL()) {
            shapedImageView.setRotationY(180);
            shapedImageView2.setRotationY(180);
        }

        // Profile
        ImageView profileImage1 = view.findViewById(R.id.savedImageView6);
        ImageView profileImage2 = view.findViewById(R.id.likedImageView6);

        Utils.setCornerRadiusImageToImageView(view.getContext(), profileImage1, R.drawable.profile1, 10, 10, R.color.colorPrimary);
        Utils.setCornerRadiusImageToImageView(view.getContext(), profileImage2, R.drawable.profile1, 10, 10, R.color.colorPrimary);


    }

    private void initDataBinding() {
        imageViewPager.setAdapter(pagerAdapter);

        setupSliderPagination();
    }

    private void initActions() {

        savedConstraintLayout.setOnClickListener((View v) -> Toast.makeText(getContext(), "Clicked Saved Collection", Toast.LENGTH_SHORT).show());
        likedConstraintLayout.setOnClickListener((View v) -> Toast.makeText(getContext(), "Clicked Liked Collection", Toast.LENGTH_SHORT).show());
        discoverMoreTextView.setOnClickListener((View v) -> Toast.makeText(getContext(), "Clicked Discover More", Toast.LENGTH_SHORT).show());

        imageViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                if (pager_indicator != null && dots == null) {
                    setupSliderPagination();
                }

                for (int i = 0; i < dotsCount; i++) {
                    dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));
                }

                dots[position].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        pagerAdapter.setOnItemClickListener((view, obj, position) -> Toast.makeText(getContext(), "Clicked : " + obj.imageName, Toast.LENGTH_SHORT).show());

    }

    public void setupSliderPagination() {

        dotsCount = pagerAdapter.getCount();

        if (dotsCount > 0 && dots == null) {

            dots = new ImageView[dotsCount];

            if (pager_indicator != null) {
                if (pager_indicator.getChildCount() > 0) {
                    pager_indicator.removeAllViewsInLayout();
                }
            }

            for (int i = 0; i < dotsCount; i++) {
                dots[i] = new ImageView(getContext());
                dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

                params.setMargins(4, 0, 4, 0);

                pager_indicator.addView(dots[i], params);
            }

            dots[0].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));

        }
    }
}

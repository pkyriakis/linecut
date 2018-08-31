package com.mylinecut.linecut.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mylinecut.linecut.R;
import com.mylinecut.linecut.object.Place;
import com.panaceasoft.awesomematerial.utils.Utils;

import java.util.List;

/**
 * Created by Panacea-Soft on 6/12/18.
 * Contact Email : teamps.is.cool@gmail.com
 */


public class AppDirectoryHome2PagerAdapter extends PagerAdapter {

    private Context context;
    private List<Place> placeList;
    private OnItemClickListener itemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, Place obj, int position);
    }

    public AppDirectoryHome2PagerAdapter(Context context, List<Place> placeList) {
        this.context = context;
        this.placeList = placeList;
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.itemClickListener = mItemClickListener;
    }

    @Override
    public int getCount() {
        return placeList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(layoutInflater != null) {
            View view = layoutInflater.inflate(R.layout.app_directory_home_2_view_pager_item, container, false);
            ImageView imageView = view.findViewById(R.id.placeImageView);

            ImageView shopIconImageView = view.findViewById(R.id.shopIconImageView);

            Context context = container.getContext();

            int id = Utils.getDrawableInt(context, placeList.get(position).imageName);
            Utils.setImageToImageView(context, imageView, id);

            int logoId = Utils.getDrawableInt(context, placeList.get(position).logoImage);
            Utils.setCornerRadiusImageToImageView(context, shopIconImageView, logoId, 10, 5, R.color.colorPrimary);

            TextView titleTextView = view.findViewById(R.id.titleTextView);
            TextView descTextView = view.findViewById(R.id.descTextView);

            String title = "Trending this week : " + placeList.get(position).name;

            titleTextView.setText(title);

            String desc = placeList.get(position).desc;
            try {
                if (desc.length() > 100) {
                    desc = desc.substring(0, 100) + "...";
                    descTextView.setText(desc);
                } else {
                    descTextView.setText(desc);
                }
            }catch (Exception e) {
                Log.e("TEAMPS", "instantiateItem: ", e);
            }

            ImageView shapedImageView = view.findViewById(R.id.shapeImageView);

            if(Utils.isRTL()) {
                shapedImageView.setRotationY(180);
            }

            ViewPager vp = (ViewPager) container;
            vp.addView(view, 0);

            // Listeners
            if (itemClickListener != null) {
                imageView.setOnClickListener((View v) -> itemClickListener.onItemClick(v, placeList.get(position), position));

            }

            return view;
        }

        return container.getRootView();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);
    }
}

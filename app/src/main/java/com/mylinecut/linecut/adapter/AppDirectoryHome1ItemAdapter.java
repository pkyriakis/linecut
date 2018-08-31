package com.mylinecut.linecut.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mylinecut.linecut.R;
import com.mylinecut.linecut.DirectoryCategory;
import com.panaceasoft.awesomematerial.utils.Utils;

import java.util.List;

/**
 * Created by Panacea-Soft on 6/10/18.
 * Contact Email : teamps.is.cool@gmail.com
 */


public class AppDirectoryHome1ItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<DirectoryCategory> placeList;
    private OnItemClickListener itemClickListener;


    public interface OnItemClickListener {
        void onItemClick(View view, DirectoryCategory obj, int position);

    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.itemClickListener = mItemClickListener;
    }

    public AppDirectoryHome1ItemAdapter(List<DirectoryCategory> placeList) {
        this.placeList = placeList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.app_directory_home_1_item, parent, false);

        return new ItemViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        if (viewHolder instanceof ItemViewHolder) {

            // Get Shop Item
            DirectoryCategory directoryCategory = placeList.get(position);

            // Convert Item Holder
            ItemViewHolder holder = (ItemViewHolder) viewHolder;

            // Get Context
            Context context = holder.itemImageView.getContext();

            // Set Data to UI
            holder.itemNameTextView.setText(directoryCategory.name);

            // Set Image
            int id = Utils.getDrawableInt(context, directoryCategory.image);
            Utils.setCircleImageToImageView(context, holder.itemImageView, id, 8, R.color.md_grey_300);

            // Listeners
            if (itemClickListener != null) {
                holder.itemImageView.setOnClickListener((View v) -> itemClickListener.onItemClick(v, placeList.get(position), position));
                holder.itemNameTextView.setOnClickListener((View v) -> itemClickListener.onItemClick(v, placeList.get(position), position));
            }
        }
    }

    @Override
    public int getItemCount() {
        return placeList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImageView;
        TextView itemNameTextView;


        ItemViewHolder(View view) {
            super(view);

            itemImageView = view.findViewById(R.id.itemImageView);
            itemNameTextView = view.findViewById(R.id.itemNameTextView);

        }
    }
}

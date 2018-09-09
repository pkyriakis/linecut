package com.mylinecut.linecut.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;
import com.mylinecut.linecut.R;
import com.mylinecut.linecut.object.Product;
import com.mylinecut.linecut.object.ProductCategory;
import com.mylinecut.linecut.object.User;
import com.panaceasoft.awesomematerial.utils.Utils;
import com.panaceasoft.awesomematerial.utils.ViewAnimationUtils;

import java.util.ArrayList;

public class ViewStoreMenuPageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    // define objects
    User user;
    ArrayList<Product> productList;
    ProductCategory category;
    FirebaseFirestore mDatabase;

    private OnItemClickListener itemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, Product product, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.itemClickListener = mItemClickListener;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_view_store_menu_item_1, parent, false);

        return new ProductViewHolder(itemView);
    }

    public ViewStoreMenuPageAdapter(User user, ProductCategory category, ArrayList<Product> productList) {
        this.user = user;
        this.category = category;
        this.productList = productList;
        this.mDatabase = FirebaseFirestore.getInstance();
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        if ( viewHolder instanceof ProductViewHolder) {
            Product product = productList.get(viewHolder.getAdapterPosition());

            ProductViewHolder holder = (ProductViewHolder) viewHolder;

            holder.productNameTextView.setText(product.getName());
            holder.productDescriptionTextView.setText(product.getDescription());

            Context context = holder.productHolderCardView.getContext();
            int id = R.drawable.baseline_heart_grey; // assume store does not belong to users favs
            // check if it does
            if (user.getFavoriteProductsIDs().contains(product.getProductId())){
                id = R.drawable.baseline_heart;
            }
            // set the appropriate heart
            //Utils.setImageToImageView(context,holder.favouriteImageView,id);

            // only one possible way to be servered
            if (category.getUnitlabels().size() == 1){
                holder.price1TextView.setText("$ "+String.valueOf(product.getPriceperunit().get(0)));
                holder.price2TextView.setVisibility(View.GONE);
                holder.priceUnit2TextView.setVisibility(View.GONE);
                holder.priceUnit1TextView.setVisibility(View.GONE);
            }
            // two ways to be served; set appropritate labels (eg glass bootle)
            else{
                holder.priceUnit1TextView.setText(category.getUnitlabels().get(0));
                holder.priceUnit2TextView.setText(category.getUnitlabels().get(1));
                holder.price1TextView.setText("$ "+String.valueOf(product.getPriceperunit().get(0)));
                holder.price2TextView.setText("$ "+String.valueOf(product.getPriceperunit().get(1)));
            }

            if ( itemClickListener != null ) {
                holder.productHolderCardView.setOnClickListener(v -> itemClickListener.onItemClick(v, productList.get(position), position));
                //holder.favouriteImageView.setOnClickListener((View v) -> favImageClickHandler(context, holder, product));
            }


        }
    }

    public void favImageClickHandler(Context context, ProductViewHolder holder, Product product){
        // check if producrt belogs to favs
        if (user.getFavoriteProductsIDs().contains(product.getProductId())){
            //if yes remove it
            user.removeStoreFromFav(product.getProductId());
            // update db
            mDatabase.collection("users").document(user.getUserId()).update("favoriteProductsIDs",user.getFavoriteProductsIDs()).addOnCompleteListener(task -> {
                if (task.isSuccessful()){
                    //show toast and change
                    // Toast.makeText(context,"Removed from favorites",Toast.LENGTH_SHORT).show();
                    int id = R.drawable.baseline_heart_grey;
                    Utils.setImageToImageView(context,holder.favouriteImageView,id);
                }else{
                    Log.e("FAILED_DATA_RETRIEVAL", "favImageClickHandler: " + task.getException().toString() );
                }
            });
        }else{
            // does not belong; add it
            user.addProductToFav(product.getProductId());
            // update db
            mDatabase.collection("users").document(user.getUserId()).update("favoriteProductsIDs",user.getFavoriteProductsIDs()).addOnCompleteListener(task -> {
                if (task.isSuccessful()){
                    //show toast and change
                    //Toast.makeText(context.this,"Added to" + " favorites",Toast.LENGTH_SHORT).show();
                    int id = R.drawable.baseline_heart;
                    Utils.setImageToImageView(context,holder.favouriteImageView,id);
                }else{
                    Log.e("FAILED_DATA_RETRIEVAL", "favImageClickHandler: " + task.getException().toString() );
                }
            });

        }

    }


    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        public TextView productNameTextView;
        public TextView productDescriptionTextView;
        public TextView priceUnit1TextView;
        public TextView priceUnit2TextView;
        public CardView productHolderCardView;
        public ImageView favouriteImageView;
        public TextView price1TextView;
        public TextView price2TextView;
        public ImageView priceRangeUpDownImageView;
        public View priceRangeLayout;

        public EditText quantityEditText;
        public ImageView minusImageView;
        public ImageView plusImageView;
        public CheckBox tip10CheckBox, tip15CheckBox, tip20CheckBox;
        public Button addToCartButton;


        public ProductViewHolder(View view) {
            super(view);
            productHolderCardView = view.findViewById(R.id.productHolderCardView);
            productNameTextView = view.findViewById(R.id.productNameTextView);
            productDescriptionTextView = view.findViewById(R.id.productDescriptionTextView);
            priceRangeUpDownImageView = view.findViewById(R.id.priceRangeUpDownImageView);
            priceRangeLayout = view.findViewById(R.id.orderOptionsLayout);
            priceRangeLayout.setVisibility(View.GONE);
            price1TextView = view.findViewById(R.id.price1TextView);
            price2TextView = view.findViewById(R.id.price2TextView);
            priceUnit1TextView = view.findViewById(R.id.priceUnit1TextView);
            priceUnit2TextView = view.findViewById(R.id.priceUnit2TextView);

            quantityEditText = view.findViewById(R.id.quantityEditText);
            minusImageView = view.findViewById(R.id.minusImageView);
            plusImageView = view.findViewById(R.id.plusImageView);
            tip10CheckBox = view.findViewById(R.id.tip10CheckBox);
            tip15CheckBox = view.findViewById(R.id.tip15CheckBox);
            tip20CheckBox = view.findViewById(R.id.tip20CheckBox);
            addToCartButton = view.findViewById(R.id.addToCartButton);

            priceRangeUpDownImageView.setOnClickListener((View v) -> {
                boolean show = Utils.toggleUpDownWithAnimation(v);
                if (show) {
                    ViewAnimationUtils.expand(priceRangeLayout);
                } else {
                    ViewAnimationUtils.collapse(priceRangeLayout);
                }
            });

            // increase qty when plus is clicked
            plusImageView.setOnClickListener(v -> {
                Integer curQ = Integer.valueOf(quantityEditText.getText().toString());
                quantityEditText.setText(String.valueOf(curQ+1));
            });
            //decrease by one when minus is clicked
            minusImageView.setOnClickListener(v -> {
                Integer curQ = Integer.valueOf(quantityEditText.getText().toString());
                Integer newQ = curQ - 1;
                if (newQ < 1){
                    newQ = 1;
                }
                quantityEditText.setText(String.valueOf(newQ));
            });
            // listeners for tip ckeckboxes
            tip10CheckBox.setOnClickListener(v -> {
                if (!tip10CheckBox.isChecked()){
                    tip10CheckBox.setChecked(true);
                }
                tip15CheckBox.setChecked(false);
                tip20CheckBox.setChecked(false);
            });
            tip15CheckBox.setOnClickListener(v -> {
                if (!tip15CheckBox.isChecked()){
                    tip15CheckBox.setChecked(true);
                }
                tip10CheckBox.setChecked(false);
                tip20CheckBox.setChecked(false);
            });
            tip20CheckBox.setOnClickListener(v -> {
                if (!tip20CheckBox.isChecked()){
                    tip20CheckBox.setChecked(true);
                }
                tip15CheckBox.setChecked(false);
                tip10CheckBox.setChecked(false);
            });



        }
    }

}

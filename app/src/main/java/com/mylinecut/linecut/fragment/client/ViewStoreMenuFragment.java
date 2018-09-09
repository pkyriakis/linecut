package com.mylinecut.linecut.fragment.client;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.gson.Gson;
import com.mylinecut.linecut.R;
import com.mylinecut.linecut.adapter.ViewStoreMenuPageAdapter;
import com.mylinecut.linecut.object.Product;
import com.mylinecut.linecut.object.ProductCategory;
import com.mylinecut.linecut.object.User;
import com.panaceasoft.awesomematerial.utils.Utils;

import java.util.ArrayList;

import javax.annotation.Nullable;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ViewStoreMenuFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ViewStoreMenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewStoreMenuFragment extends Fragment {

    // page adapter used for view product in a recycler view
    ViewStoreMenuPageAdapter adapter;

    // user object
    User user;

    // category of the products
    ProductCategory category;

    // the list of products to bw shown
    ArrayList<Product> productArrayList;

    // RecyclerView
    RecyclerView recyclerView;

    // root view componennt
    View view;

    FirebaseFirestore mDatabase;

    private OnFragmentInteractionListener mListener;

    public ViewStoreMenuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the productArrayList as parameter
     * Each product is converted to a json string and all
     * of those strings are added to a bundle
     *
     */
    // TODO: Rename and change types and number of parameters
    public static ViewStoreMenuFragment newInstance(User user, ProductCategory category, ArrayList<Product> productArrayList) {
        ViewStoreMenuFragment fragment = new ViewStoreMenuFragment();
        Bundle args = new Bundle();
        Gson json = new Gson();

        // put use into the bundle
        args.putString("userJson",json.toJson(user));
        // put the category into the bundle
        args.putString("categoryJson", json.toJson(category));

        // put product list to a bundle; key is not really needed
        for(Product product : productArrayList){
            args.putString(product.getName(), json.toJson(product));
        }

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // get arguments and set them to corresponding fields
        if (getArguments() != null) {
            Gson json = new Gson();
            productArrayList = new ArrayList<>();
            // iterate over all keys in the arguments bundle
            for(String key : getArguments().keySet()){
                // if the key is categoryJson then the corresponding item is the category object
                if (key.equals("categoryJson")) {
                    category = json.fromJson(getArguments().get(key).toString(), ProductCategory.class);
                }
                // if userJson then the user object
                else if (key.equals("userJson")){
                    user = json.fromJson(getArguments().get(key).toString(),User.class);
                }else {
                    // all other key correspond to product objects
                    Product product = json.fromJson(getArguments().get(key).toString(), Product.class);
                    productArrayList.add(product);
                }
            }
        }

        mDatabase = FirebaseFirestore.getInstance();
        mDatabase.collection("users").document(user.getUserId()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w("", "Listen failed.", e);
                    return;
                }

                if (documentSnapshot != null && documentSnapshot.exists()) {
                    Log.d("", "Current data: " + documentSnapshot.getData());
                } else {
                    Log.d("", "Current data: null");
                }
            }
        });

        // init firebase
        mDatabase = FirebaseFirestore.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_view_store_menu, container, false);

        initUi();

        initDataBindings();

        initActions();

        return view;
    }



    public void initUi(){
        // get list adapter
        adapter = new ViewStoreMenuPageAdapter(user, category, productArrayList);

        // get recycler view
        recyclerView = view.findViewById(R.id.prodcutRecyclerView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    public void initDataBindings(){
        // bind adapter to recycler
        recyclerView.setAdapter(adapter);
    }

    // there's only two actions: when user clicks the product card view and when she clicks the fav image
    // first one is handled by this method, second one by the page adapter
    private void initActions()
    {
        adapter.setOnItemClickListener((view, product, position) -> {

            // need to redirect to a view product activity with a simple add to cart and/or order now button

        });
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

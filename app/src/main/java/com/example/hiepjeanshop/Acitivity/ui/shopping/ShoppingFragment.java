package com.example.hiepjeanshop.Acitivity.ui.shopping;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.hiepjeanshop.Adapter.ShoppingAdapter;
import com.example.hiepjeanshop.Moder.Shopping;
import com.example.hiepjeanshop.R;
import com.example.hiepjeanshop.api.APIUrls;
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ShoppingFragment extends Fragment {

    private WormDotsIndicator worm_dots_indicator;
    private RecyclerView rcvShopping;
    private ShoppingAdapter shoppingAdapter;
    private List<Shopping> shoppingList;
    private List<Shopping> shoppings = new ArrayList<Shopping>();
    private Shopping shopping;
    private ProgressBar progressBar;
    private ViewPager2 viewPager2;
    private ViewpagerAdapter viewpagerAdapter;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_shopping, container, false);
        rcvShopping = root.findViewById(R.id.rcvShopping);

        progressBar = root.findViewById(R.id.proBar);

        //ngang
//        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
//        rcvShoppingNgang.setLayoutManager(layoutManager1);

        //dạng grid
        rcvShopping.setHasFixedSize(true);
        rcvShopping.setLayoutManager(new GridLayoutManager(getContext(), 2));

        shoppingList = new ArrayList<>();
        String url = APIUrls.URL_GET_PRODUCTS;

        AndroidNetworking.get(url)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray products = response.getJSONArray("products");
                            for (int i = 0; i < products.length(); i++) {
                                shopping = new Shopping(products.getJSONObject(i));
                                shoppingList.add(shopping);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        shoppingAdapter = new ShoppingAdapter(shoppingList, getContext());
                        rcvShopping.setAdapter(shoppingAdapter);
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(getContext(), anError.toString(), Toast.LENGTH_SHORT).show();
                    }
                });


        //viewpager
        ArrayList<Integer> imageList = new ArrayList<>();
        imageList.add(R.drawable.banner);
        imageList.add(R.drawable.banner2);
        imageList.add(R.drawable.banner3);
        imageList.add(R.drawable.banner4);
        imageList.add(R.drawable.banner5);


        viewPager2 = root.findViewById(R.id.pager);
        viewpagerAdapter = new ViewpagerAdapter(getActivity(),imageList);
        viewPager2.setAdapter(viewpagerAdapter);
        viewPager2.setPageTransformer(new DepthPageTransformer());

        worm_dots_indicator = (WormDotsIndicator) root.findViewById(R.id.worm_dots_indicator);
        worm_dots_indicator.setViewPager2(viewPager2);
        return root;


    }

}

package com.example.lckvappjudanten;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ProductRecyclerFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.product_recycler_view, container, false);


        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        RecyclerView productRecyclerview = getView().findViewById(R.id.productRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        productRecyclerview.setLayoutManager(layoutManager);
        productRecyclerview.hasFixedSize();


        Product[] products = new Product[10];

        products[0] = new Product("Chips", 0.35);
        products[1] = new Product("Drop", 0.08);
        products[2] = new Product("Bananensnoepje", 0.12);
        products[3] = new Product("M&M's", 0.47);
        products[4] = new Product("Skittles", 0.26);
        products[5] = new Product("Apenkoppen", 0.21);
        products[6] = new Product("Mars", 0.42);
        products[7] = new Product("Snickers", 0.42);
        products[8] = new Product("Water", 0.20);
        products[9] = new Product("Frisdrank", 0.70);

        ProductAdapter productAdapter = new ProductAdapter(products);
        productRecyclerview.setAdapter(productAdapter);
    }
}

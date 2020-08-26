package com.example.lckvappjudanten;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ProductOverviewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_overview);

        RecyclerView productRecyclerview = findViewById(R.id.productRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        productRecyclerview.setLayoutManager(layoutManager);
        productRecyclerview.hasFixedSize();


        String[] names = new String[10];
        String[] prices = new String[10];

        names[0] = "Chips";
        names[1] = "Drop";
        names[2] = "Bananensnoepje";
        names[3] = "M&M's";
        names[4] = "Skittles";
        names[5] = "Apenkoppen";
        names[6] = "Mars";
        names[7] = "Snickers";
        names[8] = "Water";
        names[9] = "Frisdrank";

        prices[0] = "€0.35";
        prices[1] = "€0.08";
        prices[2] = "€0.12";
        prices[3] = "€0.47";
        prices[4] = "€0.26";
        prices[5] = "€0.21";
        prices[6] = "€0.42";
        prices[7] = "€0.42";
        prices[8] = "€0.20";
        prices[9] = "€0.70";


        ProductAdapter productAdapter = new ProductAdapter(names, prices);
        productRecyclerview.setAdapter(productAdapter);
    }
}

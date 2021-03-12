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

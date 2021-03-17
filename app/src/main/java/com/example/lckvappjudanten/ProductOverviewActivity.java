package com.example.lckvappjudanten;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ProductOverviewActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_overview);
        Button btn = findViewById(R.id.addProductButton);
        btn.setOnClickListener(this);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ProductRecyclerFragment productRecyclerFragment = new ProductRecyclerFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean("isStore", false);
        productRecyclerFragment.setArguments(bundle);
        ft.replace(R.id.product_recycler, productRecyclerFragment);
        ft.commit();
    }


    @Override
    public void onClick(View v) {
        CustomDialog cdd=new CustomDialog(v.getContext(), this, "Product toevoegen", "Naam product", "Prijs");
        cdd.show();
    }
}

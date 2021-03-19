package com.example.lckvappjudanten;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private Product[] products;
    private Boolean isStore;
    private Activity a;

    public ProductAdapter(Product[] products, Boolean isStore, Activity a){
        this.products = products;
        this.isStore = isStore;
        this.a = a;

    }

    public void setProducts(Product[] products) {
        this.products = products;
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder{
        public TextView productName;
        public TextView productPrice;

        public ProductViewHolder(View v){
            super(v);
            productName = v.findViewById(R.id.productName);
            productPrice = v.findViewById(R.id.productPrice);
        }
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_card, parent, false);
        ProductViewHolder productViewHolder = new ProductViewHolder(v);
        return productViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, final int position) {
        holder.productName.setText(products[position].getName());
        if (isStore) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar snackbar = Snackbar.make(v, products[position].getName() + " verkocht!", Snackbar.LENGTH_SHORT);
                    ((MainActivity) a).setSelectedProductPrice(products[position].getPrice());
                    ((MainActivity) a).makeSale();
                    snackbar.show();
                }
            });
        };
        NumberFormat df = DecimalFormat.getInstance();
        df.setMinimumFractionDigits(2);
        df.setMaximumFractionDigits(4);
        df.setRoundingMode(RoundingMode.DOWN);
        String price = "€" + df.format(products[position].getPrice());
        holder.productPrice.setText(price);
        setScaleAnimation(holder.itemView);
    }

    private void setScaleAnimation(View view) {
        ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(1000);
        view.startAnimation(anim);
    }


    @Override
    public int getItemCount() {
        return products.length;
    }


}

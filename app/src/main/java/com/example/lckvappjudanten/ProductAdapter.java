package com.example.lckvappjudanten;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private Product[] products;

    public ProductAdapter(Product[] products){
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
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.productName.setText(products[position].getName());

        NumberFormat df = DecimalFormat.getInstance();
        df.setMinimumFractionDigits(2);
        df.setMaximumFractionDigits(4);
        df.setRoundingMode(RoundingMode.DOWN);
        String price = "€" + df.format(products[position].getPrice());
        holder.productPrice.setText(price);
    }

    @Override
    public int getItemCount() {
        return products.length;
    }


}

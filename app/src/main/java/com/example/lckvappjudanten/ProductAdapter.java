package com.example.lckvappjudanten;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private String[] names;
    private String[] prices;

    public ProductAdapter(String[] names, String[] prices){
        this.names = names;
        this.prices = prices;
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
        holder.productName.setText(names[position]);
        holder.productPrice.setText(prices[position]);
    }

    @Override
    public int getItemCount() {
        return names.length;
    }


}

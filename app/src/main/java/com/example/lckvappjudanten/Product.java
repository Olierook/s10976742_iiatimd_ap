package com.example.lckvappjudanten;

import java.math.BigInteger;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Product {
    @NonNull
    @PrimaryKey
    private String name;
    @NonNull
    @ColumnInfo
    private double price;


    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

package com.example.lckvappjudanten;

import java.util.UUID;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import static java.util.UUID.fromString;

@Entity
public class Camper {
    @ColumnInfo
    private String name;
    @ColumnInfo
    private int user_id;
    @ColumnInfo
    private Double startingBalance;
    @ColumnInfo
    private Double currentBalance;
    @NonNull
    @PrimaryKey
    private String id;


    public Camper(String name, int user_id, Double startingBalance, Double currentBalance) {
        this.name = name;
        this.startingBalance = startingBalance;
        this.user_id = user_id;
        this.currentBalance = currentBalance;
        this.id = UUID.randomUUID().toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getStartingBalance() {
        return startingBalance;
    }

    public void setStartingBalance(Double startingBalance) {
        this.startingBalance = startingBalance;
    }

    public Double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(Double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

}

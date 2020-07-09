package com.example.lckvappjudanten;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Camper {
    @ColumnInfo
    private String name;
    @ColumnInfo
    private int tentNumber;
    @ColumnInfo
    private Double startingBalance;
    @ColumnInfo
    private Double currentBalance;
    @PrimaryKey
    private int uuid;


    public Camper(String name, int tentNumber, Double startingBalance, Double currentBalance, int uuid) {
        this.name = name;
        this.tentNumber = tentNumber;
        this.startingBalance = startingBalance;
        this.currentBalance = currentBalance;
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTentNumber() {
        return tentNumber;
    }

    public void setTentNumber(int tentNumber) {
        this.tentNumber = tentNumber;
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

    public int getUuid() {
        return uuid;
    }

    public void setUuid(int uuid) {
        this.uuid = uuid;
    }

}

package com.android.csci455_group2.nyit.csci455_seniorproject_group2;

import java.io.Serializable;

/**
 * Created by nicholaslastella on 5/3/16.
 */
public class Transaction implements Serializable {

    private int itemLocation;
    private int purchaseAmount;
    private String purchaseDate;
    private int year;
    private int month;
    private int day;

    public Transaction(int pItemLoc, int pAmount, String pDate){
        itemLocation  = pItemLoc;
        purchaseAmount = pAmount;
        purchaseDate = pDate;
    }

    public void convertDate(){
        String nums[] = purchaseDate.split("/");
        year = Integer.parseInt(nums[0]);
        month = Integer.parseInt(nums[1]);
        day = Integer.parseInt(nums[2]);
    }

    public int getItemLocation(){
        //returns the item location in the array from enter.java
        return itemLocation;
    }

    public String getPurchaseDate(){
        return purchaseDate;
    }

    public int getPurchaseAmount(){
        return purchaseAmount;
    }

    public String toSaveable(){
        return getItemLocation() + "," + getPurchaseAmount() + "," + getPurchaseDate();
    }
}

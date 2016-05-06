package com.android.csci455_group2.nyit.csci455_seniorproject_group2;

/**
 * Created by nicholaslastella on 5/2/16.
 */
public class comparableItem {

    private String itemName;
    private double itemPrice;
    private int count;

    public comparableItem(String name, double price){
        itemName = name;
        itemPrice = price;
    }

    public String getName(){
        return itemName;
    }

    public double getPrice(){
        return itemPrice;
    }

    public void calcCount(double money){
        count = (int)(money/itemPrice);
    }

    public String toSring(){
        return count + " " + itemName + " at " + itemPrice + " each.";
    }

    public String toSaveable(){
        return getName() +  "," + getPrice();
    }
}

package com.android.csci455_group2.nyit.csci455_seniorproject_group2;

import java.io.Serializable;

/**
 * Created by nicholaslastella on 3/2/16.
 */
public class recordableItem implements Serializable {

    private String item_name;
    private double item_price;
    private int purchase_count;

    public recordableItem(String name, double price){
        item_name = name;
        item_price = price;
        purchase_count = 0;
    }

    public recordableItem(String name, double price, int pc){
        item_name = name;
        item_price = price;
        purchase_count = pc;
    }

    public String getName(){
        return item_name;
    }

    public double getPrice(){
        return item_price;
    }

    public double getAmountSpent(){
        return item_price * purchase_count;
    }

    public int getPurchaseCount() { return purchase_count; }

    public void purchaseItem(){
        purchase_count++;
    }

    public void purchaseItem(int amount){
        purchase_count += amount;
    }

    public String getListName(){ return getName() + " - $" + getPrice(); }

    public String toString(){
        return getName() + " at $" + getPrice() + " each,\nFor a total of: $" + getAmountSpent();
    }

    public String toSaveable(){
        return getName() + "," + getPrice() + "," + getPurchaseCount();
    }
}

//com.android.csci455_group2.nyit.csci455_seniorproject_group2
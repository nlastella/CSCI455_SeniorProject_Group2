package com.android.csci455_group2.nyit.csci455_seniorproject_group2;

/**
 * Created by nicholaslastella on 3/2/16.
 */
public class recordableItem {

    private String item_name;
    private double item_price;
    private int purchase_count;

    public recordableItem(String name, double price){
        item_name = name;
        item_price = price;
        purchase_count = 0;
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

    public void purchaseItem(){
        purchase_count++;
    }

    public void purchaseItem(int amount){
        purchase_count += amount;
    }

    public String toString(){
        return getName() + " at $" + getPrice() + " each\nFor a total of: " + getAmountSpent();
    }
}

//com.android.csci455_group2.nyit.csci455_seniorproject_group2
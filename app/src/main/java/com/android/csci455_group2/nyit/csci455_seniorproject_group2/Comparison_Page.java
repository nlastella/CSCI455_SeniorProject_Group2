package com.android.csci455_group2.nyit.csci455_seniorproject_group2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static com.android.csci455_group2.nyit.csci455_seniorproject_group2.UserDataViewActivity.*;

public class Comparison_Page extends AppCompatActivity {
    ArrayList<comparableItem> Items;
TextView moneySpent;
    ListView CompareItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comparison__page);
        //CONVERTING TOTAL SPENT FROM double TO A DISPLAYABLE String
        String moneySpentDisp = Double.toString(UserDataViewActivity.totalSpent);
        //SENDING STRING DISPLAY TO ACTIVITY
        moneySpent = (TextView)findViewById(R.id.totalspentview);
        moneySpent.setText(moneySpentDisp);

        Items= new ArrayList<>();
        Items.add(new comparableItem("football", 10 ));
        Items.add(new comparableItem("baseball mitt", 10 ));
        Items.add(new comparableItem("Video Game", 40 ));
        Items.add(new comparableItem("New Nikes", 100 ));
        Items.add(new comparableItem("New Phone", 300));
        Items.add(new comparableItem("Watch", 80 ));
        Items.add(new comparableItem("Designer Jeans", 60 ));
        Items.add(new comparableItem("2 movie tickets", 20 ));
        Items.add(new comparableItem("Dinner for 2 ", 50 ));
        Items.add(new comparableItem("Dr Dre BlueTooth Headphones", 200 ));




        ArrayList<String> Items_String=new ArrayList<>();
        for (int i = 0; i<Items.size();i ++){
            Items.get(i).calcCount(UserDataViewActivity.totalSpent);
            Items_String.add(Items.get(i).toSring());
        }




        ArrayAdapter<String>Adapt=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,Items_String);

        CompareItems=(ListView) findViewById(R.id.Compare_Items);
        CompareItems.setAdapter(Adapt);

            }


    }






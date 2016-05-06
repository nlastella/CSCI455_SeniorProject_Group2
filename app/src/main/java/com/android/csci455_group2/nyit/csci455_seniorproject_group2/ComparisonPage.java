package com.android.csci455_group2.nyit.csci455_seniorproject_group2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ComparisonPage extends AppCompatActivity {

    ArrayList<comparableItem> Items;
    TextView moneySpent;
    ListView CompareItems;
    Button mbtnCInput;
    Button mbtnView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comparison_page);

        Items = new ArrayList<>();
        if(!checkCompareListMade()){
            createItemCompareList();
        }

        String moneySpentDisp = Double.toString(UserDataViewActivity.totalSpent);
        moneySpent = (TextView)findViewById(R.id.compareValue);
        moneySpent.setText(moneySpentDisp);

        ArrayList<String> Items_String=new ArrayList<>();
        for (int i = 0; i<Items.size();i ++){
            Items.get(i).calcCount(UserDataViewActivity.totalSpent);
            Items_String.add(Items.get(i).toSring());
        }

        ArrayAdapter<String> Adapt=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,Items_String);
        CompareItems=(ListView) findViewById(R.id.compareList);
        CompareItems.setAdapter(Adapt);

        mbtnCInput = (Button) findViewById(R.id.btnCInput);
        mbtnView = (Button) findViewById(R.id.btnView);

        mbtnCInput.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                startActivity(new Intent(ComparisonPage.this, enter.class));
            }
        });

        mbtnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ComparisonPage.this, UserDataViewActivity.class));
            }
        });
    }

    public void createItemCompareList(){
        Items.clear();
        Items.add(new comparableItem("Football", 15 ));
        Items.add(new comparableItem("Baseball Mitt", 10 ));
        Items.add(new comparableItem("Video Game", 40 ));
        Items.add(new comparableItem("New Nikes", 100 ));
        Items.add(new comparableItem("New Phone", 300));
        Items.add(new comparableItem("Watch", 80 ));
        Items.add(new comparableItem("Designer Jeans", 60 ));
        Items.add(new comparableItem("Movie tickets", 20 ));
        Items.add(new comparableItem("Dinner for two", 50 ));
        Items.add(new comparableItem("Dr Dre BlueTooth Headphones", 200 ));
    }

    public boolean checkCompareListMade(){
        if(Items.size() == 0){
            return false;
        }
        return true;
    }

}

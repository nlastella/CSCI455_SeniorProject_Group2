package com.android.csci455_group2.nyit.csci455_seniorproject_group2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class UserDataViewActivity extends AppCompatActivity {

    ListView mlistItemsPurchased;
    ArrayList<recordableItem> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data_view);

        //CREATING AND FILLING A TEMPORARY ARRAYLIST OF STRINGS AS A TEST
        //mlistItemsPurchased = (ListView) findViewById(R.id.listItemsPurchased);

        itemList = new ArrayList<>();
        itemList.add(new recordableItem("Cigarettes", 10));
        itemList.add(new recordableItem("Alcohol", 12));
        itemList.add(new recordableItem("Other", 5));

        //CONVERTING THE ARRAYLIST TO AN ARRAY
        String[] itemListArr = new String[itemList.size()];
        for(int i = 0; i < itemList.size(); i++){
            itemListArr[i] = itemList.get(i).toString();
        }


        //private String arr[] = convert
        //android.widget.ListAdapter adapter;
        //adapter = new android.widget.ListAdapter(this, itemList);

        //ArrayAdapter<recordableItem> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemListArr);
        //mlistItemsPurchased.setAdapter(adapter);

    }
}

package com.android.csci455_group2.nyit.csci455_seniorproject_group2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import com.android.csci455_group2.nyit.csci455_seniorproject_group2.R;

public class UserDataViewActivity extends AppCompatActivity {

    ListView mlistItemsPurchased;
    ArrayList<recordableItem> itemList;
    double totalSpent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data_view);


        //CREATING AND FILLING A TEMPORARY ARRAYLIST OF recordableItems AS A TEST
        itemList = new ArrayList<>();
        itemList.add(new recordableItem("Cigarettes", 10));
        itemList.add(new recordableItem("Alcohol", 12));
        itemList.add(new recordableItem("Other", 5));

        itemList.get(0).purchaseItem(5);
        itemList.get(1).purchaseItem(2);
        itemList.get(2).purchaseItem(3);


        //CONVERTING THE ArrayList<recordableItem> TO ArrayList<String> & ADDING TOTAL SPENT
        ArrayList<String> itemListArrStrings = new ArrayList<>();
        for(int i = itemList.size()-1; i >= 0; i--){
            itemListArrStrings.add(itemList.get(i).toString());
            totalSpent += itemList.get(i).getAmountSpent();
        }

        //CONVERTING THE LIST TO BE VIEWED IN THE ACTIVITY
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemListArrStrings);
        mlistItemsPurchased = (ListView) findViewById(R.id.listItemsPurchased);
        mlistItemsPurchased.setAdapter(adapter);



        //CONVERTING TOTAL SPENT TO A DISPLAYABLE STRING
        String moneySpentDisplay = Double.toString(totalSpent);
        //SENDING STRING DISPLAY TO ACTIVITY
        TextView moneySpentView = new TextView(this);
        moneySpentView = (TextView)findViewById(R.id.moneySpentTotalValue);
        moneySpentView.setText(moneySpentDisplay);

    }
}

package com.android.csci455_group2.nyit.csci455_seniorproject_group2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import com.android.csci455_group2.nyit.csci455_seniorproject_group2.R;

public class UserDataViewActivity extends AppCompatActivity {

    ListView mlistItemsPurchased;
    TextView moneySpentView;
    Button mbtnInput;
    Button mbtnCompare;
    public static double totalSpent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data_view);

        //CONVERTING THE ArrayList<recordableItem> TO ArrayList<String> & ADDING TOTAL SPENT
        ArrayList<String> itemListArrStrings = new ArrayList<>();
        totalSpent = 0;
        for(int i = 0; i < enter.itemList.size(); i++){
            itemListArrStrings.add(enter.itemList.get(i).toString());
            totalSpent += enter.itemList.get(i).getAmountSpent();
        }

        //CONVERTING THE LIST TO BE VIEWED IN THE ACTIVITY
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemListArrStrings);
        mlistItemsPurchased = (ListView) findViewById(R.id.listItemsPurchased);
        mlistItemsPurchased.setAdapter(adapter);

        //CONVERTING TOTAL SPENT FROM double TO A DISPLAYABLE String
        String moneySpentDisplay = Double.toString(totalSpent);
        //SENDING STRING DISPLAY TO ACTIVITY
        moneySpentView = (TextView)findViewById(R.id.moneySpentTotalValue);
        moneySpentView.setText(moneySpentDisplay);

        mbtnInput = (Button) findViewById(R.id.btnInput);
        mbtnCompare = (Button) findViewById(R.id.btnCompare);

        mbtnInput.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                startActivity(new Intent(UserDataViewActivity.this, enter.class));
            }
        });

        mbtnCompare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserDataViewActivity.this, ComparisonPage.class));
            }
        });
    }

}

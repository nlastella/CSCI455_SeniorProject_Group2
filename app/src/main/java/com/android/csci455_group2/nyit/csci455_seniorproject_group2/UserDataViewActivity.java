package com.android.csci455_group2.nyit.csci455_seniorproject_group2;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import com.android.csci455_group2.nyit.csci455_seniorproject_group2.R;

public class UserDataViewActivity extends AppCompatActivity {

    ListView mlistItemsPurchased;
    TextView moneySpentView;
    ArrayList<recordableItem> itemList;
<<<<<<< HEAD
    public double totalSpent;
=======
    public static double totalSpent;
>>>>>>> master
    FeedReaderDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data_view);

        mDbHelper = new FeedReaderDbHelper(findViewById(R.layout.activity_user_data_view).getContext());
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        //SPECIFYING WHICH COLUMNS TO PULL FROM THE DATABASE;
        String[] projection = {
                FeedReaderItemContract.FeedEntryItem.COLUMN_NAME_ITEM_ID,
                FeedReaderItemContract.FeedEntryItem.COLUMN_NAME_ITEM_NAME,
                FeedReaderItemContract.FeedEntryItem.COLUMN_NAME_PRICE
        };

        //CREATING NECESSARY VARIABLES FOR THE CURSOR QUERY
        String selection = "";
        String selectionArgs[] = {};
        String sortOrder = FeedReaderItemContract.FeedEntryItem.COLUMN_NAME_ITEM_ID;

        //CREATING A Cursor FROM THE DATABASE QUERY
        Cursor c = db.query(
                FeedReaderItemContract.FeedEntryItem.TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        //CONVERT Cursor QUERY TO A READABLE ARRAY(S)
        String[] itemNames = new String[c.getCount()];
        double[] itemPrices = new double[c.getCount()];
        for(int i = 0; i < c.getCount(); i++){
            if(c.moveToPosition(i)){

            }
        }

        //CREATING AND FILLING A TEMPORARY ArrayList OF recordableItems AS A TEST
        //TO BE LATER GRABBED FROM A DATABASE
        /*itemList = new ArrayList<>();
        itemList.add(new recordableItem("Cigarettes", 10));
        itemList.add(new recordableItem("Alcohol", 12));
        itemList.add(new recordableItem("Other", 5));*/

        //CREATING AND FILLING ITEM ArrayList FROM ARRAYS CREATED BY A DB QUERY
        itemList = new ArrayList<>();
        for(int i = 0; i < itemNames.length; i++){
            itemList.add(new recordableItem(itemNames[i], itemPrices[i]));
        }

        //ADDING A THEORETICAL PURCHASE COUNT TO EACH ITEM
        itemList.get(0).purchaseItem(5);
        itemList.get(1).purchaseItem(2);
        itemList.get(2).purchaseItem(3);

        //TO BE USED ONCE A TRANSACTION TABLE IS IMPLEMENTED
        /*for(int i = 0; i < purchaseCounts.length; i++){
            itemList.get(i).purchaseItem(purchaseCounts[i]);
        }*/


        //CONVERTING THE ArrayList<recordableItem> TO ArrayList<String> & ADDING TOTAL SPENT
        ArrayList<String> itemListArrStrings = new ArrayList<>();
        for(int i = 0; i < itemList.size(); i++){
            itemListArrStrings.add(itemList.get(i).toString());
            totalSpent += itemList.get(i).getAmountSpent();
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
    }
}

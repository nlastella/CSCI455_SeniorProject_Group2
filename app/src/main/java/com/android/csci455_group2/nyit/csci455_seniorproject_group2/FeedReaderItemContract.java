package com.android.csci455_group2.nyit.csci455_seniorproject_group2;

import android.provider.BaseColumns;

/**
 * Created by nicholaslastella on 4/19/16.
 */
public class FeedReaderItemContract {

    public FeedReaderItemContract() {}

    public static abstract class FeedEntryItem implements BaseColumns {
        public static final String TABLE_NAME = "Item";
        public static final String COLUMN_NAME_ITEM_ID = "item_id";
        public static final String COLUMN_NAME_ITEM_NAME = "item_name";
        public static final String COLUMN_NAME_PRICE = "item_price";
    }


    public static final String TEXT_TYPE = " TEXT";
    public static final String DECIMAL_TYPE = " DECIMAL(100)";
    public static final String COMMA_SEP = ",";
    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedEntryItem.TABLE_NAME + " (" +
                    FeedEntryItem.COLUMN_NAME_ITEM_ID + " INTEGER PRIMARY KEY," +
                    FeedEntryItem.COLUMN_NAME_ITEM_NAME + TEXT_TYPE + COMMA_SEP +
                    FeedEntryItem.COLUMN_NAME_PRICE + DECIMAL_TYPE +
        " )";

    public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + FeedEntryItem.TABLE_NAME;

}

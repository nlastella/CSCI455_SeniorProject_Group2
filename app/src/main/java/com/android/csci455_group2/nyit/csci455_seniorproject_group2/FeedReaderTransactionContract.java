package com.android.csci455_group2.nyit.csci455_seniorproject_group2;

import android.provider.BaseColumns;

/**
 * Created by nicholaslastella on 4/22/16.
 */
public class FeedReaderTransactionContract {

    public FeedReaderTransactionContract() {}

    public static abstract class FeedEntryTransaction implements BaseColumns {
        public static final String TABLE_NAME = "Transaction";
        public static final String COLUMN_NAME_TRANSACTION_ID = "transaction_id";
        public static final String COLUMN_NAME_USER_ID = "user_id";
        public static final String COLUMN_NAME_ITEM_ID = "item_id";
        public static final String COLUMN_NAME_PURCHASE_AMOUNT = "purchase_amount";
        public static final String COLUM_NAME_DATE = "transaction_date";
    }


    public static final String TEXT_TYPE = " TEXT";
    public static final String DECIMAL_TYPE = " DECIMAL(100)";
    public static final String DATE_TYPE = " DATETIME";
    public static final String INT_TYPE = " INT";
    public static final String COMMA_SEP = ",";

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedEntryTransaction.TABLE_NAME + " (" +
                    FeedEntryTransaction.COLUMN_NAME_TRANSACTION_ID + " INTEGER PRIMARY KEY," +
                    FeedEntryTransaction.COLUMN_NAME_USER_ID + " INTEGER FOREIGN KEY," +
                    FeedEntryTransaction.COLUMN_NAME_ITEM_ID + " INTEGER FOREIGN KEY," +
                    FeedEntryTransaction.COLUMN_NAME_PURCHASE_AMOUNT + INT_TYPE + COMMA_SEP +
                    FeedEntryTransaction.COLUM_NAME_DATE + DATE_TYPE +
                    " )";

    public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + FeedEntryTransaction.TABLE_NAME;
}

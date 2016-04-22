package com.android.csci455_group2.nyit.csci455_seniorproject_group2;

import android.provider.BaseColumns;

/**
 * Created by nicholaslastella on 4/22/16.
 */
public class FeedReaderUserContract {

    public FeedReaderUserContract() {}

    public static abstract class FeedEntryUser implements BaseColumns {
        public static final String TABLE_NAME = "User";
        public static final String COLUMN_NAME_USER_ID = "user_id";
        public static final String COLUMN_NAME_USER_NAME = "user_name";
        public static final String COLUMN_NAME_USER_EMAIL = "user_email";
        public static final String COLUM_NAME_USER_PASSWORD = "user_password";
    }


    public static final String TEXT_TYPE = " TEXT";
    public static final String DECIMAL_TYPE = " DECIMAL(100)";
    public static final String DATE_TYPE = " DATETIME";
    public static final String INT_TYPE = " INT";
    public static final String COMMA_SEP = ",";

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedEntryUser.TABLE_NAME + " (" +
                    FeedEntryUser.COLUMN_NAME_USER_ID + " INTEGER PRIMARY KEY," +
                    FeedEntryUser.COLUMN_NAME_USER_NAME + TEXT_TYPE + COMMA_SEP +
                    FeedEntryUser.COLUMN_NAME_USER_EMAIL + TEXT_TYPE + COMMA_SEP +
                    FeedEntryUser.COLUM_NAME_USER_PASSWORD + DATE_TYPE +
                    " )";

    public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + FeedEntryUser.TABLE_NAME;
}

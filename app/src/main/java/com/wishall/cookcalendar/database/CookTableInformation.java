package com.wishall.cookcalendar.database;

import android.provider.BaseColumns;

/**
 * Created by wishall on 23/06/18.
 */

public class CookTableInformation implements BaseColumns {

    public static final String TABLE_NAME = "CookTableInformation";
    public static final String COLUMN_NAME_DATE = "date";
    public static final String COLUMN_NAME_VALUE = "value";

    public static final String COMMA_SEP = ",";
    public static final String SQL_CREATE_TABLE =
                    "CREATE TABLE " + TABLE_NAME + " (" +
                    _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_NAME_DATE + " DATE" + COMMA_SEP +
                    COLUMN_NAME_VALUE + " TEXT" +
                    ")";

    public static final String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

}

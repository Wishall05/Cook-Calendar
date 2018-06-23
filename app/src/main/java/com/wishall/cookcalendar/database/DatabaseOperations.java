package com.wishall.cookcalendar.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wishall on 23/06/18.
 */

public class DatabaseOperations extends SQLiteOpenHelper {

    private static DatabaseOperations instance;
    private Context context;
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Calendar.db";

    private DatabaseOperations(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    public static DatabaseOperations getInstance(Context context) {
        if (instance == null) {
            synchronized (DatabaseOperations.class) {
                if (instance == null) {
                    instance = new DatabaseOperations(context);
                }
            }
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CookTableInformation.SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(CookTableInformation.SQL_DELETE_TABLE);
        onCreate(sqLiteDatabase);
    }

    public long insertValue(String sampleType) {
        SQLiteDatabase db = this.getWritableDatabase();

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        ContentValues contentValues = new ContentValues();
        contentValues.put(CookTableInformation.COLUMN_NAME_VALUE, sampleType);
        contentValues.put(CookTableInformation.COLUMN_NAME_DATE, dateFormat.format(new Date()));

        return db.insert(CookTableInformation.TABLE_NAME, null, contentValues);
    }

    public Cursor getAllValues() {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = {
                CookTableInformation.COLUMN_NAME_DATE,
                CookTableInformation.COLUMN_NAME_VALUE
        };

        return db.query(CookTableInformation.TABLE_NAME, columns, null, null, null, null, null);
    }
}

package com.aesthetics.lovetally.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by xujianhui on 2017/2/26.
 */

public class MySQLiteHelper extends SQLiteOpenHelper {

    private final static int VERSION = 1;
    private final static String DB_NAME = "tally.db";
    private final static String TABLE_NAME = "tally";

    public MySQLiteHelper(Context context){
        super(context, DB_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+DB_NAME+"(id integer primary key autoincrement, username text, infokey text, infovalue text, savetime text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

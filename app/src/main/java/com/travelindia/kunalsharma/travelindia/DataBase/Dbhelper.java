package com.travelindia.kunalsharma.travelindia.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


/**
 * Created by kunal sharma on 14-May-17.
 */

public class Dbhelper extends SQLiteOpenHelper {


    private  static  final int DATABASE_VERSION = 1;
    private  final String CREATE_CATEGORY_TABLE = "CREATE TABLE " + DbContract.TABLE_CATEGORY_NAME +
            " (" +
            DbContract.CATEGORYID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            DbContract.CATEGORYTITLE + " TEXT , " +
            DbContract.CATEGORYTHUMBNAIL + " TEXT , " +
            DbContract.CATEGORYTHUMBNAILINFO + " TEXT  NULL" +
            ")";

    private  static  final String DROP_TABLE = "drope table if exists"+DbContract.TABLE_CATEGORY_NAME;


    public Dbhelper(Context context )
    {
        super(context,DbContract.DB_NAME,null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_CATEGORY_TABLE);
        Log.e("Db Operation","Table Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    public void SaveToLocalDb(String title,String thumbnail,SQLiteDatabase database){

        ContentValues values = new ContentValues();
        values.put(DbContract.CATEGORYTITLE, title);
        values.put(DbContract.CATEGORYTHUMBNAIL, thumbnail);
       // values.put(DbContract.CATEGORYTHUMBNAILINFO, thumbnailinfo);
        database.insert(DbContract.TABLE_CATEGORY_NAME, null, values);

        Log.e("Db Operation","One Row Inserted");
    }
}

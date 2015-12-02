package com.example.nezarsaleh.headsup1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;

    public static final String DB_NAME                  = "heads.db";

    public static final String CAT_TABLE_NAME           = "categories_table";
    public static final String ITEMS_TABLE_NAME         = "items_table";

    public static final String ID_COLUMN_CAT_NAME       = "ID";
    public static final String NAME_COLUMN_CAT_NAME     = "NAME";
    public static final String IMAGE_COLUMN_CAT_NAME    = "IMAGE";
    public static final String FAV_COLUMN_CAT_NAME      = "FAV";
    public static final String CUS_COLUMN_CAT_NAME      = "CUSTOM";

    public static final String ID_COLUMN_ITEMS_NAME             = "ID";
    public static final String CAT_ID_COLUMN_ITEMS_NAME         = "CAT_ID";
    public static final String CAT_NAME_COLUMN_ITEMS_NAME       = "CAT_NAME";
    public static final String TEXT_COLUMN_ITEMS_NAME           = "TEXT";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + CAT_TABLE_NAME +
                " (" + ID_COLUMN_CAT_NAME + " INTEGER PRIMARY KEY AUTOINCREMENT" +
                "," + NAME_COLUMN_CAT_NAME +
                "," + IMAGE_COLUMN_CAT_NAME +
                "," + FAV_COLUMN_CAT_NAME + " INTEGER " +
                "," + CUS_COLUMN_CAT_NAME + " INTEGER " +
                ")");

        db.execSQL("create table " + ITEMS_TABLE_NAME +
                " (" + ID_COLUMN_ITEMS_NAME + " INTEGER PRIMARY KEY AUTOINCREMENT" +
                "," + CAT_ID_COLUMN_ITEMS_NAME +
                "," + CAT_NAME_COLUMN_ITEMS_NAME +
                "," + TEXT_COLUMN_ITEMS_NAME +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CAT_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + CAT_TABLE_NAME);
        onCreate(db);
    }

    public long insertCat(String Name,int Image,int fav,int Custom){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME_COLUMN_CAT_NAME, Name);
        contentValues.put(IMAGE_COLUMN_CAT_NAME, Image);
        contentValues.put(FAV_COLUMN_CAT_NAME, fav);
        contentValues.put(CUS_COLUMN_CAT_NAME, Custom);
        return db.insert(CAT_TABLE_NAME,null,contentValues);
    }

    public boolean insertItems(String Name,String CatNAME, int CatID){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CAT_ID_COLUMN_ITEMS_NAME,CatID);
        contentValues.put(CAT_NAME_COLUMN_ITEMS_NAME,CatNAME);
        contentValues.put(TEXT_COLUMN_ITEMS_NAME,Name);
        long result = db.insert(ITEMS_TABLE_NAME,null,contentValues);
        return result != -1;
    }

    public boolean updateFav(int ID,int fav){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FAV_COLUMN_CAT_NAME, fav);
        long result = db.update(CAT_TABLE_NAME, contentValues, "ID=" + ID, null);
        db.close();
        return result != -1;
    }

    public boolean updateName(int ID,String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME_COLUMN_CAT_NAME, name);
        long result = db.update(CAT_TABLE_NAME, contentValues, "ID="+ID, null);
        db.close();
        return result != -1;
    }
    
    public Cursor getAllCat(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from "+CAT_TABLE_NAME,null);
    }

    public Cursor getItemsByCat(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from "+ITEMS_TABLE_NAME + " where CAT_ID = "+id,null);
    }

    public Cursor getAllFav() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from "+CAT_TABLE_NAME + " where "+FAV_COLUMN_CAT_NAME + " like true",null);
    }
}

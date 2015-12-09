package com.example.nezarsaleh.headsup1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;

    private static String DB_PATH = "/data/data/com.example.nezarsaleh.headsup1/databases/";
    private final Context myContext;
    private SQLiteDatabase myDataBase;

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
        this.myContext = context;
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
        return db.rawQuery("select * from " + ITEMS_TABLE_NAME + " where CAT_ID = " + id, null);
    }

    public Cursor getAllFav() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from "+CAT_TABLE_NAME + " where "+FAV_COLUMN_CAT_NAME + " like true",null);
    }

    public int removeCat(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(CAT_TABLE_NAME,ID_COLUMN_CAT_NAME + "=" + id,null);
    }


    public void createDataBase() throws IOException {

        boolean dbExist = checkDataBase();

        if(dbExist){
            //do nothing - database already exist
        }else{
            //By calling this method and empty database will be created into the default system path
            //of your application so we are gonna be able to overwrite that database with our database.
            this.getReadableDatabase();
            try {
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     * @return true if it exists, false if it doesn't
     */
    private boolean checkDataBase(){

        SQLiteDatabase checkDB = null;

        try{
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        }catch(SQLiteException e){
            //database doesn't exist yet.
        }
        if(checkDB != null){
            checkDB.close();
        }
        return checkDB != null;
    }

    /**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transfering bytestream.
     * */
    private void copyDataBase() throws IOException{

        //Open your local db as the input stream
        Log.d("assets location",myContext.getAssets().toString());
        InputStream myInput = myContext.getAssets().open(DB_NAME);

        // Path to the just created empty db
        String outFileName = DB_PATH + DB_NAME;

        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }
        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    public void openDataBase() throws SQLException {
        //Open the database
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    }

    @Override
    public synchronized void close() {
        if(myDataBase != null)
            myDataBase.close();
        super.close();
    }
}

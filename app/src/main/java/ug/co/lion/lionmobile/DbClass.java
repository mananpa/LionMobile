package ug.co.lion.lionmobile;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by Eriq on 11/2/2015.
 */
public class DbClass {

    // details of the database that is, database name and version
    private static final String DATABASE_NAME = "LionMobile.db";
    private static final int DATABASE_VERSION = 2;

    private DbHelper ourHelper; // instance of the DbHelper class
    private Context ourContext;
    static SQLiteDatabase ourDatabase; // instance of the SQLitedatabase class

    public DbClass(Context c) {
        // this is constructor for the DbClass
        ourContext = c; // here we want to have the context passed into the
        // class to be usable
        // within the class
    }


    public DbClass open() {

        ourHelper = new DbHelper(ourContext);
        ourDatabase = ourHelper.getWritableDatabase();

        return this;
    }

    public void close() {

        ourHelper.close();

    }


    public String returnName (){

        open();

        String name = null;

        String sql = "SELECT pdt_name FROM products WHERE _id = 1";

        Cursor c = ourDatabase.rawQuery(sql, null);

        if(c.getCount() > 0){

            c.moveToFirst();

            name = c.getString(c.getColumnIndex("pdt_name"));

            c.close();


        }else{

            c.close();

            name = "tewali";
        }

        return name;

    }



    public class DbHelper extends SQLiteAssetHelper {
    //this Class gets the Db from assets/databases folder that is named appropriately ie LionMobile.db

        public DbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);

            setForcedUpgrade();
        }


    }




}

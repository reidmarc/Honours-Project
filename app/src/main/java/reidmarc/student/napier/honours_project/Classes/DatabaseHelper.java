package reidmarc.student.napier.honours_project.Classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper
{
    // Database Setup
    private static final String DATABASE_NAME = "users.db";

    // Student Table Setup
    private static final String TABLE_NAME = "coordinates";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "PATTERN";
    private static final String COL_3 = "X";
    private static final String COL_4 = "Y";



    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        //db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, DATE TEXT)");
        db.execSQL("CREATE TABLE " + TABLE_NAME + " ( " + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_2 + " INTEGER, " + COL_3 + " REAL, " + COL_4 + " REAL )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME + "");
        onCreate(db);
    }

    public boolean insertData(int pattern, float x, float y)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_2, pattern);
        contentValues.put(COL_3, x);
        contentValues.put(COL_4, y);


        // If the insert fails a value of -1 is returned.
        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    /*
    public Cursor getAllData()
    {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        return res;
    }
    */

    public String getDatabaseName()
    {
        return DATABASE_NAME;
    }

}

package reidmarc.student.napier.honours_project.Classes;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;


import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper
{
    // Database Setup
    private static final String DATABASE_NAME = "student_data.db";

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

    public boolean insertXYData(ArrayList<ArrayList<Float>> coordsListOfLists)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "INSERT INTO " + TABLE_NAME + " (" + COL_2 + ", " + COL_3 + ", " + COL_4 + ") VALUES (?, ?, ?)";
        SQLiteStatement sqLiteStatement = db.compileStatement(sql);

        boolean wasInsertSuccessful = false;

        db.beginTransaction();

        try
        {
            for (int i = 0; i < coordsListOfLists.size(); i++)
            {
                for (int j = 0; j < coordsListOfLists.get(i).size(); j = j + 2)
                {
                    int pattern = i;
                    float x = coordsListOfLists.get(i).get(j);
                    float y = coordsListOfLists.get(i).get(j + 1);

                    sqLiteStatement.clearBindings();
                    sqLiteStatement.bindDouble(1, pattern);
                    sqLiteStatement.bindDouble(2, x);
                    sqLiteStatement.bindDouble(3, y);
                    sqLiteStatement.execute();
                }
            }
            db.setTransactionSuccessful();
            wasInsertSuccessful = true;

        }
        catch(Exception ex)
        {
            System.out.println("Error: " + ex);
        }
        finally
        {
            db.endTransaction();
        }


        if (wasInsertSuccessful)
        {
            return true;
        }
        else
        {
            return false;
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

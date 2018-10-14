package reidmarc.student.napier.honours_project.Classes;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;


import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper
{
    // Database Setup
    private static final String DATABASE_NAME = "collected_data.db";

    // User Table Setup
    private static final String TABLE_01_NAME = "Users";
    private static final String TABLE_01_COL_01 = "ID";
    private static final String TABLE_01_COL_02 = "UserID";

    // Collection Table Setup
    private static final String TABLE_02_NAME = "Collection";
    private static final String TABLE_02_COL_01 = "CollectionID";
    private static final String TABLE_02_COL_02 = "IDRef";
    private static final String TABLE_02_COL_03 = "DateAndTime";

    // Pattern Table Setup
    private static final String TABLE_03_NAME = "Pattern";
    private static final String TABLE_03_COL_01 = "PatternID";
    private static final String TABLE_03_COL_02 = "Dot01X";
    private static final String TABLE_03_COL_03 = "Dot01Y";
    private static final String TABLE_03_COL_04 = "Dot02X";
    private static final String TABLE_03_COL_05 = "Dot02Y";
    private static final String TABLE_03_COL_06 = "Dot03X";
    private static final String TABLE_03_COL_07 = "Dot03Y";
    private static final String TABLE_03_COL_08 = "Dot04X";
    private static final String TABLE_03_COL_09 = "Dot04Y";
    private static final String TABLE_03_COL_10 = "Dot05X";
    private static final String TABLE_03_COL_11 = "Dot05Y";
    private static final String TABLE_03_COL_12 = "Dot06X";
    private static final String TABLE_03_COL_13 = "Dot06Y";
    private static final String TABLE_03_COL_14 = "Dot07X";
    private static final String TABLE_03_COL_15 = "Dot07Y";
    private static final String TABLE_03_COL_16 = "Dot08X";
    private static final String TABLE_03_COL_17 = "Dot08Y";
    private static final String TABLE_03_COL_18 = "Dot09X";
    private static final String TABLE_03_COL_19 = "Dot09Y";
    private static final String TABLE_03_COL_20 = "Dot10X";
    private static final String TABLE_03_COL_21 = "Dot10Y";
    private static final String TABLE_03_COL_22 = "Dot11X";
    private static final String TABLE_03_COL_23 = "Dot11Y";
    private static final String TABLE_03_COL_24 = "Dot12X";
    private static final String TABLE_03_COL_25 = "Dot12Y";
    private static final String TABLE_03_COL_26 = "Dot13X";
    private static final String TABLE_03_COL_27 = "Dot13Y";
    private static final String TABLE_03_COL_28 = "Dot14X";
    private static final String TABLE_03_COL_29 = "Dot14Y";
    private static final String TABLE_03_COL_30 = "Dot15X";
    private static final String TABLE_03_COL_31 = "Dot15Y";
    private static final String TABLE_03_COL_32 = "Dot16X";
    private static final String TABLE_03_COL_33 = "Dot16Y";
    private static final String TABLE_03_COL_34 = "Dot17X";
    private static final String TABLE_03_COL_35 = "Dot17Y";
    private static final String TABLE_03_COL_36 = "Dot18X";
    private static final String TABLE_03_COL_37 = "Dot18Y";
    private static final String TABLE_03_COL_38 = "Dot19X";
    private static final String TABLE_03_COL_39 = "Dot19Y";
    private static final String TABLE_03_COL_40 = "Dot20X";
    private static final String TABLE_03_COL_41 = "Dot20Y";
    private static final String TABLE_03_COL_42 = "Dot21X";
    private static final String TABLE_03_COL_43 = "Dot21Y";

    // Timings Table Setup
    private static final String TABLE_04_NAME = "Timings";
    private static final String TABLE_04_COL_01 = "TimingID";
    private static final String TABLE_04_COL_02 = "CollectionRef";
    private static final String TABLE_04_COL_03 = "PatternRef";
    private static final String TABLE_04_COL_04 = "SectorNumber";
    private static final String TABLE_04_COL_05 = "SectorDuration";

    // Pause Table Setup
    private static final String TABLE_05_NAME = "Pause";
    private static final String TABLE_05_COL_01 = "PauseID";
    private static final String TABLE_05_COL_02 = "CollectionRef";
    private static final String TABLE_05_COL_03 = "PatternRef";
    private static final String TABLE_05_COL_04 = "SectorNumber";
    private static final String TABLE_05_COL_05 = "PauseDuration";
    private static final String TABLE_05_COL_06 = "PauseX";
    private static final String TABLE_05_COL_07 = "PauseY";

    // Lift Table Setup
    private static final String TABLE_06_NAME = "Lift";
    private static final String TABLE_06_COL_01 = "LiftID";
    private static final String TABLE_06_COL_02 = "CollectionRef";
    private static final String TABLE_06_COL_03 = "PatternRef";
    private static final String TABLE_06_COL_04 = "SectorNumber";
    private static final String TABLE_06_COL_05 = "LiftDuration";
    private static final String TABLE_06_COL_06 = "LiftX";
    private static final String TABLE_06_COL_07 = "LiftY";
    private static final String TABLE_06_COL_08 = "RestartedX";
    private static final String TABLE_06_COL_09 = "RestartedY";

    // Coords Table Setup
    private static final String TABLE_07_NAME = "Coords";
    private static final String TABLE_07_COL_01 = "CoordsID";
    private static final String TABLE_07_COL_02 = "CollectionRef";
    private static final String TABLE_07_COL_03 = "PatternRef";
    private static final String TABLE_07_COL_04 = "SectorNumber";
    private static final String TABLE_07_COL_05 = "DrawnPathX";
    private static final String TABLE_07_COL_06 = "DrawnPathY";



    private int collectionNo = 0;











    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////// TESTING DB WITHOUT NOT NULL CONSTRAINT ////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // User Table Setup
        db.execSQL( "CREATE TABLE " + TABLE_01_NAME + " ( " + TABLE_01_COL_01 + " INTEGER PRIMARY KEY AUTOINCREMENT," + TABLE_01_COL_02 + " TEXT UNIQUE )" );

        // Collection Table Setup
        db.execSQL( "CREATE TABLE " + TABLE_02_NAME + " ( " + TABLE_02_COL_01 + " INTEGER PRIMARY KEY AUTOINCREMENT," + TABLE_02_COL_02 + " INTEGER, " + TABLE_02_COL_03 + " TEXT, FOREIGN KEY (" + TABLE_02_COL_02 + ") REFERENCES " + TABLE_01_NAME + " (" + TABLE_01_COL_01 + ") )" );

        // Pattern Table Setup
        db.execSQL( "CREATE TABLE " + TABLE_03_NAME + " ( " + TABLE_03_COL_01 + " INTEGER PRIMARY KEY AUTOINCREMENT," + TABLE_03_COL_02 + " INTEGER, " + TABLE_03_COL_03 + " INTEGER, " + TABLE_03_COL_04 +  " INTEGER, " + TABLE_03_COL_05 +  " INTEGER, " + TABLE_03_COL_06 +  " INTEGER, " + TABLE_03_COL_07 +  " INTEGER, " + TABLE_03_COL_08 + " INTEGER, " + TABLE_03_COL_09 +  " INTEGER, " + TABLE_03_COL_10 +  " INTEGER, " + TABLE_03_COL_11 +  " INTEGER, " + TABLE_03_COL_12 +  " INTEGER, " + TABLE_03_COL_13 + " INTEGER, " + TABLE_03_COL_14 +  " INTEGER, " + TABLE_03_COL_15 +  " INTEGER, " + TABLE_03_COL_16 +  " INTEGER, " + TABLE_03_COL_17 +  " INTEGER, " + TABLE_03_COL_18 + " INTEGER, " + TABLE_03_COL_19 +  " INTEGER, " + TABLE_03_COL_20 +  " INTEGER, " + TABLE_03_COL_21 +  " INTEGER, " + TABLE_03_COL_22 +  " INTEGER, " + TABLE_03_COL_23 + " INTEGER, " + TABLE_03_COL_24 +  " INTEGER, " + TABLE_03_COL_25 +  " INTEGER, " + TABLE_03_COL_26 +  " INTEGER, " + TABLE_03_COL_27 +  " INTEGER, " + TABLE_03_COL_28 + " INTEGER, " + TABLE_03_COL_29 +  " INTEGER, " + TABLE_03_COL_30 +  " INTEGER, " + TABLE_03_COL_31 +  " INTEGER, " + TABLE_03_COL_32 +  " INTEGER, " + TABLE_03_COL_33 + " INTEGER, " + TABLE_03_COL_34 +  " INTEGER, " + TABLE_03_COL_35 +  " INTEGER, " + TABLE_03_COL_36 +  " INTEGER, " + TABLE_03_COL_37 +  " INTEGER, " + TABLE_03_COL_38 + " INTEGER, " + TABLE_03_COL_39 +  " INTEGER, " + TABLE_03_COL_40 +  " INTEGER, " + TABLE_03_COL_41 +  " INTEGER, " + TABLE_03_COL_42 +  " INTEGER, " + TABLE_03_COL_43 + " INTEGER )" );

        // Timings Table Setup
        db.execSQL( "CREATE TABLE " + TABLE_04_NAME + " ( " + TABLE_04_COL_01 + " INTEGER PRIMARY KEY AUTOINCREMENT," + TABLE_04_COL_02 + " INTEGER, " + TABLE_04_COL_03 + " INTEGER, " + TABLE_04_COL_04 +  " INTEGER, " + TABLE_04_COL_05 +  " REAL, FOREIGN KEY (" + TABLE_04_COL_02 + ") REFERENCES " + TABLE_02_NAME + " (" + TABLE_02_COL_01 + "), FOREIGN KEY (" + TABLE_04_COL_03 + ") REFERENCES " + TABLE_03_NAME + " (" + TABLE_03_COL_01 + ") )" );

        // Pause Table Setup
        db.execSQL( "CREATE TABLE " + TABLE_05_NAME + " ( " + TABLE_05_COL_01 + " INTEGER PRIMARY KEY AUTOINCREMENT," + TABLE_05_COL_02 + " INTEGER, " + TABLE_05_COL_03 + " INTEGER, " + TABLE_05_COL_04 +  " INTEGER, " + TABLE_05_COL_05 +  " REAL, " + TABLE_05_COL_06 + " REAL, " + TABLE_05_COL_07 + " REAL, FOREIGN KEY (" + TABLE_04_COL_02 + ") REFERENCES " + TABLE_02_NAME + " (" + TABLE_02_COL_01 + "), FOREIGN KEY (" + TABLE_04_COL_03 + ") REFERENCES " + TABLE_03_NAME + " (" + TABLE_03_COL_01 + ") )" );

        // Lift Table Setup
        db.execSQL( "CREATE TABLE " + TABLE_06_NAME + " ( " + TABLE_06_COL_01 + " INTEGER PRIMARY KEY AUTOINCREMENT," + TABLE_06_COL_02 + " INTEGER, " + TABLE_06_COL_03 + " INTEGER, " + TABLE_06_COL_04 +  " INTEGER, " + TABLE_06_COL_05 +  " REAL, " + TABLE_06_COL_06 + " REAL, " + TABLE_06_COL_07 + " REAL, " + TABLE_06_COL_08 + " REAL, " + TABLE_06_COL_09 + " REAL, FOREIGN KEY (" + TABLE_04_COL_02 + ") REFERENCES " + TABLE_02_NAME + " (" + TABLE_02_COL_01 + "), FOREIGN KEY (" + TABLE_04_COL_03 + ") REFERENCES " + TABLE_03_NAME + " (" + TABLE_03_COL_01 + ") )" );

        // Coords Table Setup
        db.execSQL( "CREATE TABLE " + TABLE_07_NAME + " ( " + TABLE_07_COL_01 + " INTEGER PRIMARY KEY AUTOINCREMENT," + TABLE_07_COL_02 + " INTEGER, " + TABLE_07_COL_03 + " INTEGER, " + TABLE_07_COL_04 +  " INTEGER, " + TABLE_07_COL_05 +  " REAL, " + TABLE_07_COL_06 + " REAL, FOREIGN KEY (" + TABLE_04_COL_02 + ") REFERENCES " + TABLE_02_NAME + " (" + TABLE_02_COL_01 + "), FOREIGN KEY (" + TABLE_04_COL_03 + ") REFERENCES " + TABLE_03_NAME + " (" + TABLE_03_COL_01 + ") )" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_07_NAME + "");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_06_NAME + "");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_05_NAME + "");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_04_NAME + "");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_03_NAME + "");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_02_NAME + "");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_01_NAME + "");
        onCreate(db);
    }

    public boolean insertXYData(ArrayList<ArrayList<Float>> coordsListOfLists)
    {

        SQLiteDatabase db = this.getWritableDatabase();

        return true; //////////////////

        /*
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "INSERT INTO " + TABLE_01_NAME + " (" + COL_2 + ", " + COL_3 + ", " + COL_4 + ") VALUES (?, ?, ?)";
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

        */
    }

    public boolean insertUserTable(String userID)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        String sql = "INSERT INTO " + TABLE_01_NAME + " (" + TABLE_01_COL_02 + ") VALUES (?)";

        SQLiteStatement sqLiteStatement = db.compileStatement(sql);

        boolean wasInsertSuccessful = false;

        db.beginTransaction();

        try
        {
            sqLiteStatement.clearBindings();
            sqLiteStatement.bindString(1, userID);
            sqLiteStatement.execute();

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



    public boolean insertPatternTable(ArrayList<int[][]> patterns)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        String sql; // = "";

        boolean wasInsertSuccessful = false;

        try
        {
            db.beginTransaction();

            for (int k = 0; k < patterns.size(); k++)
            {
                int xCounter = -1;
                int yCounter = 0;

                if (patterns.get(k).length == 9)
                {
                    sql = "INSERT INTO " + TABLE_03_NAME + " ( " + TABLE_03_COL_02 + " , " + TABLE_03_COL_03 + " , " + TABLE_03_COL_04 + " , " + TABLE_03_COL_05 + " , " + TABLE_03_COL_06 + " , " + TABLE_03_COL_07 + " , " + TABLE_03_COL_08 + " , " + TABLE_03_COL_09 + " , " + TABLE_03_COL_10 + " , " + TABLE_03_COL_11 + " , " + TABLE_03_COL_12 + " , " + TABLE_03_COL_13 + " , " + TABLE_03_COL_14 + " , " + TABLE_03_COL_15 + " , " + TABLE_03_COL_16 + " , " + TABLE_03_COL_17 + " , " + TABLE_03_COL_18 + " , " + TABLE_03_COL_19 + ")  VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                }
                else if (patterns.get(k).length == 15)
                {
                    sql = "INSERT INTO " + TABLE_03_NAME + " ( " + TABLE_03_COL_02 + " , " + TABLE_03_COL_03 + " , " + TABLE_03_COL_04 + " , " + TABLE_03_COL_05 + " , " + TABLE_03_COL_06 + " , " + TABLE_03_COL_07 + " , " + TABLE_03_COL_08 + " , " + TABLE_03_COL_09 + " , " + TABLE_03_COL_10 + " , " + TABLE_03_COL_11 + " , " + TABLE_03_COL_12 + " , " + TABLE_03_COL_13 + " , " + TABLE_03_COL_14 + " , " + TABLE_03_COL_15 + " , " + TABLE_03_COL_16 + " , " + TABLE_03_COL_17 + " , " + TABLE_03_COL_18 + " , " + TABLE_03_COL_19 + " , " + TABLE_03_COL_20 + " , " + TABLE_03_COL_21 + " , " + TABLE_03_COL_22 + " , " + TABLE_03_COL_23 + " , " + TABLE_03_COL_24 + " ," + TABLE_03_COL_25 + " ," + TABLE_03_COL_26 + " ," + TABLE_03_COL_27 + " , " + TABLE_03_COL_28 + " , " + TABLE_03_COL_29 + " , " + TABLE_03_COL_30 + " , " + TABLE_03_COL_31 + " )  VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                }
                else if (patterns.get(k).length == 21)
                {
                    sql = "INSERT INTO " + TABLE_03_NAME + " ( " + TABLE_03_COL_02 + " , " + TABLE_03_COL_03 + " , " + TABLE_03_COL_04 + " , " + TABLE_03_COL_05 + " , " + TABLE_03_COL_06 + " , " + TABLE_03_COL_07 + " , " + TABLE_03_COL_08 + " , " + TABLE_03_COL_09 + " , " + TABLE_03_COL_10 + " , " + TABLE_03_COL_11 + " , " + TABLE_03_COL_12 + " , " + TABLE_03_COL_13 + " , " + TABLE_03_COL_14 + " , " + TABLE_03_COL_15 + " , " + TABLE_03_COL_16 + " , " + TABLE_03_COL_17 + " , " + TABLE_03_COL_18 + " , " + TABLE_03_COL_19 + " , " + TABLE_03_COL_20 + " , " + TABLE_03_COL_21 + " , " + TABLE_03_COL_22 + " , " + TABLE_03_COL_23 + " , " + TABLE_03_COL_24 + " ," + TABLE_03_COL_25 + " ," + TABLE_03_COL_26 + " ," + TABLE_03_COL_27 + " , " + TABLE_03_COL_28 + " , " + TABLE_03_COL_29 + " , " + TABLE_03_COL_30 + " , " + TABLE_03_COL_31 + " , " + TABLE_03_COL_32 + " , " + TABLE_03_COL_33 + " , " + TABLE_03_COL_34 + " , " + TABLE_03_COL_35 + " , " + TABLE_03_COL_36 + " , " + TABLE_03_COL_37 + " , " + TABLE_03_COL_38 + " , " + TABLE_03_COL_39 + " , " + TABLE_03_COL_40 + " , " + TABLE_03_COL_41 + " , " + TABLE_03_COL_42 + " , " + TABLE_03_COL_43 + " )  VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                }
                else
                {
                    sql = "INSERT INTO " + TABLE_03_NAME + " ( " + TABLE_03_COL_02 + " ) VALUES (123456789)";
                }

                SQLiteStatement sqLiteStatement = db.compileStatement(sql);

                sqLiteStatement.clearBindings();

                for (int j = 0; j < patterns.get(k).length ; j++)
                {
                    int x = patterns.get(k)[j][0];
                    int y = patterns.get(k)[j][1];

                    sqLiteStatement.bindLong((xCounter = xCounter + 2), x);
                    sqLiteStatement.bindLong((yCounter = yCounter + 2), y);
                }

                sqLiteStatement.execute();
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


    public boolean insertCollectionTable(String dateAndTime, String user)
    {


        SQLiteDatabase db = this.getWritableDatabase();

        String sql = "INSERT INTO " + TABLE_02_NAME + " (" + TABLE_02_COL_02 + ", " + TABLE_02_COL_03 + ") VALUES (?, ?)";

        SQLiteStatement sqLiteStatement = db.compileStatement(sql);

        boolean wasInsertSuccessful = false;

        db.beginTransaction();

        try
        {
            sqLiteStatement.clearBindings();
            sqLiteStatement.bindString(1, user);
            sqLiteStatement.bindString(2, dateAndTime);
            sqLiteStatement.execute();

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





    private void getCurrentCollectionNumber(SQLiteDatabase db)
    {
        // Checks the last entry in Collection Table to find the current Collection number
        Cursor cursor = db.rawQuery("SELECT last_insert_rowid() FROM " + TABLE_02_NAME, null);

        if (cursor.moveToFirst())
        {
            collectionNo = cursor.getInt(0);
        }
    }




    public boolean insertTimingsTable(ArrayList<ArrayList<Double>> timings)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        String sql = "INSERT INTO " + TABLE_04_NAME + " (" + TABLE_04_COL_01 + " , " + TABLE_04_COL_02 + " , " + TABLE_04_COL_03 + " , " + TABLE_04_COL_04 + " , " + TABLE_04_COL_05 + ") VALUES (?, ?, ?, ?, ?)";

        SQLiteStatement sqLiteStatement = db.compileStatement(sql);

        boolean wasInsertSuccessful = false;

        getCurrentCollectionNumber(this.getWritableDatabase());

        db.beginTransaction();

        try
        {
            for (int i = 0; i < timings.size(); i++)
            {

                for (int j = 0; j < timings.get(i).size(); j = j + 3)
                {
                    Double patternNumber = timings.get(i).get(0);
                    Double sectorNumber = timings.get(i).get(j + 1);
                    Double sectorTiming = timings.get(i).get(j + 2);

                    int patternNo = (patternNumber.intValue() + 1);
                    int sectorNo = (sectorNumber.intValue() + 1);

                    ////////////////////////////////////////////////////////////////////////////////////////////////////
                    ////////////////////////////////////////////// TESTING /////////////////////////////////////////////
                    ////////////////////////////////////////////////////////////////////////////////////////////////////
                    /*
                    System.out.println("Collection #: " + collectionNo);
                    System.out.println("Pattern #: " + patternNo);
                    System.out.println("Sector #: " + sectorNo);
                    System.out.println("Sector Time: " + sectorTiming);
                    */
                    ////////////////////////////////////////////////////////////////////////////////////////////////////
                    ////////////////////////////////////////////////////////////////////////////////////////////////////

                    sqLiteStatement.clearBindings();
                    sqLiteStatement.bindDouble(2, collectionNo);
                    sqLiteStatement.bindDouble(3, patternNo);
                    sqLiteStatement.bindDouble(4, sectorNo);
                    sqLiteStatement.bindDouble(5, sectorTiming);
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

    public boolean insertCoordsTable(ArrayList<ArrayList<Float>> coordsListOfLists)
    {

        SQLiteDatabase db = this.getWritableDatabase();

        String sql = "INSERT INTO " + TABLE_07_NAME + " (" + TABLE_07_COL_01 + " , " + TABLE_07_COL_02 + " , " + TABLE_07_COL_03 + " , " + TABLE_07_COL_04 + " , " + TABLE_07_COL_05 + " , " + TABLE_07_COL_06 + " ) VALUES (?, ?, ?, ?, ?, ?)";

        SQLiteStatement sqLiteStatement = db.compileStatement(sql);

        boolean wasInsertSuccessful = false;

        db.beginTransaction();

        try
        {
            for (int i = 0; i < coordsListOfLists.size(); i++)
            {
                for (int j = 0; j < coordsListOfLists.get(i).size(); j = j + 4)
                {

                    Float patternNumber = coordsListOfLists.get(i).get(0);
                    Float sectorNumber = coordsListOfLists.get(i).get(j + 1);

                    int patternNo = (patternNumber.intValue() + 1);
                    int sectorNo = (sectorNumber.intValue() + 1);

                    float x = coordsListOfLists.get(i).get(j + 2);
                    float y = coordsListOfLists.get(i).get(j + 3);

                    sqLiteStatement.clearBindings();
                    sqLiteStatement.bindDouble(2, collectionNo);
                    sqLiteStatement.bindDouble(3, patternNo);
                    sqLiteStatement.bindDouble(4, sectorNo);
                    sqLiteStatement.bindDouble(5, x);
                    sqLiteStatement.bindDouble(6, y);
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


    public boolean insertPauseTable(ArrayList<Float> liftData)
    {

        SQLiteDatabase db = this.getWritableDatabase();

        String sql = "INSERT INTO " + TABLE_06_NAME + " (" + TABLE_06_COL_01 + " , " + TABLE_06_COL_02 + " , " + TABLE_06_COL_03 + " , " + TABLE_06_COL_04 + " , " + TABLE_06_COL_05 + " , " + TABLE_06_COL_06 + " , " + TABLE_06_COL_07 + " , " + TABLE_06_COL_08 + " , " + TABLE_06_COL_09 + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        SQLiteStatement sqLiteStatement = db.compileStatement(sql);

        boolean wasInsertSuccessful = false;

        db.beginTransaction();


        try
        {
            for (int j = 0; j < liftData.size(); j = j + 7)
            {
                Float patternNumber = liftData.get(j);
                Float sectorNumber = liftData.get(j + 1);
                Float pauseTiming = liftData.get(j + 2);

                int patternNo = (patternNumber.intValue() + 1);
                int sectorNo = (sectorNumber.intValue() + 1);
                double liftTime = pauseTiming.doubleValue();

                float xStart = liftData.get(j + 3);
                float yStart = liftData.get(j + 4);
                float xEnd = liftData.get(j + 5);
                float yEnd = liftData.get(j + 6);

                sqLiteStatement.clearBindings();
                sqLiteStatement.bindDouble(2, collectionNo);
                sqLiteStatement.bindDouble(3, patternNo);
                sqLiteStatement.bindDouble(4, sectorNo);
                sqLiteStatement.bindDouble(5, liftTime);
                sqLiteStatement.bindDouble(6, xStart);
                sqLiteStatement.bindDouble(7, yStart);
                sqLiteStatement.bindDouble(8, xEnd);
                sqLiteStatement.bindDouble(9, yEnd);

                sqLiteStatement.execute();
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

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public boolean insertLiftTable(ArrayList<Float> pauseData)
    {

        SQLiteDatabase db = this.getWritableDatabase();


        String sql = "INSERT INTO " + TABLE_05_NAME + " (" + TABLE_05_COL_01 + " , " + TABLE_05_COL_02 + " , " + TABLE_05_COL_03 + " , " + TABLE_05_COL_04 + " , " + TABLE_05_COL_05 + " , " + TABLE_05_COL_06 + " , " + TABLE_05_COL_07 + ") VALUES (?, ?, ?, ?, ?, ?, ?)";
        SQLiteStatement sqLiteStatement = db.compileStatement(sql);

        boolean wasInsertSuccessful = false;

        db.beginTransaction();

        try
        {
            for (int j = 0; j < pauseData.size(); j = j + 5)
            {
                Float patternNumber = pauseData.get(j);
                Float sectorNumber = pauseData.get(j + 1);
                Float pauseTiming = pauseData.get(j + 2);

                int patternNo = (patternNumber.intValue() + 1);
                int sectorNo = (sectorNumber.intValue() + 1);
                double pauseTime = pauseTiming.doubleValue();

                float x = pauseData.get(j + 3);
                float y = pauseData.get(j + 4);

                sqLiteStatement.clearBindings();
                sqLiteStatement.bindDouble(2, collectionNo);
                sqLiteStatement.bindDouble(3, patternNo);
                sqLiteStatement.bindDouble(4, sectorNo);
                sqLiteStatement.bindDouble(5, pauseTime);
                sqLiteStatement.bindDouble(6, x);
                sqLiteStatement.bindDouble(7, y);
                sqLiteStatement.execute();
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


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


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

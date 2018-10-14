package reidmarc.student.napier.honours_project.Activities;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import reidmarc.student.napier.honours_project.*;
import reidmarc.student.napier.honours_project.Classes.*;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;


public class MainActivity extends AppCompatActivity
{
    private TextView nameTextView, dateTextView;
    private String incomingName, currentDate, currentDateTime;
    private Button backButton, addButton, exportButton, clearButton;
    private DatabaseHelper myDb;
    private CanvasView canvasView;
    private Timing dbInsertTiming;
    private Today today;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        nameTextView = findViewById(R.id.nameTextView);
        incomingName = getIntent().getStringExtra("name");
        nameTextView.setText(incomingName);

        backButton = findViewById(R.id.backButton);
        addButton = findViewById(R.id.addButton);
        exportButton = findViewById(R.id.exportButton);


        dateTextView = findViewById(R.id.dateTextView);

        canvasView = findViewById(R.id.canvas);

        myDb = new DatabaseHelper(MainActivity.this);


        today = new Today();

        setTheDate();
        setupBackButton();
        exportDatabase();
        addData();




        dbInsertTiming = new Timing();

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
    }

    private void hideSystemUI()
    {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }






        private void setupBackButton()
    {
        backButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(MainActivity.this, WelcomeActivity.class));
            }
        });
    }


    private void setTheDate()
    {

        currentDate = today.getAbbrToday();

        if (currentDate == null)
        {
            dateTextView.setText("NO DATE!!!");
        }
        else
        {
            dateTextView.setText(currentDate);
        }
    }


    private void exportDatabase()
    {
        exportButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                copyDatabaseFile();
            }
        });
    }

    private void copyDatabaseFile()
    {
        if (isExternalStorageMounted())
        {
            try
            {
                File sd = Environment.getExternalStorageDirectory();
                File data = Environment.getDataDirectory();

                String packageName = getApplicationContext().getApplicationInfo().packageName;

                currentDateTime = today.getAbbrTodayAndTime();



                if (sd.canWrite())
                {
                    String currentDBPath = String.format("//data//%s//databases//%s", packageName, myDb.getDatabaseName());
                    // String backupDBPath = currentDateTime + ".txt";
                    String backupDBPath = String.format("//Database//%s.db", currentDateTime);

                    File currentDB = new File(data, currentDBPath);
                    File backupDB = new File(sd, backupDBPath);

                    if (currentDB.exists())
                    {
                        FileChannel src = new FileInputStream(currentDB).getChannel();
                        FileChannel dst = new FileOutputStream(backupDB).getChannel();
                        dst.transferFrom(src, 0, src.size());
                        src.close();
                        dst.close();

                        Toast.makeText(getApplicationContext(), "DATABASE EXPORTED TO STORAGE", Toast.LENGTH_LONG).show();

                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "DATABASE NOT FOUND", Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "CANNOT WRITE TO STORAGE", Toast.LENGTH_LONG).show();
                }
            }
            catch (Exception e)
            {
                Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
            }
        }
    }

    private boolean isExternalStorageMounted()
    {
        String state = Environment.getExternalStorageState();

        return Environment.MEDIA_MOUNTED.equals(state);
    }

    // Adds coordinates to Database
    public void addData()
    {
        addButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                dbInsertTiming.startTiming();                                                               // <----------  TESTING









                boolean insertUserCompleted = myDb.insertUserTable(incomingName);

                boolean insertPatternsCompleted = myDb.insertPatternTable(canvasView.getPatternsList());

                boolean insertCollectionCompleted = myDb.insertCollectionTable(today.getAbbrTodayAndTime(), incomingName);

                boolean insertTimingsCompleted = myDb.insertTimingsTable(canvasView.getSectorTimingListOfLists());

                boolean insertCoordsCompleted = myDb.insertCoordsTable(canvasView.getCoordsListOfLists());

                boolean insertPauseCompleted = myDb.insertPauseTable(canvasView.getPauseTimingList());

                boolean insertLiftCompleted = myDb.insertLiftTable(canvasView.getLiftTimingList());






                System.out.println("TIME TAKEN TO INSERT DATA: " + dbInsertTiming.timeDurationSeconds());   // <----------  TESTING

                if (insertUserCompleted && insertPatternsCompleted && insertCollectionCompleted && insertTimingsCompleted && insertCoordsCompleted && insertPauseCompleted && insertLiftCompleted)
                {
                    Toast.makeText(MainActivity.this, "DATA INSERTED", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "DATA NOT INSERTED", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}


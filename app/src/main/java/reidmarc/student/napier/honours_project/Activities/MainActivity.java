package reidmarc.student.napier.honours_project.Activities;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import reidmarc.student.napier.honours_project.*;
import reidmarc.student.napier.honours_project.Classes.CanvasView;
import reidmarc.student.napier.honours_project.Classes.DatabaseHelper;
import reidmarc.student.napier.honours_project.Classes.Timing;
import reidmarc.student.napier.honours_project.Classes.Today;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity
{
    private TextView nameTextView, dateTextView;
    private String incomingName, currentDate;
    private Button backButton, addButton, exportButton, clearButton;
    private DatabaseHelper myDb;
    private CanvasView canvasView;
    private Timing dbInsertTiming;

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

        setTheDate();
        setupBackButton();
        exportDatabase();
        addData();


        dbInsertTiming = new Timing();

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
        Today today = new Today();
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

                if (sd.canWrite())
                {
                    String currentDBPath = String.format("//data//%s//databases//%s", packageName, myDb.getDatabaseName());
                    String backupDBPath = "backup.db";
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

                boolean isInserted = false;
                int counter = 0;

                ArrayList<ArrayList<Float>> coordsListOfLists = canvasView.getCoordsList();


                dbInsertTiming.startTiming();

                for (int i = 0; i < coordsListOfLists.size(); i++)
                {
                    for (int j = 0; j < coordsListOfLists.get(i).size(); j = j + 2)
                    {
                        int pattern = i;
                        float x = coordsListOfLists.get(i).get(j);
                        float y = coordsListOfLists.get(i).get(j + 1);

                        isInserted = myDb.insertData(pattern, x, y );

                        if (!isInserted && counter < 1)
                        {
                            Toast.makeText(MainActivity.this, "Data NOT Inserted", Toast.LENGTH_LONG).show();
                            counter = counter + 1;
                        }
                    }
                }

                System.out.println("TIME TAKEN TO INSERT DATA: " + dbInsertTiming.timeDurationSeconds());



                if (counter == 0)
                {
                    Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                }


            }
        });
    }





}


package reidmarc.student.napier.honoursproject.groovy.classes;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import reidmarc.student.napier.honoursproject.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;


public class MainActivity extends AppCompatActivity
{
    TextView nameTextView, dateTextView;
    String incomingName, currentDate;
    Button backButton, addButton, exportButton;
    DatabaseHelper myDb;

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

        myDb = new DatabaseHelper(MainActivity.this);

        setTheDate();

        addData();
        setupBackButton();
        exportDatabase();

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
        Today theDate = new Today();
        currentDate = theDate.getAbbrToday().toString();

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
                    String currentDBPath = String.format("//data//%s//databases//%s", packageName, myDb.DATABASE_NAME);
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

        /*
        if (Environment.MEDIA_MOUNTED.equals(state))
        {
            return true;
        }
        return false;
        */
    }

    public void addData()
    {
        addButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                boolean isInserted = myDb.insertData
                        (
                                incomingName,
                                currentDate
                        );

                if (isInserted)
                {
                    Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Data NOT Inserted", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    /*
    public void ViewAll()
    {
        btnViewAll.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Cursor res = myDb.getAllData();

                // If there is no data to display the count will == 0
                if (res.getCount() == 0)
                {
                    showMessage("Error", "No Data Found.....");
                    return;
                }

                StringBuffer buffer = new StringBuffer();

                while (res.moveToNext())
                {
                    buffer.append("ID :"+res.getString(0)+"\n");
                    buffer.append("Name :"+res.getString(1)+"\n");
                    buffer.append("Surname :"+res.getString(2)+"\n");
                    buffer.append("Marks :"+res.getString(3)+"\n\n");


                    // extracting the data
                    textName.setText(res.getString(1));
                    textSurname.setText(res.getString(2));
                    textMarks.setText(res.getString(3));


                }

                showMessage("Data", buffer.toString());

            }
        });
    }


    public void showMessage(String title, String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);

        builder.show();
    }
    */

}


package reidmarc.student.napier.honoursproject.groovy.classes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import reidmarc.student.napier.honoursproject.R;

public class MainActivity2 extends AppCompatActivity
{
    TextView nameTextView;
    TextView dateTextView;
    String incomingName;
    String date;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        nameTextView = findViewById(R.id.nameTextView);
        incomingName = getIntent().getStringExtra("name");
        nameTextView.setText(incomingName);

        Today theDate = new Today();

        dateTextView = findViewById(R.id.dateTextView);
        date = theDate.getAbbrToday().toString();

        if (date != null) {
            dateTextView.setText(date);
        }
        else
        {
            dateTextView.setText("NO DATE!!!");
        }


        setupBackButton();
    }

    private void setupBackButton()
    {
        Button backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(MainActivity2.this, MainActivity.class));
            }
        });
    }
}

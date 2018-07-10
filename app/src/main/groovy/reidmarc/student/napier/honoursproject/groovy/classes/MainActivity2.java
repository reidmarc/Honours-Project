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
    String incomingName;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        nameTextView = findViewById(R.id.nameTextView);
        incomingName = getIntent().getStringExtra("name");
        nameTextView.setText(incomingName);

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

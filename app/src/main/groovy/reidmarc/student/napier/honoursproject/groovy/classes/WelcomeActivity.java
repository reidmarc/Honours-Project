package reidmarc.student.napier.honoursproject.groovy.classes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import reidmarc.student.napier.honoursproject.R;

public class WelcomeActivity extends AppCompatActivity
{
    EditText name;
    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        setupNextButton();
    }

    private void setupNextButton()
    {
        Button nextButton = findViewById(R.id.nextButton);

        nextButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                name = findViewById(R.id.nameInputTextbox);
                userName = name.getText().toString();

                // IF statement ensures a name is entered before navigating to the next page.
                if (!userName.trim().isEmpty())
                {
                    Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                    intent.putExtra("name", userName);
                    startActivity(intent);

                    Toast.makeText(WelcomeActivity.this, "Welcome " + userName + " !", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(WelcomeActivity.this, "You must enter a name to continue....", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}

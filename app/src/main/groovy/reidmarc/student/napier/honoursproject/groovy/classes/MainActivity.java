package reidmarc.student.napier.honoursproject.groovy.classes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.android.volley.*;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import reidmarc.student.napier.honoursproject.R;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity
{
    TextView nameTextView, dateTextView;
    String incomingName, date;


    RequestQueue requestQueue;
    String insertURL = "http://10.0.2.1/honours_project/insertStudent"; // "http://192.168.0.12/honours_project/insertStudent.php";





    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameTextView = findViewById(R.id.nameTextView);
        incomingName = getIntent().getStringExtra("name");
        nameTextView.setText(incomingName);

        Today theDate = new Today();

        dateTextView = findViewById(R.id.dateTextView);
        date = theDate.getAbbrToday().toString();

        if (date != null)
        {
            dateTextView.setText(date);
        }
        else
        {
            dateTextView.setText("NO DATE!!!");
        }

        requestQueue = Volley.newRequestQueue(getApplicationContext());


        setupAddButton(incomingName, date);
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
                startActivity(new Intent(MainActivity.this, WelcomeActivity.class));
            }
        });
    }

    private void setupAddButton(String name, String date)
    {
        Button addButton = findViewById(R.id.addButton);

        addButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                StringRequest request = new StringRequest(Request.Method.POST, insertURL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s)
                    {

                    }
                }, new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError volleyError)
                    {

                    }
                })
                {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError
                    {
                        Map<String,String> parameters = new HashMap<String, String>();
                        parameters.put("name", nameTextView.getText().toString());
                        parameters.put("date", dateTextView.getText().toString());


                        return parameters;
                    }
                };

                requestQueue.add(request);
            }
        });

    }


}


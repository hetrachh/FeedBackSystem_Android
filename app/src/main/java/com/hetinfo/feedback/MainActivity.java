package com.hetinfo.feedback;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    EditText email,uname,phno,feed_back;
    Button submit_btn;
    String rname;
    private String Url = "http://ljmcaintegrated.dx.am/Feedback_app/connection.php";
    private final String TAG = MainActivity.class.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email =     (EditText) findViewById(R.id.txt_email);
        uname =     (EditText) findViewById(R.id.txt_name);
        phno  =     (EditText) findViewById(R.id.txt_phno);
        //feed_back = (EditText) findViewById(R.id.ideas);

        submit_btn = (Button) findViewById(R.id.submit);


        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Upload_Data();
            }
        });


    }
    public void Upload_Data()
    {
            String urlprms = "?name="+uname.getText().toString().trim() +
                        "&email="+email.getText().toString().trim()+
                    "&phno="+phno.getText().toString().trim()+
                    "&feed="+email.getText().toString().trim();
            rname = uname.getText().toString().trim();

        try {

                StringRequest request = new StringRequest(Request.Method.GET, Url + urlprms, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.d(TAG, "Response: " + response);

                            Toast.makeText(MainActivity.this, "Thank You for  Giving FeedBack/Suggestion", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this,ResultActivity.class);
                            intent.putExtra("name",rname);
                            startActivity(intent);
                            uname.setText("");
                            email.setText("");
                            phno.setText("");
                            feed_back.setText("");
                        } catch (Exception e) {
                            Log.e(TAG, "Error: ", e);
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "Error: ", error);
                        Toast.makeText(MainActivity.this, "Upload Data Failed", Toast.LENGTH_SHORT).show();

                    }
                });
                RetryPolicy retryPolicy = new DefaultRetryPolicy(50000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
                request.setRetryPolicy(retryPolicy);
                MySinglotone.getInstance(MainActivity.this).addToRequestQueue(request);
            }catch (Exception e)
            {
                e.printStackTrace();
                Log.e(TAG, "Error: ", e);
            }
    }

}

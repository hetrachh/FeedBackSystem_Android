package com.hetinfo.feedback;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity {
    TextView textView;
    Button feedback1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        feedback1 =  (Button) findViewById(R.id.feedback);
        textView = (TextView) findViewById(R.id.txt_view);

        Intent i = getIntent();

        String uname = i.getStringExtra("name");
        Toast.makeText(this, uname, Toast.LENGTH_SHORT).show();
        textView.setText("Thank You "+uname+" For Giving FeedBack/Suggestion");

        feedback1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}

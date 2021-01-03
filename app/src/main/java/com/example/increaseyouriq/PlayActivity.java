package com.example.increaseyouriq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import static com.example.increaseyouriq.MainActivity.MY_PREFERENCES;

public class PlayActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        initActionBar();

        sharedPreferences=getSharedPreferences(MY_PREFERENCES,MODE_PRIVATE);
        TextView textView=findViewById(R.id.current_level);
        textView.setText(String.valueOf(sharedPreferences.getInt("level",0)));

    }
    private void initActionBar() {
        //View v = getLayoutInflater().inflate(R.layout.back_toolbar,null);
        Toolbar toolbar= findViewById(R.id.appBar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("BAsic");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        if(getSupportActionBar()!=null) {

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

    }
}
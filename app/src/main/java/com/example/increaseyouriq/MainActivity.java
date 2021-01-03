package com.example.increaseyouriq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String MY_PREFERENCES = "MyPrefs" ;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences=getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt("sound",1);
        editor.commit();
        init();
    }

    private void init() {
        TextView soundView=findViewById(R.id.sound_text_view);

        if (sharedPreferences.getInt("sound",0)==0){
            soundView.setText("Sound  OFF");
        }
        else{
            soundView.setText("Sound ON");
        }

    }

    public void playFunc(View v){
        Intent intent=new Intent(this,PlayActivity.class);
        startActivity(intent);
    }
    public void settingFunc(View v){
        Toast.makeText(getBaseContext(),"ID: "+ v.toString(), Toast.LENGTH_LONG).show();
    }
    public void levelsFunc(View v){
        Intent intent=new Intent(this,LevelActivity.class);
        startActivity(intent);
    }
    public void soundFunc(View v){
        TextView soundView=findViewById(R.id.sound_text_view);
        SharedPreferences.Editor editor= sharedPreferences.edit();

        if (sharedPreferences.getInt("sound",0)==0){
            editor.putInt("sound",1);
            soundView.setText("Sound  ON");
        }
        else{
            editor.putInt("sound",0);
            soundView.setText("Sound OFF");
        }
        editor.commit();
    }
    public void followFunc(View v){
        String url = "https://telegram.me/riddles4d";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
}
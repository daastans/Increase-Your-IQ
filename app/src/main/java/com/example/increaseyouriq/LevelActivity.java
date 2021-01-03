package com.example.increaseyouriq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.increaseyouriq.adapter.LevelAdapter;
import com.example.increaseyouriq.database.Question;
import com.example.increaseyouriq.database.QuestionDatabase;
import com.example.increaseyouriq.model.LevelItem;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import static com.example.increaseyouriq.MainActivity.MY_PREFERENCES;

public class LevelActivity extends AppCompatActivity  {
    SharedPreferences sharedPreferences;
    private QuestionDatabase questionDatabase;
    private Question question;
    private GridView gridView;
    private LevelAdapter levelAdapter;
    private static final String LOG_TAG="LevelActivity";
    List<Question> q;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
        initActionBar();

        questionDatabase=QuestionDatabase.getInstance(LevelActivity.this);
        gridView= findViewById(R.id.level_grid);

        new RetrieveTask(LevelActivity.this).execute();

        sharedPreferences=getSharedPreferences(MY_PREFERENCES,MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                editor.putInt("level",i+1);
                editor.commit();

                Toast.makeText(getBaseContext(),"Level" + (i + 1) +" selected",Toast.LENGTH_SHORT).show();
                //Log.e(LOG_TAG, "Clicked  :"+String.valueOf(i));

                TextView textView=view.findViewById(R.id.level_text_view);
                textView.setBackgroundResource(R.drawable.btn_gradient);

                question=new Question(i+1,null,null,1);
                new InsertTask(LevelActivity.this,question).execute();


                Intent intent= new Intent(LevelActivity.this,QuizActivity.class);
                intent.putExtra("questionToAsk", (Serializable) q.get(i));
                startActivity(intent);

            }
        });

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

            Log.e(LOG_TAG,getSupportActionBar().toString());
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

    }
    private static class InsertTask extends AsyncTask<Void,Void,Boolean>{
        private WeakReference<LevelActivity> activityWeakReference;
        private Question question;

        InsertTask(LevelActivity context,Question question){
            activityWeakReference= new WeakReference<>(context);
            this.question=question;
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            activityWeakReference.get().questionDatabase.getQuestionDao().Update(question);
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
        }

    }
    private static class RetrieveTask extends AsyncTask<Void,Void, List<Question>>{
        private WeakReference<LevelActivity> activityWeakReference;
        private LevelActivity mContext;

        RetrieveTask(LevelActivity context){
            activityWeakReference=new WeakReference<>(context);
            mContext=context;
        }

        @Override
        protected void onPostExecute(List<Question> questions) {
            Log.e(LOG_TAG,questions.toString());

            if(questions!=null){
                activityWeakReference.get().levelAdapter=new LevelAdapter(mContext,(ArrayList)questions);
                activityWeakReference.get().gridView.
                        setAdapter(activityWeakReference.get().levelAdapter);
            }
        }

        @Override
        protected List<Question> doInBackground(Void... voids) {

            if (activityWeakReference.get() != null) {
                activityWeakReference.get().q = activityWeakReference.get().questionDatabase.getQuestionDao().getAll();
                //Log.e(LOG_TAG, q.toString());
                return activityWeakReference.get().q;
            } else
                return null;

        }
        }


}
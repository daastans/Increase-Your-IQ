package com.example.increaseyouriq.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.util.Log;

import com.example.increaseyouriq.model.LevelItem;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Question.class},version=1)
public abstract  class QuestionDatabase extends RoomDatabase {
    public abstract QuestionDao getQuestionDao();

    public static QuestionDatabase questionDatabase;

    public static QuestionDatabase getInstance(Context context){
        if(null==questionDatabase){
            questionDatabase=buildDatabaseInstance(context);
        }
        return questionDatabase;
    }

    private static QuestionDatabase buildDatabaseInstance(Context context){

        RoomDatabase.Callback rdc=new RoomDatabase.Callback(){
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);

                for (int a = 0; a < 100; a++) {
                    ContentValues contentValues=new ContentValues();
                    contentValues.put(Constants.Q_NO_COL_NAME,a+1);
                    contentValues.put(Constants.QUESTION_COL_NAME,"How many hairs do you have?");
                    contentValues.put(Constants.ANSWER_COL_NAME,"0");
                    contentValues.put(Constants.ANSWERED_COLL_NAME,0);
                    try{
                        db.insert(Constants.TABLE_NAME, OnConflictStrategy.REPLACE,contentValues);
                    }
                    catch (SQLException e){
                        Log.e("CALLBACK DONE",e.toString());
                    }
                }


            }
        };

        return Room.databaseBuilder(context,
                QuestionDatabase.class,
                Constants.DB_NAME)
                .addCallback(rdc)
                .allowMainThreadQueries().build();
    }
    public void cleanUp(){
        questionDatabase=null;
    }
}

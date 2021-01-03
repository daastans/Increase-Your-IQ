package com.example.increaseyouriq.database;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
@Dao
public interface QuestionDao {

    @Query("SELECT * FROM "+Constants.TABLE_NAME)
    List<Question> getAll();

    //@Query("SELECT * FROM "+Constants.TABLE_NAME +
    //        " WHERE "+Constants.Q_NO_COL_NAME+" = '%:questionNo%'")
    //Question getQuestionByNumber(int questionNo);

    @Insert
    void Insert(Question question);

    @Update
    void Update(Question question);

    @Delete
    void Delete(Question question);

    @Delete
    void Delete(Question... questions);
}

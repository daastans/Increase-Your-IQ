package com.example.increaseyouriq.database;

import java.io.Serializable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = Constants.TABLE_NAME)
public class Question implements Serializable {
    @PrimaryKey
    @ColumnInfo(name = Constants.Q_NO_COL_NAME)
    private int q_no;

    @ColumnInfo(name = Constants.QUESTION_COL_NAME)
    private String question;

    @ColumnInfo(name=Constants.ANSWER_COL_NAME)
    private String answer;

    @ColumnInfo(name=Constants.ANSWERED_COLL_NAME)
    private int answered;

    public Question(int q_no, String question, String answer,int answered) {
        this.q_no = q_no;
        this.question = question;
        this.answer = answer;
        this.answered=answered;
    }

    public int getQ_no() {
        return q_no;
    }

    public void setQ_no(int q_no) {
        this.q_no = q_no;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "q_no=" + q_no +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", answered=" + answered +
                '}';
    }

    public int getAnswered() {
        return answered;
    }

    public void setAnswered(int answered) {
        this.answered = answered;
    }
}

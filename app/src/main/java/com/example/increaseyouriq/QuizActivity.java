package com.example.increaseyouriq;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.ActionBar;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.widget.Toolbar;
import com.example.increaseyouriq.database.Question;

public class QuizActivity extends AppCompatActivity implements EnterCallback {

    private static final String LOG_TAG="QuizActivity";

    LineEditText question;
    EditText editText;
    MyKeyboard keyboard;
    private Question questionAsked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        init();
        initActionBar();

        questionAsked= (Question) getIntent().getSerializableExtra("questionToAsk");
        question=findViewById(R.id.question_edittext);
        question.setText(questionAsked.getQuestion());

        editText = (EditText) findViewById(R.id.editText);

        keyboard = (MyKeyboard) findViewById(R.id.keyboard);

        editText.setRawInputType(InputType.TYPE_CLASS_TEXT);
        editText.setTextIsSelectable(true);


        InputConnection ic = editText.onCreateInputConnection(new EditorInfo());
        keyboard.setInputConnection(ic);
        keyboard.onEnter=this;

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
        }
    );

        if(getSupportActionBar()!=null) {

            Log.e(LOG_TAG,getSupportActionBar().toString());
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

        }

    }

    void init(){
        DisplayMetrics displayMetrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height=displayMetrics.heightPixels;
        int width=displayMetrics.widthPixels;

        BitmapDrawable bmap = (BitmapDrawable) this.getResources().getDrawable(R.drawable.question_img);
        float bmapWidth = bmap.getBitmap().getWidth();
        float bmapHeight = bmap.getBitmap().getHeight();

        float wRatio = width / bmapWidth;
        float hRatio = height / bmapHeight;

        float ratioMultiplier = wRatio;
// Untested conditional though I expect this might work for landscape mode
        if (hRatio < wRatio) {
            ratioMultiplier = hRatio;
        }

        int newBmapWidth = (int) (bmapWidth*ratioMultiplier);
        int newBmapHeight = (int) (bmapHeight*ratioMultiplier);

        ImageView iView = (ImageView) findViewById(R.id.questin_image);
        iView.setLayoutParams(new LinearLayout.LayoutParams(newBmapWidth, newBmapHeight));

        Log.w(LOG_TAG,String.valueOf(bmapHeight)+" "+String.valueOf(bmapWidth)
                +" "+String.valueOf(wRatio)+" "+String.valueOf(hRatio)
                +" "+String.valueOf(ratioMultiplier)+" "+String.valueOf(newBmapHeight)
                +" "+String.valueOf(newBmapWidth));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(LOG_TAG,"Destroyed");
    }

    @Override
    public void onEnter(String string) {
        showDialog();

        Log.e(LOG_TAG,string);
    }
    private void showDialog(){
        final View dialogView=View.inflate(this,R.layout.correct_answer_dialog,null);
        final Dialog dialog=new Dialog(QuizActivity.this,R.style.Theme_AppCompat_NoActionBar);
        dialog.setContentView(dialogView);

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onShow(DialogInterface dialogInterface) {
                revealShow(dialogView,true,dialog);
            }
        });
        dialog.show();

    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void revealShow(View dialogView, boolean b, final Dialog dialog) {
        Log.e(LOG_TAG,"Reached end");

        final View view = dialogView.findViewById(R.id.correct_answer_screen);

        int w = view.getWidth();
        int h = view.getHeight();

        int endRadius = (int) Math.hypot(w, h);

        int cx = (int) w/2;
        int cy=(int) h/2;


        if(b){
            Animator revealAnimator = ViewAnimationUtils.createCircularReveal(view, cx,cy, 0, endRadius);

            view.setVisibility(View.VISIBLE);
            revealAnimator.setDuration(700);
            revealAnimator.start();

        } else {

            Animator anim =
                    ViewAnimationUtils.createCircularReveal(view, cx, cy, endRadius, 0);

            anim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    dialog.dismiss();
                    view.setVisibility(View.INVISIBLE);

                }
            });
            anim.setDuration(900);
            anim.start();
        }



    }
}
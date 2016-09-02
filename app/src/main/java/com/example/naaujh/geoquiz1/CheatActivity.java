package com.example.naaujh.geoquiz1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;

/**
 * Created by Jeff Hammond on 8/31/2016.
 */
public class CheatActivity extends Activity{
    public static final String EXTRA_ANSWER_IS_TRUE=
            "com.bignerdranch.android.geoquiz.answer_is_true";
    public static final String EXTRA_ANSWER_SHOWN=
            "com.bignerdranch.android.geoquiz.answer_shown";
    private boolean mAnswerIsTrue;
    private TextView mAnswerTextView1;
    private Button mShowAnswer1;

    private void setAnswerShownResult(boolean isAnswerShown){
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK,data);
    }




    @Override
    protected void onCreate(Bundle savedInstanceState){
       super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        mAnswerIsTrue=getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE,false);

        //mAnswerTextView1=(TextView)findViewById(R.id.answerTextView
        mAnswerTextView1=(TextView)findViewById(R.id.answerTextView1);



        mShowAnswer1=(Button)findViewById(R.id.showAnswerButton1);
        mShowAnswer1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(mAnswerIsTrue){
                    mAnswerTextView1.setText(R.string.true_button);
                }else{
                    mAnswerTextView1.setText(R.string.false_button);
                }

                setAnswerShownResult(true);
            }
        });



    }
}

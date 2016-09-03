package com.example.naaujh.geoquiz1;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mNextButton;
    private ImageButton mPreviousButton;
    private Button mCheatButton;
    //private boolean mIsCheater;
    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";
    private static final String CHEAT_STATUS="cheat_status";


    private TextView mQuestionTextView;
    private TextView mLevelAPI;

    private TrueFalse[] mQuestionBank=new TrueFalse[]{
            new TrueFalse(R.string.question_one,false),
            new TrueFalse(R.string.question_two,true),
            new TrueFalse(R.string.question_three,false),
            new TrueFalse(R.string.question_four,true),
    };

    private boolean[] mQuestionCheatStatus = new boolean[mQuestionBank.length];

    private int mCurrentIndex=0;

    private void updateQuestion(){
        int question = mQuestionBank[mCurrentIndex].getQuestion();
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue){
        boolean answerIsTrue=mQuestionBank[mCurrentIndex].isTrueQuestion();

        int messageResId=0;

        if(mQuestionCheatStatus[mCurrentIndex]){
            messageResId=R.string.judgement_toast;
        }else{
            if(userPressedTrue == answerIsTrue){
                messageResId = R.string.correct_toast;
            }
            else{
                messageResId = R.string.incorrect_toast;
            }

        }


        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(data==null){
            return;
        }

        //mIsCheater=data.getBooleanExtra(CheatActivity.EXTRA_ANSWER_SHOWN, false);
        mQuestionCheatStatus[mCurrentIndex]=data.getBooleanExtra(CheatActivity.EXTRA_ANSWER_SHOWN, false);
    }







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mQuestionTextView = (TextView)findViewById(R.id.question_text_view);

        mLevelAPI =  (TextView)findViewById(R.id.API_version);
        //mLevelAPI.setText(Build.VERSION.SDK_INT);
        mLevelAPI.setText("API Level is: " + Integer.toString(Build.VERSION.SDK_INT));
        //mLevelAPI.setText("16");


        mTrueButton = (Button)findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
            checkAnswer(true);
            }
        });

        mFalseButton = (Button)findViewById((R.id.false_button));
        mFalseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
            checkAnswer(false);
            }
        });


        mNextButton = (ImageButton)findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();

            }
        });

        mPreviousButton = (ImageButton)findViewById(R.id.previous_button);
        mPreviousButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(mCurrentIndex > 0)
                mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.length;
                else
                mCurrentIndex=mQuestionBank.length-1;

                updateQuestion();
            }
        });
    //
        //mQuestionTextView = (Button)findViewById(R.id.question_text_view);
        mQuestionTextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mCurrentIndex = (mCurrentIndex+1) % mQuestionBank.length;

                updateQuestion();
            }
        });

        if(savedInstanceState != null){
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX,0);
            //mIsCheater = savedInstanceState.getBoolean(CHEAT_STATUS, false);
            mQuestionCheatStatus = savedInstanceState.getBooleanArray(CHEAT_STATUS);
        }

        mCheatButton=(Button)findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i=new Intent(QuizActivity.this,CheatActivity.class);
                boolean answerIsTrue=mQuestionBank[mCurrentIndex].isTrueQuestion();
                i.putExtra(CheatActivity.EXTRA_ANSWER_IS_TRUE,answerIsTrue);
                startActivityForResult(i,0);
            }
        });



        updateQuestion();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG,"onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX,mCurrentIndex);
        //savedInstanceState.putBoolean(CHEAT_STATUS, mIsCheater);
        savedInstanceState.putBooleanArray(CHEAT_STATUS,mQuestionCheatStatus);
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.d(TAG, "onStart() called");

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }


}

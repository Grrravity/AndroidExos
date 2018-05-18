package com.error.grrravity.topquizz.Controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.error.grrravity.topquizz.Model.Question;
import com.error.grrravity.topquizz.Model.QuestionBank;
import com.error.grrravity.topquizz.R;

import java.util.Arrays;

import static java.lang.System.out;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mQuestionText;
    private Button mAnswer1, mAnswer2, mAnswer3, mAnswer4;

    private QuestionBank mQuestionBank;
    private Question mCurrentQuestion;

    private int mScore;
    private int mNumberOfQuestions;
    public static final String BUNDLE_EXTRA_SCORE = "BUNDLE_EXTRA_SCORE";
    public static final String BUNDLE_STATE_SCORE = "currentScore";
    public static final String BUNDLE_STATE_QUESTION = "currentQuestion";

    private boolean mEnableTouchEvents;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        System.out.print("GameActivity::onCreate()");
        mQuestionBank = this.generateQuestions();

        if (savedInstanceState != null) {
            mScore = savedInstanceState.getInt(BUNDLE_STATE_SCORE);
            mNumberOfQuestions = savedInstanceState.getInt(BUNDLE_STATE_QUESTION);
        }
        else {
            mScore = 0;
            mNumberOfQuestions = 4;
        }

        mEnableTouchEvents = true;

        mQuestionText = findViewById(R.id.activity_game_question);
        mAnswer1 = findViewById(R.id.activity_game_answer_1);
        mAnswer2 = findViewById(R.id.activity_game_answer_2);
        mAnswer3 = findViewById(R.id.activity_game_answer_3);
        mAnswer4 = findViewById(R.id.activity_game_answer_4);

        mAnswer1.setTag(0);
        mAnswer2.setTag(1);
        mAnswer3.setTag(2);
        mAnswer4.setTag(3);

        mAnswer1.setOnClickListener(this);
        mAnswer2.setOnClickListener(this);
        mAnswer3.setOnClickListener(this);
        mAnswer4.setOnClickListener(this);

        mCurrentQuestion = mQuestionBank.getQuestion();
        this.displayQuestion(mCurrentQuestion);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(BUNDLE_STATE_SCORE, mScore);
        outState.putInt(BUNDLE_STATE_QUESTION, mNumberOfQuestions);

        super.onSaveInstanceState(outState);
    }

    @Override
    public void onClick(View v) {
        int responseIndex = (int) v.getTag();
        if (responseIndex == mCurrentQuestion.getAnswerIndex()){
            //Good
            Toast.makeText(this, getString(R.string.correct), Toast.LENGTH_SHORT).show();
            mScore++;
        }
        else {
            Toast.makeText(this,getString(R.string.wrong), Toast.LENGTH_SHORT).show();
            //Wrong
        }
        mEnableTouchEvents = false;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mEnableTouchEvents = true;
                if (--mNumberOfQuestions == 0) {
                    //End game
                    endGame();
                } else {
                    mCurrentQuestion = mQuestionBank.getQuestion();
                    displayQuestion(mCurrentQuestion);
                }
            }
            }, 2000);
        }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return mEnableTouchEvents && super.dispatchTouchEvent(ev);
    }

    private void endGame() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(getString(R.string.wd))
                .setMessage(getString(R.string.score_is) + mScore)
                .setPositiveButton(getString(R.string.ok_button), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //End the activity
                        Intent intent = new Intent();
                        intent.putExtra(BUNDLE_EXTRA_SCORE, mScore);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                })
                .create()
                .show();
    }

    private void displayQuestion(final Question question){
        mQuestionText.setText(question.getQuestion());
        mAnswer1.setText(question.getChoiceList().get(0));
        mAnswer2.setText(question.getChoiceList().get(1));
        mAnswer3.setText(question.getChoiceList().get(2));
        mAnswer4.setText(question.getChoiceList().get(3));
    }


    private QuestionBank generateQuestions(){

        Question question1 = new Question(getString(R.string.french_president_name),
                Arrays.asList(getString(R.string.president_answer_0),
                        getString(R.string.president_answer_1),
                        getString(R.string.president_answer_2),
                        getString(R.string.president_answer_3)), 1);

        Question question2 = new Question(getString(R.string.european_union_number),
                Arrays.asList(getString(R.string.EU_number_answer_0),
                        getString(R.string.EU_number_answer_1),
                        getString(R.string.EU_number_answer_2),
                        getString(R.string.EU_number_answer_3)), 2);

        Question question3 = new Question(getString(R.string.android_OS_System_Creator),
                Arrays.asList(getString(R.string.android_OS_answer_0),
                        getString(R.string.android_OS_answer_1),
                        getString(R.string.android_OS_answer_2),
                        getString(R.string.android_OS_answer_3)), 0);

        Question question4 = new Question(getString(R.string.moon_landing_year),
                Arrays.asList(getString(R.string.moon_landing_answer_0)
                        ,getString(R.string.moon_landing_answer_1)
                        ,getString(R.string.moon_landing_answer_2)
                        ,getString(R.string.moon_landing_answer_3)), 3);

        Question question5 = new Question(getString(R.string.romania_capital),
                Arrays.asList(getString(R.string.romania_capital_answer_0)
                        ,getString(R.string.romania_capital_answer_1)
                        ,getString(R.string.romania_capital_answer_2)
                        ,getString(R.string.romania_capital_answer_3)), 0);

        Question question6 = new Question(getString(R.string.mona_lisa_painter),
                Arrays.asList(getString(R.string.mona_lisa_painter_answer_0)
                        ,getString(R.string.mona_lisa_painter_answer_1)
                        ,getString(R.string.mona_lisa_painter_answer_2)
                        ,getString(R.string.mona_lisa_painter_answer_3)), 1);

        Question question7 = new Question(getString(R.string.chopin_buried),
                Arrays.asList(getString(R.string.chopin_buried_answer_0)
                        ,getString(R.string.chopin_buried_answer_1)
                        ,getString(R.string.chopin_buried_answer_2)
                        ,getString(R.string.chopin_buried_answer_3)), 2);

        Question question8 = new Question(getString(R.string.belgium_domain),
                Arrays.asList(getString(R.string.belgium_domain_answer_0)
                        ,getString(R.string.belgium_domain_answer_1)
                        ,getString(R.string.belgium_domain_answer_2)
                        ,getString(R.string.belgium_domain_answer_3)), 3);

        Question question9 = new Question(getString(R.string.simpsons_house),
                Arrays.asList(getString(R.string.simpsons_house_answer_0)
                        ,getString(R.string.simpsons_house_answer_1)
                        ,getString(R.string.simpsons_house_answer_2)
                        ,getString(R.string.simpsons_house_answer_3)), 3);

        return new QuestionBank(Arrays.asList(question1,
                question2,
                question3,
                question4,
                question5,
                question6,
                question7,
                question8,
                question9));
    }
    @Override
    protected void onStart() {
        super.onStart();

        out.println("GameActivity::onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();

        out.println("GameActivity::onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();

        out.println("GameActivity::onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();

        out.println("GameActivity::onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        out.println("GameActivity::onDestroy()");
    }
}

package com.error.grrravity.topquizz.Controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.error.grrravity.topquizz.Model.User;
import com.error.grrravity.topquizz.R;

import static java.lang.System.out;

public class MainActivity extends AppCompatActivity implements TextWatcher, View.OnClickListener {

    private TextView mGreetingText;
    private EditText mNameImput;
    private Button mPlayButton;
    private User mUser;
    public static final int GAME_ACTIVITY_REQUEST_CODE = 42;
    private SharedPreferences mPreferences;

    public static final String PREF_KEY_SCORE = "PREF_KEY_SCORE";
    public static final String PREF_KEY_FIRST_NAME = "PREF_KEY_FIRST_NAME";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.out.println("MainActivity::onCreate");

        mUser = new User();

        mPreferences = getPreferences(MODE_PRIVATE);

        initVars();

        initView();

        initListener();

    }

    private void initListener() {
        mNameImput.addTextChangedListener(this);

        mPlayButton.setOnClickListener(this);
    }

    private void initView() {

        mPlayButton.setEnabled(false);

        greetUser();

    }

    private void initVars() {
        mGreetingText = findViewById(R.id.activity_main_greeting_txt);
        mNameImput = findViewById(R.id.activity_main_name_input);
        mPlayButton = findViewById(R.id.activity_main_play_btn);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(GAME_ACTIVITY_REQUEST_CODE == requestCode && RESULT_OK == resultCode){
            int score = data.getIntExtra(GameActivity.BUNDLE_EXTRA_SCORE, 0);

            mPreferences.edit().putInt(PREF_KEY_SCORE, score).apply();

            greetUser();
        }
    }

    private void greetUser() {
        String firstname = mPreferences.getString(PREF_KEY_FIRST_NAME, null);

        if (firstname != null){
            int score = mPreferences.getInt(PREF_KEY_SCORE, 0);

            String fulltext = getString(R.string.greet_on_restart) + firstname + getString(R.string.last_score) + score + getString(R.string.do_better);
            mGreetingText.setText(fulltext);
            mNameImput.setText(firstname);
            mNameImput.setSelection(firstname.length());
            mPlayButton.setEnabled(true);
        }
    }
    @Override
    protected void onStart() {
        super.onStart();

        out.println("MainActivity::onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();

        out.println("MainActivity::onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();

        out.println("MainActivity::onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();

        out.println("MainActivity::onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        out.println("MainActivity::onDestroy()");
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        mPlayButton.setEnabled(s.toString().length() != 0);

    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.activity_main_play_btn:
                mUser.setFirstName(mNameImput.getText().toString());
                mPreferences.edit().putString(PREF_KEY_FIRST_NAME, mUser.getFirstName()).apply();
                Intent gameActivityIntent = new Intent(MainActivity.this,
                        GameActivity.class);
                startActivityForResult(gameActivityIntent, GAME_ACTIVITY_REQUEST_CODE);
                break;
        }

    }
}

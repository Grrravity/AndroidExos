package com.error.grrravity.topquizz.Model;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class User2 extends AppCompatActivity{

    private static final String PREFS = "PREFS";
    private static final String PREFS_SCORE = "PREFS_SCORE";
    private static final String PREFS_NAME = "PREFS_NAME";
    private SharedPreferences sharedPreferences;

    public void setPlayerInfo (String userName, int userScore){
        sharedPreferences = getBaseContext().getSharedPreferences(PREFS, MODE_PRIVATE);

        //objectif : sauvegarder 1 seule fois le nom et l'age de l'utilisateur
        //pour cela, on commence par regarder si on a déjà des éléments sauvegardés

        if (sharedPreferences.contains(PREFS_SCORE) && sharedPreferences.contains(PREFS_NAME)) {

            int score = sharedPreferences.getInt(PREFS_SCORE, 0);
            String name = sharedPreferences.getString(PREFS_NAME, null);
            Toast.makeText(this, "Name: " + name + " Score: " + score, Toast.LENGTH_SHORT).show();

        } else {

            //si aucun utilisateur n'est sauvegardé, on ajoute

            sharedPreferences
                    .edit()
                    .putInt(PREFS_SCORE, userName)
                    .putString(PREFS_NAME, userScore)
                    .apply();
            Toast.makeText(this, "Sauvegardé Name: " +PREFS_SCORE+ "Score" +PREFS_SCORE, Toast.LENGTH_SHORT).show();
        }
    }

    public static String getPlayerInfo (){
        String playerInfo = PREFS_NAME +" : "+ PREFS_SCORE;
        return playerInfo;
    }
}
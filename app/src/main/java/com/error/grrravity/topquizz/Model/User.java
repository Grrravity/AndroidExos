package com.error.grrravity.topquizz.Model;

public class User {
    private String mFirstName, mFirstNameP2, mFirstNameP3, mFirstNameP4;
    private boolean mPlayer1 = false, mPlayer2 = false, mPlayer3 = false, mPlayer4 = false;

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {

        if (mPlayer4){
            mFirstNameP4 = mFirstNameP3;
            mFirstNameP3 = mFirstNameP2;
            mFirstNameP2 = mFirstName;
            mFirstName = firstName;
        }

        else if ((mPlayer3) || (!mPlayer4)){
            mFirstNameP4 = mFirstNameP3;
            mFirstNameP3 = mFirstNameP2;
            mFirstNameP2 = mFirstName;
            mFirstName = firstName;
            mPlayer4 = true;
        }

        else if ((mPlayer2) || (!mPlayer3)){
            mFirstNameP3 = mFirstNameP2;
            mFirstNameP2 = mFirstName;
            mFirstName = firstName;
            mPlayer2 = true;
        }

        else if ((mPlayer1) || (!mPlayer2)){
            mFirstNameP2 = mFirstName;
            mFirstName = firstName;
            mPlayer2 = true;
        }

        else {
                mFirstName = firstName;
                mPlayer1 = true;
        }

    }

    @Override
    public String toString() {
        return "User{mFirstName='" + mFirstName + '\''+'}';
    }
}

package com.example.pma_project_accelerometer;

import android.content.Context;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class Timer extends CountDownTimer {

    private long mTimeLeftInMillis;
    private boolean mTimerRunning;
    private Context context;

    public void resetTimer(){
        mTimeLeftInMillis = 30000;
    }

    public Timer(Context context) {
        super(30000,1000);
        this.context = context;

        mTimeLeftInMillis = 30000;
    }

    public long getmTimeLeftInMillis() {
        return mTimeLeftInMillis;
    }

    public void setmTimeLeftInMillis(long mTimeLeftInMillis) {
        this.mTimeLeftInMillis = mTimeLeftInMillis;
    }

    public boolean ismTimerRunning() {
        return mTimerRunning;
    }

    public void setmTimerRunning(boolean mTimerRunning) {
        this.mTimerRunning = mTimerRunning;
    }

    public void startTimer(long millisUntilFinished){
        onTick(millisUntilFinished);
        //onFinish();
        start();
    }


    @Override
    public void onTick(long millisUntilFinished) {
        setmTimeLeftInMillis(millisUntilFinished);

        int seconds = (int) getmTimeLeftInMillis()/1000;
        MainActivity.timerText.setText(String.valueOf(seconds));
    }
    @Override
    public void onFinish() {
        setmTimerRunning(false);
        LeaderboardActivity.addToResultsList(MainActivity.stepCount);
        MainActivity.stepCount = 0;
        Toast.makeText(context,"Your score has been uploaded to the leaderboards",Toast.LENGTH_SHORT).show();
        resetTimer();
    }




}

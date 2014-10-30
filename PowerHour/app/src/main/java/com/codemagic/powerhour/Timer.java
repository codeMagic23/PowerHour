package com.codemagic.powerhour;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by James on 10/30/2014.
 * Class to track time left,
 * restart time
 */
public class Timer {

    private static final String  TIMER_TAG = "com.codemagic.powerhour.Timer class";
    private static long SONG_INTERVAL = 60000;
    private static long TICKS = 1000;
    private static long TIME_LEFT_IN_SONG;
    private static boolean PLAY_NEXT = true;
    private static int SONGS_LEFT = 1;

    private static int NUMBER_OF_SONGS;

    private Context mContext;

    public Timer(Context c) {
        mContext = c;
    }

    public void setNewTimer(Context c, long songLength, int numSongs) {
        mContext = c;
        try {
            SONG_INTERVAL = songLength;
            NUMBER_OF_SONGS = numSongs;
            SONGS_LEFT = NUMBER_OF_SONGS;
        } catch (NumberFormatException e) {
            Log.d(TIMER_TAG, e.getMessage());
            NUMBER_OF_SONGS = 1;
        }
        startTimer();
    }

    private class CountDown extends CountDownTimer
    {

        public CountDown(long millisUntilFinished, long ticks)
        {
            super(millisUntilFinished, ticks);
            if (SONGS_LEFT > 0)
                SONGS_LEFT -= 1;
            else
                PLAY_NEXT = false;
        }
        public void onTick(long millisUntilFinished){
            Toast.makeText(mContext, "Ticks left: " + millisUntilFinished/1000, Toast.LENGTH_SHORT).show();
        }

        public void onFinish(){
            if (PLAY_NEXT) {
                Toast.makeText(mContext, "com.codemagic.powerhour.Timer has finished", Toast.LENGTH_SHORT);
                startTimer();
            }
            else {
                // will need to display some message here and null out the MediaPlayer
                Toast.makeText(mContext, "No more songs to play", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void startTimer(){
        new CountDown(SONG_INTERVAL, TICKS).start();
    }
}

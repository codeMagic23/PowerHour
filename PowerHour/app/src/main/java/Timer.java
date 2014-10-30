import android.os.CountDownTimer;
import android.util.Log;

/**
 * Created by James on 10/30/2014.
 * Class to track time left,
 * restart time
 */
public class Timer {

    private static final String  TIMER_TAG = "Timer class";
    private static long SONG_INTERVAL = 60000;
    private static long TICKS = 1000;
    private static long TIME_LEFT_IN_SONG;
    private static boolean PLAY_NEXT = true;

    private static int NUMBER_OF_SONGS;

    public static void setNewTimer(long songLength, int numSongs) {
        try {
            SONG_INTERVAL = songLength;
            NUMBER_OF_SONGS = numSongs;
        } catch (NumberFormatException e) {
            Log.d(TIMER_TAG, e.getMessage());
            NUMBER_OF_SONGS = 1;
        }

        startTimer();

    }

    private static class CountDown extends CountDownTimer
    {

        public CountDown(long millisUntilFinished, long ticks)
        {
            super(millisUntilFinished, ticks);
        }
        public void onTick(long millisUntilFinished){}

        public void onFinish(){
            if (PLAY_NEXT) {
                startTimer();
            }
            else {
                // will need to display some message here and null out the MediaPlayer
            }
        }
    }

    private static void startTimer(){
        new CountDown(SONG_INTERVAL, TICKS);

    }

}

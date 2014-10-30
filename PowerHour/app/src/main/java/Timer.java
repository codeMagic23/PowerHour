import android.os.CountDownTimer;

/**
 * Created by James on 10/30/2014.
 */
public class Timer {

    private static long RESTART_TIME = 60000;
    private static long TICKS = 1000;
    private static long TIME_LEFT_IN_SONG;
    private static boolean PLAY_NEXT = true;

    public static void setNewTimer() {

    }

    private class CountDown extends CountDownTimer
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


        }

    }

    private void startTimer(){
        new CountDown(RESTART_TIME, TICKS);

    }

}

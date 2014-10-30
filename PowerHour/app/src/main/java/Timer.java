import android.os.CountDownTimer;

/**
 * Created by James on 10/30/2014.
 */
public class Timer {

    private static long RESTART_TIME = 6000;
    private static long TIME_LEFT_IN_SONG;

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

        }

    }

}

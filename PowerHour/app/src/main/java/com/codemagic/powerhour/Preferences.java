package com.codemagic.powerhour;

import android.content.Context;
import android.preference.Preference;

/**
 * Created by James on 10/30/2014.
 *
 * This class may store time preferences,
 * music service prefs,
 * themes, etc...
 */
public class Preferences extends Preference {
    public Preferences(Context context) {
        super(context);
    }

    // amount of milliseconds each song should play
    public void setSongLength(long length) {
        // set the length here
    }

    // user can choose to play a certain # of songs
    public void setNumberSongs(int numSongs) {

    }

    // user can choose to play random songs for a certain amount of time
    public void setLengthOfSession(long sessionTime) {

    }


}

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

    public void setSongLength(long length) {
        // set the length here
    }

    public void setNumberSongs(int numSongs) {
        
    }


}

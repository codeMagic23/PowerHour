import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by James on 10/30/2014.
 *
 * Class to provide details of the currently playing song
 */
public class NowPlaying {

    private ArrayList<Object> currentList = new ArrayList<Object>();
    private String mName;

    public NowPlaying(String name){
        currentList.add(mName);
    }

    public String getSongName() {
        String title = currentList.get(0).toString();
        return title;
    }

    public Bitmap getAlbumCover() {
        Bitmap bm = null;
        return bm;
    }
}



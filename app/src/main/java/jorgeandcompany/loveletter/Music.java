package jorgeandcompany.loveletter;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by Penguins94 on 11/4/2015.
 */
public class Music implements Runnable{
    private static MediaPlayer mp;
    private static int currentPosition;
    private static boolean isMute;

    static {
        currentPosition = 0;
        isMute = false;
        mp = new MediaPlayer();
    }
    public Music (MediaPlayer aPlayer) {
        mp = aPlayer;
    }

    @Override
    public void run() {
        mp.seekTo(currentPosition);
        mp.setLooping(true);
        mp.start();
    }

    public void stop () {
        mp.stop();
    }

    public int getCurrentPosition () {
        currentPosition = mp.getCurrentPosition();
       return currentPosition;
    }

    public void pause () {
        mp.pause();
    }

    public void setVolume (float a, float b) {
        mp.setVolume(a, b);
    }

    public static void changeMuteStatus () {
        if (isMute) {
            isMute = false;
        }
        else {
            isMute = true;
        }
    }

    public static boolean isMute () {
        return isMute;
    }

    public boolean isPlaying () {
        return mp.isPlaying();
    }

    public void restartPosition () {
       currentPosition = 0;
    }

    public void setPlayer (MediaPlayer aPlayer) {
        mp = aPlayer;
    }
}

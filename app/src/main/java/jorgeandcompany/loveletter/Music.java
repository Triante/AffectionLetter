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
        if (isPlaying()) {
            mp.stop();
        }
        mp.seekTo(currentPosition);
        mp.setLooping(true);
        mp.start();
    }

    public void stop () {
        mp.stop();
    }

    /**
     * Gets the current position of the music
     * @return the position of the music.
     */
    public int getCurrentPosition () {
        currentPosition = mp.getCurrentPosition();
       return currentPosition;
    }

    /**
     * Pauses the current music that is playing.
     */

    public void pause () {
        mp.pause();
    }

    /**
     * sets the volume for the music
     * @param a the volume of the right speaker
     * @param b the volume of the left speaker
     */
    public void setVolume (float a, float b) {
        mp.setVolume(a, b);
    }

    /**
     * Changes the flag for muting the music from true to false or false to true.
     */
    public static void changeMuteStatus () {
        if (isMute) {
            isMute = false;
        }
        else {
            isMute = true;
        }
    }

    /**
     * Checks if the music is muted.
     * @return returns true if the music is muted, false otherwise.
     */
    public static boolean isMute () {
        return isMute;
    }

    /**
     * Checks if there is any music currently playing.
     * @return true if there is music currently playing, false otherwise.
     */
    public boolean isPlaying () {
        return mp.isPlaying();
    }

    /**
     * Restarts the music.
     */
    public void restartPosition () {
       currentPosition = 0;
    }

    /**
     * Sets a MediaPlayer for the music.
     * @param aPlayer the MediaPlayer to be set.
     */
    public void setPlayer (MediaPlayer aPlayer) {
        mp = aPlayer;
    }
}

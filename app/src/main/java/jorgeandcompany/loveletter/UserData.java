package jorgeandcompany.loveletter;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Penguins94 on 11/13/2015.
 */
public class UserData implements Serializable {
    private ArrayList<String> skinNames;
    private String activeSkin;
    private boolean musicOn;

    /**
     * Sets the key for the active skin as a String to be saved in a file.
     * @param skin the key for the active skin as a String.
     */
    public void setActiveSkin (String skin) {
        this.activeSkin = skin;
        SkinRes.activeSkin = skin;
    }

    /**
     * Returns the key for the active skin as a String.
     * @return the key for the active skin as a String.
     */
    public String getActiveSkin () {
        return activeSkin;
    }

    /**
     * Returns the flag for whether the music is muted or not.
     * @return the flag for whether the music is muted or not.
     */
    public boolean isMusicOn () {
        return musicOn;
    }

    /**
     * Sets the flag of whether the music is muted or not to be saved later in a file.
     * @param status the flag of whether the music is muted or not
     */
    public void setMusicStatus (boolean status) {
        musicOn = status;
    }

    /**
     * Sets the Skins that the player has unlocked to be saved in a file.
     * @param skins the list of skins the player has unlocked.
     */
    public void setSkinsAvailable (ArrayList <String> skins) {
        this.skinNames = skins;
    }

    /**
     * Returns the list of skins the player has unlocked.
     * @return the list of skins the player has unlocked.
     */
    public ArrayList <String> getSkinsAvailable () {
        return skinNames;
    }
}

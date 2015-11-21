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

    public void setActiveSkin (String skin) {
        this.activeSkin = skin;
        SkinRes.activeSkin = skin;
    }
    public String getActiveSkin () {
        return activeSkin;
    }
    public boolean isMusicOn () {
        return musicOn;
    }
    public void setMusicStatus (boolean status) {
        musicOn = status;
    }
    public void setSkinsAvailable (ArrayList <String> skins) {
        this.skinNames = skins;
    }
    public ArrayList <String> getSkinsAvailable () {
        return skinNames;
    }
}

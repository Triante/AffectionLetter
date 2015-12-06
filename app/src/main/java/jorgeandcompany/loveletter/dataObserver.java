package jorgeandcompany.loveletter;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 * Created by Penguins94 on 11/20/2015.
 */
public class dataObserver implements Observer {
    Music theMusic;
    UserData theData;
    File userFile;
    public dataObserver (Music music, UserData data, File file) {
        theMusic = music;
        theData = data;
        userFile = file;
    }

    /**
     * Updates the serializable file for a user when there is a change in the data
     */
    public void updateSave() {
        try {
            if (!theData.getActiveSkin().equalsIgnoreCase(SkinRes.activeSkin)) {
                theData.setActiveSkin(SkinRes.activeSkin);
            }
            if (theMusic.isMute()) {
                theData.setMusicStatus(false);
            } else {
                theData.setMusicStatus(true);
            }
            OutputStream file = new FileOutputStream(userFile.getPath());
            OutputStream buffer = new BufferedOutputStream(file);
            ObjectOutput opstream = new ObjectOutputStream(buffer);
            opstream.writeObject(theData);
            opstream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

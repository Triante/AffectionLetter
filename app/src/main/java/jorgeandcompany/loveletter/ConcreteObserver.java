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
public class ConcreteObserver implements Observer {
    private Music theMusic;
    private UserData theData;
    private File userFile;
    
    public ConcreteObserver(Music music, UserData data, File file) {
        theMusic = music;
        theData = data;
        userFile = file;
    }
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

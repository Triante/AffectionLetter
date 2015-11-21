package jorgeandcompany.loveletter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StreamCorruptedException;
import java.util.ArrayList;


public class MainMenu extends ActionBarActivity implements View.OnClickListener {

    private Button bMainMenu1, bMainMenu2, bMainMenu3, bMainMenu4;
    private boolean COMPUTER_FLAG = false;
    private boolean MULTIPLAYER_FLAG = false;
    private boolean SINGLE_PLAYER = false;
    private int MULTIPLAYER_COUNT = 0;
    private int COMPUTER_LEVEL = 0;
    private static Music theMusic = null;
    public static int returnState = 0;
    public static int otherState = 0;
    private Thread newThread;
    private static UserData theData;
    private static File userFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        readFile();
        bMainMenu1 = (Button) findViewById(R.id.bMainMenu1);
        bMainMenu2 = (Button) findViewById(R.id.bMainMenu2);
        bMainMenu3 = (Button) findViewById(R.id.bMainMenu3);
        bMainMenu4 = (Button) findViewById(R.id.bMainMenu4);

        bMainMenu1.setOnClickListener(this);
        bMainMenu2.setOnClickListener(this);
        bMainMenu3.setOnClickListener(this);
        bMainMenu4.setOnClickListener(this);
    }

    public void readFile() {
        try {
            userFile = new File(Environment.getExternalStorageDirectory(), "User.ser");
            if (userFile.exists()) {
                try {
                    FileInputStream newReader = new FileInputStream(userFile.getPath());
                    ObjectInputStream reader = new ObjectInputStream(newReader);
                    theData = (UserData) reader.readObject();
                    reader.close();
                    if (theData.isMusicOn()) {
                        theMusic = new Music(new MediaPlayer().create(getApplicationContext(), R.raw.classical_open));
                        Runnable musicRunnable = theMusic;
                        newThread = new Thread(musicRunnable);
                        newThread.start();
                    } else {
                        theMusic = new Music(new MediaPlayer().create(getApplicationContext(), R.raw.classical_open));
                        theMusic.setVolume(0, 0);
                        theMusic.changeMuteStatus();
                        Runnable musicRunnable = theMusic;
                        newThread = new Thread(musicRunnable);
                        newThread.start();
                    }
                    theData.setActiveSkin(theData.getActiveSkin());
                    SkinRes.skinNames = theData.getSkinsAvailable();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                theData = new UserData();
                theData.setActiveSkin("Fire Emblem Skin");
                ArrayList<String> skins = new ArrayList<>();
                skins.add("Avengers Skin");
                skins.add("Magi Skin");
                skins.add("Fire Emblem Skin");
                theData.setSkinsAvailable(skins);
                SkinRes.skinNames.add("Avengers Skin");
                SkinRes.skinNames.add("Magi Skin");
                SkinRes.skinNames.add("Fire Emblem Skin");
                theData.setMusicStatus(true);
                theMusic = new Music(new MediaPlayer().create(getApplicationContext(), R.raw.classical_open));
                Runnable musicRunnable = theMusic;
                newThread = new Thread(musicRunnable);
                newThread.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bMainMenu1:
                //Single Player
                if (!COMPUTER_FLAG && !MULTIPLAYER_FLAG) {
                    bMainMenu1.setText("Level 1 Computer(s)");
                    bMainMenu2.setText("Level 2 Computer(s)");
                    bMainMenu3.setText("Level 3 Computer(s)");
                    bMainMenu4.setText("Back");
                    COMPUTER_FLAG = true;
                    SINGLE_PLAYER = true;
                }
                //Level 1 Computers
                else if (COMPUTER_FLAG) {
                    COMPUTER_LEVEL = 1;
                    readyMessage();
                }
                //2 Player Mode
                else if (MULTIPLAYER_FLAG) {
                    MULTIPLAYER_COUNT = 2;
                    bMainMenu1.setText("Level 1 Computer(s)");
                    bMainMenu2.setText("Level 2 Computer(s)");
                    bMainMenu3.setText("Level 3 Computer(s)");
                    COMPUTER_FLAG = true;
                }
                break;
            case R.id.bMainMenu2:
                //MultiPlayer
                if (!COMPUTER_FLAG && !MULTIPLAYER_FLAG) {
                    bMainMenu1.setText("2 Player Mode");
                    bMainMenu2.setText("3 Player Mode");
                    bMainMenu3.setText("4 Player Mode");
                    bMainMenu4.setText("Back");
                    MULTIPLAYER_FLAG = true;
                }
                //Level 2 Computers
                else if (COMPUTER_FLAG) {
                    COMPUTER_LEVEL = 2;
                    readyMessage();
                }
                //3 Player Mode
                else if (MULTIPLAYER_FLAG) {
                    MULTIPLAYER_COUNT = 3;
                    bMainMenu1.setText("Level 1 Computer(s)");
                    bMainMenu2.setText("Level 2 Computer(s)");
                    bMainMenu3.setText("Level 3 Computer(s)");
                    COMPUTER_FLAG = true;
                }
                break;
            case R.id.bMainMenu3:
                //Options
                if (!COMPUTER_FLAG && !MULTIPLAYER_FLAG) {
                    Option optionClass = new Option();
                    optionClass.setMusic(theMusic);
                    optionClass.setSaveObserver(new dataObserver(theMusic,theData,userFile));
                    Intent option = new Intent(this, optionClass.getClass());
                    startActivity(option);
                }
                //Level 3 Computers
                else if (COMPUTER_FLAG) {
                    COMPUTER_LEVEL = 3;
                    readyMessage();
                }
                //4 Player Mode
                else if (MULTIPLAYER_FLAG) {
                    MULTIPLAYER_COUNT = 4;
                    COMPUTER_LEVEL = 0;
                    readyMessage();
                }
                break;
            case R.id.bMainMenu4:
                //Quit
                if (!COMPUTER_FLAG && !MULTIPLAYER_FLAG) {
                    onBackPressed();
                }
                //Back
                else if (COMPUTER_FLAG) {
                    if (MULTIPLAYER_FLAG) {
                        bMainMenu1.setText("2 Player Mode");
                        bMainMenu2.setText("3 Player Mode");
                        bMainMenu3.setText("4 Player Mode");
                        bMainMenu4.setText("Back");
                        COMPUTER_FLAG = false;
                    } else {
                        bMainMenu1.setText("Single Player");
                        bMainMenu2.setText("Multi Player");
                        bMainMenu3.setText("Options");
                        bMainMenu4.setText("Quit");
                        COMPUTER_FLAG = false;
                        SINGLE_PLAYER = false;
                    }
                }
                //Back
                else if (MULTIPLAYER_FLAG) {
                    bMainMenu1.setText("Single Player");
                    bMainMenu2.setText("Multi Player");
                    bMainMenu3.setText("Options");
                    bMainMenu4.setText("Quit");
                    MULTIPLAYER_FLAG = false;
                }
                break;
        }

    }

    private void readyMessage() {
        AlertDialog.Builder ready = new AlertDialog.Builder(this);
        ready.setTitle("READY?");
        String message;
        if (SINGLE_PLAYER) message = "Single Player Mode\n" +
                "Computer Level: " + COMPUTER_LEVEL;
        else message = "Multi Player Mode\n" +
                "Players: " + MULTIPLAYER_COUNT;
        if (!SINGLE_PLAYER && COMPUTER_LEVEL != 0) message += "\nComputer Level: " + COMPUTER_LEVEL;
        ready.setMessage(message);
        ready.setCancelable(false);
        ready.setNegativeButton("Not Yet", null);
        ready.setPositiveButton("Start Game", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startGame();
            }
        });
        ready.show();
    }

    private void startGame() {
        Game aGame = new Game();
        aGame.setMusic(theMusic);
        Intent game = new Intent(this, aGame.getClass());
        game.putExtra("SinglePlayer", SINGLE_PLAYER);
        game.putExtra("MultiPlayer", MULTIPLAYER_COUNT);
        game.putExtra("ComputerLevel", COMPUTER_LEVEL);
        startActivity(game);
        returnState = 0;
        theMusic.stop();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (newThread != null && !theMusic.isPlaying()) {
            theMusic.stop();
            theMusic.setPlayer(new MediaPlayer().create(getApplicationContext(), R.raw.classical_open));
            theMusic.restartPosition();
            newThread = new Thread(theMusic);
            if (theMusic.isMute()) {
                theMusic.setVolume(0, 0);
            }
            newThread.start();
            return;
        }
        if (returnState == otherState && newThread != null) {
            theMusic.setPlayer(new MediaPlayer().create(getApplicationContext(), R.raw.classical_open));
            theMusic.run();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        returnState = theMusic.getCurrentPosition();
        theMusic.pause();
        moveTaskToBack(true);
        otherState = returnState;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_HOME)) {
            returnState = theMusic.getCurrentPosition();
            theMusic.pause();
            moveTaskToBack(true);
            otherState = returnState;
            return true;
        } else if ((keyCode == KeyEvent.KEYCODE_POWER)) {
            returnState = theMusic.getCurrentPosition();
            theMusic.pause();
            moveTaskToBack(true);
            otherState = returnState;
            return true;
        } else if ((keyCode == KeyEvent.KEYCODE_WINDOW)) {
            returnState = theMusic.getCurrentPosition();
            theMusic.pause();
            moveTaskToBack(true);
            otherState = returnState;
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }


}
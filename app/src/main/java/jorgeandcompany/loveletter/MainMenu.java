package jorgeandcompany.loveletter;

import android.app.ActionBar;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;


public class MainMenu extends ActionBarActivity implements View.OnClickListener{

    private Button bMainMenu1, bMainMenu2, bMainMenu3, bMainMenu4;
    private boolean singlePlayerState = false;
    private boolean multiPlayerState = false;
    private static Music theMusic = null;
    public static int returnState = 0;
    public static int otherState = 0;
    private Thread newThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        bMainMenu1 = (Button) findViewById(R.id.bMainMenu1);
        bMainMenu2 = (Button) findViewById(R.id.bMainMenu2);
        bMainMenu3 = (Button) findViewById(R.id.bMainMenu3);
        bMainMenu4 = (Button) findViewById(R.id.bMainMenu4);

        bMainMenu1.setOnClickListener(this);
        bMainMenu2.setOnClickListener(this);
        bMainMenu3.setOnClickListener(this);
        bMainMenu4.setOnClickListener(this);
        newThread = null;
        if (theMusic == null) {
            theMusic = new Music (new MediaPlayer().create(getApplicationContext(), R.raw.pokemon_steven));
            Runnable musicRunnable = theMusic;
            newThread = new Thread (musicRunnable);
            newThread.start();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bMainMenu1:
                singlePlayerState = true;
                bMainMenu1.setVisibility(View.INVISIBLE);
                bMainMenu2.setText("Campaign");
                bMainMenu3.setText("Solo Game");
                bMainMenu4.setText("Back");
                break;
            case R.id.bMainMenu2:
                //campaign
                if (singlePlayerState) {

                }
                //local play
                else {
                    Intent gameBeta = new Intent(this, Game.class);
                    Game multiGame = new Game ();
                    multiGame.setMusic(theMusic);
                    gameBeta.putExtra("single", false);
                    startActivity(gameBeta);
                    returnState = 0;
                    theMusic.stop();
                }
                break;
            case R.id.bMainMenu3:
                //Solo Game
                if (singlePlayerState) {
                    Intent gameBeta = new Intent(this, Game.class);
                    gameBeta.putExtra("single", true);
                    Game singleGame = new Game ();
                    singleGame.setMusic(theMusic);
                    startActivity(gameBeta);
                    returnState = 0;
                    theMusic.stop();
                }
                //Network play
                else if (multiPlayerState) {

                }
                //shop
                else {
                    Intent toShop = new Intent(this, Shop.class);
                    startActivity(toShop);
                    otherState = returnState+1;
                    //finish();
                }
                break;
            case R.id.bMainMenu4:
                if (singlePlayerState) {
                    bMainMenu1.setVisibility(View.VISIBLE);
                    bMainMenu2.setText("Multiplayer");
                    bMainMenu3.setText("Shop");
                    bMainMenu4.setText("Options");
                    singlePlayerState = false;
                }
                else if (multiPlayerState) {
                    bMainMenu1.setVisibility(View.VISIBLE);
                    bMainMenu2.setText("Multiplayer");
                    bMainMenu3.setText("Shop");
                    bMainMenu4.setText("Options");
                    multiPlayerState = false;
                }
                //options
                else {
                    Option optionClass = new Option ();
                    optionClass.setMusic(theMusic);
                    Intent option = new Intent(this, optionClass.getClass());
                    startActivity(option);
                }
                break;

        }

    }

    @Override
    public void onBackPressed() {
        returnState = theMusic.getCurrentPosition();
        theMusic.pause();
        moveTaskToBack(true);
        otherState = returnState;
    }

    @Override
    protected void onResume() {
        super.onResume();
            if (newThread != null && !theMusic.isPlaying()) {
                theMusic.stop();
                theMusic.setPlayer(new MediaPlayer().create(getApplicationContext(), R.raw.pokemon_steven));
                theMusic.restartPosition();
                newThread = new Thread(theMusic);
                if (theMusic.isMute()) {
                    theMusic.setVolume(0,0);
                }
                newThread.start();
                return;
            }
            if (returnState == otherState && newThread != null) {
                theMusic.setPlayer(new MediaPlayer().create(getApplicationContext(), R.raw.pokemon_steven));
                theMusic.run();
            }
    }
}

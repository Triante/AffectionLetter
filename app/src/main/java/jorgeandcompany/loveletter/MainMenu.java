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
    public static MediaPlayer mp;
    public static boolean isMute = false;
    public static int returnState;
    public static int otherState;

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

        if (!isMute) {
            mp = MediaPlayer.create(getApplicationContext(), R.raw.pokemon_steven);
            mp.start();
            mp.setLooping(true);
            isMute = false;
        }
        if (GameData.skin == 0) {
            GameData.skin = 2;
            GameData.addSkinSet(GameData.skin);
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
                else if (multiPlayerState) {

                }
                else {
                    multiPlayerState = true;
                    bMainMenu1.setVisibility(View.INVISIBLE);
                    bMainMenu2.setText("Local Multiplayer");
                    bMainMenu3.setText("Network Multiplayer");
                    bMainMenu4.setText("Back");
                }
                break;
            case R.id.bMainMenu3:
                //Solo Game
                if (singlePlayerState) {
                    Intent gameBeta = new Intent(this, Game.class);
                    startActivity(gameBeta);
                    returnState = 0;
                    mp.stop();
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
                    Intent option = new Intent(this, Option.class);
                    startActivity(option);
                }
                break;

        }

    }

    @Override
    public void onBackPressed() {
        returnState = mp.getCurrentPosition();
        mp.pause();
        moveTaskToBack(true);
        otherState = returnState;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (returnState == otherState) {
            mp.seekTo(returnState);
            mp.start();
        }
    }
}

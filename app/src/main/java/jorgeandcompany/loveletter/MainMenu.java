package jorgeandcompany.loveletter;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainMenu extends ActionBarActivity implements View.OnClickListener{

    private Button bMainMenu1, bMainMenu2, bMainMenu3, bMainMenu4;
    private boolean singlePlayerState = false;
    private boolean multiPlayerState = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        bMainMenu1 = (Button) findViewById(R.id.bMainMenu1);
        bMainMenu2 = (Button) findViewById(R.id.bMainMenu2);
        bMainMenu3 = (Button) findViewById(R.id.bMainMenu3);
        bMainMenu4 = (Button) findViewById(R.id.bMainMenu4);

        bMainMenu1.setOnClickListener(this);
        bMainMenu2.setOnClickListener(this);
        bMainMenu3.setOnClickListener(this);
        bMainMenu4.setOnClickListener(this);
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
                }
                //Network play
                else if (multiPlayerState) {

                }
                //shop
                else {
                    Intent toShop = new Intent(this, Shop.class);
                    startActivity(toShop);
                    finish();
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

                }
                break;

        }

    }

}

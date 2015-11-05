package jorgeandcompany.loveletter;

import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;


public class Option extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);
        final Button skinset = (Button) findViewById(R.id.skinset);
        final Switch mute = (Switch) findViewById(R.id.musicmute);
        mute.setTextOff("Off");
        mute.setTextOn("On");
        mute.setText ("Music");
        if (Build.VERSION.SDK_INT >= 21) {
            mute.setShowText(true);
        }
        if (!MainMenu.theMusic.isMute()) {
            mute.setChecked(true);
        }

        skinset.setText(GameData.skinNames.get(GameData.skinID - 1));

        skinset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (GameData.skinID == 3) {
                    GameData.skinID = 1;
                }
                else {
                    GameData.skinID++;
                }
                skinset.setText(GameData.skinNames.get(GameData.skinID - 1));
            }
        });

        mute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainMenu.theMusic.isMute()) {
                    MainMenu.theMusic.setVolume(1, 1);
                    MainMenu.theMusic.changeMuteStatus();

                } else {
                    MainMenu.theMusic.setVolume(0, 0);
                    MainMenu.theMusic.changeMuteStatus();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        MainMenu.otherState++;
        finish();
    }
}

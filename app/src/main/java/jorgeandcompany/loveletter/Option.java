package jorgeandcompany.loveletter;

import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;


public class Option extends ActionBarActivity {
    private static Music theMusic = null;
    private static boolean saveActive;
    private static Observer saveObserver = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);
        final Button skinset = (Button) findViewById(R.id.skinset);
        final Switch mute = (Switch) findViewById(R.id.musicmute);
        final Button back = (Button) findViewById(R.id.backButtonOptions);
        final Button instruction = (Button) findViewById(R.id.instructionsButton);
        saveActive = false;
        mute.setTextOff("Off");
        mute.setTextOn("On");
        mute.setText ("Music");
        if (Build.VERSION.SDK_INT >= 21) {
            mute.setShowText(true);
        }
        if (!theMusic.isMute()) {
            mute.setChecked(true);
        }

        skinset.setText(SkinRes.activeSkin);

        skinset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (skinset.getText().toString().equalsIgnoreCase("Avengers Skin") && SkinRes.skinNames.size() > 1) {
                    SkinRes.activeSkin = SkinRes.skinNames.get(1);
                } else if (skinset.getText().toString().equalsIgnoreCase("Magi Skin") && SkinRes.skinNames.size() > 2){
                    SkinRes.activeSkin = SkinRes.skinNames.get(2);
                }
                else if (skinset.getText().toString().equalsIgnoreCase("Fire Emblem Skin")) {
                    SkinRes.activeSkin = SkinRes.skinNames.get(0);
                }
                skinset.setText(SkinRes.activeSkin);
                saveObserver.updateSave();
                saveActive = true;
            }
        });

        mute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (theMusic.isMute()) {
                    theMusic.setVolume(1, 1);
                    theMusic.changeMuteStatus();

                } else {
                    theMusic.setVolume(0, 0);
                    theMusic.changeMuteStatus();
                }
                saveObserver.updateSave();
                saveActive = true;
            }
        });

        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                MainMenu.otherState++;
                if (saveActive) {
                    Toast.makeText(getApplicationContext(), "Save Complete!", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });

        instruction.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void onBackPressed() {

    }

    public void setMusic (Music piece) {
        theMusic = piece;
    }

    public void setSaveObserver (Observer anObserver) {
        saveObserver = anObserver;
    }
}

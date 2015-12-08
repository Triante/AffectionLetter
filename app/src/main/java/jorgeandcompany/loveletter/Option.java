package jorgeandcompany.loveletter;

import android.media.MediaPlayer;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;


public class Option extends ActionBarActivity {
    private static Music theMusic = null;
    private static boolean saveActive;
    private static Observer saveObserver = null;
    int a = 0;
    int b = 0;
    int c = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);
        final Button skinset = (Button) findViewById(R.id.skinset);
        skinset.setBackgroundResource(SkinRes.getButtonTheme());
        final Switch mute = (Switch) findViewById(R.id.musicmute);
        final Button back = (Button) findViewById(R.id.backButtonOptions);
        back.setBackgroundResource(SkinRes.getButtonTheme());
        final Button instruction = (Button) findViewById(R.id.instructionsButton);
        instruction.setBackgroundResource(SkinRes.getButtonTheme());
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
                skinset.setBackgroundResource(SkinRes.getButtonTheme());
                back.setBackgroundResource(SkinRes.getButtonTheme());
                instruction.setBackgroundResource(SkinRes.getButtonTheme());
                if (b == 4) c++;
                else a++;
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
                if (a == 3) {
                    b++;
                }
                saveObserver.updateSave();
                saveActive = true;
            }
        });

        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                MainMenu.otherState++;
                // Skin(3x), Music(4x), Skin(1x), PhoneBackButton(1x), AppBackButton(1x)
                if (a == 4 && b == 4 && c ==1)
                {
                    if (GameData.debug) {
                        GameData.debug = false;
                        Toast.makeText(getApplicationContext(), "Debug Disabled!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        GameData.debug = true;
                        Toast.makeText(getApplicationContext(), "Debug Enabled!", Toast.LENGTH_SHORT).show();
                    }
                }
                else if (saveActive) {
                    Toast.makeText(getApplicationContext(), "Save Complete!", Toast.LENGTH_SHORT).show();
                }
                MainMenu.gameNotStart = false;
                finish();
            }
        });

        instruction.setOnClickListener(new View.OnClickListener() {
                        @Override
            public void onClick(View v) {
                    ThemedDialog.Builder howTo = new ThemedDialog.Builder(Option.this);
                    View view = View.inflate(Option.this, R.layout.how_to_play_layout, null);
                            howTo.setView(view);
                    howTo.show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (c == 1) a++;
    }

    /**
     * Sets the music to be played in the options menu.
     * @param piece the music piece to be set.
     */
    public void setMusic (Music piece) {
        theMusic = piece;
    }

    /**
     * Sets the Observer which options uses to save user data to a file
     * @param anObserver
     */
    public void setSaveObserver (Observer anObserver) {
        saveObserver = anObserver;
    }

    @Override
    public void onPause () {
        super.onPause();
        theMusic.setVolume(0, 0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!theMusic.isMute()) {
            theMusic.setVolume(1, 1);
        }
    }
}

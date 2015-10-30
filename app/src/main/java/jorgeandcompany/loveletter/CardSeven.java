package jorgeandcompany.loveletter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.CountDownTimer;

/**
 * Created by Firemon123 on 10/1/2015.
 */
public class CardSeven implements Card {
    private final int value = 7;

    @Override
    public void drawAffect(Player player) {
        GameData.CARD_SEVEN_IN_PLAY = true;
        return;
    }

    @Override
    public void cardEffect(final Player player) {
        AlertDialog.Builder play = new AlertDialog.Builder(GameData.game);
        play.setCancelable(false);
        play.setTitle("Card 7 Effect");
        play.setMessage("Player" + player.getPlayerNumber() + " played card 7.");
        play.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                GameData.CARD_SEVEN_IN_PLAY = false;
                GameData.game.endOfTurn(player);
            }
        });
        play.show();
    }

    @Override
    public void discardAffect(Player player) {
        return;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public String getDescription(Context c) {
        Resources res = c.getResources();
        String string = res.getString(R.string.Card_Seven_Description);
        return string;
    }
}

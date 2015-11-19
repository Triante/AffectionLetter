package jorgeandcompany.loveletter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;

/**
 * Created by Firemon123 on 10/1/2015.
 */
public class CardSeven implements Card {
    private final int value = 7;

    @Override
    public void cardEffect(final Player player) {
        AlertDialog.Builder play = new AlertDialog.Builder(GameData.game);
        play.setCancelable(false);
        play.setTitle("Card 7 Effect");
        play.setMessage("Player" + player.getPlayerNumber() + " played card 7.");
        play.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                player.setSeven(false);
                GameData.game.endOfTurn();
            }
        });
        play.show();
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

    @Override
    public int getSkinRes(int skinId) {
        switch (skinId) {
            case 1:
                return R.drawable.yunanver;
            case 2:
                return R.drawable.harleyquinnver;
            case 3:
                return R.drawable.captainamericaver;
            default:
                return R.drawable.yunanver;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof Card)) return false;
        Card other = (Card) o;
        int thisCard = getValue();
        int otherCard = other.getValue();
        if (thisCard == otherCard) return true;
        else return false;
    }
}

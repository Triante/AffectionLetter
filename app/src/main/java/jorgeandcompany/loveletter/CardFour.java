package jorgeandcompany.loveletter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;

/**
 * Created by Firemon123 on 10/1/2015.
 */
public class CardFour implements Card {
    private final int value = 4;

    @Override
    public void cardEffect(final Player player) {
        AlertDialog.Builder toProtect = new AlertDialog.Builder(GameData.game);
        toProtect.setTitle("Card 4 Effect");
        toProtect.setMessage("Player " + player.getPlayerNumber() + " is now protected.");
        toProtect.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                player.setProtected(true);
                GameData.game.endOfTurn();
            }
        });
        toProtect.setCancelable(false);
        toProtect.show();
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public String getDescription(Context c) {
        Resources res = c.getResources();
        String string = res.getString(R.string.Card_Four_Description);
        return string;
    }

    @Override
    public int getSkinRes(String orientation) {
        return SkinRes.skinRes(4, orientation);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof Card)) return false;
        Card other = (Card) o;
        if (getValue() == other.getValue()) return true;
        else return false;
    }
}

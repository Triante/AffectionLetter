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

    /**
     * @param player the player whose card was selected
     */
    @Override
    public void cardEffect(final Player player) {
        ThemedDialog.Builder toProtect = new ThemedDialog.Builder(GameData.game);
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
    /**
     * @return value of Card
     */
    @Override
    public int getValue() {
        return value;
    }
    /**
     * @param c the Context of the current application
     * @return Returns the description of the card as a string
     */
    @Override
    public String getDescription(Context c) {
        Resources res = c.getResources();
        String string = res.getString(R.string.Card_Four_Description);
        return string;
    }
    /**
     * @param orientation a String passed for the orientation
     * @return returns the R.drawable int of the card
     */
    @Override
    public int getSkinRes(String orientation) {
        return SkinRes.skinRes(4, orientation);
    }
    /**
     * @param o The object to compare this Card against
     * @return true if the given object represents a Card equivalent to this cards value, false otherwise
     */
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

package jorgeandcompany.loveletter;

import android.content.Context;
import android.content.res.Resources;

/**
 * Created by Firemon123 on 9/25/2015.
 */
public class CardOne implements Card {

    private final int value = 1;

    @Override
    public void drawAffect(Player player) {
        return;
    }

    /**
     * Name a card (other than a card that has a value of 1) and choose another player.
     * If that player has that card, he or she is out of the round
     * @param player
     */
    @Override
    public void cardEffect(Player player) {
        return;
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
        String string = res.getString(R.string.Card_One_Description);
        return string;
    }
}

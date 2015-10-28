package jorgeandcompany.loveletter;

import android.content.Context;
import android.content.res.Resources;

/**
 * Created by Firemon123 on 10/1/2015.
 */
public class CardTwo implements Card {

    private final int value = 2;

    @Override
    public void drawAffect(Player player) {
        return;
    }

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
        String string = res.getString(R.string.Card_Two_Description);
        return string;
    }
}

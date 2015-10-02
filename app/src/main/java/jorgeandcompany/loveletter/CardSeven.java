package jorgeandcompany.loveletter;

import android.content.res.Resources;

/**
 * Created by Firemon123 on 10/1/2015.
 */
public class CardSeven implements Card {
    private final int value = 7;

    @Override
    public void drawAffect() {
        return;
    }

    @Override
    public void cardEffect() {
        return;
    }

    @Override
    public void discardAffect() {
        return;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public String getDescription() {
        Resources res = Resources.getSystem();
        String string = res.getString(R.string.Card_Seven_Description);
        return string;
    }
}

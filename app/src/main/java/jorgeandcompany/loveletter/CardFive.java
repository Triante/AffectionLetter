package jorgeandcompany.loveletter;

import android.content.res.Resources;

/**
 * Created by Firemon123 on 10/1/2015.
 */
public class CardFive implements Card {
    private final int value = 5;

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
        return 5;
    }

    @Override
    public String getDescription() {
        Resources res = Resources.getSystem();
        String string = res.getString(R.string.Card_Five_Description);
        return string;
    }
}

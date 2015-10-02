package jorgeandcompany.loveletter;

import android.content.res.Resources;

/**
 * Created by Firemon123 on 10/1/2015.
 */
public class CardThree implements Card {
    private final int value = 3;

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
        String string = res.getString(R.string.Card_Three_Description);
        return string;
    }
}

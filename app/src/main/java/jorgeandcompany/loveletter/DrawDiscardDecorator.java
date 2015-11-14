package jorgeandcompany.loveletter;

import android.content.Context;

/**
 * Created by kd on 11/13/15.
 */
public abstract class DrawDiscardDecorator implements Card {
    protected Card card;

    public DrawDiscardDecorator(Card c)
    {
        card = c;
    }

    @Override
    public void cardEffect(Player player) {

        card.cardEffect(player);

    }

    @Override
    public int getValue() {
        return card.getValue();
    }

    @Override
    public String getDescription(Context c) {
        return card.getDescription(c);
    }

    @Override
    public int getSkinRes(int skinId) {
        return card.getSkinRes(skinId);
    }
}

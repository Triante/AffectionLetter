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

    /** {@inheritDoc} */
    @Override
    public void cardEffect(Player player) {
        card.cardEffect(player);
    }

    /** {@inheritDoc} */
    @Override
    public int getValue() {
        return card.getValue();
    }

    /** {@inheritDoc} */
    @Override
    public String getDescription(Context c) {
        return card.getDescription(c);
    }

    /** {@inheritDoc} */
    @Override
    public int getSkinRes(String orientation) {
        return card.getSkinRes(orientation);
    }

    /** {@inheritDoc} */
    @Override
    public boolean equals(Object o) {
        return card.equals(o);
    }
}

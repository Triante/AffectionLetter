package jorgeandcompany.loveletter;

import android.content.Context;

/**
 * Created by kd on 10/2/15.
 */
public interface Player
{


    void drawFirstCard();

    void drawOutCard();

    void drawCard();

    void playCard(int hand);

    void discardCard();

    int getPlayerNumber();

    boolean isOut();

    void out();

    boolean hasLeftCard();

    boolean hasRightCard();

    void setCard(Card c);

    Card getCard(int hand);

    int getTotal();

    Card getLeft();

    Card getRight();

    Card getCard();

    void clearHand();

    boolean isProtected();

    void setProtected(boolean set);

    boolean hasSeven();

    void setSeven(boolean seven);

    boolean isHuman();
}

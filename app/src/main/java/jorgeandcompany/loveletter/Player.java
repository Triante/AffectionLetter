package jorgeandcompany.loveletter;

import android.content.Context;

/**
 * Created by kd on 10/2/15.
 */
public class Player
{
    private Card leftCard = null; //left
    private Card rightCard = null; //right
    private final int playerNumber;
    private boolean isOut = false;
    boolean hasLeft = false;
    boolean hasRight = false;
    private boolean isProtected = false;
    private int total = 0;

    public Player (int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public void drawFirstCard() {
        leftCard = GameData.deck.draw();
        hasLeft = true;
    }

    public void drawCard()
    {
        if (!hasLeft) {
            hasLeft = true;
            leftCard = GameData.deck.draw();
            leftCard.drawAffect(this);
        }
        else {
            hasRight = true;
            rightCard = GameData.deck.draw();
            rightCard.drawAffect(this);
        }
    }

    public void playCard(int hand)
    {
        //right
        if (hand == 1) {
            hasRight = false;
            rightCard.cardEffect(this);
            total += rightCard.getValue();
            rightCard = null;
        }
        //left
        else {
            hasLeft = false;
            leftCard.cardEffect(this);
            total += leftCard.getValue();
            leftCard = null;
        }
    }

    public void discardCard()
    {
        if (hasLeft) {
            hasLeft = false;
            leftCard.discardAffect(this);
            total += leftCard.getValue();
            leftCard = null;
        }
        else {
            hasRight = false;
            rightCard.discardAffect(this);
            total += rightCard.getValue();
            rightCard = null;
        }
    }

    public int getPlayerNumber() {
        return playerNumber;
    }
    public boolean isOut() {
        return isOut;
    }
    public void out() {
        isOut = true;
        leftCard = null;
        rightCard = null;
        hasLeft = false;
        hasRight = false;
    }

    public boolean hasLeftCard() {
        return hasLeft;
    }
    public boolean hasRightCard() {
        return hasRight;
    }

    public void setCard(Card c) {
        if (hasLeft) leftCard = c;
        else rightCard = c;
    }
    public Card getCard(int hand) {
        if (hand == 0) return getLeft();
        else return getRight();
    }
    public int getTotal() {
       return total;
    }
    private Card getLeft() {
        return leftCard;
    }
    private Card getRight() {
        return rightCard;
    }
    public Card getCard() {
        if (hasLeft) return leftCard;
        else return rightCard;
    }
    public void clearHand() {
        isOut = false;
        hasLeft = false;
        hasRight = false;
        isProtected = false;
        leftCard = null;
        rightCard = null;
    }

    public boolean isProtected() {
        return isProtected;
    }
    public void setProtected(boolean set) {
        isProtected = set;
    }


}

package jorgeandcompany.loveletter;

/**
 * Created by kd on 10/2/15.
 */
public class Player
{
    private Card leftCard; //left
    private Card rightCard; //right
    private final int playerNumber;
    private boolean isOut = false;

    public Player (int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public void drawFirstCard() {
        leftCard = GameData.deck.draw();
    }

    public void drawCard()
    {
        if (leftCard.equals(null)) {
            leftCard = GameData.deck.draw();
            //leftCard.drawAffect(this);
        }
        else {
            rightCard = GameData.deck.draw();
            //rightCard.drawAffect(this);
        }
    }

    public void discardCard(int hand)
    {
        if (hand == 0) {
            leftCard.cardEffect(this);
            leftCard = rightCard;
            rightCard = null;
        }
        else if (hand == 1) {
            rightCard.cardEffect(this);
            rightCard = null;
        }
        else {
            leftCard.discardAffect(this);
            leftCard = null;
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
    }

    public boolean hasLeftCard() {
        if (leftCard.equals(null)) {
            return false;
        }
        return true;
    }

    public Card getLeft() {
        return leftCard;
    }

    public Card getRight() {
        return rightCard;
    }
}

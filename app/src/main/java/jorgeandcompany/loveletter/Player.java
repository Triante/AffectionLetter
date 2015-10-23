package jorgeandcompany.loveletter;

/**
 * Created by kd on 10/2/15.
 */
public class Player
{
    private Card leftCard; //left
    private Card rightCard; //right
    private final int playerNumber;

    public Player (int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public void drawCard(Deck deck)
    {
        if (leftCard.equals(null)) {
            leftCard = deck.draw();
            leftCard.drawAffect(this);
        }
        else {
            rightCard = deck.draw();
            rightCard.drawAffect(this);
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
}

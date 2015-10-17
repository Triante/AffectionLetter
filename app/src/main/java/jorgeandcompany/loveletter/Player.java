package jorgeandcompany.loveletter;

/**
 * Created by kd on 10/2/15.
 */
public class Player
{
    protected Card c1; //left
    protected Card c2; //right
    private final int playerNumber;

    public Player (int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public void drawCard(Deck deck)
    {
        if (c1.equals(null)) {
            c1 = deck.draw();
            c1.drawAffect(this);
        }
        else {
            c2 = deck.draw();
            c2.drawAffect(this);
        }
    }

    public void discardCard(int hand)
    {
        if (hand == 0) {
            c1.cardEffect(this);
            c1 = c2;
            c2 = null;
        }
        else if (hand == 1) {
            c2.cardEffect(this);
            c2 = null;
        }
        else {
            c1.discardAffect(this);
            c1 = null;
        }
    }

    public int getPlayerNumber() {
        return playerNumber;
    }
}

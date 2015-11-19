package jorgeandcompany.loveletter;

/**
 * Created by Firemon123 on 11/3/2015.
 */
public class HumanPlayer implements Player {

    private Card leftCard = null;
    private Card rightCard = null;
    private DrawDiscard leftDrawDiscard = null;
    private DrawDiscard rightDrawDiscard = null;
    private final int playerNumber;
    private boolean isOut = false;
    private boolean hasLeft = false;
    private boolean hasRight = false;
    private boolean isProtected = false;
    private boolean hasSeven = false;
    private int total = 0;

    public HumanPlayer (int playerNumber) {
        this.playerNumber = playerNumber;
    }

    @Override
    public void drawFirstCard() {
        leftCard = GameData.deck.draw();
        leftDrawDiscard = new DrawDiscard(leftCard);
        leftDrawDiscard.drawAffect(this);
        hasLeft = true;
    }
    @Override
    public void drawOutCard() {
        if (!hasLeft) {
            hasLeft = true;
            leftCard = GameData.drawOutCard();
        }
        else {
            hasRight = true;
            rightCard = GameData.drawOutCard();
        }
    }
    @Override
    public void drawCard() {
        if (!hasLeft) {
            hasLeft = true;
            leftCard = GameData.deck.draw();
            leftDrawDiscard = new DrawDiscard(leftCard);
            leftDrawDiscard.drawAffect(this);
        }
        else {
            hasRight = true;
            rightCard = GameData.deck.draw();
            rightDrawDiscard = new DrawDiscard(rightCard);
            rightDrawDiscard.drawAffect(this);
        }
    }
    @Override
    public void playCard(int hand) {
        //right
        if (hand == 1) {
            hasRight = false;
            rightCard.cardEffect(this);
            total += rightCard.getValue();
            GameData.discardPile.addToDiscard(rightCard);
            rightCard = null;
        }
        //left
        else {
            hasLeft = false;
            leftCard.cardEffect(this);
            total += leftCard.getValue();
            GameData.discardPile.addToDiscard(leftCard);
            leftCard = null;
        }
    }
    @Override
    public void discardCard() {
        if (hasLeft) {
            hasLeft = false;
            leftDrawDiscard = new DrawDiscard(leftCard);
            leftDrawDiscard.discardAffect(this);
            total += leftCard.getValue();
            GameData.discardPile.addToDiscard(leftCard);
            leftCard = null;
        }
        else {
            hasRight = false;
            rightDrawDiscard = new DrawDiscard(rightCard);
            rightDrawDiscard.discardAffect(this);
            total += rightCard.getValue();
            GameData.discardPile.addToDiscard(rightCard);
            rightCard = null;
        }
    }

    @Override
    public int getPlayerNumber() {
        return playerNumber;
    }
    @Override
    public boolean isOut() {
        return isOut;
    }
    @Override
    public void out() {
        if (hasLeftCard() || hasRightCard()) {
            GameData.discardPile.addToDiscard(getCard());
        }
        isOut = true;
        hasLeft = false;
        hasRight = false;
        isProtected = false;
        hasSeven = false;
        leftCard = null;
        rightCard = null;
    }

    @Override
    public boolean hasLeftCard() {
        return hasLeft;
    }
    @Override
    public boolean hasRightCard() {
        return hasRight;
    }

    @Override
    public void setCard(Card c) {
        if (hasLeft) leftCard = c;
        else rightCard = c;
    }
    @Override
    public Card getCard(int hand) {
        if (hand == 0) return getLeft();
        else return getRight();
    }
    @Override
    public int getTotal() {
        return total;
    }
    @Override
    public Card getLeft() {
        return leftCard;
    }
    @Override
    public Card getRight() {
        return rightCard;
    }
    @Override
    public Card getCard() {
        if (hasLeft) return leftCard;
        else return rightCard;
    }
    @Override
    public void clearHand() {
        isOut = false;
        hasLeft = false;
        hasRight = false;
        isProtected = false;
        hasSeven = false;
        leftCard = null;
        rightCard = null;
    }

    @Override
    public boolean isProtected() {
        return isProtected;
    }
    @Override
    public void setProtected(boolean set) {
        isProtected = set;
    }

    @Override
    public boolean hasSeven() {
        return hasSeven;
    }

    @Override
    public void setSeven(boolean seven) {
        hasSeven = seven;
    }

    @Override
    public boolean isHuman() {
        return true;
    }

}

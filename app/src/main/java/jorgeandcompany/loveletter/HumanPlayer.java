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

    /** {@inheritDoc} */
    @Override
    public void drawFirstCard() {
        leftCard = GameData.deck.draw();
        leftDrawDiscard = new DrawDiscard(leftCard);
        leftDrawDiscard.drawAffect(this);
        hasLeft = true;
    }
    /** {@inheritDoc} */
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
    /** {@inheritDoc} */
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
        updateHasSeven();
    }
    /** {@inheritDoc} */
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
    /** {@inheritDoc} */
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
    /** {@inheritDoc} */
    @Override
    public int getPlayerNumber() {
        return playerNumber;
    }
    /** {@inheritDoc} */
    @Override
    public boolean isOut() {
        return isOut;
    }
    /** {@inheritDoc} */
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
    /** {@inheritDoc} */
    @Override
    public boolean hasLeftCard() {
        return hasLeft;
    }
    /** {@inheritDoc} */
    @Override
    public boolean hasRightCard() {
        return hasRight;
    }
    /** {@inheritDoc} */
    @Override
    public void setCard(Card c) {
        if (hasLeft) leftCard = c;
        else rightCard = c;
    }
    /** {@inheritDoc} */
    @Override
    public Card getCard(int hand) {
        if (hand == 0) return getLeft();
        else return getRight();
    }
    /** {@inheritDoc} */
    @Override
    public int getTotal() {
        return total;
    }
    /** {@inheritDoc} */
    @Override
    public Card getLeft() {
        return leftCard;
    }
    /** {@inheritDoc} */
    @Override
    public Card getRight() {
        return rightCard;
    }
    /** {@inheritDoc} */
    @Override
    public Card getCard() {
        if (hasLeft) return leftCard;
        else return rightCard;
    }
    /** {@inheritDoc} */
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
    /** {@inheritDoc} */
    @Override
    public boolean isProtected() {
        return isProtected;
    }
    /** {@inheritDoc} */
    @Override
    public void setProtected(boolean set) {
        isProtected = set;
    }
    /** {@inheritDoc} */
    @Override
    public boolean hasSeven() {
        return hasSeven;
    }
    /** {@inheritDoc} */
    @Override
    public void setSeven(boolean seven) {
        hasSeven = seven;
    }
    /** {@inheritDoc} */
    @Override
    public boolean isHuman() {
        return true;
    }

    /**
     * updates the hasSeven flag. Used in case correction for updating flag is skipped in other classes.
     * If player is holding  card 7, sets hasSeven as true, otherwise sets it as false.
     */
    private void updateHasSeven() {
        if (leftCard.getValue() == 7 || rightCard.getValue() == 7) hasSeven = true;
        else hasSeven = false;
    }

}

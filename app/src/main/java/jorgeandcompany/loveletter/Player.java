package jorgeandcompany.loveletter;

import android.content.Context;

/**
 * Created by kd on 10/2/15.
 */
public interface Player
{


    /**
     * Player draws a card from the Deck class. No card affect is played here.
     */
    void drawFirstCard();

    /**
     * Player draws a card from the out card stored in GameData. No card affect is played here.
     */
    void drawOutCard();

    /**
     * Player draws a card from the Deck class. The card drawn plays its draw affect (if it has any).
     */
    void drawCard();

    /**
     * Plays the affect of the card that is chosen then later discards it.
     * @param hand the location of the card that was played. 0 for left, 1 for right.
     *             Any other int plays the right card (1).
     */
    void playCard(int hand);

    /**
     * Player discards their currently held card and plays the affect (if it has any).
     */
    void discardCard();

    /**
     * Returns the ID of the player
     * @return returns the player number
     */
    int getPlayerNumber();

    /**
     * Checks whether the player is out of the round.
     * @return returns true if the player is out, false otherwise.
     */
    boolean isOut();

    /**
     * Sets the player's isOut flag boolean to true.
     */
    void out();

    /**
     * Checks whether the player has a card on their left hand.
     * @return true if player has a left card, false otherwise.
     */
    boolean hasLeftCard();

    /**
     * Checks whether the player has a card on their right right.
     * @return true if player has a right card, false otherwise.
     */
    boolean hasRightCard();

    /**
     * Sets a Card to the player. If the Player has a left card then it sets on left left,
     * otherwise on right hand.
     * @param c the Card to be set for the player
     */
    void setCard(Card c);

    /**
     * Gets the Card the player is holding from the selected hand. Used when Player has two cards
     * in their hand.
     * @param hand 0 is the left hand, any other value is the right hand.
     * @return the Card belonging to the hand location.
     */
    Card getCard(int hand);

    /**
     * Gets the total amount of all the Card values that the player has used.
     * @return sum of all Card values used.
     */
    int getTotal();

    /**
     * Gets the card on the players left hand.
     * @return the Card, if left hand is empty returns null.
     */
    Card getLeft();

    /**
     * Gets the card on the players right hand.
     * @return the Card, if right hand is empty returns null.
     */
    Card getRight();

    /**
     * Gets the left or right Card that the player is currently holding.
     * @return the Card player is holding, returns null if player has no card.
     */
    Card getCard();

    /**
     * Resets all the flags of the player and sets both hands to null.
     */
    void clearHand();

    /**
     * Checks if the player is protected due to Card 4 affect.
     * @return true if player is protected, otherwise false.
     */
    boolean isProtected();

    /**
     * Sets the player's isProtected boolean to the parameter passed.
     * @param set boolean used to set isProtected flag.
     */
    void setProtected(boolean set);

    /**
     * Checks if the player has Card 7 in their hand due to Card 7 draw affect.
     * @return true if player has Card 7, otherwise false.
     */
    boolean hasSeven();

    /**
     * Sets the player's hasSeven boolean to the parameter passed.
     * @param seven boolean used to set hasSeven flag.
     */
    void setSeven(boolean seven);

    /**
     * Checks whether the Player is a human or a computer.
     * @return true if player is human, false otherwise.
     */
    boolean isHuman();
}

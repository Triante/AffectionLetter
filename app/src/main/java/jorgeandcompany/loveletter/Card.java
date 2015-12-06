package jorgeandcompany.loveletter;

import android.content.Context;

/**
 * Created by Firemon123 on 9/25/2015.
 */
public interface Card {

    /**
     * Plays the effect of the card
     * @param player the player whose card was selected
     */
    void cardEffect(Player player);
    /**
     * Gets the value of the card
     * @return returns the value of the card
     */
    int getValue();
    /**
     * Gets the description of the card
     * @param c the Context of the current application
     * @return Returns the description of the card as a string
     */
    String getDescription(Context c);
    /**
     * Gets the int value from R.drawable of the corresponding card depending of the orientation
     * inputted. Strings accepted are "Up", "Down", "Left", of "Right" . Any other String defualts
     * to "Down"
     * @param orientation a String passed for the orientation
     * @return returns the R.drawable int of the card
     */
    int getSkinRes(String orientation);
    /**
     * Compares this card to the specified object. The result is true if and only if the argument
     *  is not null and is a card object that represents the same card value as this object.
     * @param o The object to compare this Card against
     * @return true if the given object represents a Card equivalent to this cards value, false otherwise
     */
    boolean equals(Object o);

}

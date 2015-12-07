package jorgeandcompany.loveletter;

import java.util.ArrayList;

/**
 * Created by Firemon123 on 11/13/2015.
 */
public class DiscardPile {
    private ArrayList<Integer> discard;

    public DiscardPile() {
        discard = new ArrayList<>();
    }

    /**
     * Adds the card value to the DiscardPile
     * @param card the card value to be added to the DiscardPile
     */
    public void addToDiscard(Card card) {
        discard.add(card.getValue());
    }

    /**
     * Returns the size of the DiscardPile
     * @return the size of the DiscardPile
     */
    public int discardSize() {
        return discard.size();
    }

    /**
     * Returns an array containing all of the values in DiscardPile in proper sequence (in order of discarded values).
     * @return an array containing all of the elements in DiscardPile in proper sequence
     */
    public int[] getDiscardInformation() {
        if (discard.isEmpty()) {
            int[] empty = {0};
            return empty;
        }
        int[] pile = new int[discard.size()];
        for (int i = 0; i < pile.length; i++) {
            pile[i] = discard.get(i);
        }
        return pile;
    }

    /**
     * Returns a string representation of the DiscardPile.
     * Represented as an array containing all of the values in DiscardPile in proper sequence (in order of discarded values).
     * @return a string representation of the DiscardPile.
     */
    @Override
    public String toString() {
        int[] pile = getDiscardInformation();
        String string = "[";
        for (int i = 0; i < pile.length; i++)
        {
            string += pile[i];
            if (i < pile.length-1) string += ", ";
        }
        string += "]";
        return string;
    }


}

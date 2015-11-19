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

    public void addToDiscard(Card card) {
        discard.add(card.getValue());
    }

    public int discardSize() {
        return discard.size();
    }

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

    public String toString() {
        int[] pile = getDiscardInformation();
        String string = "[ ";
        for (int i = 0; i < pile.length; i++)
        {
            string += pile[i];
            if (i < pile.length-1) string += ", ";
        }
        string += "]";
        return string;
    }


}

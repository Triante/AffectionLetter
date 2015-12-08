package jorgeandcompany.loveletter;

/**
 * Created by Penguins94 on 12/4/2015.
 */
public abstract class CardFactory {
    /**
     * creates a card based on the key passed.
     * @param CardNumber the card number to be created.
     * @return the card corresponding to the key value.
     */
    public abstract Card createCard(int CardNumber);
}

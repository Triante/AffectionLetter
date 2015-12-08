package jorgeandcompany.loveletter;

/**
 * Created by Firemon123 on 10/20/2015.
 */
public class ConcreteCardFactory extends CardFactory{

    /** {@inheritDoc} */
    public Card createCard(int CardNumber) {
        switch (CardNumber) {
            case 1:
                return new CardOne();
            case 2:
                return new CardTwo();
            case 3:
                return new CardThree();
            case 4:
                return new CardFour();
            case 5:
                return new CardFive();
            case 6:
                return new CardSix();
            case 7:
                return new DrawDiscard(new CardSeven());
            case 8:
                return new DrawDiscard(new CardEight());
            default:
                return new CardOne();
        }
    }


}

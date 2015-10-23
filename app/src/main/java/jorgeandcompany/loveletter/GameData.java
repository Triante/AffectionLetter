package jorgeandcompany.loveletter;

import java.util.ArrayList;

/**
 * Created by Firemon123 on 10/21/2015.
 */
public class GameData {

    public static Player pOne;
    public static Player pTwo;
    public static Player pThree;
    public static Player pFour;

    public static Deck deck;
    public static ArrayList<Card> discard;
    public static Card OutCard;
    public static int[] Score = {0,0,0,0};

    public static int TURN;
    public static boolean FINISH_GAME = false;


    public static void NewGame() {
        pOne = new Player(1);
        pTwo = new Player(2);
        pThree = new Player(3);
        pFour = new Player(4);
        deck = new Deck();
        discard = new ArrayList<>();
    }
}

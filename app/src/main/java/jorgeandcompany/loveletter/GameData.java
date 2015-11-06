package jorgeandcompany.loveletter;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Firemon123 on 10/21/2015.
 */
public class GameData {

    public static Player[] PlayerList = new Player[5];

    public static Deck deck;
    public static ArrayList<Card> discard;
    public static int TURN;
    public static Card OutCard;
    public static int remain;
    public static int firstOut;
    public static boolean FINISH_GAME;
    public static boolean noOut;


    public static Game game;
    public static int[] Score = {0,0,0,0};
    public static ArrayList <String> skinNames;
    public static int [] skinset = new int [8];
    public static int skinID;
    static {
        skinNames = new ArrayList<String>();
        skinNames.add("Magi Skin");
        skinNames.add("Batman Skin");
        skinNames.add("Avengers Skin");
        skinID = 1;
    }

    public static void setPlayerMode(boolean single) {
        if (single) {
            PlayerList[1] = new HumanPlayer(1);
            PlayerList[2] = new ComPlayerLevelTwo(2);
            PlayerList[3] = new ComPlayerLevelTwo(3);
            PlayerList[4] = new ComPlayerLevelTwo(4);
        }
        else {
            PlayerList[1] = new HumanPlayer(1);
            PlayerList[2] = new HumanPlayer(2);
            PlayerList[3] = new HumanPlayer(3);
            PlayerList[4] = new HumanPlayer(4);
        }
    }

    public static void newGame() {
        deck = new Deck();
        discard = new ArrayList<Card>();
        selectFirstTurnPlayer();
        OutCard = deck.draw();
        FINISH_GAME = false;
        noOut = false;
        remain = 4;
        PlayerList[TURN].drawFirstCard();
        nextTurn();
        PlayerList[TURN].drawFirstCard();
        nextTurn();
        PlayerList[TURN].drawFirstCard();
        nextTurn();
        PlayerList[TURN].drawFirstCard();
        nextTurn();
    }
    public static void newRound() {
        game.clearTable();

        deck = new Deck();
        discard = new ArrayList<Card>();
        TURN = firstOut;
        OutCard = deck.draw();
        FINISH_GAME = false;
        noOut = false;
        remain = 4;


        PlayerList[TURN].clearHand();
        PlayerList[TURN].drawFirstCard();
        nextTurn();
        PlayerList[TURN].clearHand();
        PlayerList[TURN].drawFirstCard();
        nextTurn();
        PlayerList[TURN].clearHand();
        PlayerList[TURN].drawFirstCard();
        nextTurn();
        PlayerList[TURN].clearHand();
        PlayerList[TURN].drawFirstCard();
        nextTurn();
    }
    private static void selectFirstTurnPlayer() {
        int[] players = {1,2,3,4,4,3,2,1};
        shuffleArray(players);
        shuffleArray(players);
        shuffleArray(players);
        TURN = players[0];
        firstOut = TURN;
    }
    private static void shuffleArray(int[] ar) {
        long tsLong = (System.currentTimeMillis()/1000) + (System.currentTimeMillis()/777);
        tsLong = tsLong/2;
        Random rnd = new Random(tsLong);
        for (int i = ar.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }
    public static void nextTurn() {
        TURN++;
        if (TURN > 4) {
            TURN = 1;
        }
    }
    public static int getDeckCount() {
        return deck.getDeckCount();
    }
    public static void setContextMenu(Game aGame) {
        game = aGame;
    }
    public static void out(int p) {
        PlayerList[p].out();
        if (!noOut) firstOut = p;
        remain--;
        if (remain == 1) {
            FINISH_GAME = true;
        }
    }
    public static Card drawOutCard() {
        return OutCard;
    }



}

package jorgeandcompany.loveletter;

import android.view.ContextMenu;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Firemon123 on 10/21/2015.
 */
public class GameData {

    public static Player[] PlayerList = {null, new Player(1), new Player(2),
            new Player(3), new Player(4)};

    public static Deck deck;
    public static ArrayList<Card> discard;
    public static Card OutCard;
    public static int[] Score = {0,0,0,0};
    public static int firstOut;
    public static int remain;
    public static boolean CARD_SEVEN_IN_PLAY = false;
    public static int TURN;
    public static boolean FINISH_GAME = false;
    public static boolean noOut = false;
    public static Game game;


    public static void newGame() {
        deck = new Deck();
        discard = new ArrayList<Card>();
        selectFirstTurnPlayer();
        OutCard = deck.draw();
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
        TURN = firstOut;
        game.clearTable();


        deck = new Deck();
        discard = new ArrayList<>();
        OutCard = null;
        FINISH_GAME = false;
        noOut = false;
        remain = 4;

        PlayerList[TURN].drawFirstCard();
        PlayerList[TURN].in();
        PlayerList[TURN].clearHand();
        nextTurn();
        PlayerList[TURN].drawFirstCard();
        PlayerList[TURN].in();
        PlayerList[TURN].clearHand();
        nextTurn();
        PlayerList[TURN].drawFirstCard();
        PlayerList[TURN].in();
        PlayerList[TURN].clearHand();
        nextTurn();
        PlayerList[TURN].drawFirstCard();
        PlayerList[TURN].in();
        PlayerList[TURN].clearHand();
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
            int winner = TURN -1;
            Score[winner]++;
        }
    }

}

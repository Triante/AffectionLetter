package jorgeandcompany.loveletter;

import android.view.View;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Firemon123 on 10/21/2015.
 */
public class GameData {

    public static Player[] PlayerList = new Player[5];
    public static int gamesUntilWin = 5;

    public static Deck deck;
    public static DiscardPile discardPile;
    public static int TURN;
    public static Card OutCard;
    public static int remain;
    public static int firstOut;
    public static boolean FINISH_GAME;
    public static boolean noOut;
    public static boolean GAME_COMPLETE;
    public static boolean SINGLE_MODE;


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

    public static void setMode(Boolean isSingle, int players, int level) {
        SINGLE_MODE = isSingle;
        if (isSingle) {
            PlayerList[1] = new HumanPlayer(1);
            PlayerList[2] = ComputerLevelFactory.createComputerPlayer(level, 2);
            PlayerList[3] = ComputerLevelFactory.createComputerPlayer(level, 3);
            PlayerList[4] = ComputerLevelFactory.createComputerPlayer(level, 4);
        }
        else if (players == 2) {
            PlayerList[1] = new HumanPlayer(1);
            PlayerList[2] = new HumanPlayer(2);
            PlayerList[3] = ComputerLevelFactory.createComputerPlayer(level, 3);
            PlayerList[4] = ComputerLevelFactory.createComputerPlayer(level, 4);
        }
        else if (players == 3) {
            PlayerList[1] = new HumanPlayer(1);
            PlayerList[2] = new HumanPlayer(2);
            PlayerList[3] = new HumanPlayer(3);
            PlayerList[4] = ComputerLevelFactory.createComputerPlayer(level, 4);
        }
        //players == 4
        else {
            PlayerList[1] = new HumanPlayer(1);
            PlayerList[2] = new HumanPlayer(2);
            PlayerList[3] = new HumanPlayer(3);
            PlayerList[4] = new HumanPlayer(4);
        }
    }
    public static void newGame() {
        Score[0] = 0;
        Score[1] = 0;
        Score[2] = 0;
        Score[3] = 0;

        deck = new Deck();
        discardPile = new DiscardPile();
        selectFirstTurnPlayer();
        OutCard = deck.draw();
        FINISH_GAME = false;
        noOut = false;
        GAME_COMPLETE = false;
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
    public static void newRound() {
        game.getButton("discard").setVisibility(View.INVISIBLE );
        game.clearTable();
        deck = new Deck();
        discardPile = new DiscardPile();
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

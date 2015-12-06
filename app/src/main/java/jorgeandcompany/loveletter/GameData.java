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
    public static boolean debug = false;


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

    /**
     * Sets the mode of the game based off the parameters passed.
     * @param isSingle boolean flag for whether the game is single player or not.
     * @param players the amount of players if running a multi-player game. If isSingle is trues sets at 1, otherwise default 4.
     * @param level the level of the AI
     */
    public static void setMode(Boolean isSingle, int players, int level) {
        ComputerLevelFactory theAIs = new ConcreteComputerLevelFactory();
        SINGLE_MODE = isSingle;
        if (isSingle) {
            PlayerList[1] = new HumanPlayer(1);
            PlayerList[2] = theAIs.createComputerPlayer(level, 2);
            PlayerList[3] = theAIs.createComputerPlayer(level, 3);
            PlayerList[4] = theAIs.createComputerPlayer(level, 4);
        }
        else if (players == 2) {
            PlayerList[1] = new HumanPlayer(1);
            PlayerList[2] = new HumanPlayer(2);
            PlayerList[3] = theAIs.createComputerPlayer(level, 3);
            PlayerList[4] = theAIs.createComputerPlayer(level, 4);
        }
        else if (players == 3) {
            PlayerList[1] = new HumanPlayer(1);
            PlayerList[2] = new HumanPlayer(2);
            PlayerList[3] = new HumanPlayer(3);
            PlayerList[4] = theAIs.createComputerPlayer(level, 4);
        }
        //players == 4
        else {
            PlayerList[1] = new HumanPlayer(1);
            PlayerList[2] = new HumanPlayer(2);
            PlayerList[3] = new HumanPlayer(3);
            PlayerList[4] = new HumanPlayer(4);
        }
    }

    /**
     * Initiates or resets all the values in GameData in order to start a new game.
     */
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

    /**
     * Resets most of the values in GameData in order to start a new round.
     */
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

    /**
     * Selects a player to go first
     */
    private static void selectFirstTurnPlayer() {
        int[] players = {1,2,3,4,4,3,2,1};
        shuffleArray(players);
        shuffleArray(players);
        shuffleArray(players);
        TURN = players[0];
        firstOut = TURN;
    }

    /**
     * Shuffles an array using Fisher Yates algorithm.
     * @param ar array to be shuffled.
     */
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

    /**
     * Increments the turn count. If the turn count reaches 5, resets back to 1.
     */
    public static void nextTurn() {
        TURN++;
        if (TURN > 4) {
            TURN = 1;
        }
    }

    /**
     * Returns the amount of cards still left in the Deck
     * @return the amount of cards left in the Deck
     */
    public static int getDeckCount() {
        return deck.getDeckCount();
    }

    /**
     * Sets the Context of the Game class to GameData.
     * @param aGame
     */
    public static void setContextMenu(Game aGame) {
        game = aGame;
    }

    /**
     * Sets a player out and sets the flag for the player.
     * @param p the player ID who is out.
     */
    public static void out(int p) {
        PlayerList[p].out();
        if (!noOut) firstOut = p;
        remain--;
        if (remain == 1) {
            FINISH_GAME = true;
        }
    }

    /**
     * Returns the out Card
     * @return the out Card
     */
    public static Card drawOutCard() {
        return OutCard;
    }



}

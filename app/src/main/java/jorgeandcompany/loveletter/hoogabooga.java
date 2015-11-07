package jorgeandcompany.loveletter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.CountDownTimer;

import java.util.Random;

/**
 * Created by Firemon123 on 11/5/2015.
 */
public class hoogabooga implements Player {
    private Card leftCard = null;
    private Card rightCard = null;
    private final int playerNumber;
    private boolean isOut = false;
    private boolean hasLeft = false;
    private boolean hasRight = false;
    private boolean isProtected = false;
    private int total = 0;

    //com lvl 2 variables
    private int rememberPlayerNum = 0;
    private int rememberCardNum = 0;
    private boolean remembers = false;
    private boolean toPlayOne = false;


    public hoogabooga(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    @Override
    public void drawFirstCard() {
        leftCard = GameData.deck.draw();
        hasLeft = true;
    }
    @Override
    public void drawOutCard() {
        if (!hasLeft) {
            hasLeft = true;
            leftCard = GameData.drawOutCard();
        }
        else {
            hasRight = true;
            rightCard = GameData.drawOutCard();
        }
    }
    @Override
    public void drawCard() {
        if (!hasLeft) {
            hasLeft = true;
            leftCard = GameData.deck.draw();
        }
        else {
            hasRight = true;
            rightCard = GameData.deck.draw();
        }
    }
    @Override
    public void playCard(int hand) {
        final int toPlay = selectCardToPlay();
        //(3000, 1000) where the first 1000 is the flip, whenever that gets implemented
        CountDownTimer play = new CountDownTimer(2000, 500) {
            boolean done = false;
            boolean otherDone = false;
            @Override
            public void onTick(long millisUntilFinished) {
                if (!done) {
                    //flipBack method here
                    if (toPlay == 0) {
                        //flipCardToBack(firstPlayerRight);
                    } else {
                        //flipCardToBack(firstPlayerLeft);
                    }
                    done = true;
                } else if (!otherDone) {
                    GameData.game.cardToCenterSinglePlayer(hoogabooga.this, toPlay);
                    otherDone = true;
                }

            }
            @Override
            public void onFinish() {
                cardEffect(toPlay);
                //reset card background for non re-flipped card.
            }
        };
        play.start();

    }
    @Override
    public void discardCard() {
        if (getCard().getValue() == 8) {
            effectEight();
        }
        else if (hasLeft) {
            hasLeft = false;
            total += leftCard.getValue();
            leftCard = null;
        }
        else {
            hasRight = false;
            total += rightCard.getValue();
            rightCard = null;
        }
    }



    //do not change these, remain constant throughout both computer and human player
    @Override
    public int getPlayerNumber() {
        return playerNumber;
    }
    @Override
    public boolean isOut() {
        return isOut;
    }
    @Override
    public void out() {
        isOut = true;
        leftCard = null;
        rightCard = null;
        hasLeft = false;
        hasRight = false;
    }

    @Override
    public boolean hasLeftCard() {
        return hasLeft;
    }
    @Override
    public boolean hasRightCard() {
        return hasRight;
    }

    @Override
    public void setCard(Card c) {
        if (hasLeft) leftCard = c;
        else rightCard = c;
    }
    @Override
    public Card getCard(int hand) {
        if (hand == 0) return getLeft();
        else return getRight();
    }
    @Override
    public int getTotal() {
        return total;
    }
    @Override
    public Card getLeft() {
        return leftCard;
    }
    @Override
    public Card getRight() {
        return rightCard;
    }
    @Override
    public Card getCard() {
        if (hasLeft) return leftCard;
        else return rightCard;
    }
    @Override
    public void clearHand() {
        isOut = false;
        hasLeft = false;
        hasRight = false;
        isProtected = false;
        remembers = false;
        leftCard = null;
        rightCard = null;
        rememberCardNum = 0;
        rememberPlayerNum = 0;
    }

    @Override
    public boolean isProtected() {
        return isProtected;
    }
    @Override
    public void setProtected(boolean set) {
        isProtected = set;
    }

    @Override
    public boolean hasSeven() {
        return false;
    }

    @Override
    public void setSeven(boolean seven) {

    }

    @Override
    public boolean isHuman() {
        return false;
    }


    //computer related classes
    private void shuffleArray(int[] ar) {
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
    private int selectPlayer() {
        if (toPlayOne) {
            return rememberPlayerNum;
        }
        int[] selectablePlayers = new int[3];
        int turn = GameData.TURN;
        turn++;
        boolean anySelectable = false;
        int i = 0;
        if (turn == 5) {
            turn = 1;
        }
        while (turn != playerNumber) {
            if (!GameData.PlayerList[turn].isOut() && !GameData.PlayerList[turn].isProtected()) {
                selectablePlayers[i] = GameData.PlayerList[turn].getPlayerNumber();
                anySelectable = true;
            }
            else {
                selectablePlayers[i] = 0;
            }
            turn++;
            if (turn == 5) {
                turn = 1;
            }
            i++;
        }
        int chosen;
        if (anySelectable) {
            shuffleArray(selectablePlayers);
            shuffleArray(selectablePlayers);
            chosen = selectablePlayers[0];
            if (chosen == 0) {
                chosen = selectablePlayers[1];
                if (chosen == 0) {
                    chosen = selectablePlayers[2];
                }
            }
            return chosen;
        }
        else {
            return 0;
        }
    }
    private int selectCardToPlay() {
        int[] hand = {leftCard.getValue(), rightCard.getValue()};
        if (hand[0] == 4 || hand[1] == 4) {
            if (hand[0] == 4) return 0;
            else return 1;
        }
        else if ((hand[0] == 1 || hand[1]== 1) && remembers && !GameData.PlayerList[rememberPlayerNum].isProtected() && rememberCardNum != 1) {
            toPlayOne = true;
            if (hand[0] == 1) return 0;
            else return 1;
        }
        else if (hand[0] == 3 || hand[1]== 3) {
            if (hand[0] ==  2 || hand[0] == 1) return 0;
            else if (hand[1] == 2 || hand[1] == 1) return 1;
            else if (hand[0] == 3) return 0;
            else return 1;
        }
        else if (hand[0] == 5 || hand[1]== 5){
            if (hand[0] == 7) return 0;
            else if (hand[1] == 7) return 1;
            else if (hand[0] == 5) return 0;
            else return 1;
        }
        else if (hand[0] == 2 || hand[1]== 2){
            if (hand[0] == 2) return 0;
            else return 1;
        }
        else if (hand[0] == 1 || hand[1]== 1) {
            if (hand[0] == 1) return 0;
            else return 1;
        }
        else if (hand[0] == 7 || hand[1]== 7){
            if (hand[0] == 7) return 0;
            else return 1;
        }
        else if (hand[0] == 6 || hand[1]== 6){
            if (hand[0] == 6) return 0;
            else return 1;
        }
        else {
            // (hand[0] == 8 || hand[1] == 8)
            if (hand[0] == 8) return 1;
            else return 0;
        }

        //createAccount
    }
    private void cardEffect(int hand) {
        int key;
        if (hand == 0) {
            key = leftCard.getValue();
            total += leftCard.getValue();
            hasLeft = false;
            leftCard = null;
        }
        else {
            key = rightCard.getValue();
            total += rightCard.getValue();
            hasRight = false;
            rightCard = null;
        }

        switch (key) {
            case 1:
                effectOne();
                break;
            case 2:
                effectTwo();
                break;
            case 3:
                effectThree();
                break;
            case 4:
                effectFour();
                break;
            case 5:
                effectFive();
                break;
            case 6:
                effectSix();
                break;
            case 7:
                effectSeven();
                break;
            case 8:
                effectEight();
                break;
            default:
                effectEight();
                break;
        }
    }
    private void effectOne() {
        String message = "";
        int chosen = selectPlayer();
        int guess;
        if(toPlayOne && rememberCardNum == 1) {
            guess = rememberCardNum;
            toPlayOne = false;
            remembers = false;
            rememberPlayerNum = 0;
            rememberCardNum = 0;
        }
        else {
            int[] cards = {1,2,3,4,5,6,7,8};
            shuffleArray(cards);
            shuffleArray(cards);
            shuffleArray(cards);
            guess = cards[3];
        }

        if (chosen != 0) {
            message = "Player " + playerNumber + " used card 1.\n" +
                    "Player " + playerNumber + " guessed if player " + chosen + " had card "+ guess + "." ;
            if (GameData.PlayerList[chosen].getCard().getValue() == guess) {
                message += "\nPlayer " + playerNumber + " guessed correctly. Player "+ chosen + "is out.";
                GameData.out(chosen);
            }
            else {
                message += "\nPlayer " + playerNumber + " guessed incorrectly.\n" +
                        "Player "+ chosen + " is safe.";
            }
        }
        else {
            message = "Player " + playerNumber + " used card 1. Active players were all protected";
        }
        AlertDialog.Builder alert = new AlertDialog.Builder(GameData.game);
        alert.setCancelable(false);
        alert.setTitle("Card 1 Effect");
        alert.setMessage(message);
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                GameData.game.endOfTurn(hoogabooga.this);
            }
        });
        alert.show();
    }
    private void effectTwo() {
        String message = "";
        int chosen = selectPlayer();
        remembers = true;
        rememberPlayerNum = chosen;
        rememberCardNum = GameData.PlayerList[chosen].getCard().getValue();
        if (chosen != 0) {
            message = "Player " + playerNumber + " used card 2. Player " + playerNumber + " now knows player " + chosen + "'s card";
        }
        else {
            message = "Player " + playerNumber + " used card 2. Active players were all protected";
        }
        AlertDialog.Builder alert = new AlertDialog.Builder(GameData.game);
        alert.setCancelable(false);
        alert.setTitle("Card 2 Effect");
        alert.setMessage(message);
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                GameData.game.endOfTurn(hoogabooga.this);
            }
        });
        alert.show();
    }
    private void effectThree() {
        String message = "";
        final int chosen = selectPlayer();
        if (chosen != 0) {
            int c1 = getCard().getValue();
            int c2 = GameData.PlayerList[chosen].getCard().getValue();
            int lower = -1;
            int loser = -1;
            if (c1 > c2) {
                loser = chosen;
                lower = c2;
                message = "Player " + playerNumber + " used card 3. Player " + playerNumber + " compared hands with player " + chosen + "\n" +
                        "Player " + loser + " had card [" + lower +"] and was the lowest. Player " + loser + " is out.";
                GameData.out(loser);
            }
            else if (c1 < c2) {
                loser = playerNumber;
                lower = c1;
                message = "Player " + playerNumber + " used card 3. Player " + playerNumber + " compared hands with player " + chosen + "\n" +
                        "Player " + loser + " had card [" + lower +"] and was the lowest. Player " + loser + " is out.";
                GameData.out(loser);
            }
            else {
                message = "Player " + playerNumber + " and player " + chosen + " had the same value card. No one is out.";
            }

        }
        else {
            message = "Player " + playerNumber + " used card 3. Active players were all protected";
        }
        AlertDialog.Builder alert = new AlertDialog.Builder(GameData.game);
        alert.setCancelable(false);
        alert.setTitle("Card 3 Effect");
        alert.setMessage(message);
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                GameData.game.endOfTurn(hoogabooga.this);
            }
        });
        alert.show();
    }
    private void effectFour() {
        isProtected = true;
        AlertDialog.Builder alert = new AlertDialog.Builder(GameData.game);
        alert.setCancelable(false);
        alert.setTitle("Card 4 Effect");
        alert.setMessage("Player " + playerNumber + " used card 4. Player " + playerNumber + " is now protected.");
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                GameData.game.endOfTurn(hoogabooga.this);
            }
        });
        alert.show();
    }
    private void effectFive() {
        String message = "";
        final int chosen = selectPlayer();
        if (chosen != 0) {
            message = "Player " + playerNumber + " used card 5. Player " + playerNumber + " chose player " + chosen + " to discard his or her card and draw a new one";
        }
        else {
            message = "Player " + playerNumber + " used card 5. Player " + playerNumber + " discarded his or her own card";
        }
        AlertDialog.Builder alert = new AlertDialog.Builder(GameData.game);
        alert.setCancelable(false);
        alert.setTitle("Card 5 Effect");
        alert.setMessage(message);
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int select;
                if (chosen == 0) select = playerNumber;
                else select = chosen;
                if (GameData.PlayerList[select].getCard().getValue() != 8) {
                    GameData.PlayerList[select].discardCard();
                    if (GameData.getDeckCount() == 0) {
                        GameData.PlayerList[select].drawOutCard();
                    } else {
                        GameData.PlayerList[select].drawFirstCard();
                    }
                    GameData.game.endOfTurn(hoogabooga.this);
                }
                else {
                    GameData.PlayerList[select].discardCard();
                }
            }
        });
        alert.show();
    }
    private void effectSix() {
        String message = "";
        int chosen = selectPlayer();
        if (chosen != 0) {
            message = "Player " + playerNumber + " used card 6.\n" +
                    "Player " + playerNumber + " traded cards with player " + chosen;
            Card c1 = getCard();
            Card c2 = GameData.PlayerList[chosen].getCard();
            setCard(c2);
            GameData.PlayerList[chosen].setCard(c1);
        }
        else {
            message = "Player " + playerNumber + " used card 6. Active players were all protected.";
        }
        AlertDialog.Builder alert = new AlertDialog.Builder(GameData.game);
        alert.setCancelable(false);
        alert.setTitle("Card 6 Effect");
        alert.setMessage(message);
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                GameData.game.endOfTurn(hoogabooga.this);
            }
        });
        alert.show();
    }
    private void effectSeven() {
        AlertDialog.Builder alert = new AlertDialog.Builder(GameData.game);
        alert.setCancelable(false);
        alert.setTitle("Card 7 Effect");
        alert.setMessage("Player " + playerNumber + " played card 7.");
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                GameData.game.endOfTurn(hoogabooga.this);
            }
        });
        alert.show();
    }
    private void effectEight() {
        AlertDialog.Builder alert = new AlertDialog.Builder(GameData.game);
        alert.setCancelable(false);
        alert.setTitle("Card 8 Effect");
        alert.setMessage("Player " + playerNumber + " lost card 8. Player " + playerNumber + " is now out.");
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                GameData.out(playerNumber);
                GameData.game.endOfTurn(hoogabooga.this);
            }
        });
        alert.show();
    }
}

package jorgeandcompany.loveletter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.CountDownTimer;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by Firemon123 on 11/13/2015.
 */
public class ComPlayerLevelThree implements Player {

    private Card leftCard = null;
    private Card rightCard = null;
    private final int playerNumber;
    private boolean isOut = false;
    private boolean hasLeft = false;
    private boolean hasRight = false;
    private boolean isProtected = false;
    private int total = 0;

    //com lvl 3 variables
    private AIBrain ai;


    public ComPlayerLevelThree(int playerNumber) {
        this.playerNumber = playerNumber;
        ai = new AIBrain();
    }

    /** {@inheritDoc} */
    @Override
    public void drawFirstCard() {
        leftCard = GameData.deck.draw();
        hasLeft = true;
    }
    /** {@inheritDoc} */
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
    /** {@inheritDoc} */
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
    /** {@inheritDoc} */
    @Override
    public void playCard(int hand) {
        ai.updateRememberFlags();
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
                    GameAnimation theAnimation = GameData.game.provideAnimations();
                    theAnimation.cardToDiscardSinglePlayer(ComPlayerLevelThree.this, toPlay);
                    otherDone = true;
                }

            }
            @Override
            public void onFinish() {
                cardEffect(toPlay);
                if (getPlayerNumber() == 2) {
                    GameData.game.getButton("secondPlayerLeft").setBackgroundResource(SkinRes.skinRes(9,"left"));
                    GameData.game.getButton("secondPlayerRight").setBackgroundResource(SkinRes.skinRes(9,"left"));
                }
                else if (getPlayerNumber() == 3) {
                    GameData.game.getButton("thirdPlayerLeft").setBackgroundResource(SkinRes.skinRes(9,"down"));
                    GameData.game.getButton("thirdPlayerRight").setBackgroundResource(SkinRes.skinRes(9,"down"));
                }
                else {
                    GameData.game.getButton("fourthPlayerLeft").setBackgroundResource(SkinRes.skinRes(9,"right"));
                    GameData.game.getButton("fourthPlayerRight").setBackgroundResource(SkinRes.skinRes(9,"right"));
                }
                //reset card background for non re-flipped card.
            }
        };
        play.start();

    }
    /** {@inheritDoc} */
    @Override
    public void discardCard() {
        if (getCard().getValue() == 8) {
            effectEight();
        }
        else if (hasLeft) {
            hasLeft = false;
            total += leftCard.getValue();
            GameData.discardPile.addToDiscard(leftCard);
            leftCard = null;
        }
        else {
            hasRight = false;
            total += rightCard.getValue();
            GameData.discardPile.addToDiscard(rightCard);
            rightCard = null;
        }
    }
    /** {@inheritDoc} */
    @Override
    public int getPlayerNumber() {
        return playerNumber;
    }
    /** {@inheritDoc} */
    @Override
    public boolean isOut() {
        return isOut;
    }
    /** {@inheritDoc} */
    @Override
    public void out() {
        GameData.discardPile.addToDiscard(getCard());
        isOut = true;
        hasLeft = false;
        hasRight = false;
        isProtected = false;
        leftCard = null;
        rightCard = null;
        ai = new AIBrain();
    }
    /** {@inheritDoc} */
    @Override
    public boolean hasLeftCard() {
        return hasLeft;
    }
    /** {@inheritDoc} */
    @Override
    public boolean hasRightCard() {
        return hasRight;
    }
    /** {@inheritDoc} */
    @Override
    public void setCard(Card c) {
        if (hasLeft) leftCard = c;
        else rightCard = c;
    }
    /** {@inheritDoc} */
    @Override
    public Card getCard(int hand) {
        if (hand == 0) return getLeft();
        else return getRight();
    }
    /** {@inheritDoc} */
    @Override
    public int getTotal() {
        return total;
    }
    /** {@inheritDoc} */
    @Override
    public Card getLeft() {
        return leftCard;
    }
    /** {@inheritDoc} */
    @Override
    public Card getRight() {
        return rightCard;
    }
    /** {@inheritDoc} */
    @Override
    public Card getCard() {
        if (hasLeft) return leftCard;
        else return rightCard;
    }
    /** {@inheritDoc} */
    @Override
    public void clearHand() {
        isOut = false;
        hasLeft = false;
        hasRight = false;
        isProtected = false;
        leftCard = null;
        rightCard = null;
        ai = new AIBrain();
    }
    /** {@inheritDoc} */
    @Override
    public boolean isProtected() {
        return isProtected;
    }
    /** {@inheritDoc} */
    @Override
    public void setProtected(boolean set) {
        isProtected = set;
    }
    /** {@inheritDoc} */
    @Override
    public boolean hasSeven() {
        return false;
    }
    /** {@inheritDoc} */
    @Override
    public void setSeven(boolean seven) {

    }
    /** {@inheritDoc} */
    @Override
    public boolean isHuman() {
        return false;
    }
    /**
     * Shuffles an array using Fisher Yates algorithm.
     * @param ar array to be shuffled.
     */
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
    /**
     * Calls the AIBrain to select a card to play.
     * @return 0 for left hand, 1 for right.
     */
    private int selectCardToPlay() {
        return ai.selectCardToPlay();
    }
    /**
     * Plays the affect of the card chosen.
     * @param hand hand value, 0 will play left card affect, otherwise the right card affect.
     */
    private void cardEffect(int hand) {
        int key;
        if (hand == 0) {
            key = leftCard.getValue();
            total += leftCard.getValue();
            GameData.discardPile.addToDiscard(leftCard);
            hasLeft = false;
            leftCard = null;
        }
        else {
            key = rightCard.getValue();
            total += rightCard.getValue();
            GameData.discardPile.addToDiscard(rightCard);
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
    /**
     * Affect of Card 1. Computer will guess the card of a selected player which was derived from AIBrain.
     * If guessed correctly chosen player will be out. Creates a dialog for the results. This method also ends the current turn.
     */
    private void effectOne() {
        String message = "";
        int chosen = ai.selectPlayer(1);
        if (chosen != 0) {
            int guess = ai.guessCard(chosen);
            message = "Player " + playerNumber + " used card [1].\n" +
                    "Player " + playerNumber + " guessed if player " + chosen + " had card ["+ guess + "].\n" ;
            if (GameData.PlayerList[chosen].getCard().getValue() == guess) {
                message += "\nPlayer " + playerNumber + " guessed correctly.\n" +
                        "Player "+ chosen + " is out.";
                GameData.out(chosen);
            }
            else {
                message += "\nPlayer " + playerNumber + " guessed incorrectly.\n" +
                        "Player "+ chosen + " is safe.";
            }
        }
        else {
            message = "Player " + playerNumber + " used card [1]. Active players were all protected and no action was taken.";
        }
        ThemedDialog.Builder alert = new ThemedDialog.Builder(GameData.game);
        alert.setCancelable(false);
        alert.setTitle("Card 1 Effect");
        alert.setMessage(message);
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                GameData.game.endOfTurn();
            }
        });
        alert.show();
    }
    /**
     * Affect of Card 2. Selects a player and stores their current card in the AIBrain. Creates a dialog for the results.
     * This method also ends the current turn.
     */
    private void effectTwo() {
        String message = "";
        int chosen = ai.selectPlayer(2);
        if (chosen != 0) {
            int chosenCard = GameData.PlayerList[chosen].getCard().getValue();
            ai.setCardToRemember(chosen, chosenCard);
            message = "Player " + playerNumber + " used card [2]. Player " + playerNumber + " now knows player " + chosen + "'s card.";
        }
        else {
            message = "Player " + playerNumber + " used card 2. Active players were all protected and no action was taken.";
        }
        ThemedDialog.Builder alert = new ThemedDialog.Builder(GameData.game);
        alert.setCancelable(false);
        alert.setTitle("Card 2 Effect");
        alert.setMessage(message);
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                GameData.game.endOfTurn();
            }
        });
        alert.show();
    }
    /**
     * Affect of Card 3. Selects a player based off its knowledge in the AIBRain and compares cards with it.
     * Player with the lowest Card value is out. Creates a dialog for the results. This method also ends the current turn.
     */
    private void effectThree() {
        String message = "";
        final int chosen = ai.selectPlayer(3);
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
            message = "Player " + playerNumber + " used card 3. Active players were all protected and no action was taken.";
        }
        ThemedDialog.Builder alert = new ThemedDialog.Builder(GameData.game);
        alert.setCancelable(false);
        alert.setTitle("Card 3 Effect");
        alert.setMessage(message);
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                GameData.game.endOfTurn();
            }
        });
        alert.show();
    }
    /**
     * Affect of Card 4. Sets isProtected flag to true. Creates a dialog for the results. This method also ends the current turn.
     */
    private void effectFour() {
        isProtected = true;
        ThemedDialog.Builder alert = new ThemedDialog.Builder(GameData.game);
        alert.setCancelable(false);
        alert.setTitle("Card 4 Effect");
        alert.setMessage("Player " + playerNumber + " used card 4. Player " + playerNumber + " is now protected.");
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                GameData.game.endOfTurn();
            }
        });
        alert.show();
    }
    /**
     * Affect of Card 5. Selects a player based off its knowledge in the AIBRain and the chosen player
     * discards their card. Creates a dialog for the results. This method also ends the current turn.
     */
    private void effectFive() {
        final int chosen = ai.selectPlayer(5);
        ai.forgetCard(chosen);
        if (chosen != 0) {
            new CountDownTimer(4000,1000) {
                String message = "Player " + playerNumber + " used card 5. Player " + playerNumber + " chose player " + chosen + " to discard his or her card and draw a new one";
                int a = 0;
                @Override
                public void onTick(long millisUntilFinished) {
                    if (a == 0) {
                        int select;
                        if (chosen == 0) select = playerNumber;
                        else select = chosen;
                        if (GameData.PlayerList[select].getCard().getValue() != 8) {
                            GameAnimation animation = GameData.game.provideAnimations();
                            final ImageButton[] set = chooseCardButton(select);
                            animation.discardAnimation(set[0], set[1], select);
                        }
                        else {
                            GameAnimation animation = GameData.game.provideAnimations();
                            final ImageButton[] set = chooseCardButton(select);
                            animation.discardAnimation(set[0], set[1], select);
                        }
                        a++;
                    }
                }

                @Override
                public void onFinish() {
                    ThemedDialog.Builder alert = new ThemedDialog.Builder(GameData.game);
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
                                GameData.game.endOfTurn();
                            }
                            else {
                                GameData.PlayerList[select].discardCard();
                            }
                        }
                    });
                    alert.show();
                }
            }.start();

        }
        else {
            new CountDownTimer(4000,1000) {
                String message = "Player " + playerNumber + " used card 5. Player " + playerNumber + " discarded his or her own card";
                int a = 0;
                @Override
                public void onTick(long millisUntilFinished) {
                    if(a==0) {
                        int select;
                        if (chosen == 0) select = playerNumber;
                        else select = chosen;
                            GameAnimation animation = GameData.game.provideAnimations();
                            final ImageButton[] set = chooseCardButton(select);
                            animation.discardAnimation(set[0], set[1], select);
                        a++;
                    }
                }

                @Override
                public void onFinish() {
                    ThemedDialog.Builder alert = new ThemedDialog.Builder(GameData.game);
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
                                GameData.game.endOfTurn();
                            }
                            else {
                                GameData.PlayerList[select].discardCard();
                            }
                        }
                    });
                    alert.show();
                }
            }.start();
        }

    }
    /**
     * Affect of Card 6. Selects a player and the chosen player trades cards with the current player.
     * Stores the information of the card that it traded and the player it chose into its AIBrain.
     * Creates a dialog for the results. This method also ends the current turn.
     */
    private void effectSix() {
        String message = "";
        final int chosen = ai.selectPlayer(6);
        if (chosen != 0) {
            Card c1 = getCard();
            Card c2 = GameData.PlayerList[chosen].getCard();
            ai.setCardToRemember(chosen, c1.getValue());
            setCard(c2);
            GameData.PlayerList[chosen].setCard(c1);
            new CountDownTimer(2000,1000) {

                @Override
                public void onTick(long millisUntilFinished) {
                    GameAnimation theAnimation = GameData.game.provideAnimations();
                    theAnimation.swapSingle6(playerNumber, chosen);

                }

                @Override
                public void onFinish() {
                    String message = "";
                    message = "Player " + playerNumber + " used card 6.\n" +
                            "Player " + playerNumber + " traded cards with player " + chosen;
                    ThemedDialog.Builder alert = new ThemedDialog.Builder(GameData.game);
                    alert.setCancelable(false);
                    alert.setTitle("Card 6 Effect");
                    alert.setMessage(message);
                    alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            GameData.game.endOfTurn();
                        }
                    });
                    alert.show();
                }
            }.start();
        }
        else {
            message = "Player " + playerNumber + " used card 6. Active players were all protected and no action was taken.";
            ThemedDialog.Builder alert = new ThemedDialog.Builder(GameData.game);
            alert.setCancelable(false);
            alert.setTitle("Card 6 Effect");
            alert.setMessage(message);
            alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    GameData.game.endOfTurn();
                }
            });
            alert.show();
        }
    }
    /**
     * Affect of Card 7. Creates a dialog for the results. This method also ends the current turn.
     */
    private void effectSeven() {
        ThemedDialog.Builder alert = new ThemedDialog.Builder(GameData.game);
        alert.setCancelable(false);
        alert.setTitle("Card 7 Effect");
        alert.setMessage("Player " + playerNumber + " played card 7.");
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                GameData.game.endOfTurn();
            }
        });
        alert.show();
    }
    /**
     * Affect of Card 8. Sets player as out. Creates a dialog for the results. This method also ends the current turn.
     */
    private void effectEight() {
        ThemedDialog.Builder alert = new ThemedDialog.Builder(GameData.game);
        alert.setCancelable(false);
        alert.setTitle("Card 8 Effect");
        alert.setMessage("Player " + playerNumber + " lost card 8. Player " + playerNumber + " is now out.");
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                GameData.out(playerNumber);
                GameData.game.endOfTurn();
            }
        });
        alert.show();
    }
    /**
     * retrieves the button of the player's card that was selected.
     * @param playerNumber ID of the player selected.
     * @return pair of buttons of the player in an array size 2.
     */
    private ImageButton [] chooseCardButton (int playerNumber) {
        ImageButton [] leftandright = new ImageButton [2];
        if (playerNumber == 1) {
            if (GameData.PlayerList[playerNumber].hasLeftCard()) {
                leftandright[0] = GameData.game.getButton("firstplayerleft");
                leftandright[1] = GameData.game.getButton("firstplayerleft");
            }
            else {
                leftandright [0] = GameData.game.getButton("firstplayerright");
                leftandright [1] = GameData.game.getButton("firstplayerleft");
            }
        }
        else if (playerNumber == 2) {
            if (GameData.PlayerList[playerNumber].hasLeftCard()) {
                leftandright [0] = GameData.game.getButton("secondplayerleft");
                leftandright [1] = GameData.game.getButton("secondplayerleft");
            }
            else {
                leftandright [0] =  GameData.game.getButton("secondplayerright");
                leftandright [1] = GameData.game.getButton("secondplayerleft");
            }
        }
        else if (playerNumber == 3) {
            if (GameData.PlayerList[playerNumber].hasLeftCard()) {
                leftandright [0] = GameData.game.getButton("thirdplayerleft");
                leftandright [1] = GameData.game.getButton("thirdplayerleft");
            }
            else {
                leftandright [0] = GameData.game.getButton("thirdplayerright");
                leftandright [1] = GameData.game.getButton("thirdplayerleft");
            }
        }
        else {
            if (GameData.PlayerList[playerNumber].hasLeftCard()) {
                leftandright [0] = GameData.game.getButton("fourthplayerleft");
                leftandright [1] = GameData.game.getButton("fourthplayerleft");
            }
            else {
                leftandright [0] = GameData.game.getButton("fourthplayerright");
                leftandright [1] = GameData.game.getButton("fourthplayerleft");
            }
        }

        return leftandright;
    }

    /**
     * Class used to store information for ComPlayerLevelThree. Also handles the methods of how to
     * select cards and players.
     */
    class AIBrain {
        private int[] rememberedCards = {-1, -1, -1, -1, -1};
        private boolean remembersCard = false;
        private boolean remembersEight = false;

        /**
         * Uses card priority based on what it has on its left hand.
         * @return 0 if left card was selected, 1 otherwise.
         */
        private int selectCardToPlay() {
            int leftValue = leftCard.getValue();
            switch (leftValue) {
                case 1:
                    return cardOnePriority();
                case 2:
                    return cardTwoPriority();
                case 3:
                    return cardThreePriority();
                case 4:
                    return cardFourPriority();
                case 5:
                    return cardFivePriority();
                case 6:
                    return cardSixPriority();
                case 7:
                    return cardSevenPriority();
                case 8:
                    return cardEightPriority();
                default:
                    return 1;
            }
        }

        /**
         * Card priority for when left card is Card 1.
         * @return 0 if left card is best, 1 otherwise.
         */
        private int cardOnePriority() {
            if (remembersCard || remembersEight) return 0;
            int rightValue = rightCard.getValue();
            if (rightValue == 1 || rightValue == 2 || rightValue == 4) return 1;
            if (rightValue == 7 && toFluff()) return 1;
            return 0;
        }

        /**
         * Card priority for when left card is Card 2.
         * @return 0 if left card is best, 1 otherwise.
         */
        private int cardTwoPriority() {
            int rightValue = rightCard.getValue();
            if (rightValue == 1 && remembersCard) return 1;
            if (rightValue == 5 && remembersEight) return 1;
            if (rightValue ==2 || rightValue == 4) return 1;
            if (rightValue == 7 && toFluff()) return 1;
            return 0;
        }

        /**
         * Card priority for when left card is Card 3.
         * @return 0 if left card is best, 1 otherwise.
         */
        private int cardThreePriority() {
            int rightValue = rightCard.getValue();
            if (rightValue == 1 || rightValue == 2 || rightValue ==3 || rightValue == 4 || rightValue == 5) return 1;
            return 0;
        }

        /**
         * Card priority for when left card is Card 4.
         * @return 0 if left card is best, 1 otherwise.
         */
        private int cardFourPriority() {
            int rightValue = rightCard.getValue();
            if (rightValue == 1 && remembersCard) return 1;
            if (rightValue == 5 && remembersEight) return 1;
            return 0;
        }

        /**
         * Card priority for when left card is Card 5.
         * @return 0 if left card is best, 1 otherwise.
         */
        private int cardFivePriority() {
            if (remembersEight) return 0;
            int rightValue = rightCard.getValue();
            if (rightValue == 1 && remembersCard) return 1;
            if (rightValue == 2 || rightValue == 4 || rightValue == 5 || rightValue == 7) return 1;
            return 0;
        }

        /**
         * Card priority for when left card is Card 6.
         * @return 0 if left card is best, 1 otherwise.
         */
        private int cardSixPriority() {
            int rightValue = rightCard.getValue();
            if (rightValue == 1 || rightValue == 3 || rightValue == 4 || rightValue == 7) return 1;
            return 0;
        }

        /**
         * Card priority for when left card is Card 7.
         * @return 0 if left card is best, 1 otherwise.
         */
        private int cardSevenPriority() {
            int rightValue = rightCard.getValue();
            if (rightValue == 1 && remembersCard) return 1;
            if (rightValue == 3 || rightValue == 4) return 1;
            return 0;
        }

        /**
         * Card priority for when left card is Card 8.
         * @return always return 1.
         */
        private int cardEightPriority() {
            return 1;
        }

        /**
         * Generates a random boolean from the Random class in order to play card 7 when forced affect is not in play.
         * @return random boolean.
         */
        private boolean toFluff() {
            Random rnd = new Random();
            return rnd.nextBoolean();
        }

        /**
         * Selects a player influenced off its stored knowledge. Player is selected based off what card
         * the computer will play. If there is no previous knowledge, the computer will select at random.
         * @param cardNumber Card number computer will play
         * @return the player ID
         */
        private int selectPlayer(int cardNumber) {
            if (cardNumber == 1) {
                if (remembersCard || remembersEight) {
                    if (rememberedCards[1] != 1 &&  rememberedCards[1] != -1
                            && !GameData.PlayerList[1].isProtected()  && !GameData.PlayerList[1].isOut()) return 1;
                    if (rememberedCards[2] != 1 &&  rememberedCards[2] != -1
                            && !GameData.PlayerList[2].isProtected()  && !GameData.PlayerList[2].isOut()) return 2;
                    if (rememberedCards[3] != 1 &&  rememberedCards[3] != -1
                            && !GameData.PlayerList[3].isProtected()  && !GameData.PlayerList[3].isOut()) return 3;
                    if (rememberedCards[4] != 1 &&  rememberedCards[4] != -1
                            && !GameData.PlayerList[4].isProtected()  && !GameData.PlayerList[4].isOut()) return 4;
                }
            }

            if (cardNumber == 3) {
                if (rememberedCards[1] < getCard().getValue() &&  rememberedCards[1] != -1
                        && !GameData.PlayerList[1].isProtected()  && !GameData.PlayerList[1].isOut()) return 1;
                if (rememberedCards[2] < getCard().getValue() &&  rememberedCards[2] != -1
                        && !GameData.PlayerList[2].isProtected()  && !GameData.PlayerList[2].isOut()) return 2;
                if (rememberedCards[3] < getCard().getValue() &&  rememberedCards[3] != -1
                        && !GameData.PlayerList[3].isProtected()  && !GameData.PlayerList[3].isOut()) return 3;
                if (rememberedCards[4] < getCard().getValue() &&  rememberedCards[4] != -1
                        && !GameData.PlayerList[4].isProtected()  && !GameData.PlayerList[4].isOut()) return 4;
            }

            if (cardNumber == 5) {
                if (rememberedCards[1] == 8
                        && !GameData.PlayerList[1].isProtected()  && !GameData.PlayerList[1].isOut()) return 1;
                if (rememberedCards[2] == 8
                        && !GameData.PlayerList[2].isProtected()  && !GameData.PlayerList[2].isOut()) return 2;
                if (rememberedCards[3] == 8
                        && !GameData.PlayerList[3].isProtected()  && !GameData.PlayerList[3].isOut()) return 3;
                if (rememberedCards[4] == 8
                        && !GameData.PlayerList[4].isProtected()  && !GameData.PlayerList[4].isOut()) return 4;
            }

            // else if another card or no match
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

        /**
         * Guesses a card influenced off its stored knowledge, the player selected, and the discarded cards.
         * @param player Player to have it's card guessed.
         * @return the guessed number
         */
        private int guessCard(int player) {
            int guess;
            if (remembersCard && rememberedCards[player] != 1 && rememberedCards[player] != -1)
            {
                guess = rememberedCards[player];
                forgetCard(player);
                return guess;
            }
            int[] discard = GameData.discardPile.getDiscardInformation();
            int[] cardsNotToPlay = {-1, 0, 0, 0, 0, 0, 0, 0, 0};
            for (int i = 0; i < discard.length; i++) {
                int card = discard[i];
                cardsNotToPlay[card]++;
            }
            cardsNotToPlay[getCard().getValue()]++;
            for (int i = 0; i < rememberedCards.length; i++) {
                int check = rememberedCards[i];
                if (check > 1) cardsNotToPlay[check]++;
            }

            ArrayList<Integer> cardsToGuess = new ArrayList<>();
            if (cardsNotToPlay[2] != 2) cardsToGuess.add(2);
            if (cardsNotToPlay[3] != 2) cardsToGuess.add(3);
            if (cardsNotToPlay[4] != 2) cardsToGuess.add(4);
            if (cardsNotToPlay[5] != 2) cardsToGuess.add(5);
            if (cardsNotToPlay[6] != 1) cardsToGuess.add(6);
            if (cardsNotToPlay[7] != 1) cardsToGuess.add(7);
            if (cardsNotToPlay[8] != 1) cardsToGuess.add(8);

            if (cardsToGuess.isEmpty()) return 2;
            long seed = (System.currentTimeMillis()/1000) + (System.currentTimeMillis()/777);
            Collections.shuffle(cardsToGuess, new Random(seed));
            Collections.shuffle(cardsToGuess, new Random(seed));

            return cardsToGuess.get(0);
        }

        /**
         * updates the flags for AIBrain related to whether it remembers the other players cards.
         */
        private void updateRememberFlags() {
            if (GameData.PlayerList[1].isOut()) rememberedCards[1] = -1;
            if (GameData.PlayerList[2].isOut()) rememberedCards[1] = -1;
            if (GameData.PlayerList[3].isOut()) rememberedCards[1] = -1;
            if (GameData.PlayerList[4].isOut()) rememberedCards[1] = -1;


            if (rememberedCards[1] == -1 &&  rememberedCards[2] == -1 &&  rememberedCards[3] == -1 &&  rememberedCards[4] == -1) {
                remembersCard = false;
            }
            if (rememberedCards[1] != 8 &&  rememberedCards[2] != 8 &&  rememberedCards[3] != 8 &&  rememberedCards[4] != 8) {
                remembersEight = false;
            }
        }

        /**
         * Adds the player and their card to the computers knowledge and updates the flags for whether
         * it remembers if players are holding cards.
         * @param player the player to be remembered.
         * @param card the player's card to be remembered.
         */
        private void setCardToRemember(int player, int card) {
            if (card == 8) remembersEight = true;
            remembersCard = true;
            rememberedCards[player] = card;
        }

        /**
         * Forgets the card that the player is holding
         * @param player player for the AIBrain to forget their card.
         */
        private void forgetCard(int player) {
            rememberedCards[player] = -1;
            updateRememberFlags();
        }

    }

}

package jorgeandcompany.loveletter;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.CountDownTimer;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;

/**
 * Created by Penguins94 on 11/6/2015.
 */
public class GameAnimation {
    private ImageButton discard, deck, firstPlayerRight, firstPlayerLeft, secondPlayerRight,
            secondPlayerLeft, thirdPlayerRight, thirdPlayerLeft, fourthPlayerRight, fourthPlayerLeft, outCard;
    private boolean isAnimating;

    public GameAnimation () {
        isAnimating = true;
        firstPlayerLeft = GameData.game.getButton("firstPlayerLeft");
        firstPlayerRight = GameData.game.getButton("firstPlayerRight");
        secondPlayerLeft = GameData.game.getButton("secondPlayerLeft");
        secondPlayerRight = GameData.game.getButton("secondPlayerRight");
        thirdPlayerLeft = GameData.game.getButton("thirdPlayerLeft");
        thirdPlayerRight = GameData.game.getButton("thirdPlayerRight");
        fourthPlayerLeft = GameData.game.getButton("fourthPlayerLeft");
        fourthPlayerRight = GameData.game.getButton("fourthPlayerRight");
        deck = GameData.game.getButton("deck");
        discard = GameData.game.getButton("discard");
        outCard = GameData.game.getButton("outcard");
    }

    public boolean isAnimating () {
        return isAnimating;
    }
    public void changeAnimatingState () {
        if (isAnimating) {
            isAnimating = false;
        }
        else {
            isAnimating = true;
        }
    }
    public void deckToOutCard() {
        int[] cardcoordinates = new int[2];
        int[] deckcoordinates = new int[2];
        Animation translate;
        AnimationSet set;
        outCard.getLocationOnScreen(cardcoordinates);
        deck.getLocationOnScreen(deckcoordinates);
        translate = new TranslateAnimation(0, cardcoordinates[0] - deckcoordinates[0], 0, cardcoordinates[1] - deckcoordinates[1]);
        translate.setDuration(1000);
        set = new AnimationSet (true);
        set.addAnimation(translate);
        deck.startAnimation(set);
        new CountDownTimer(1000, 1000) {
            public void onTick(long millisUntilFinished) {}

            public void onFinish() {
                outCard.setVisibility(View.VISIBLE);
            }
        }.start();
    }
    public void deckToRight(int playerID) {
        switch (playerID){
            case 1:
                deckToFirstRight();
                break;
            case 2:
                deckToSecondRight();
                break;
            case 3:
                deckToThirdRight();
                break;
            case 4:
                deckToFourthRight();
                break;
        }
    }
    public void deckToLeft(int playerID) {
        switch (playerID){
            case 1:
                deckToFirstLeft();
                break;
            case 2:
                deckToSecondLeft();
                break;
            case 3:
                deckToThirdLeft();
                break;
            case 4:
                deckToFourthLeft();
                break;
        }
    }
    private void dealCard(int rotation, ImageButton player) {
        int[] cardcoordinates = new int [2];
        int[] deckcoordinates = new int [2];
        Animation translate;
        AnimationSet set;
        Animation rotate;
        player.getLocationOnScreen(cardcoordinates);
        deck.getLocationOnScreen(deckcoordinates);
        float y = player.getPivotY() - deck.getPivotY();
        float x = player.getPivotX() - deck.getPivotX();
        translate = new TranslateAnimation(0, cardcoordinates[0] - deckcoordinates[0] + x, 0, cardcoordinates[1] - deckcoordinates[1] + y);
        rotate = new RotateAnimation(0, rotation, deck.getPivotX(),deck.getPivotY());
        translate.setDuration(1000);
        rotate.setDuration(1000);
        set = new AnimationSet(true);
        set.addAnimation(rotate);
        set.addAnimation(translate);
        deck.startAnimation(set);
        if (GameData.deck.getDeckCount() <= 1) {
            deck.setVisibility(View.INVISIBLE);
        }
    }
    public void deckToFirstRight() {
        dealCard(0, firstPlayerRight);
        new CountDownTimer(1000, 1000) {
            public void onTick(long millisUntilFinished) {}

            public void onFinish() {
                firstPlayerRight.setVisibility(firstPlayerRight.VISIBLE);
            }
        }.start();

    }
    public void deckToSecondRight() {
        dealCard(90, secondPlayerRight);
        new CountDownTimer(1000, 1000) {
            public void onTick(long millisUntilFinished) {}

            public void onFinish() {
                secondPlayerRight.setVisibility(secondPlayerRight.VISIBLE);
            }
        }.start();


    }
    public void deckToThirdRight() {
        dealCard(180, thirdPlayerRight);
        new CountDownTimer(1000, 1000) {
            public void onTick(long millisUntilFinished) {}

            public void onFinish() {
                thirdPlayerRight.setVisibility(thirdPlayerRight.VISIBLE);
            }
        }.start();


    }
    public void deckToFourthRight() {
        dealCard(-90, fourthPlayerRight);
        new CountDownTimer(1000, 1000) {
            public void onTick(long millisUntilFinished) {}

            public void onFinish() {
                fourthPlayerRight.setVisibility(fourthPlayerRight.VISIBLE);
            }
        }.start();

    }
    public void deckToFirstLeft() {
        dealCard(0, firstPlayerLeft);
        new CountDownTimer(1000, 1000) {
            public void onTick(long millisUntilFinished) {}

            public void onFinish() {
                firstPlayerLeft.setVisibility(firstPlayerLeft.VISIBLE);
            }
        }.start();

    }
    public void deckToSecondLeft() {
        dealCard(90, secondPlayerLeft);
        new CountDownTimer(1000, 1000) {
            public void onTick(long millisUntilFinished) {}

            public void onFinish() {
                secondPlayerLeft.setVisibility(secondPlayerLeft.VISIBLE);
            }
        }.start();


    }
    public void deckToThirdLeft() {
        dealCard(180, thirdPlayerLeft);
        new CountDownTimer(1000, 1000) {
            public void onTick(long millisUntilFinished) {}

            public void onFinish() {
                thirdPlayerLeft.setVisibility(thirdPlayerLeft.VISIBLE);
            }
        }.start();


    }
    public void deckToFourthLeft() {
        dealCard(-90, fourthPlayerLeft);
        new CountDownTimer(1000, 1000) {
            public void onTick(long millisUntilFinished) {}

            public void onFinish() {
                fourthPlayerLeft.setVisibility(fourthPlayerLeft.VISIBLE);
            }
        }.start();

    }
    private void cardToDiscard(ImageButton buttonToDiscard, int playerNumber) {
        int[] cardcoordinates = new int[2];
        int[] discardcoordinates = new int[2];
        int rotation;

        if (playerNumber == 1) rotation = 0;
        else if (playerNumber == 2) rotation = -90;
        else if (playerNumber == 3) rotation = 180;
        else rotation = 90;

        discard.getLocationOnScreen(discardcoordinates);
        buttonToDiscard.getLocationOnScreen(cardcoordinates);
        float x = discard.getPivotX() - buttonToDiscard.getPivotX();
        float y =  discard.getPivotY() - buttonToDiscard.getPivotY();
        Animation rotate = new RotateAnimation(0, rotation, buttonToDiscard.getPivotX(), buttonToDiscard.getPivotY());
        rotate.setDuration(1000);
        Animation translateleftrigt = new TranslateAnimation(0, discardcoordinates[0] - cardcoordinates[0] + x, 0, discardcoordinates[1] - cardcoordinates[1] + y);
        translateleftrigt.setDuration(1000);
        AnimationSet rotateandmove = new AnimationSet(false);
        rotateandmove.addAnimation(rotate);
        rotateandmove.addAnimation(translateleftrigt);
        buttonToDiscard.startAnimation(rotateandmove);
        buttonToDiscard.setVisibility(View.INVISIBLE);
    };
    public void flipCard(final View toFlip, final int id) {
        final int rightout, leftin;
        if (toFlip == firstPlayerLeft || toFlip == firstPlayerRight || toFlip == thirdPlayerLeft || toFlip == thirdPlayerRight) {
            rightout = R.animator.flip_right_out;
            leftin = R.animator.flight_left_in;
        }
        else {
            rightout = R.animator.flip_horizontal_right_out;
            leftin = R.animator.flip_horizontal_left_in;
        }
        new CountDownTimer(400, 100) {
            int a = 0;
            @Override
            public void onTick(long millisUntilFinished) {
                if (a == 0) {
                    final AnimatorSet setRightOut = (AnimatorSet) AnimatorInflater.loadAnimator(GameData.game.getApplicationContext(),
                            rightout);
                    setRightOut.setTarget(toFlip);
                    setRightOut.start();
                    a++;
                }
                else if (a == 1) {
                    toFlip.setBackgroundResource(id);
                    final AnimatorSet setLeftIn = (AnimatorSet) AnimatorInflater.loadAnimator(GameData.game.getApplicationContext(),
                            leftin);
                    setLeftIn.setTarget(toFlip);
                    setLeftIn.start();
                    a++;
                }
            }

            @Override
            public void onFinish() {}
        }.start();
    }
    public void flipCardToBack(final View toFlip) {
        new CountDownTimer(400, 100) {
            int a = 0;
            @Override
            public void onTick(long millisUntilFinished) {
                if (a == 0) {

                    final AnimatorSet setRightOut = (AnimatorSet) AnimatorInflater.loadAnimator(GameData.game.getApplicationContext(),
                            R.animator.flip_right_out);
                    setRightOut.setTarget(toFlip);
                    setRightOut.start();
                    a++;
                }
                else if (a ==1) {
                    toFlip.setBackgroundResource(SkinRes.skinRes(9,"up"));
                    final AnimatorSet setLeftIn = (AnimatorSet) AnimatorInflater.loadAnimator(GameData.game.getApplicationContext(),
                            R.animator.flight_left_in);
                    setLeftIn.setTarget(toFlip);
                    setLeftIn.start();
                    a++;
                }
            }

            @Override
            public void onFinish() {

            }
        }.start();
    }
    public void cardToDiscardSinglePlayer(final Player on, final int hand) {
        int position;
        if (GameData.SINGLE_MODE || !GameData.PlayerList[GameData.TURN].isHuman()) position = on.getPlayerNumber();
        else {
            position = 1;
            int player = on.getPlayerNumber();
            while (player != GameData.TURN) {
                position--;
                player++;
                if (player == 5) player = 1;
                if (position == 0) position = 4;
            }
        }
        final int playerNum = position;
        final int card = on.getCard(hand).getValue();
            new CountDownTimer(400,100) {
                int a = 0;
                ImageButton theButtonToDiscard = null;
                String orientation = "";
                @Override
                public void onTick(long millisUntilFinished) {
                    if (a == 0) {
                        if (hand == 0) {
                            if (playerNum == 1) {
                                theButtonToDiscard = firstPlayerLeft;
                                orientation = "up";
                            }
                            else if (playerNum == 2) {
                                theButtonToDiscard = secondPlayerLeft;
                                orientation = "left";
                            }
                            else if (playerNum == 3) {
                                theButtonToDiscard = thirdPlayerLeft;
                                orientation = "down";
                            }
                            else {
                                theButtonToDiscard = fourthPlayerLeft;
                                orientation = "right";
                            }
                        }
                        else {
                            if (playerNum == 1) {
                                theButtonToDiscard = firstPlayerRight;
                                orientation = "up";
                            } else if (playerNum == 2) {
                                theButtonToDiscard = secondPlayerRight;
                                orientation = "left";
                            } else if (playerNum == 3) {
                                theButtonToDiscard = thirdPlayerRight;
                                orientation = "down";
                            } else {
                                theButtonToDiscard = fourthPlayerRight;
                                orientation = "right";
                            }
                        }
                        flipCard(theButtonToDiscard, on.getCard(hand).getSkinRes(orientation));
                        a++;
                    }
                }

                @Override
                public void onFinish() {
                    new CountDownTimer(2000,1000) {
                        int a = 0;
                        @Override
                        public void onTick(long millisUntilFinished) {
                            if (a == 0) {
                                cardToDiscard(theButtonToDiscard, playerNum);
                                new CountDownTimer(1000, 1000) {
                                    @Override
                                    public void onTick(long millisUntilFinished) {}

                                    @Override
                                    public void onFinish() {
                                        if (discard.getVisibility() == View.INVISIBLE) {
                                            discard.setVisibility(View.VISIBLE);
                                        }
                                        discard.setBackgroundResource(SkinRes.skinRes(card, "up"));
                                    }
                                }.start();
                                a++;
                            }
                        }

                        @Override
                        public void onFinish() {}
                    }.start();
                }
            }.start();
    }
    public void cardToDiscardMultiPlayer(final int hand) {
        if (hand == 0) cardToDiscard(firstPlayerLeft, 1);
        else cardToDiscard(firstPlayerRight, 1);

        new CountDownTimer(1000,1000) {

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                if (discard.getVisibility() == View.INVISIBLE) {
                    discard.setVisibility(View.VISIBLE);
                }
                discard.setBackgroundResource(SkinRes.skinRes(GameData.PlayerList[GameData.TURN].getCard(hand).getValue(), "up"));
            }
        }.start();
    }
    public void swapCard6(final ImageButton a, final ImageButton b, final Player thePlayer, final int id, final int swap1, final int swap2) {
        new CountDownTimer(2000, 1000) {
            public void onTick(long millisUntilFinished) {
                int[] bcoordinates = new int[2];
                int[] acoordinates = new int[2];
                a.getLocationOnScreen(acoordinates);
                b.getLocationOnScreen(bcoordinates);
                float aX = b.getPivotX() - a.getPivotX();
                float bX = a.getPivotX() - b.getPivotX();
                float aY = b.getPivotY() - a.getPivotY();
                float bY = a.getPivotY() - b.getPivotY();
                Animation rotateb = new RotateAnimation(0, swap1, b.getPivotX(), b.getPivotY());
                Animation rotatea = new RotateAnimation(0, swap2, a.getPivotX(), a.getPivotY());
                rotatea.setDuration(1000);
                rotateb.setDuration(1000);
                Animation translateb = new TranslateAnimation(0, acoordinates[0] - bcoordinates[0] + bX, 0, acoordinates[1] - bcoordinates[1] + bY);
                Animation translatea = new TranslateAnimation(0, bcoordinates[0] - acoordinates[0] + aX, 0, bcoordinates[1] - acoordinates[1] + aY);
                translateb.setDuration(1000);
                translatea.setDuration(1000);
                AnimationSet rotateandmovea = new AnimationSet(false), rotateandmoveb = new AnimationSet(false);
                rotateandmovea.addAnimation(rotatea);
                rotateandmovea.addAnimation(translatea);
                rotateandmoveb.addAnimation(rotateb);
                rotateandmoveb.addAnimation(translateb);
                a.startAnimation(rotateandmovea);
                b.startAnimation(rotateandmoveb);
            }

            public void onFinish() {
                new CountDownTimer(2000, 1000) {
                    public void onTick(long millisUntilFinished) {

                    }

                    public void onFinish() {
                        ThemedDialog.Builder success = new ThemedDialog.Builder(GameData.game);
                        success.setCancelable(false);
                        success.setTitle("Card 6 Effect");
                        success.setMessage("Player " + thePlayer.getPlayerNumber() + " traded cards with player " + id + ".");
                        success.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                GameData.game.endOfTurn();
                            }
                        });
                        success.show();
                    }
                }.start();

            }
        }.start();
    }
    public void swapSingle6 (int playerNumber, int chosen) {
        ImageButton temp1 = null;
        ImageButton temp2 = null;
        final int swap1;
        final int swap2;
        if (GameData.PlayerList[playerNumber].hasLeftCard()) {
            switch(playerNumber){
                case 1:
                    temp1 = firstPlayerLeft;
                    break;
                case 2:
                    temp1 = secondPlayerLeft;
                    break;
                case 3:
                    temp1 = thirdPlayerLeft;
                    break;
                case 4:
                    temp1 = fourthPlayerLeft;
                    break;
            }
        }
        else {
            switch(playerNumber){
                case 1:
                    temp1 = firstPlayerRight;
                    break;
                case 2:
                    temp1 = secondPlayerRight;
                    break;
                case 3:
                    temp1 = thirdPlayerRight;
                    break;
                case 4:
                    temp1 = fourthPlayerRight;
                    break;
            }
        }
        if (GameData.PlayerList[chosen].hasLeftCard()) {
            switch(chosen){
                case 1:
                    temp2 = firstPlayerLeft;
                    break;
                case 2:
                    temp2 = secondPlayerLeft;
                    break;
                case 3:
                    temp2 = thirdPlayerLeft;
                    break;
                case 4:
                    temp2 = fourthPlayerLeft;
                    break;
            }
        }
        else {
            switch(chosen){
                case 1:
                    temp2 = firstPlayerRight;
                    break;
                case 2:
                    temp2 = secondPlayerRight;
                    break;
                case 3:
                    temp2 = thirdPlayerRight;
                    break;
                case 4:
                    temp2 = fourthPlayerRight;
                    break;
            }
        }
        final ImageButton a = temp1;
        final ImageButton b = temp2;

        if ((playerNumber == 1 && chosen == 2) || (playerNumber == 2 && chosen == 3) || (playerNumber == 3 && chosen == 4) || (playerNumber == 4 && chosen == 1)) {
            swap1 = -90;
            swap2 = 90;
        }
        else if((playerNumber == 2 && chosen == 1) || (playerNumber == 3 && chosen == 2) || (playerNumber == 4 && chosen == 3) || (playerNumber == 1 && chosen == 4)) {
            swap1 = 90;
            swap2 = -90;
        }
        else if ((playerNumber == 1 && chosen == 3) || (playerNumber == 2 && chosen == 4)){
            swap1 = -180;
            swap2 = 180;
        }
        else {
            swap1 = 180;
            swap2 = -180;
        }
        new CountDownTimer(2000, 1000) {
            public void onTick(long millisUntilFinished) {
                int[] bcoordinates = new int[2];
                int[] acoordinates = new int[2];
                a.getLocationOnScreen(acoordinates);
                b.getLocationOnScreen(bcoordinates);
                float aX = b.getPivotX() - a.getPivotX();
                float bX = a.getPivotX() - b.getPivotX();
                float aY = b.getPivotY() - a.getPivotY();
                float bY = a.getPivotY() - b.getPivotY();
                Animation rotateb = new RotateAnimation(0, swap1, b.getPivotX(), b.getPivotY());
                Animation rotatea = new RotateAnimation(0, swap2, a.getPivotX(), a.getPivotY());
                rotatea.setDuration(1000);
                rotateb.setDuration(1000);
                Animation translateb = new TranslateAnimation(0, acoordinates[0] - bcoordinates[0] + bX, 0, acoordinates[1] - bcoordinates[1] + bY);
                Animation translatea = new TranslateAnimation(0, bcoordinates[0] - acoordinates[0] + aX, 0, bcoordinates[1] - acoordinates[1] + aY);
                translateb.setDuration(1000);
                translatea.setDuration(1000);
                AnimationSet rotateandmovea = new AnimationSet(false), rotateandmoveb = new AnimationSet(false);
                rotateandmovea.addAnimation(rotatea);
                rotateandmovea.addAnimation(translatea);
                rotateandmoveb.addAnimation(rotateb);
                rotateandmoveb.addAnimation(translateb);
                a.startAnimation(rotateandmovea);
                b.startAnimation(rotateandmoveb);
            }

            public void onFinish() {
                new CountDownTimer(2000, 1000) {
                    public void onTick(long millisUntilFinished) {

                    }

                    public void onFinish() {
                    }
                }.start();

            }
        }.start();
    }
    public void discardAnimation (final ImageButton button, final ImageButton leftDefault, final int playerSelected) {

        new CountDownTimer(2000, 1000) {
            int a = 0;
            Card theCard;
            int[] coordinatesout = new int[2];
            int[] acoordinates = new int[2];
            Animation rotatea;
            Animation translatea;
            AnimationSet rotateandmovea = new AnimationSet(false);
            public void onTick(long millisUntilFinished) {
                if (a == 0) {
                    if (button == GameData.game.getButton("firstPlayerLeft")) {
                        cardToDiscardSinglePlayer(GameData.PlayerList[playerSelected], 0);
                        theCard = GameData.PlayerList[playerSelected].getCard(0);
                    } else if (button == GameData.game.getButton("firstPlayerRight")) {
                        cardToDiscardSinglePlayer(GameData.PlayerList[playerSelected], 1);
                        theCard = GameData.PlayerList[playerSelected].getCard(1);
                    } else if (button == GameData.game.getButton("secondPlayerLeft")) {
                        cardToDiscardSinglePlayer(GameData.PlayerList[playerSelected], 0);
                        theCard = GameData.PlayerList[playerSelected].getCard(0);
                    } else if (button == GameData.game.getButton("secondPlayerRight")) {
                        cardToDiscardSinglePlayer(GameData.PlayerList[playerSelected], 1);
                        theCard = GameData.PlayerList[playerSelected].getCard(1);
                    } else if (button == GameData.game.getButton("thirdPlayerLeft")) {
                        cardToDiscardSinglePlayer(GameData.PlayerList[playerSelected], 0);
                        theCard = GameData.PlayerList[playerSelected].getCard(0);
                    } else if (button == GameData.game.getButton("thirdPlayerRight")) {
                        cardToDiscardSinglePlayer(GameData.PlayerList[playerSelected], 1);
                        theCard = GameData.PlayerList[playerSelected].getCard(1);
                    } else if (button == GameData.game.getButton("fourthPlayerLeft")) {
                        cardToDiscardSinglePlayer(GameData.PlayerList[playerSelected], 0);
                        theCard = GameData.PlayerList[playerSelected].getCard(0);
                    } else {
                        cardToDiscardSinglePlayer(GameData.PlayerList[playerSelected], 1);
                        theCard = GameData.PlayerList[playerSelected].getCard(1);
                    }
                    a++;
                }
            }

            public void onFinish() {
                if (theCard.getValue() != 8) {
                    new CountDownTimer(2000, 1000) {
                        int a = 0;

                        public void onTick(long millisUntilFinished) {
                            if (a == 0) {
                                if (leftDefault == GameData.game.getButton("firstPlayerLeft")) {
                                    if (GameData.deck.getDeckCount() <= 1) {
                                        outCard.getLocationOnScreen(coordinatesout);
                                        leftDefault.getLocationOnScreen(acoordinates);
                                        float x = leftDefault.getPivotX() - outCard.getPivotX();
                                        float y = leftDefault.getPivotY() - outCard.getPivotY();
                                        rotatea = new RotateAnimation(0, 0, outCard.getPivotX(), outCard.getPivotY());
                                        translatea = new TranslateAnimation(0, acoordinates[0] - coordinatesout[0] + x, 0, acoordinates[1] - coordinatesout[1] + y);
                                        rotatea.setDuration(1000);
                                        translatea.setDuration(1000);
                                        rotateandmovea.addAnimation(rotatea);
                                        rotateandmovea.addAnimation(translatea);
                                        outCard.startAnimation(rotateandmovea);
                                        outCard.setVisibility(View.INVISIBLE);
                                    } else {
                                        deckToFirstLeft();
                                    }
                                    leftDefault.setBackgroundResource(SkinRes.skinRes(9, "up"));
                                    button.setBackgroundResource(SkinRes.skinRes(9, "up"));
                                } else if (leftDefault == GameData.game.getButton("secondPlayerLeft")) {
                                    if (GameData.deck.getDeckCount() <= 1) {
                                        outCard.getLocationOnScreen(coordinatesout);
                                        leftDefault.getLocationOnScreen(acoordinates);
                                        float x = leftDefault.getPivotX() - outCard.getPivotX();
                                        float y = leftDefault.getPivotY() - outCard.getPivotY();
                                        rotatea = new RotateAnimation(0, 90, outCard.getPivotX(), outCard.getPivotY());
                                        translatea = new TranslateAnimation(0, acoordinates[0] - coordinatesout[0] + x, 0, acoordinates[1] - coordinatesout[1] + y);
                                        rotatea.setDuration(1000);
                                        translatea.setDuration(1000);
                                        rotateandmovea.addAnimation(rotatea);
                                        rotateandmovea.addAnimation(translatea);
                                        outCard.startAnimation(rotateandmovea);
                                        outCard.setVisibility(View.INVISIBLE);
                                    } else {
                                        deckToSecondLeft();
                                    }
                                    leftDefault.setBackgroundResource(SkinRes.skinRes(9, "left"));
                                    button.setBackgroundResource(SkinRes.skinRes(9, "left"));
                                } else if (leftDefault == GameData.game.getButton("thirdPlayerLeft")) {
                                    if (GameData.deck.getDeckCount() <= 1) {
                                        outCard.getLocationOnScreen(coordinatesout);
                                        leftDefault.getLocationOnScreen(acoordinates);
                                        float x = leftDefault.getPivotX() - outCard.getPivotX();
                                        float y = leftDefault.getPivotY() - outCard.getPivotY();
                                        rotatea = new RotateAnimation(0, 180, outCard.getPivotX(), outCard.getPivotY());
                                        translatea = new TranslateAnimation(0, acoordinates[0] - coordinatesout[0] + x, 0, acoordinates[1] - coordinatesout[1] + y);
                                        rotatea.setDuration(1000);
                                        translatea.setDuration(1000);
                                        rotateandmovea.addAnimation(rotatea);
                                        rotateandmovea.addAnimation(translatea);
                                        outCard.startAnimation(rotateandmovea);
                                        outCard.setVisibility(View.INVISIBLE);
                                    } else {
                                        deckToThirdLeft();
                                    }
                                    leftDefault.setBackgroundResource(SkinRes.skinRes(9, "down"));
                                    button.setBackgroundResource(SkinRes.skinRes(9, "down"));
                                } else {
                                    if (GameData.deck.getDeckCount() <= 1) {
                                        outCard.getLocationOnScreen(coordinatesout);
                                        leftDefault.getLocationOnScreen(acoordinates);
                                        float x = leftDefault.getPivotX() - outCard.getPivotX();
                                        float y = leftDefault.getPivotY() - outCard.getPivotY();
                                        rotatea = new RotateAnimation(0, -90, outCard.getPivotX(), outCard.getPivotY());
                                        translatea = new TranslateAnimation(0, acoordinates[0] - coordinatesout[0] + x, 0, acoordinates[1] - coordinatesout[1] + y);
                                        rotatea.setDuration(1000);
                                        translatea.setDuration(1000);
                                        rotateandmovea.addAnimation(rotatea);
                                        rotateandmovea.addAnimation(translatea);
                                        outCard.startAnimation(rotateandmovea);
                                        outCard.setVisibility(View.INVISIBLE);
                                    } else {
                                        deckToFourthLeft();
                                    }
                                    leftDefault.setBackgroundResource(SkinRes.skinRes(9, "right"));
                                    button.setBackgroundResource(SkinRes.skinRes(9, "right"));
                                }
                                new CountDownTimer(1000, 1000) {
                                    public void onTick(long millisUntilFinished) {

                                    }

                                    public void onFinish() {
                                        discard.setBackgroundResource(SkinRes.skinRes(theCard.getValue(), "up"));
                                        leftDefault.setVisibility(View.VISIBLE);
                                        if (GameData.deck.getDeckCount() <= 1) {
                                            GameData.game.getButton("deck").setVisibility(View.INVISIBLE);
                                        }

                                    }
                                }.start();
                            }
                            a++;
                        }

                        public void onFinish() {
                        }
                    }.start();
                }
            }
        }.start();
    }
}
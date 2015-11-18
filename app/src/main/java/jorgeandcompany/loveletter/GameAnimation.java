package jorgeandcompany.loveletter;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;

/**
 * Created by Penguins94 on 11/6/2015.
 */
public class GameAnimation {
    private Game thisGame;
    private ImageButton discard, deck, firstPlayerRight, firstPlayerLeft, secondPlayerRight,
            secondPlayerLeft, thirdPlayerRight, thirdPlayerLeft, fourthPlayerRight, fourthPlayerLeft, outCard;
    private boolean isAnimating;
    public GameAnimation (Game aGame) {
        thisGame = aGame;
        isAnimating = false;
        firstPlayerLeft = aGame.getButton("firstPlayerLeft");
        firstPlayerRight = aGame.getButton("firstPlayerRight");
        secondPlayerLeft = aGame.getButton("secondPlayerLeft");
        secondPlayerRight = aGame.getButton("secondPlayerRight");
        thirdPlayerLeft = aGame.getButton("thirdPlayerLeft");
        thirdPlayerRight = aGame.getButton("thirdPlayerRight");
        fourthPlayerLeft = aGame.getButton("fourthPlayerLeft");
        fourthPlayerRight = aGame.getButton("fourthPlayerRight");
        deck = aGame.getButton("deck");
        discard = aGame.getButton("discard");
        outCard = aGame.getButton("outcard");
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
                outCard.setVisibility(firstPlayerLeft.VISIBLE);
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
        int[] cardcoordinates = new int[2];
        int[] deckcoordinates = new int[2];
        Animation translate;
        AnimationSet set;
        Animation rotate;
        player.getLocationOnScreen(cardcoordinates);
        deck.getLocationOnScreen(deckcoordinates);
        translate = new TranslateAnimation(0, cardcoordinates[0] - deckcoordinates[0], 0, cardcoordinates[1] - deckcoordinates[1]);
        rotate = new RotateAnimation(0, rotation, deck.getPivotX(), deck.getPivotY());
        translate.setDuration(1000);
        rotate.setDuration(1000);
        set = new AnimationSet(true);
        set.addAnimation(rotate);
        set.addAnimation(translate);
        deck.startAnimation(set);
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

    public void firstRightToDiscard() {
        int[] cardcoordinates = new int[2];
        int[] deckcoordinates = new int[2];
        discard.getLocationOnScreen(deckcoordinates);
        firstPlayerRight.getLocationOnScreen(cardcoordinates);
        android.view.animation.Animation translate = new TranslateAnimation(0, deckcoordinates[0] - cardcoordinates[0], 0, deckcoordinates[1] - cardcoordinates[1]);
        translate.setDuration(1000);
        firstPlayerRight.startAnimation(translate);
        firstPlayerRight.setVisibility(firstPlayerRight.INVISIBLE);
    };
    public void secondRightToDiscard() {
        int[] cardcoordinates = new int[2];
        int[] deckcoordinates = new int[2];
        discard.getLocationOnScreen(deckcoordinates);
        secondPlayerRight.getLocationOnScreen(cardcoordinates);
        android.view.animation.Animation rotate = new RotateAnimation(0, -90, secondPlayerRight.getPivotX(), secondPlayerRight.getPivotY());
        rotate.setDuration(1000);
        android.view.animation.Animation translateleftrigt = new TranslateAnimation(0, deckcoordinates[0] - cardcoordinates[0], 0, deckcoordinates[1] - cardcoordinates[1]);
        translateleftrigt.setDuration(1000);
        AnimationSet rotateandmove = new AnimationSet(false);
        rotateandmove.addAnimation(rotate);
        rotateandmove.addAnimation(translateleftrigt);
        secondPlayerRight.startAnimation(rotateandmove);
        secondPlayerRight.setVisibility(secondPlayerRight.INVISIBLE);
    };
    public void thirdRightToDiscard() {
        int[] cardcoordinates = new int[2];
        int[] deckcoordinates = new int[2];
        discard.getLocationOnScreen(deckcoordinates);
        thirdPlayerRight.getLocationOnScreen(cardcoordinates);
        android.view.animation.Animation translate = new TranslateAnimation(0, deckcoordinates[0] - cardcoordinates[0], 0, deckcoordinates[1] - cardcoordinates[1]);
        android.view.animation.Animation rotate = new RotateAnimation(0, 180, thirdPlayerRight.getPivotX(), thirdPlayerRight.getPivotY());
        translate.setDuration(1000);
        rotate.setDuration(1000);
        AnimationSet rotateandmove = new AnimationSet(false);
        rotateandmove.addAnimation(rotate);
        rotateandmove.addAnimation(translate);
        thirdPlayerRight.startAnimation(rotateandmove);
        thirdPlayerRight.setVisibility(thirdPlayerRight.INVISIBLE);
    };
    public void fourthRightToDiscard() {
        int[] cardcoordinates = new int[2];
        int[] deckcoordinates = new int[2];
        discard.getLocationOnScreen(deckcoordinates);
        fourthPlayerRight.getLocationOnScreen(cardcoordinates);
        android.view.animation.Animation rotate = new RotateAnimation(0, 90, fourthPlayerRight.getPivotX(), fourthPlayerRight.getPivotY());
        rotate.setDuration(1000);
        android.view.animation.Animation translateleftrigt = new TranslateAnimation(0, deckcoordinates[0] - cardcoordinates[0], 0, deckcoordinates[1] - cardcoordinates[1]);
        translateleftrigt.setDuration(1000);
        AnimationSet rotateandmove = new AnimationSet(false);
        rotateandmove.addAnimation(rotate);
        rotateandmove.addAnimation(translateleftrigt);
        fourthPlayerRight.startAnimation(rotateandmove);
        fourthPlayerRight.setVisibility(fourthPlayerRight.INVISIBLE);
    };

    public void firstLeftToDiscard() {
        int[] cardcoordinates = new int[2];
        int[] deckcoordinates = new int[2];
        discard.getLocationOnScreen(deckcoordinates);
        firstPlayerLeft.getLocationOnScreen(cardcoordinates);
        android.view.animation.Animation translate = new TranslateAnimation(0, deckcoordinates[0] - cardcoordinates[0], 0, deckcoordinates[1] - cardcoordinates[1]);
        translate.setDuration(1000);
        firstPlayerLeft.startAnimation(translate);
        firstPlayerLeft.setVisibility(firstPlayerLeft.INVISIBLE);
    };
    public void secondLeftToDiscard() {
        int[] cardcoordinates = new int[2];
        int[] deckcoordinates = new int[2];
        discard.getLocationOnScreen(deckcoordinates);
        secondPlayerLeft.getLocationOnScreen(cardcoordinates);
        android.view.animation.Animation rotate = new RotateAnimation(0, -90, secondPlayerLeft.getPivotX(), secondPlayerLeft.getPivotY());
        rotate.setDuration(1000);
        android.view.animation.Animation translateleftrigt = new TranslateAnimation(0, deckcoordinates[0] - cardcoordinates[0], 0, deckcoordinates[1] - cardcoordinates[1]);
        translateleftrigt.setDuration(1000);
        AnimationSet rotateandmove = new AnimationSet(false);
        rotateandmove.addAnimation(rotate);
        rotateandmove.addAnimation(translateleftrigt);
        secondPlayerLeft.startAnimation(rotateandmove);
        secondPlayerLeft.setVisibility(secondPlayerLeft.INVISIBLE);
    };
    public void thirdLeftToDiscard() {
        int[] cardcoordinates = new int[2];
        int[] deckcoordinates = new int[2];
        discard.getLocationOnScreen(deckcoordinates);
        thirdPlayerLeft.getLocationOnScreen(cardcoordinates);
        android.view.animation.Animation rotate = new RotateAnimation(0, 180, thirdPlayerLeft.getPivotX(), thirdPlayerLeft.getPivotY());
        android.view.animation.Animation translate = new TranslateAnimation(0, deckcoordinates[0] - cardcoordinates[0], 0, deckcoordinates[1] - cardcoordinates[1]);
        translate.setDuration(1000);
        rotate.setDuration(1000);
        AnimationSet rotateandmove = new AnimationSet(false);
        rotateandmove.addAnimation(rotate);
        rotateandmove.addAnimation(translate);
        thirdPlayerLeft.startAnimation(rotateandmove);
        thirdPlayerLeft.setVisibility(thirdPlayerLeft.INVISIBLE);
    };
    public void fourthLeftToDiscard() {
        int[] cardcoordinates = new int[2];
        int[] deckcoordinates = new int[2];
        discard.getLocationOnScreen(deckcoordinates);
        fourthPlayerLeft.getLocationOnScreen(cardcoordinates);
        android.view.animation.Animation rotate = new RotateAnimation(0, 90, fourthPlayerLeft.getPivotX(), fourthPlayerLeft.getPivotY());
        rotate.setDuration(1000);
        android.view.animation.Animation translateleftrigt = new TranslateAnimation(0, deckcoordinates[0] - cardcoordinates[0], 0, deckcoordinates[1] - cardcoordinates[1]);
        translateleftrigt.setDuration(1000);
        AnimationSet rotateandmove = new AnimationSet(false);
        rotateandmove.addAnimation(rotate);
        rotateandmove.addAnimation(translateleftrigt);
        fourthPlayerLeft.startAnimation(rotateandmove);
        fourthPlayerLeft.setVisibility(fourthPlayerLeft.INVISIBLE);
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
                    final AnimatorSet setRightOut = (AnimatorSet) AnimatorInflater.loadAnimator(thisGame.getApplicationContext(),
                            rightout);
                    setRightOut.setTarget(toFlip);
                    setRightOut.start();
                    a++;
                }
                else if (a == 1) {
                    toFlip.setBackgroundResource(id);
                    final AnimatorSet setLeftIn = (AnimatorSet) AnimatorInflater.loadAnimator(thisGame.getApplicationContext(),
                            leftin);
                    setLeftIn.setTarget(toFlip);
                    setLeftIn.start();
                    a++;
                }
            }

            @Override
            public void onFinish() {
                if (isAnimating()) {
                    changeAnimatingState();
               }
            }
        }.start();
    }
    public void flipCardToBack(final View toFlip) {
        new CountDownTimer(400, 100) {
            int a = 0;
            @Override
            public void onTick(long millisUntilFinished) {
                if (a == 0) {

                    final AnimatorSet setRightOut = (AnimatorSet) AnimatorInflater.loadAnimator(thisGame.getApplicationContext(),
                            R.animator.flip_right_out);
                    setRightOut.setTarget(toFlip);
                    setRightOut.start();
                    a++;
                }
                else if (a ==1) {
                    toFlip.setBackgroundResource(R.drawable.magi_up);
                    final AnimatorSet setLeftIn = (AnimatorSet) AnimatorInflater.loadAnimator(thisGame.getApplicationContext(),
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
    public void cardToDiscardSinglePlayer(Player on, int hand) {
        //left
        int playerNum = on.getPlayerNumber();
        if (hand == 0) {
            if (playerNum == 1) {
                new CountDownTimer(400,100) {
                    int a = 0;
                    @Override
                    public void onTick(long millisUntilFinished) {
                        if (a == 0) {
                            flipCard(firstPlayerLeft, R.drawable.background_trans);
                            a++;
                        }
                    }

                    @Override
                    public void onFinish() {
                        new CountDownTimer(2000,1000) {
                            @Override
                            public void onTick(long millisUntilFinished) {
                                firstLeftToDiscard();
                            }

                            @Override
                            public void onFinish() {
                                if (discard.getVisibility() == View.INVISIBLE) {
                                    discard.setVisibility(View.VISIBLE);
                                }
                            }
                        }.start();
                    }
                }.start();
            }
            else if (playerNum == 2) {
                new CountDownTimer(400,100) {
                    int a = 0;
                    @Override
                    public void onTick(long millisUntilFinished) {
                        if (a == 0) {
                            flipCard(secondPlayerLeft, R.drawable.background_trans);
                            a++;
                        }
                    }

                    @Override
                    public void onFinish() {
                        new CountDownTimer(2000,1000) {
                            @Override
                            public void onTick(long millisUntilFinished) {
                                secondLeftToDiscard();
                            }

                            @Override
                            public void onFinish() {
                                if (discard.getVisibility() == View.INVISIBLE) {
                                    discard.setVisibility(View.VISIBLE);
                                }
                            }
                        }.start();
                    }
                }.start();
            }
            else if (playerNum == 3) {
                new CountDownTimer(400,100) {
                    int a = 0;
                    @Override
                    public void onTick(long millisUntilFinished) {
                        if (a == 0) {
                            flipCard(thirdPlayerLeft, R.drawable.background_trans);
                            a++;
                        }
                    }

                    @Override
                    public void onFinish() {
                        new CountDownTimer(2000,1000) {
                            @Override
                            public void onTick(long millisUntilFinished) {
                                thirdLeftToDiscard();
                            }

                            @Override
                            public void onFinish() {
                                if (discard.getVisibility() == View.INVISIBLE) {
                                    discard.setVisibility(View.VISIBLE);
                                }
                            }
                        }.start();
                    }
                }.start();
            }
            else {
                new CountDownTimer(400,100) {
                    int a = 0;
                    @Override
                    public void onTick(long millisUntilFinished) {
                        if (a == 0) {
                            flipCard(fourthPlayerLeft, R.drawable.background_trans);
                            a++;
                        }
                    }

                    @Override
                    public void onFinish() {
                        new CountDownTimer(2000,1000) {
                            @Override
                            public void onTick(long millisUntilFinished) {
                                fourthLeftToDiscard();
                            }

                            @Override
                            public void onFinish() {
                                if (discard.getVisibility() == View.INVISIBLE) {
                                    discard.setVisibility(View.VISIBLE);
                                }
                            }
                        }.start();;
                    }
                }.start();
            }
        }
        //right
        else {
            if (playerNum == 1) {
                new CountDownTimer(400,100) {
                    int a = 0;
                    @Override
                    public void onTick(long millisUntilFinished) {
                        if (a == 0) {
                            flipCard(firstPlayerRight, R.drawable.background_trans);
                            a++;
                        }
                    }

                    @Override
                    public void onFinish() {
                        new CountDownTimer(2000,1000) {
                            @Override
                            public void onTick(long millisUntilFinished) {
                               firstRightToDiscard();
                            }

                            @Override
                            public void onFinish() {
                                if (discard.getVisibility() == View.INVISIBLE) {
                                    discard.setVisibility(View.VISIBLE);
                                }
                            }
                        }.start();
                    }
                }.start();
            }
            else if (playerNum == 2) {
                new CountDownTimer(400,100) {
                    int a = 0;
                    @Override
                    public void onTick(long millisUntilFinished) {
                        if (a == 0) {
                            flipCard(secondPlayerRight, R.drawable.background_trans);
                            a++;
                        }
                    }

                    @Override
                    public void onFinish() {
                        new CountDownTimer(2000,1000) {
                            @Override
                            public void onTick(long millisUntilFinished) {
                                secondRightToDiscard();
                            }

                            @Override
                            public void onFinish() {
                                if (discard.getVisibility() == View.INVISIBLE) {
                                    discard.setVisibility(View.VISIBLE);
                                }
                            }
                        }.start();
                    }
                }.start();
            }
            else if (playerNum == 3) {
                new CountDownTimer(400,100) {
                    int a = 0;
                    @Override
                    public void onTick(long millisUntilFinished) {
                        if (a == 0) {
                            flipCard(thirdPlayerRight, R.drawable.background_trans);
                            a++;
                        }
                    }

                    @Override
                    public void onFinish() {
                        new CountDownTimer(2000,1000) {
                            @Override
                            public void onTick(long millisUntilFinished) {
                                thirdRightToDiscard();
                            }

                            @Override
                            public void onFinish() {
                                if (discard.getVisibility() == View.INVISIBLE) {
                                    discard.setVisibility(View.VISIBLE);
                                }
                            }
                        }.start();
                    }
                }.start();
            }
            else {
                new CountDownTimer(400,100) {
                    int a = 0;
                    @Override
                    public void onTick(long millisUntilFinished) {
                        if (a == 0) {
                            flipCard(fourthPlayerRight, R.drawable.background_trans);
                            a++;
                        }
                    }

                    @Override
                    public void onFinish() {
                        new CountDownTimer(2000,1000) {
                            @Override
                            public void onTick(long millisUntilFinished) {
                                fourthRightToDiscard();
                            }

                            @Override
                            public void onFinish() {
                                if (discard.getVisibility() == View.INVISIBLE) {
                                    discard.setVisibility(View.VISIBLE);
                                }
                            }
                        }.start();
                    }
                }.start();
            }

        }
    }
    public void cardToDiscardMultiPlayer(final int hand) {
        if (hand == 0) firstLeftToDiscard();
        else firstRightToDiscard();

        new CountDownTimer(1000,1000) {

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                if (discard.getVisibility() == View.INVISIBLE) {
                    discard.setVisibility(View.VISIBLE);
                }
                discard.setBackgroundResource(GameData.PlayerList[GameData.TURN].getCard(hand).getSkinRes(GameData.skinID));
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
                Animation rotateb = new RotateAnimation(0, swap1, b.getPivotX(), b.getPivotY());
                Animation rotatea = new RotateAnimation(0, swap2, a.getPivotX(), a.getPivotY());
                rotatea.setDuration(1000);
                rotateb.setDuration(1000);
                Animation translateb = new TranslateAnimation(0, acoordinates[0] - bcoordinates[0], 0, acoordinates[1] - bcoordinates[1]);
                Animation translatea = new TranslateAnimation(0, bcoordinates[0] - acoordinates[0], 0, bcoordinates[1] - acoordinates[1]);
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
                        AlertDialog.Builder success = new AlertDialog.Builder(GameData.game);
                        success.setCancelable(false);
                        success.setTitle("Card 6 Effect");
                        success.setMessage("Player " + thePlayer.getPlayerNumber() + " traded cards with player " + id + ".");
                        success.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                GameData.game.endOfTurn(thePlayer);
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
                Animation rotateb = new RotateAnimation(0, swap1, b.getPivotX(), b.getPivotY());
                Animation rotatea = new RotateAnimation(0, swap2, a.getPivotX(), a.getPivotY());
                rotatea.setDuration(1000);
                rotateb.setDuration(1000);
                Animation translateb = new TranslateAnimation(0, acoordinates[0] - bcoordinates[0], 0, acoordinates[1] - bcoordinates[1]);
                Animation translatea = new TranslateAnimation(0, bcoordinates[0] - acoordinates[0], 0, bcoordinates[1] - acoordinates[1]);
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

    public void discardAnimation (final ImageButton button, final ImageButton leftDefault) {
        final int[] bcoordinates = new int[2];
        final int[] ccoordinates = new int[2];
        final int[] acoordinates = new int[2];
        final int[] discardcoordinates = new int [2];
        final Animation rotateb;
        final Animation rotatea;
        final Animation translateb;
        final Animation translatea;
        final AnimationSet rotateandmovea = new AnimationSet(false), rotateandmoveb = new AnimationSet(false);
        if (GameData.deck.getDeckCount() < 2) {
            GameData.game.getButton("outcard").getLocationOnScreen(acoordinates);
        }
        else {
            GameData.game.getButton("deck").getLocationOnScreen(acoordinates);
        }
        GameData.game.getButton("discard").getLocationOnScreen(discardcoordinates);
        button.getLocationOnScreen(bcoordinates);
        leftDefault.getLocationOnScreen(ccoordinates);
        if (button == GameData.game.getButton("firstPlayerLeft") || button == GameData.game.getButton("firstPlayerRight")) {
            rotateb = new RotateAnimation(0, 0, button.getPivotX(), button.getPivotY());
            rotatea = new RotateAnimation(0, 0, GameData.game.getButton("deck").getPivotX(), GameData.game.getButton("deck").getPivotY());
        } else if (button == GameData.game.getButton("secondPlayerLeft") || button == GameData.game.getButton("secondPlayerRight")) {
            rotateb = new RotateAnimation(0, -90, button.getPivotX(), button.getPivotY());
            rotatea = new RotateAnimation(0, 90, GameData.game.getButton("deck").getPivotX(), GameData.game.getButton("deck").getPivotY());
        } else if (button == GameData.game.getButton("thirdPlayerLeft") || button == GameData.game.getButton("thirdPlayerRight")) {
            rotateb = new RotateAnimation(0, 180, button.getPivotX(), button.getPivotY());
            rotatea = new RotateAnimation(0, 180, GameData.game.getButton("deck").getPivotX(), GameData.game.getButton("deck").getPivotY());
        } else {
            rotateb = new RotateAnimation(0, 90, button.getPivotX(), button.getPivotY());
            rotatea = new RotateAnimation(0, -90, GameData.game.getButton("deck").getPivotX(), GameData.game.getButton("deck").getPivotY());
        }

        translateb = new TranslateAnimation(0, discardcoordinates[0] - bcoordinates[0], 0, discardcoordinates[1] - bcoordinates[1]);
        translatea = new TranslateAnimation(0, ccoordinates[0] - acoordinates[0], 0, ccoordinates[1] - acoordinates[1]);
        new CountDownTimer(2000, 1000) {
            public void onTick(long millisUntilFinished) {
                rotatea.setDuration(1000);
                rotateb.setDuration(1000);
                translateb.setDuration(1000);
                translatea.setDuration(1000);
                rotateandmovea.addAnimation(rotatea);
                rotateandmovea.addAnimation(translatea);
                rotateandmoveb.addAnimation(rotateb);
                rotateandmoveb.addAnimation(translateb);
                button.startAnimation(rotateandmoveb);
                button.setVisibility(View.INVISIBLE);
            }

            public void onFinish() {
                new CountDownTimer(2000, 1000) {
                    public void onTick(long millisUntilFinished) {
                        GameData.game.getButton("deck").startAnimation(rotateandmovea);
                        new CountDownTimer(1000, 1000) {
                            public void onTick(long millisUntilFinished) {

                            }

                            public void onFinish() {
                                leftDefault.setVisibility(View.VISIBLE);
                                if (GameData.deck.getDeckCount() == 0) {
                                    GameData.game.getButton("deck").setVisibility(View.INVISIBLE);
                                }
                            }
                        }.start();
                    }

                    public void onFinish() {
                    }
                }.start();

            }
        }.start();
    }
}

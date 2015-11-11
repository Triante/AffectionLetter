package jorgeandcompany.loveletter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
<<<<<<< HEAD
=======
import java.util.NoSuchElementException;
import java.util.Timer;
import java.util.TimerTask;
>>>>>>> 88841dfa837c8589c7852f8b1c1c53b5045c221f


public class Game extends ActionBarActivity {
    private ImageButton discard, deck, firstPlayerRight, firstPlayerLeft, secondPlayerRight,
            secondPlayerLeft, thirdPlayerRight, thirdPlayerLeft, fourthPlayerRight, fourthPlayerLeft, outCard;
    private Button bPlay, bCancel;
    private ImageView expandedCardImage, backgroundOnPaused;
    private TextView cardDescriptionText, betaView;
    private boolean isSingleGame;
    private static Music gameMusic = null;
    private GameAnimation theAnimation = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        isSingleGame = getIntent().getBooleanExtra("single", false);
        discard = (ImageButton) findViewById(R.id.discard);
        deck = (ImageButton) findViewById(R.id.deck);
        firstPlayerLeft = (ImageButton) findViewById(R.id.player1left);
        firstPlayerRight = (ImageButton) findViewById(R.id.player1right);
        secondPlayerRight = (ImageButton) findViewById(R.id.player2left);
        secondPlayerLeft = (ImageButton) findViewById(R.id.player2right);
        thirdPlayerLeft = (ImageButton) findViewById(R.id.player3left);
        thirdPlayerRight = (ImageButton) findViewById(R.id.player3right);
        fourthPlayerLeft = (ImageButton) findViewById(R.id.player4right);
        fourthPlayerRight = (ImageButton) findViewById(R.id.player4left);
        outCard = (ImageButton) findViewById(R.id.outCard);
        bPlay = (Button) findViewById(R.id.bPlay);
        bCancel = (Button) findViewById(R.id.bCancel);
        cardDescriptionText = (TextView) findViewById(R.id.card_description_text);
        expandedCardImage = (ImageView) findViewById(R.id.expanded_image);
        bCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageZoomToClose();
            }
        });
        backgroundOnPaused = (ImageView) findViewById(R.id.backGround);
        theAnimation = new GameAnimation(this);
        //beta view
        betaView = (TextView) findViewById(R.id.beta_card_data);

        try{
            startMusic();
        }
        catch (Exception e) {

        }
        GameData.setContextMenu(this);
        GameData.setPlayerMode(isSingleGame);
        GameData.newGame();
        handOutCards(GameData.TURN);


<<<<<<< HEAD
//        Timer toChangeBeta = new Timer();
//        toChangeBeta.schedule(new TimerTask() {
//            Runnable beta = new Runnable() {
//                @Override
//                public void run() {
//                    setBetaStuff();
//                }
//            };
//            @Override
//            public void run() {
//                Game.this.runOnUiThread(beta);
//            }
//        }, 0, 200);
    }

    public ImageButton getButton (String imageType) {
        if (imageType.equalsIgnoreCase("firstplayerleft")) {
            return firstPlayerLeft;
        }
        else if (imageType.equalsIgnoreCase("firstplayerright")) {
            return firstPlayerRight;
        }
        else if (imageType.equalsIgnoreCase("secondplayerleft")) {
            return secondPlayerLeft;
        }
        else if (imageType.equalsIgnoreCase("secondplayerright")) {
            return secondPlayerRight;
        }
        else if (imageType.equalsIgnoreCase("thirdplayerleft")) {
            return thirdPlayerLeft;
        }
        else if (imageType.equalsIgnoreCase("thirdplayerright")) {
            return thirdPlayerRight;
        }
        else if (imageType.equalsIgnoreCase("fourthplayerleft")) {
            return fourthPlayerLeft;
        }
        else if (imageType.equalsIgnoreCase("fourthplayerright")) {
            return fourthPlayerRight;
        }
        else if (imageType.equalsIgnoreCase("deck")) {
            return deck;
        }
        else if (imageType.equalsIgnoreCase("discard")) {
            return discard;
        }
        else {
            return outCard;
        }

    }
    public void startMusic () {
        gameMusic.setPlayer(new MediaPlayer().create(getApplication(), R.raw.magi_game));
        if (gameMusic.isMute()) {
            gameMusic.setVolume(0,0);
        }
        Thread newThread = new Thread(gameMusic);
        newThread.start();
=======
        Timer toChangeBeta = new Timer();
        toChangeBeta.schedule(new TimerTask() {
            Runnable beta = new Runnable() {
                @Override
                public void run() {
                    setBetaStuff();
                }
            };
            @Override
            public void run() {
                Game.this.runOnUiThread(beta);
            }
        }, 0, 200);
>>>>>>> 88841dfa837c8589c7852f8b1c1c53b5045c221f
    }


    public void multiPlayerGame() {
        int turn = GameData.TURN;;
        final Player on = GameData.PlayerList[turn];
        //remove card 4 effect if active
        if (on.isProtected()) {
            on.setProtected(false);
        }
        if (!theAnimation.isAnimating()) {
            theAnimation.changeAnimatingState();
        }
        CountDownTimer toMove = new CountDownTimer(2000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (on.hasLeftCard()) {
                    theAnimation.deckToRight(1);
                }
                else {
                    theAnimation.deckToLeft(1);
                }
                on.drawCard();
            }

            @Override
            public void onFinish() {
                if (GameData.getDeckCount() == 0) {
                    deck.setVisibility(View.INVISIBLE);
                }
                if (on.isHuman()) {
                    playerMove(on);
                }
                else computerMove(on);

            }
        };
        toMove.start();


        //player or ai does turn
//        if (turn == 1) {  // player
//            playerMove(on);
//        }
//        else { //ai
//            playerMove(on);
//        }
    }
    public void singlePlayerGame() {
        int turn = GameData.TURN;;
        final Player on = GameData.PlayerList[turn];
        //remove card 4 effect if active
        if (on.isProtected()) {
            on.setProtected(false);
        }
        if (!theAnimation.isAnimating()) {
            theAnimation.changeAnimatingState();
        }
        CountDownTimer toMove = new CountDownTimer(2000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (on.hasLeftCard()) {
                    theAnimation.deckToRight(on.getPlayerNumber());
                }
                else {
                    theAnimation.deckToLeft(on.getPlayerNumber());
                }
                on.drawCard();
            }

            @Override
            public void onFinish() {
                if (GameData.getDeckCount() == 0) {
                    deck.setVisibility(View.INVISIBLE);
                }
                if (on.isHuman()) {
                    playerMove(on);
                    theAnimation.changeAnimatingState();
                }
                else computerMove(on);

            }
        };
        toMove.start();


    }
    public void nextTurn() {
        if (isSingleGame) singlePlayerGame();
        else multiPlayerGame();
    }
    private void playerMove(final Player on) {
        final ImageButton left = firstPlayerLeft;
        final int drawable1 = on.getCard(0).getSkinRes(GameData.skinID);
        final int drawable2 = on.getCard(1).getSkinRes(GameData.skinID);
        theAnimation.flipCard(left, drawable1);
        final ImageButton right = firstPlayerRight;
        left.setClickable(true);
        theAnimation.flipCard(right, drawable2);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageZoomToOpen(on, 0, left);
            }
        });
        right.setClickable(true);
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageZoomToOpen(on, 1, right);
            }
        });
    }
    private void computerMove(final Player on) {
        on.playCard(0);
    }
    //decides the end of games too
    public void endOfTurn(final Player on) {
        if (!theAnimation.isAnimating()) {
            theAnimation.changeAnimatingState();
        }
        CountDownTimer toEnd = new CountDownTimer(2000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                firstPlayerLeft.setClickable(false);
                firstPlayerLeft.setOnClickListener(null);
                firstPlayerRight.setClickable(false);
                firstPlayerRight.setOnClickListener(null);
                secondPlayerLeft.setClickable(false);
                secondPlayerLeft.setOnClickListener(null);
                secondPlayerRight.setClickable(false);
                secondPlayerRight.setOnClickListener(null);
                thirdPlayerLeft.setClickable(false);
                thirdPlayerLeft.setOnClickListener(null);
                thirdPlayerRight.setClickable(false);
                thirdPlayerRight.setOnClickListener(null);
                fourthPlayerLeft.setClickable(false);
                fourthPlayerLeft.setOnClickListener(null);
                fourthPlayerRight.setClickable(false);
                fourthPlayerRight.setOnClickListener(null);
                if (GameData.getDeckCount() == 1) {
                    ImageButton deckDummy = (ImageButton) findViewById(R.id.deckDummy);
                    deckDummy.setVisibility(View.INVISIBLE);
                }
                GameData.nextTurn();
            }
            @Override
            public void onFinish() {
               if (GameData.FINISH_GAME) {
                   while (GameData.PlayerList[GameData.TURN].isOut()) {
                       GameData.nextTurn();
                   }
                   GameData.Score[GameData.TURN - 1]++;
                   if (GameData.Score[GameData.TURN - 1] == 7) {
                       GameData.GAME_COMPLETE = true;
                   }
                   AlertDialog.Builder nextPlayerReady = new AlertDialog.Builder(Game.this);
                   nextPlayerReady.setTitle("END");
                   nextPlayerReady.setMessage("Player " + GameData.TURN + " has won!\n" +
                           "Current Score:\n" +
                           "Player 1: " + GameData.Score[0]+ "\n" +
                           "Player 2: " + GameData.Score[1]+ "\n" +
                           "Player 3: " + GameData.Score[2]+ "\n" +
                           "Player 4: " + GameData.Score[3]+ "\n");
                   DialogInterface.OnClickListener ok = new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialog, int which) {
                           if (GameData.GAME_COMPLETE) {
                               endOfGame(GameData.TURN);
                           }
                           else {
                               GameData.newRound();
                               handOutCards(GameData.TURN);
                           }
                       }
                   };
                   nextPlayerReady.setPositiveButton("YAY", ok);
                   nextPlayerReady.setCancelable(false);
                   nextPlayerReady.show();
               }
               else if (GameData.getDeckCount() == 0) {
                   ArrayList<Integer> index = new ArrayList<Integer>(4);
                   int maxValue = 0;
                   int maxTotal = 0;
                   for(int i = 1; i < GameData.PlayerList.length; i++)
                   {
                       if(!(GameData.PlayerList[i].isOut()) && GameData.PlayerList[i].getCard().getValue() > maxValue) {
                           index = new ArrayList<Integer>(4);
                           index.add(GameData.PlayerList[i].getPlayerNumber());
                           maxValue = GameData.PlayerList[i].getCard().getValue();
                           maxTotal = GameData.PlayerList[i].getTotal();
                       }
                       else if(!(GameData.PlayerList[i].isOut()) && GameData.PlayerList[i].getCard().getValue() == maxValue && GameData.PlayerList[i].getTotal() > maxTotal) {
                           index = new ArrayList<Integer>(4);
                           index.add(GameData.PlayerList[i].getPlayerNumber());
                           maxValue = GameData.PlayerList[i].getCard().getValue();
                           maxTotal = GameData.PlayerList[i].getTotal();
                       }
                       else if(!(GameData.PlayerList[i].isOut()) && GameData.PlayerList[i].getCard().getValue() == maxValue && GameData.PlayerList[i].getTotal() == maxTotal) {
                           index.add(GameData.PlayerList[i].getPlayerNumber());
                           maxValue = GameData.PlayerList[i].getCard().getValue();
                           maxTotal = GameData.PlayerList[i].getTotal();
                       }

                   }
                   String winners = "";
                   for(int x : index)
                   {
                       winners += " " + x + " and";
                       GameData.Score[x-1]++;
                       if (GameData.Score[x - 1] == 7) {
                           GameData.GAME_COMPLETE = true;
                       }
                   }
                   winners = winners.substring(0, winners.length() - 4);
                   AlertDialog.Builder end = new AlertDialog.Builder(Game.this);
                   end.setTitle("Game");
                   end.setMessage("Deck out of cards. Game is over. Player(s)" + winners + " win!\n" + "Current Score:\n" +
                           "Player 1: " + GameData.Score[0]+ "\n" +
                           "Player 2: " + GameData.Score[1]+ "\n" +
                           "Player 3: " + GameData.Score[2]+ "\n" +
                           "Player 4: " + GameData.Score[3]+ "\n");
                   end.setCancelable(false);
                   end.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialog, int which) {
                           if (GameData.GAME_COMPLETE) {
                               if (GameData.Score[0] == 7) endOfGame(1);
                               else if (GameData.Score[1] == 7) endOfGame(2);
                               else if (GameData.Score[2] == 7) endOfGame(3);
                               else endOfGame(4);
                           }
                           else
                           {
                               GameData.newRound();
                               handOutCards(GameData.TURN);
                           }
                       }
                   });
                   end.show();
               }
                else {
                   while (GameData.PlayerList[GameData.TURN].isOut()) {
                       GameData.nextTurn();
                   }
                   AlertDialog.Builder nextPlayerReady = new AlertDialog.Builder(Game.this);
                   String message;
                   if (isSingleGame) {
                       if (GameData.TURN == 1) {
                           message = "You are up.\nSelect OK when ready.";
                       }
                       else {
                           message = "Player " + GameData.TURN + " is up.\nSelect OK when ready.";
                       }
                   }
                   else {
                       message = "Player " + GameData.TURN + " is up.\nPlease pass to player and select OK when ready.";
                   }
                   nextPlayerReady.setTitle("Next Player");
                   nextPlayerReady.setMessage(message);
                   DialogInterface.OnClickListener ok = new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialog, int which) {
                           if (!isSingleGame) repaint();
                           else repaintSingle();
                           nextTurn();
                       }
                   };
                   nextPlayerReady.setPositiveButton("OK", ok);
                   nextPlayerReady.setCancelable(false);
                   nextPlayerReady.show();
               }
            }
        };
        toEnd.start();
     }
    private void endOfGame(int winner) {
        AlertDialog.Builder win = new AlertDialog.Builder(this);
        win.setCancelable(false);
        win.setTitle("Game Over");
        win.setMessage("The winner is player " + winner + "!\n" +
                "Play again?");
        win.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                clearTable();
                GameData.newGame();
                handOutCards(GameData.TURN);
            }
        });
        win.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                gameMusic.stop();
                finish();
            }
        });
        win.show();
    }

    //animations
<<<<<<< HEAD

=======
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

    private void deckToFirstRight() {
        dealCard(0, firstPlayerRight);
        new CountDownTimer(1000, 1000) {
            public void onTick(long millisUntilFinished) {}

            public void onFinish() {
                firstPlayerRight.setVisibility(firstPlayerRight.VISIBLE);
            }
        }.start();

    }
    private void deckToSecondRight() {
        dealCard(90, secondPlayerRight);
        new CountDownTimer(1000, 1000) {
            public void onTick(long millisUntilFinished) {}

            public void onFinish() {
                secondPlayerRight.setVisibility(secondPlayerRight.VISIBLE);
            }
        }.start();


    }
    private void deckToThirdRight() {
        dealCard(180, thirdPlayerRight);
        new CountDownTimer(1000, 1000) {
            public void onTick(long millisUntilFinished) {}

            public void onFinish() {
                thirdPlayerRight.setVisibility(thirdPlayerRight.VISIBLE);
            }
        }.start();


    }
    private void deckToFourthRight() {
        dealCard(-90, fourthPlayerRight);
        new CountDownTimer(1000, 1000) {
            public void onTick(long millisUntilFinished) {}

            public void onFinish() {
                fourthPlayerRight.setVisibility(fourthPlayerRight.VISIBLE);
            }
        }.start();

    }

    private void deckToFirstLeft() {
        dealCard(0, firstPlayerLeft);
        new CountDownTimer(1000, 1000) {
            public void onTick(long millisUntilFinished) {}

            public void onFinish() {
                firstPlayerLeft.setVisibility(firstPlayerLeft.VISIBLE);
            }
        }.start();

    }
    private void deckToSecondLeft() {
        dealCard(90, secondPlayerLeft);
        new CountDownTimer(1000, 1000) {
            public void onTick(long millisUntilFinished) {}

            public void onFinish() {
                secondPlayerLeft.setVisibility(secondPlayerLeft.VISIBLE);
            }
        }.start();


    }
    private void deckToThirdLeft() {
        dealCard(180, thirdPlayerLeft);
        new CountDownTimer(1000, 1000) {
            public void onTick(long millisUntilFinished) {}

            public void onFinish() {
                thirdPlayerLeft.setVisibility(thirdPlayerLeft.VISIBLE);
            }
        }.start();


    }
    private void deckToFourthLeft() {
        dealCard(-90, fourthPlayerLeft);
        new CountDownTimer(1000, 1000) {
            public void onTick(long millisUntilFinished) {}

            public void onFinish() {
                fourthPlayerLeft.setVisibility(fourthPlayerLeft.VISIBLE);
            }
        }.start();

    }

    private void firstRightToDeck() {
        int[] cardcoordinates = new int[2];
        int[] deckcoordinates = new int[2];
        deck.getLocationOnScreen(deckcoordinates);
        firstPlayerRight.getLocationOnScreen(cardcoordinates);
        Animation translate = new TranslateAnimation(0, deckcoordinates[0] - cardcoordinates[0], 0, deckcoordinates[1] - cardcoordinates[1]);
        translate.setDuration(1000);
        firstPlayerRight.startAnimation(translate);
        firstPlayerRight.setVisibility(firstPlayerRight.INVISIBLE);
    }
    private void secondRightToDeck() {
        int[] cardcoordinates = new int[2];
        int[] deckcoordinates = new int[2];
        deck.getLocationOnScreen(deckcoordinates);
        secondPlayerRight.getLocationOnScreen(cardcoordinates);
        Animation rotate = new RotateAnimation(0, -90, secondPlayerRight.getPivotX(), secondPlayerRight.getPivotY());
        rotate.setDuration(1000);
        Animation translateleftrigt = new TranslateAnimation(0, deckcoordinates[0] - cardcoordinates[0], 0, deckcoordinates[1] - cardcoordinates[1]);
        translateleftrigt.setDuration(1000);
        AnimationSet rotateandmove = new AnimationSet(false);
        rotateandmove.addAnimation(rotate);
        rotateandmove.addAnimation(translateleftrigt);
        secondPlayerRight.startAnimation(rotateandmove);
        secondPlayerRight.setVisibility(secondPlayerRight.INVISIBLE);
    }
    private void thirdRightToDeck() {
        int[] cardcoordinates = new int[2];
        int[] deckcoordinates = new int[2];
        deck.getLocationOnScreen(deckcoordinates);
        thirdPlayerRight.getLocationOnScreen(cardcoordinates);
        Animation translate = new TranslateAnimation(0, deckcoordinates[0] - cardcoordinates[0], 0, deckcoordinates[1] - cardcoordinates[1]);
        translate.setDuration(1000);
        thirdPlayerRight.startAnimation(translate);
        thirdPlayerRight.setVisibility(thirdPlayerRight.INVISIBLE);
    }
    private void fourthRightToDeck() {
        int[] cardcoordinates = new int[2];
        int[] deckcoordinates = new int[2];
        deck.getLocationOnScreen(deckcoordinates);
        fourthPlayerRight.getLocationOnScreen(cardcoordinates);
        Animation rotate = new RotateAnimation(0, 90, fourthPlayerRight.getPivotX(), fourthPlayerRight.getPivotY());
        rotate.setDuration(1000);
        Animation translateleftrigt = new TranslateAnimation(0, deckcoordinates[0] - cardcoordinates[0], 0, deckcoordinates[1] - cardcoordinates[1]);
        translateleftrigt.setDuration(1000);
        AnimationSet rotateandmove = new AnimationSet(false);
        rotateandmove.addAnimation(rotate);
        rotateandmove.addAnimation(translateleftrigt);
        fourthPlayerRight.startAnimation(rotateandmove);
        fourthPlayerRight.setVisibility(fourthPlayerRight.INVISIBLE);
    }

    private void firstLeftToDeck() {
        int[] cardcoordinates = new int[2];
        int[] deckcoordinates = new int[2];
        deck.getLocationOnScreen(deckcoordinates);
        firstPlayerLeft.getLocationOnScreen(cardcoordinates);
        Animation translate = new TranslateAnimation(0, deckcoordinates[0] - cardcoordinates[0], 0, deckcoordinates[1] - cardcoordinates[1]);
        translate.setDuration(1000);
        firstPlayerLeft.startAnimation(translate);
        firstPlayerLeft.setVisibility(firstPlayerLeft.INVISIBLE);
    }
    private void secondLeftToDeck() {
        int[] cardcoordinates = new int[2];
        int[] deckcoordinates = new int[2];
        deck.getLocationOnScreen(deckcoordinates);
        secondPlayerLeft.getLocationOnScreen(cardcoordinates);
        Animation rotate = new RotateAnimation(0, -90, secondPlayerLeft.getPivotX(), secondPlayerLeft.getPivotY());
        rotate.setDuration(1000);
        Animation translateleftrigt = new TranslateAnimation(0, deckcoordinates[0] - cardcoordinates[0], 0, deckcoordinates[1] - cardcoordinates[1]);
        translateleftrigt.setDuration(1000);
        AnimationSet rotateandmove = new AnimationSet(false);
        rotateandmove.addAnimation(rotate);
        rotateandmove.addAnimation(translateleftrigt);
        secondPlayerLeft.startAnimation(rotateandmove);
        secondPlayerLeft.setVisibility(secondPlayerLeft.INVISIBLE);
    }
    private void thirdLeftToDeck() {
        int[] cardcoordinates = new int[2];
        int[] deckcoordinates = new int[2];
        deck.getLocationOnScreen(deckcoordinates);
        thirdPlayerLeft.getLocationOnScreen(cardcoordinates);
        Animation translate = new TranslateAnimation(0, deckcoordinates[0] - cardcoordinates[0], 0, deckcoordinates[1] - cardcoordinates[1]);
        translate.setDuration(1000);
        thirdPlayerLeft.startAnimation(translate);
        thirdPlayerLeft.setVisibility(thirdPlayerLeft.INVISIBLE);
    }
    private void fourthLeftToDeck() {
        int[] cardcoordinates = new int[2];
        int[] deckcoordinates = new int[2];
        deck.getLocationOnScreen(deckcoordinates);
        fourthPlayerLeft.getLocationOnScreen(cardcoordinates);
        Animation rotate = new RotateAnimation(0, 90, fourthPlayerLeft.getPivotX(), fourthPlayerLeft.getPivotY());
        rotate.setDuration(1000);
        Animation translateleftrigt = new TranslateAnimation(0, deckcoordinates[0] - cardcoordinates[0], 0, deckcoordinates[1] - cardcoordinates[1]);
        translateleftrigt.setDuration(1000);
        AnimationSet rotateandmove = new AnimationSet(false);
        rotateandmove.addAnimation(rotate);
        rotateandmove.addAnimation(translateleftrigt);
        fourthPlayerLeft.startAnimation(rotateandmove);
        fourthPlayerLeft.setVisibility(fourthPlayerLeft.INVISIBLE);
    }

    private void deckToOutCard() {
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

    //currently just does animation, from back to front.
    private void flipCard(final View toFlip, final int id) {

        new CountDownTimer(300, 100) {
            int a = 0;
            @Override
            public void onTick(long millisUntilFinished) {
                if (a == 0) {
                    final AnimatorSet setRightOut = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(),
                            R.animator.flip_right_out);
                    setRightOut.setTarget(toFlip);
                    setRightOut.start();
                    a++;
                }
                else if (a == 1) {
                    toFlip.setBackgroundResource(id);
                    final AnimatorSet setLeftIn = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(),
                            R.animator.flight_left_in);
                    setLeftIn.setTarget(toFlip);
                    setLeftIn.start();
                }
            }

            @Override
            public void onFinish() {

            }
        }.start();
    }
    //front to back
    private void flipCardToBack(final View toFlip) {
        new CountDownTimer(300, 100) {
            int a = 0;
            @Override
            public void onTick(long millisUntilFinished) {
                if (a == 0) {

                    final AnimatorSet setRightOut = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(),
                            R.animator.flip_right_out);
                    setRightOut.setTarget(toFlip);
                    setRightOut.start();
                    a++;
                }
                else if (a ==1) {
                    toFlip.setBackgroundResource(R.drawable.magi_up);
                    final AnimatorSet setLeftIn = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(),
                            R.animator.flight_left_in);
                    setLeftIn.setTarget(toFlip);
                    setLeftIn.start();
                }
            }

            @Override
            public void onFinish() {

            }
        }.start();
    }

    public void cardToCenterSinglePlayer(Player on, int hand) {
        //left
        int playerNum = on.getPlayerNumber();
        if (hand == 0) {
            if (playerNum == 1) {
                firstLeftToDeck();
            }
            else if (playerNum == 2) {
                secondLeftToDeck();
            }
            else if (playerNum == 3) {
                thirdLeftToDeck();
            }
            else {
                fourthLeftToDeck();
            }
        }
        //right
        else {
            if (playerNum == 1) {
                firstRightToDeck();
            }
            else if (playerNum == 2) {
                secondRightToDeck();
            }
            else if (playerNum == 3) {
                thirdRightToDeck();
            }
            else {
                fourthRightToDeck();
            }
        }
    }
    private void cardToCenterMultiPlayer(final int hand) {
        if (hand == 0) firstLeftToDeck();
        else firstRightToDeck();
    }
>>>>>>> 88841dfa837c8589c7852f8b1c1c53b5045c221f

    //has method to play card. Ends a turn.
    private void imageZoomToOpen(final Player on, final int hand, final View toFlip) {
        Animation zoomOutImage = AnimationUtils.loadAnimation(this, R.anim.anim_scale_up);
        Animation zoomOutImage1 = AnimationUtils.loadAnimation(this, R.anim.anim_scale_up);
        Animation zoomOutImage2 = AnimationUtils.loadAnimation(this, R.anim.anim_scale_up);
        Animation zoomOutImage3 = AnimationUtils.loadAnimation(this, R.anim.anim_scale_up);
        backgroundOnPaused.setVisibility(View.VISIBLE);
        expandedCardImage.setImageResource(on.getCard(hand).getSkinRes(GameData.skinID));
        expandedCardImage.startAnimation(zoomOutImage);
        bPlay.startAnimation(zoomOutImage1);
        bCancel.startAnimation(zoomOutImage2);
        cardDescriptionText.startAnimation(zoomOutImage3);
        expandedCardImage.setVisibility(View.VISIBLE);
        bPlay.setVisibility(View.VISIBLE);
        bPlay.setClickable(true);
        bPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (on.hasSeven()) {
                    if (on.getCard(hand).getValue() == 5 || on.getCard(hand).getValue() == 6) {
                        cardSevenError();
                    }
                    else {
                        playCard(on, hand);
                    }
                }
                else {
                    playCard(on, hand);
                    if (!theAnimation.isAnimating()) {
                        theAnimation.changeAnimatingState();
                    }
                }
            }
        });
        bCancel.setVisibility(View.VISIBLE);
        bCancel.setClickable(true);
        cardDescriptionText.setText(on.getCard(hand).getDescription(this));
        cardDescriptionText.setVisibility(View.VISIBLE);

    }
    private void imageZoomToClose() {
        bPlay.setClickable(false);
        bCancel.setClickable(false);
        Animation zoomOut = AnimationUtils.loadAnimation(this, R.anim.anim_scale_down);
        Animation zoomOut1 = AnimationUtils.loadAnimation(this, R.anim.anim_scale_down);
        Animation zoomOut2 = AnimationUtils.loadAnimation(this, R.anim.anim_scale_down);
        Animation zoomOut3 = AnimationUtils.loadAnimation(this, R.anim.anim_scale_down);
        backgroundOnPaused.setVisibility(View.INVISIBLE);
        expandedCardImage.startAnimation(zoomOut);
        bPlay.startAnimation(zoomOut1);
        bCancel.startAnimation(zoomOut2);
        cardDescriptionText.startAnimation(zoomOut3);
        expandedCardImage.setVisibility(View.INVISIBLE);
        bPlay.setVisibility(View.INVISIBLE);
        bCancel.setVisibility(View.INVISIBLE);
        cardDescriptionText.setVisibility(View.INVISIBLE);
    }
    private void handOutCards(final int firstPlayer) {
        if (!theAnimation.isAnimating()) {
            theAnimation.changeAnimatingState();
        }
        new CountDownTimer(7000, 1000) {
            int a = -1;
            public void onTick(long millisUntilFinished) {
                if (a == 0) {
                    deckToOutCard();
                    a = firstPlayer;
                }
                else if (a == 1) {
                    a = 2;
                    theAnimation.deckToFirstLeft();
                }
                else if (a == 2) {
                    a = 3;
                    theAnimation.deckToSecondLeft();
                }
                else if (a == 3) {
                    a = 4;
                    theAnimation.deckToThirdLeft();
                }
                else if (a == 4) {
                    a = 1;
                    theAnimation.deckToFourthLeft();
                }
                else {
                    a = 0;
                }

            }

            public void onFinish() {
                AlertDialog.Builder preGame = new AlertDialog.Builder(Game.this);
                String mes;
                if (isSingleGame) mes = "Player " + GameData.TURN + " is up. Select OK when ready.";
                else  mes = "Player " + GameData.TURN + " is the first to gop.\nPlease pass to player and select OK when ready.";
                preGame.setMessage(mes);
                DialogInterface.OnClickListener ok = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        nextTurn();
                    }
                };
                preGame.setPositiveButton("OK", ok);
                preGame.setCancelable(false);
                preGame.show();
            }
        }.start();
    }



    //other methods
    private void repaint() {
        int turn = GameData.TURN;
        //person who turn is now is set to main view. Scenario: turn = 1 therefore redraw player 1 to center.
        if (GameData.PlayerList[turn].hasLeftCard()) {
            firstPlayerLeft.setVisibility(View.VISIBLE);
            firstPlayerRight.setVisibility(View.INVISIBLE);
        }
        else if (GameData.PlayerList[turn].hasRightCard()){
            firstPlayerLeft.setVisibility(View.INVISIBLE);
            firstPlayerRight.setVisibility(View.VISIBLE);
        }
        else {
            firstPlayerLeft.setVisibility(View.INVISIBLE);
            firstPlayerRight.setVisibility(View.INVISIBLE);
        }
        turn++;
        if (turn == 5) {
            turn = 1;
        }
        //set next players location, scn: turn 1 therefore redraw player 2 to left side.
        if (GameData.PlayerList[turn].hasLeftCard()) {
            secondPlayerLeft.setVisibility(View.VISIBLE);
            secondPlayerRight.setVisibility(View.INVISIBLE);
        }
        else if (GameData.PlayerList[turn].hasRightCard()){
            secondPlayerLeft.setVisibility(View.INVISIBLE);
            secondPlayerRight.setVisibility(View.VISIBLE);
        }
        else {
            secondPlayerLeft.setVisibility(View.INVISIBLE);
            secondPlayerRight.setVisibility(View.INVISIBLE);
        }
        turn++;
        if (turn == 5) {
            turn = 1;
        }
        //set next players location, scn: turn 1 therefore redraw player 3 to top side.
        if (GameData.PlayerList[turn].hasLeftCard()) {
            thirdPlayerLeft.setVisibility(View.VISIBLE);
            thirdPlayerRight.setVisibility(View.INVISIBLE);
        }
        else if (GameData.PlayerList[turn].hasRightCard()){
            thirdPlayerLeft.setVisibility(View.INVISIBLE);
            thirdPlayerRight.setVisibility(View.VISIBLE);
        }
        else {
            thirdPlayerLeft.setVisibility(View.INVISIBLE);
            thirdPlayerRight.setVisibility(View.INVISIBLE);
        }
        turn++;
        if (turn == 5) {
            turn = 1;
        }
        //set next players location, scn: turn 1 therefore redraw player 4 to right side.
        if (GameData.PlayerList[turn].hasLeftCard()) {
            fourthPlayerLeft.setVisibility(View.VISIBLE);
            fourthPlayerRight.setVisibility(View.INVISIBLE);
        }
        else if (GameData.PlayerList[turn].hasRightCard()){
            fourthPlayerLeft.setVisibility(View.INVISIBLE);
            fourthPlayerRight.setVisibility(View.VISIBLE);
        }
        else {
            fourthPlayerLeft.setVisibility(View.INVISIBLE);
            fourthPlayerRight.setVisibility(View.INVISIBLE);
        }
    }
    private void repaintSingle() {
        if (GameData.PlayerList[1].hasLeftCard()) {
            firstPlayerLeft.setVisibility(View.VISIBLE);
            firstPlayerRight.setVisibility(View.INVISIBLE);
        }
        else if (GameData.PlayerList[1].hasRightCard()){
            firstPlayerLeft.setVisibility(View.INVISIBLE);
            firstPlayerRight.setVisibility(View.VISIBLE);
        }
        else {
            firstPlayerLeft.setVisibility(View.INVISIBLE);
            firstPlayerRight.setVisibility(View.INVISIBLE);
        }

        if (GameData.PlayerList[2].hasLeftCard()) {
            secondPlayerLeft.setVisibility(View.VISIBLE);
            secondPlayerRight.setVisibility(View.INVISIBLE);
        }
        else if (GameData.PlayerList[2].hasRightCard()){
            secondPlayerLeft.setVisibility(View.INVISIBLE);
            secondPlayerRight.setVisibility(View.VISIBLE);
        }
        else {
            secondPlayerLeft.setVisibility(View.INVISIBLE);
            secondPlayerRight.setVisibility(View.INVISIBLE);
        }

        if (GameData.PlayerList[3].hasLeftCard()) {
            thirdPlayerLeft.setVisibility(View.VISIBLE);
            thirdPlayerRight.setVisibility(View.INVISIBLE);
        }
        else if (GameData.PlayerList[3].hasRightCard()){
            thirdPlayerLeft.setVisibility(View.INVISIBLE);
            thirdPlayerRight.setVisibility(View.VISIBLE);
        }
        else {
            thirdPlayerLeft.setVisibility(View.INVISIBLE);
            thirdPlayerRight.setVisibility(View.INVISIBLE);
        }

        if (GameData.PlayerList[4].hasLeftCard()) {
            fourthPlayerLeft.setVisibility(View.VISIBLE);
            fourthPlayerRight.setVisibility(View.INVISIBLE);
        }
        else if (GameData.PlayerList[4].hasRightCard()){
            fourthPlayerLeft.setVisibility(View.INVISIBLE);
            fourthPlayerRight.setVisibility(View.VISIBLE);
        }
        else {
            fourthPlayerLeft.setVisibility(View.INVISIBLE);
            fourthPlayerRight.setVisibility(View.INVISIBLE);
        }
    }
    public void clearTable() {
        firstPlayerLeft.setClickable(false);
        firstPlayerLeft.setOnClickListener(null);
        firstPlayerLeft.setVisibility(View.INVISIBLE);
        firstPlayerRight.setClickable(false);
        firstPlayerRight.setOnClickListener(null);
        firstPlayerRight.setVisibility(View.INVISIBLE);
        secondPlayerLeft.setClickable(false);
        secondPlayerLeft.setOnClickListener(null);
        secondPlayerLeft.setVisibility(View.INVISIBLE);
        secondPlayerRight.setClickable(false);
        secondPlayerRight.setOnClickListener(null);
        secondPlayerRight.setVisibility(View.INVISIBLE);
        thirdPlayerLeft.setClickable(false);
        thirdPlayerLeft.setOnClickListener(null);
        thirdPlayerLeft.setVisibility(View.INVISIBLE);
        thirdPlayerRight.setClickable(false);
        thirdPlayerRight.setOnClickListener(null);
        thirdPlayerRight.setVisibility(View.INVISIBLE);
        fourthPlayerLeft.setClickable(false);
        fourthPlayerLeft.setOnClickListener(null);
        fourthPlayerLeft.setVisibility(View.INVISIBLE);
        fourthPlayerRight.setClickable(false);
        fourthPlayerRight.setOnClickListener(null);
        fourthPlayerRight.setVisibility(View.INVISIBLE);
        outCard.setVisibility(View.INVISIBLE);
        ImageButton deckDummy = (ImageButton) findViewById(R.id.deckDummy);
        deckDummy.setVisibility(View.VISIBLE);
    }
    private void playCard(final Player on, final int hand) {
        AlertDialog.Builder play = new AlertDialog.Builder(this);
        play.setCancelable(false);
        play.setMessage("Play card " + on.getCard(hand).getValue() + "?");
        play.setNegativeButton("No", null);
        play.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                firstPlayerLeft.setClickable(false);
                firstPlayerRight.setClickable(false);
                imageZoomToClose();
                CountDownTimer t = new CountDownTimer(2000, 500) {
                    boolean done = false;
                    boolean otherDone = false;

                    @Override
                    public void onTick(long millisUntilFinished) {
                        if (!done) {
                            //flipBack method here
                            if (hand == 0) {
                                theAnimation.flipCardToBack(firstPlayerRight);
                            } else {
                                theAnimation.flipCardToBack(firstPlayerLeft);
                            }
                            done = true;
                        } else if (!otherDone) {
                            theAnimation.cardToDiscardMultiPlayer(hand);
                            otherDone = true;
                        }
                    }

                    @Override
                    public void onFinish() {
                        on.playCard(hand);
                        firstPlayerLeft.setBackgroundResource(R.drawable.magi_up);
                        firstPlayerRight.setBackgroundResource(R.drawable.magi_up);
                    }
                };
                t.start();
            }
        });
        play.show();
    }
    private void cardSevenError() {
        AlertDialog.Builder no = new AlertDialog.Builder(this);
        no.setCancelable(false);
        no.setTitle("Card 7 Effect");
        no.setMessage("This card can't be played. Please play card 7.");
        no.setPositiveButton("OK", null);
        no.show();
    }
    private void setBetaStuff() {
        Card c1 = GameData.PlayerList[1].getCard(0);
        Card c2 = GameData.PlayerList[1].getCard(1);
        Card c3 = GameData.PlayerList[2].getCard(0);
        Card c4 = GameData.PlayerList[2].getCard(1);
        Card c5 = GameData.PlayerList[3].getCard(0);
        Card c6 = GameData.PlayerList[3].getCard(1);
        Card c7 = GameData.PlayerList[4].getCard(0);
        Card c8 = GameData.PlayerList[4].getCard(1);
        String top, middle, middle2, bottom;
        if (c1 == null) {
            top = "0 : ";
        }
        else {
            top = c1.getValue() + " : ";
        }
        if (c2 == null) {
            top = top + 0;
        }
        else {
            top = top + c2.getValue();
        }
        if (c3 == null) {
            middle = "0 : ";
        }
        else {
            middle = c3.getValue() + " : ";
        }
        if (c4 == null) {
            middle = middle + 0;
        }
        else {
            middle = middle + c4.getValue();
        }

        if (c5 == null) {
            middle2 = "0 : ";
        }
        else {
            middle2 = c5.getValue() + " : ";
        }
        if (c6 == null) {
            middle2 = middle2 + 0;
        }
        else {
            middle2 = middle2 + c6.getValue();
        }
        if (c7 == null) {
            bottom = "0 : ";
        }
        else {
            bottom = c7.getValue() + " : ";
        }
        if (c8 == null) {
            bottom = bottom + 0;
        }
        else {
            bottom = bottom + c8.getValue();
        }

        String whole = "Beta Card Data" + "\nPlayer 1: " + top + "\nPlayer 2: " + middle + "\nPlayer 3: " + middle2
                + "\nPlayer 4: " + bottom + "\nOut Card: " + GameData.OutCard.getValue() +"\nIn Deck: " +GameData.deck.getDeckCount();
        betaView.setText(whole);
    }
    public void startMusic () throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method m = Music.class.getDeclaredMethod("setPlayer", MediaPlayer.class);
        m.invoke(gameMusic, new MediaPlayer().create(getApplication(), R.raw.magi_game));
        if (Music.isMute()) {
            gameMusic.setVolume(0,0);
        }
        Thread newThread = new Thread(gameMusic);
        newThread.start();
    }
    public void setMusic (Music piece) {
        gameMusic = piece;
    }

    @Override
    public void onBackPressed() {
        if (!theAnimation.isAnimating()) {
            AlertDialog.Builder back = new AlertDialog.Builder(this);
            back.setCancelable(false);
            back.setTitle("Quit");
            back.setMessage("Are you sure?");
            back.setNegativeButton("No", null);
            back.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    gameMusic.stop();
                    gameMusic.setPlayer(new MediaPlayer().create(getApplicationContext(), R.raw.pokemon_steven));
                    finish();
                }
            });
            back.show();
        }
    }
}

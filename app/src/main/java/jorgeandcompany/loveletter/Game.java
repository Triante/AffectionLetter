package jorgeandcompany.loveletter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Timer;
import java.util.TimerTask;


public class Game extends ActionBarActivity {
    private ImageButton discard, deck, firstPlayerRight, firstPlayerLeft, secondPlayerRight,
            secondPlayerLeft, thirdPlayerRight, thirdPlayerLeft, fourthPlayerRight, fourthPlayerLeft, outCard, deckDummy;
    private Button bPlay, bCancel;
    private ImageView expandedCardImage, backgroundOnPaused;
    private TextView cardDescriptionText, textOne, textTwo, textThree, textFour, betaView;
    RelativeLayout gameLayout;
    private boolean isSingleGame;
    private int comLevel = 0;
    private int playerAmount = 0;
    private static Music gameMusic = null;
    private GameAnimation theAnimation = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        isSingleGame = getIntent().getBooleanExtra("SinglePlayer", false);
        playerAmount = getIntent().getIntExtra("MultiPlayer", 0);
        comLevel = getIntent().getIntExtra("ComputerLevel", 1);
        deckDummy = (ImageButton) findViewById(R.id.deckDummy);
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
        textOne = (TextView) findViewById(R.id.area_text_one);
        textTwo = (TextView) findViewById(R.id.area_text_two);
        textThree = (TextView) findViewById(R.id.area_text_three);
        textFour = (TextView) findViewById(R.id.area_text_four);
        outCard = (ImageButton) findViewById(R.id.outCard);
        bPlay = (Button) findViewById(R.id.bPlay);
        bPlay.setBackgroundResource(SkinRes.getButtonTheme());
        bCancel = (Button) findViewById(R.id.bCancel);
        bCancel.setBackgroundResource(SkinRes.getButtonTheme());
        cardDescriptionText = (TextView) findViewById(R.id.card_description_text);
        cardDescriptionText.setBackgroundResource(SkinRes.getButtonTheme());
        expandedCardImage = (ImageView) findViewById(R.id.expanded_image);
        bCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageZoomToClose();
            }
        });
        backgroundOnPaused = (ImageView) findViewById(R.id.backGround);
        gameLayout = (RelativeLayout) findViewById(R.id.gameLayout);
        gameLayout.setBackgroundResource(SkinRes.getTableTheme());

        //beta view
        betaView = (TextView) findViewById(R.id.beta_card_data);
        betaView.setVisibility(View.INVISIBLE);
        setBackSkins();
        try{
            startMusic();
        }
        catch (Exception e) {

        }
        GameData.setContextMenu(this);
        GameData.setMode(isSingleGame, playerAmount, comLevel);
        GameData.newGame();
        theAnimation = new GameAnimation();
        handOutCards(GameData.TURN);

        if (GameData.debug) {
            betaView.setVisibility(View.VISIBLE);
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
        }
    }

    /**
     * Sets the back card skins for all the buttons
     */
    public void setBackSkins () {
        deckDummy.setBackgroundResource(SkinRes.skinRes(9,"up"));
        deck.setBackgroundResource(SkinRes.skinRes(9,"up"));
        firstPlayerLeft.setBackgroundResource(SkinRes.skinRes(9,"up"));
        firstPlayerRight.setBackgroundResource(SkinRes.skinRes(9,"up"));
        secondPlayerRight.setBackgroundResource(SkinRes.skinRes(9,"left"));
        secondPlayerLeft.setBackgroundResource(SkinRes.skinRes(9,"left"));
        thirdPlayerLeft.setBackgroundResource(SkinRes.skinRes(9,"down"));
        thirdPlayerRight.setBackgroundResource(SkinRes.skinRes(9,"down"));
        fourthPlayerLeft.setBackgroundResource(SkinRes.skinRes(9,"right"));
        fourthPlayerRight.setBackgroundResource(SkinRes.skinRes(9,"right"));
        outCard.setBackgroundResource(SkinRes.skinRes(9,"up"));
    }

    /**
     * Returns the ImageButton corresponding to the string key that was passed in the parameter.
     * @param imageType the String key used for getting the requested ImageButton. Keys:
     *                  firstplayerleft, firstplayerright, secondplayerleft, secondplayerright,
     *                  thirdplayerleft, thirdplayerright, fourthplayerleft, fourthplayerright, deck
     *                  discard.
     * @return returns the specified ImageButton. firstplayerleft returns first player left button,
     *                  firstplayerright returns first player right button,
     *                  secondplayerleft returns second player left button,
     *                  secondplayerright returns second player right button,
     *                  thirdplayerleft returns third player left button,
     *                  thirdplayerright returns third player right button,
     *                  fourthplayerleft returns fourth player left button,
     *                  fourthplayerright returns fourth player right button,
     *                  deck returns the deck button,
     *                  discard returns the discard button,
     *                  otherwise any other key returns the out card button.
     */
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

    /**
     * Begins the the turn of the current player. This method leads to other methods related to the
     * multi-player portion of the game. This method is in charge of handling the change of data and animations when
     * the player draws a card from the deck to begin their turn.
     */
    public void multiPlayerGame() {
        int turn = GameData.TURN;;
        final Player on = GameData.PlayerList[turn];
        //remove card 4 effect if active
        if (on.isProtected()) {
            on.setProtected(false);
        }
        CountDownTimer toMove = new CountDownTimer(2000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (on.hasLeftCard()) {
                    if (on.isHuman()) theAnimation.deckToRight(1);
                    else theAnimation.deckToRight(on.getPlayerNumber());
                }
                else {
                    if (on.isHuman()) theAnimation.deckToLeft(1);
                    else theAnimation.deckToLeft(on.getPlayerNumber());
                }
                if (GameData.getDeckCount() == 0) {
                    deck.setVisibility(View.INVISIBLE);
                }
                on.drawCard();
            }

            @Override
            public void onFinish() {
                if (on.isHuman()) playerMove(on);
                else computerMove(on);

            }
        };
        toMove.start();
    }

    /**
     * Begins the the turn of the current player. This method leads to other methods related to the
     * single-player portion of the game. This method is in charge of handling the change of data and animations when
     * the player draws a card from the deck to begin their turn.
     */
    public void singlePlayerGame() {
        int turn = GameData.TURN;;
        final Player on = GameData.PlayerList[turn];
        //remove card 4 effect if active
        if (on.isProtected()) {
            on.setProtected(false);
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
                if (GameData.getDeckCount() == 0) {
                    deck.setVisibility(View.INVISIBLE);
                }
                on.drawCard();
            }

            @Override
            public void onFinish() {
                if (on.isHuman()) {
                    playerMove(on);
                }
                else computerMove(on);

            }
        };
        toMove.start();


    }

    /**
     * Allows the game to move on to the next turn. Runs the game on single-player if the
     * isSingleGame flag is true, otherwise runs the multi-player game.
     */
    public void nextTurn() {
        if (isSingleGame) singlePlayerGame();
        else multiPlayerGame();
    }

    /**
     * Handles the animations, data, and states for when a human player is doing their move.
     * Allows for the human player to interact with the screen. Calls the card affect when the human
     * @param on the Player whose current turn is
     */
    private void playerMove(final Player on) {
        final ImageButton left = firstPlayerLeft;
        final int drawable1 = on.getCard(0).getSkinRes("up");
        final int drawable2 = on.getCard(1).getSkinRes("up");
        theAnimation.flipCard(left, drawable1);
        final ImageButton right = firstPlayerRight;
        left.setClickable(true);
        theAnimation.flipCard(right, drawable2);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageZoomToOpen(on, 0);
            }
        });
        right.setClickable(true);
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageZoomToOpen(on, 1);
            }
        });
        new CountDownTimer(400,100) {
            @Override
            public void onTick(long millisUntilFinished) {
            }
            @Override
            public void onFinish() {
                if (theAnimation.isAnimating()) {
                    theAnimation.changeAnimatingState();
                }
            }
        }.start();
    }

    /**
     * Plays the computers move
     * @param on the Player whose current turn is
     */
    private void computerMove(final Player on) {
        on.playCard(0);
    }

    /**
     * Handles the end of turn state. Continues the game when into the next turn when no player has achieved
     *  a total win of 7 rounds. Ends the round when the deck is out of cards or one wins. In this case, it awards any player(s) who
     *  won a point. Lastly it handles the end of game when one player reaches 7 points.
     */
    public void endOfTurn() {
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
                   if (GameData.Score[GameData.TURN - 1] == GameData.gamesUntilWin) {
                       GameData.GAME_COMPLETE = true;
                   }
                   ThemedDialog.Builder nextPlayerReady = new ThemedDialog.Builder(Game.this);
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
                               if (GameData.Score[0] == GameData.gamesUntilWin) endOfGame(1);
                               else if (GameData.Score[1] == GameData.gamesUntilWin) endOfGame(2);
                               else if (GameData.Score[2] == GameData.gamesUntilWin) endOfGame(3);
                               else if (GameData.Score[3] == GameData.gamesUntilWin) endOfGame(4);
                               else endOfGame(-1);
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
                       if (GameData.Score[x - 1] == GameData.gamesUntilWin) {
                           GameData.GAME_COMPLETE = true;
                       }
                   }
                   winners = winners.substring(0, winners.length() - 4);
                   ThemedDialog.Builder end = new ThemedDialog.Builder(Game.this);
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
                               if (GameData.Score[0] == GameData.gamesUntilWin) endOfGame(1);
                               else if (GameData.Score[1] == GameData.gamesUntilWin) endOfGame(2);
                               else if (GameData.Score[2] == GameData.gamesUntilWin) endOfGame(3);
                               else if (GameData.Score[3] == GameData.gamesUntilWin) endOfGame(4);
                               else endOfGame(-1);
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
                   ThemedDialog.Builder nextPlayerReady = new ThemedDialog.Builder(Game.this);
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
                       if (GameData.PlayerList[GameData.TURN].isHuman()) {
                           message = "Player " + GameData.TURN + " is up.\nPlease pass to player and select OK when ready.";
                       }
                       else {
                           message = "Player " + GameData.TURN + " is up.\nSelect OK when ready.";
                       }
                   }
                   nextPlayerReady.setTitle("Next Player");
                   nextPlayerReady.setMessage(message);
                   DialogInterface.OnClickListener ok = new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialog, int which) {
                           if (GameData.PlayerList[GameData.TURN].isHuman()) repaint();
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

    /**
     * Creates a dialog when the end of the game is reached. Handles starting a new game.
     * @param winner the ID of the player who won.
     */
    private void endOfGame(int winner) {
        ThemedDialog.Builder win = new ThemedDialog.Builder(this);
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

    /**
     * Handles the layout for when a player selects a card to view. Also calls the affect for the card
     * selected and calls the animation for playin the card.
     * @param on the Player whose current turn is
     * @param hand the int value for the card that was selected. 0 for left card, otherwise right card.
     */
    private void imageZoomToOpen(final Player on, final int hand) {
        Animation zoomOutImage = AnimationUtils.loadAnimation(this, R.anim.anim_scale_up);
        Animation zoomOutImage1 = AnimationUtils.loadAnimation(this, R.anim.anim_scale_up);
        Animation zoomOutImage2 = AnimationUtils.loadAnimation(this, R.anim.anim_scale_up);
        Animation zoomOutImage3 = AnimationUtils.loadAnimation(this, R.anim.anim_scale_up);
        backgroundOnPaused.setVisibility(View.VISIBLE);
        expandedCardImage.setImageResource(on.getCard(hand).getSkinRes("up"));
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
                    } else {
                        playCard(on, hand);
                    }
                } else {
                    playCard(on, hand);
                }
            }
        });
        bCancel.setVisibility(View.VISIBLE);
        bCancel.setClickable(true);
        cardDescriptionText.setText(on.getCard(hand).getDescription(this));
        cardDescriptionText.setVisibility(View.VISIBLE);

    }

    /**
     * Handles the layout for when a player exits the card view.
     */
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

    /**
     * Handles calling the opening animations for the game when dealing out cards.
     * @param firstPlayer the player ID for who plays first.
     */
    private void handOutCards(final int firstPlayer) {
        new CountDownTimer(7000, 1000) {
            int a = -1;
            public void onTick(long millisUntilFinished) {
                if (a == 0) {
                    theAnimation.deckToOutCard();
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
                ThemedDialog.Builder preGame = new ThemedDialog.Builder(Game.this);
                String mes;
                if (isSingleGame) mes = "Player " + GameData.TURN + " is up. Select OK when ready.";
                else  mes = "Player " + GameData.TURN + " is the first to go.\nPlease pass to player and select OK when ready.";
                preGame.setMessage(mes);
                DialogInterface.OnClickListener ok = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (GameData.PlayerList[GameData.TURN].isHuman()) repaint();
                        else repaintSingle();
                        nextTurn();
                    }
                };
                preGame.setPositiveButton("OK", ok);
                preGame.setCancelable(false);
                preGame.show();
            }
        }.start();
    }

    /**
     * Redraws the GUI to match the current status of the game. This method is only
     * meant to be used in single-player as it doesn't change the view of the players each turn.
     */
    private void repaint() {
        firstPlayerLeft.setBackgroundResource(SkinRes.skinRes(9, "up"));
        firstPlayerRight.setBackgroundResource(SkinRes.skinRes(9, "up"));
        secondPlayerLeft.setBackgroundResource(SkinRes.skinRes(9, "left"));
        secondPlayerRight.setBackgroundResource(SkinRes.skinRes(9, "left"));
        thirdPlayerLeft.setBackgroundResource(SkinRes.skinRes(9, "down"));
        thirdPlayerRight.setBackgroundResource(SkinRes.skinRes(9, "down"));
        fourthPlayerLeft.setBackgroundResource(SkinRes.skinRes(9, "right"));
        fourthPlayerRight.setBackgroundResource(SkinRes.skinRes(9, "right"));
        int turn = GameData.TURN;
        textOne.setVisibility(View.VISIBLE);
        textTwo.setVisibility(View.VISIBLE);
        textThree.setVisibility(View.VISIBLE);
        textFour.setVisibility(View.VISIBLE);
        //person who turn is now is set to main view. Scenario: turn = 1 therefore redraw player 1 to center.
        if (GameData.PlayerList[turn].hasLeftCard()) {
            firstPlayerLeft.setVisibility(View.VISIBLE);
            firstPlayerRight.setVisibility(View.INVISIBLE);
            textOne.setText("Player " + turn);
        }
        else if (GameData.PlayerList[turn].hasRightCard()){
            firstPlayerLeft.setVisibility(View.INVISIBLE);
            firstPlayerRight.setVisibility(View.VISIBLE);
            textOne.setText("Player " + turn);
        }
        else {
            firstPlayerLeft.setVisibility(View.INVISIBLE);
            firstPlayerRight.setVisibility(View.INVISIBLE);
            textOne.setVisibility(View.INVISIBLE);
        }
        turn++;
        if (turn == 5) {
            turn = 1;
        }
        //set next players location, scn: turn 1 therefore redraw player 2 to left side.
        if (GameData.PlayerList[turn].hasLeftCard()) {
            secondPlayerLeft.setVisibility(View.VISIBLE);
            secondPlayerRight.setVisibility(View.INVISIBLE);
            textTwo.setText("Player " + turn);
        }
        else if (GameData.PlayerList[turn].hasRightCard()){
            secondPlayerLeft.setVisibility(View.INVISIBLE);
            secondPlayerRight.setVisibility(View.VISIBLE);
            textTwo.setText("Player " + turn);
        }
        else {
            secondPlayerLeft.setVisibility(View.INVISIBLE);
            secondPlayerRight.setVisibility(View.INVISIBLE);
            textTwo.setVisibility(View.INVISIBLE);
        }
        turn++;
        if (turn == 5) {
            turn = 1;
        }
        //set next players location, scn: turn 1 therefore redraw player 3 to top side.
        if (GameData.PlayerList[turn].hasLeftCard()) {
            thirdPlayerLeft.setVisibility(View.VISIBLE);
            thirdPlayerRight.setVisibility(View.INVISIBLE);
            textThree.setText("Player " + turn);
        }
        else if (GameData.PlayerList[turn].hasRightCard()){
            thirdPlayerLeft.setVisibility(View.INVISIBLE);
            thirdPlayerRight.setVisibility(View.VISIBLE);
            textThree.setText("Player " + turn);
        }
        else {
            thirdPlayerLeft.setVisibility(View.INVISIBLE);
            thirdPlayerRight.setVisibility(View.INVISIBLE);
            textThree.setVisibility(View.INVISIBLE);
        }
        turn++;
        if (turn == 5) {
            turn = 1;
        }
        //set next players location, scn: turn 1 therefore redraw player 4 to right side.
        if (GameData.PlayerList[turn].hasLeftCard()) {
            fourthPlayerLeft.setVisibility(View.VISIBLE);
            fourthPlayerRight.setVisibility(View.INVISIBLE);
            textFour.setText("Player " + turn);
        }
        else if (GameData.PlayerList[turn].hasRightCard()){
            fourthPlayerLeft.setVisibility(View.INVISIBLE);
            fourthPlayerRight.setVisibility(View.VISIBLE);
            textFour.setText("Player " + turn);
        }
        else {
            fourthPlayerLeft.setVisibility(View.INVISIBLE);
            fourthPlayerRight.setVisibility(View.INVISIBLE);
            textFour.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * Redraws the GUI to match the current status of the game. This method is only
     * meant to be used in multi-player as it changes the view of the players each turn.
     */
    private void repaintSingle() {
        firstPlayerLeft.setBackgroundResource(SkinRes.skinRes(9, "up"));
        firstPlayerRight.setBackgroundResource(SkinRes.skinRes(9, "up"));
        secondPlayerLeft.setBackgroundResource(SkinRes.skinRes(9, "left"));
        secondPlayerRight.setBackgroundResource(SkinRes.skinRes(9, "left"));
        thirdPlayerLeft.setBackgroundResource(SkinRes.skinRes(9, "down"));
        thirdPlayerRight.setBackgroundResource(SkinRes.skinRes(9, "down"));
        fourthPlayerLeft.setBackgroundResource(SkinRes.skinRes(9, "right"));
        fourthPlayerRight.setBackgroundResource(SkinRes.skinRes(9, "right"));
        textOne.setVisibility(View.VISIBLE);
        textTwo.setVisibility(View.VISIBLE);
        textThree.setVisibility(View.VISIBLE);
        textFour.setVisibility(View.VISIBLE);
        if (GameData.PlayerList[1].hasLeftCard()) {
            firstPlayerLeft.setVisibility(View.VISIBLE);
            firstPlayerRight.setVisibility(View.INVISIBLE);
            textOne.setText("Player 1");
        }
        else if (GameData.PlayerList[1].hasRightCard()){
            firstPlayerLeft.setVisibility(View.INVISIBLE);
            firstPlayerRight.setVisibility(View.VISIBLE);
            textOne.setText("Player 1");
        }
        else {
            firstPlayerLeft.setVisibility(View.INVISIBLE);
            firstPlayerRight.setVisibility(View.INVISIBLE);
            textOne.setVisibility(View.INVISIBLE);
        }

        if (GameData.PlayerList[2].hasLeftCard()) {
            secondPlayerLeft.setVisibility(View.VISIBLE);
            secondPlayerRight.setVisibility(View.INVISIBLE);
            textTwo.setText("Player 2");
        }
        else if (GameData.PlayerList[2].hasRightCard()){
            secondPlayerLeft.setVisibility(View.INVISIBLE);
            secondPlayerRight.setVisibility(View.VISIBLE);
            textTwo.setText("Player 2");
        }
        else {
            secondPlayerLeft.setVisibility(View.INVISIBLE);
            secondPlayerRight.setVisibility(View.INVISIBLE);
            textTwo.setVisibility(View.INVISIBLE);
        }

        if (GameData.PlayerList[3].hasLeftCard()) {
            thirdPlayerLeft.setVisibility(View.VISIBLE);
            thirdPlayerRight.setVisibility(View.INVISIBLE);
            textThree.setText("Player 3");
        }
        else if (GameData.PlayerList[3].hasRightCard()){
            thirdPlayerLeft.setVisibility(View.INVISIBLE);
            thirdPlayerRight.setVisibility(View.VISIBLE);
            textThree.setText("Player 3");
        }
        else {
            thirdPlayerLeft.setVisibility(View.INVISIBLE);
            thirdPlayerRight.setVisibility(View.INVISIBLE);
            textThree.setVisibility(View.INVISIBLE);
        }

        if (GameData.PlayerList[4].hasLeftCard()) {
            fourthPlayerLeft.setVisibility(View.VISIBLE);
            fourthPlayerRight.setVisibility(View.INVISIBLE);
            textFour.setText("Player 4");
        }
        else if (GameData.PlayerList[4].hasRightCard()){
            fourthPlayerLeft.setVisibility(View.INVISIBLE);
            fourthPlayerRight.setVisibility(View.VISIBLE);
            textFour.setText("Player 4");
        }
        else {
            fourthPlayerLeft.setVisibility(View.INVISIBLE);
            fourthPlayerRight.setVisibility(View.INVISIBLE);
            textFour.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * Redraws the GUI to match the current status of the game. This method is only
     * meant to be used at the beginning of each round clear all the remaining cards in play and redraw
     * the deck if its invisible. Redraws screen to only have the Deck visible again.
     */
    public void clearTable() {
        textOne.setText("Player 1");
        textTwo.setText("Player 2");
        textThree.setText("Player 3");
        textFour.setText("Player 4");
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
        discard.setVisibility(View.INVISIBLE);
        ImageButton deckDummy = (ImageButton) findViewById(R.id.deckDummy);
        deckDummy.setVisibility(View.VISIBLE);
        firstPlayerLeft.setBackgroundResource(SkinRes.skinRes(9, "up"));
        firstPlayerRight.setBackgroundResource(SkinRes.skinRes(9, "up"));
        secondPlayerLeft.setBackgroundResource(SkinRes.skinRes(9, "left"));
        secondPlayerRight.setBackgroundResource(SkinRes.skinRes(9, "left"));
        thirdPlayerLeft.setBackgroundResource(SkinRes.skinRes(9, "down"));
        thirdPlayerRight.setBackgroundResource(SkinRes.skinRes(9, "down"));
        fourthPlayerLeft.setBackgroundResource(SkinRes.skinRes(9, "right"));
        fourthPlayerRight.setBackgroundResource(SkinRes.skinRes(9, "right"));
    }

    /**
     * Creates a dialog when a card is selected to be played. Handles the actual card affect and animation
     * to begin the state when the card is in affect.
     * @param on the Player whose current turn is
     * @param hand the int value for the card that was selected. 0 for left card, otherwise right card.
     */
    private void playCard(final Player on, final int hand) {
        ThemedDialog.Builder play = new ThemedDialog.Builder(this);
        play.setCancelable(false);
        play.setMessage("Play card " + on.getCard(hand).getValue() + "?");
        play.setNegativeButton("No", null);
        play.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (!theAnimation.isAnimating()) {
                    theAnimation.changeAnimatingState();
                }
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
                        firstPlayerLeft.setBackgroundResource(SkinRes.skinRes(9, "up"));
                        firstPlayerRight.setBackgroundResource(SkinRes.skinRes(9, "up"));
                    }
                };
                t.start();
            }
        });
        play.show();
    }

    /**
     * Creates a dialog to force the player not to play the card selected and reminds the player of
     * Card 7's affect and to play that card.
     */
    private void cardSevenError() {
        ThemedDialog.Builder no = new ThemedDialog.Builder(this);
        no.setCancelable(false);
        no.setTitle("Card 7 Effect");
        no.setMessage("This card can't be played. Please play card 7.");
        no.setPositiveButton("OK", null);
        no.show();
    }

    /**
     * updates the beta view with the specific information about the current game. The information includes
     * the amount of human players playing, the level of ai belonging to the computers, each of the players
     * cards they are holding (empty hand is displayed as a 0. If a player is out, both hands are displayed as 0). First boolean represents
     * if the player is human, the second boolean represents if the player is protected.
     */
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

        String whole = "Beta Card Data" +
                "\nComputer Level:  "+ comLevel + "   Player Amount: " +playerAmount+
                "\nPlayer 1: " + top + " : " + GameData.PlayerList[1].isHuman() + " : " + GameData.PlayerList[1].isProtected() +
                "\nPlayer 2: " + middle + " : " + GameData.PlayerList[2].isHuman() + " : " + GameData.PlayerList[2].isProtected() +
                "\nPlayer 3: " + middle2 + " : " + GameData.PlayerList[3].isHuman() + " : " + GameData.PlayerList[3].isProtected() +
                "\nPlayer 4: " + bottom + " : " + GameData.PlayerList[4].isHuman() + " : " + GameData.PlayerList[4].isProtected() +
                "\nOut Card: " + GameData.OutCard.getValue() + "    In Deck: " +GameData.deck.getDeckCount() +
                "\nDiscard Pile: " + GameData.discardPile;
        betaView.setText(whole);
    }

    /**
     * Creates a new music reference and starts the music playing in the background.
     * @throws NoSuchMethodException invoked by reflection
     * @throws InvocationTargetException invoked by reflection
     * @throws IllegalAccessException invoked by reflection
     */
    public void startMusic () throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method m = Music.class.getDeclaredMethod("setPlayer", MediaPlayer.class);
        m.invoke(gameMusic, new MediaPlayer().create(getApplication(), R.raw.game_piece));
        if (Music.isMute()) {
            gameMusic.setVolume(0,0);
        }
        Thread newThread = new Thread(gameMusic);
        newThread.start();
    }

    /**
     * sets the music pierce to be played
     * @param piece the music piece to be played
     */
    public void setMusic (Music piece) {
        gameMusic = piece;
    }
    
    /**
     * Creates a dialog warning the player they a are quiting the game.
     */
    @Override
    public void onBackPressed() {
        if (!theAnimation.isAnimating()) {
            ThemedDialog.Builder back = new ThemedDialog.Builder(this);
            back.setCancelable(false);
            back.setTitle("Quit");
            back.setMessage("Are you sure?");
            back.setNegativeButton("No", null);
            back.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    gameMusic.stop();
                    gameMusic.setPlayer(new MediaPlayer().create(getApplicationContext(), R.raw.classical_open));
                    finish();
                }
            });
            back.show();
        }
    }


    @Override
    public void onPause () {
        super.onPause();
        gameMusic.setVolume(0, 0);
    }
    /**
     * Returns the GameAnimation class that the game uses.
     * @return the GameAnimation class that the game uses.
     */
    public GameAnimation provideAnimations () {
        return theAnimation;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!gameMusic.isMute()) {
            gameMusic.setVolume(1, 1);
        }
    }

}

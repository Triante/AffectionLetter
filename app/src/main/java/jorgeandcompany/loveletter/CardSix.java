package jorgeandcompany.loveletter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;

/**
 * Created by Firemon123 on 10/1/2015.
 */
public class CardSix implements Card {
    private final int value = 6;

    @Override
    public void drawAffect(Player player) {
        return;
    }

    @Override
    public void cardEffect(final Player player) {
        AlertDialog.Builder effect = new AlertDialog.Builder(GameData.game);
        DialogInterface.OnClickListener ok = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                setButtonListeners(player);
            }
        };
        effect.setPositiveButton("OK", ok);
        effect.setCancelable(false);
        effect.setTitle("Card 6 Effect");
        effect.setMessage("Select a player to trade cards with.");
        effect.show();
    }

    @Override
    public void discardAffect(Player player) {
        return;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public String getDescription(Context c) {
        Resources res = c.getResources();
        String string = res.getString(R.string.Card_Six_Description);
        return string;
    }

    @Override
    public int getSkinRes(int skinId) {
        switch (skinId) {
            case 1:
                return R.drawable.sinbadver;
            case 2:
                return R.drawable.banever;
            case 3:
                return R.drawable.thorver;
            default:
                return R.drawable.sinbadver;
        }
    }

    private void setButtonListeners(final Player thePlayer) {
        int id = thePlayer.getPlayerNumber();
        ImageButton one;
        if (GameData.PlayerList[id].hasLeftCard()) {
            one = GameData.game.firstPlayerLeft;
        }
        else {
            one = GameData.game.firstPlayerRight;
        }
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder select = new AlertDialog.Builder(GameData.game);
                select.setTitle("End your turn?\n");
                select.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        GameData.game.endOfTurn(thePlayer);
                    }
                });
                select.setNegativeButton("No", null);
                select.setCancelable(false);
                AlertDialog alertDialogObject = select.create();
                alertDialogObject.show();
            }
        });
        id++;
        if (id == 5) id = 1;
        final int id2 = id;
        ImageButton two;
        if (GameData.PlayerList[id].hasLeftCard()) {
            two = GameData.game.secondPlayerLeft;
        }
        else {
            two = GameData.game.secondPlayerRight;
        }
        if (GameData.PlayerList[id].isProtected()) {
            two.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    protectedMessage(id2);
                }
            });
        }
        else {
            two.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    trade(id2, thePlayer);
                }
            });
        }
        id++;
        if (id == 5) id = 1;
        final int id3 = id;
        ImageButton three;
        if (GameData.PlayerList[id].hasLeftCard()) {
            three = GameData.game.thirdPlayerLeft;
        }
        else {
            three = GameData.game.thirdPlayerRight;
        }
        if (GameData.PlayerList[id].isProtected()) {
            three.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    protectedMessage(id3);
                }
            });
        }
        else {
            three.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    trade(id3, thePlayer);
                }
            });
        }
        id++;
        if (id == 5) id = 1;
        final int id4 = id;
        ImageButton four;
        if (GameData.PlayerList[id].hasLeftCard()) {
            four = GameData.game.fourthPlayerLeft;
        }
        else {
            four = GameData.game.fourthPlayerRight;
        }
        if (GameData.PlayerList[id].isProtected()) {
            four.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    protectedMessage(id4);
                }
            });
        }
        else {
            four.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    trade(id4, thePlayer);
                }
            });
        }
    }

    private void trade(final int id, final Player thePlayer) {
        AlertDialog.Builder toTrade = new AlertDialog.Builder(GameData.game);
        toTrade.setCancelable(false);
        toTrade.setTitle("Card 6 Effect");
        toTrade.setMessage("Trade with player " + id + "?");
        toTrade.setNegativeButton("No", null);
        toTrade.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tradeAction(id, thePlayer);
            }
        });
        toTrade.show();
    }
    private void tradeAction(final int id, final Player thePlayer) {
        Card card1 = thePlayer.getCard();
        Card card2 = GameData.PlayerList[id].getCard();
        thePlayer.setCard(card2);
        GameData.PlayerList[id].setCard(card1);
        performAnimation(id, thePlayer);

    }


    private void protectedMessage(int p) {
        AlertDialog.Builder protect = new AlertDialog.Builder(GameData.game);
        protect.setMessage("Player " + p + " is protected. Select another player");
        protect.setPositiveButton("OK", null);
        protect.show();
    }

    private void performAnimation (final int id, final Player thePlayer) {
        final ImageButton a, b;
        int number2 = GameData.PlayerList[id].getPlayerNumber(), number1 = thePlayer.getPlayerNumber();
        if (thePlayer.hasLeft) {
            a = GameData.game.firstPlayerLeft;
        }
        else {
            a = GameData.game.firstPlayerRight;
        }

        boolean hasLeft = GameData.PlayerList[id].hasLeft;

        if (number1 == 1 && number2 == 2) {
            if (hasLeft) {
                b = GameData.game.secondPlayerLeft;
            }
            else {
                b = GameData.game.secondPlayerRight;
            }
            animateSwap1to2(a,b,thePlayer,id);
        }
        else if (number1 == 1 && number2 == 3) {
            if (hasLeft) {
                b = GameData.game.thirdPlayerLeft;
            }
            else {
                b = GameData.game.thirdPlayerRight;
            }
            animateSwap1to3(a, b, thePlayer, id);
        }
        else if (number1 == 1 && number2 == 4) {
            if (hasLeft) {
                b = GameData.game.fourthPlayerLeft;
            }
            else {
                b = GameData.game.fourthPlayerRight;
            }
            animateSwap1to4(a, b, thePlayer, id);
        }
        else if (number1 == 2 && number2 == 1) {
            if (hasLeft) {
                b = GameData.game.fourthPlayerLeft;
            }
            else {
                b = GameData.game.fourthPlayerRight;
            }
            animateSwap1to4(a, b, thePlayer, id);
        }
        else if (number1 == 2 && number2 == 4) {
            if (hasLeft) {
                b = GameData.game.thirdPlayerLeft;
            }
            else {
                b = GameData.game.thirdPlayerRight;
            }
            animateSwap1to3(a, b, thePlayer, id);
        }
        else if (number1 == 2 && number2 == 3) {
            if (hasLeft) {
                b = GameData.game.secondPlayerLeft;
            }
            else {
                b = GameData.game.secondPlayerRight;
            }
            animateSwap1to2(a,b,thePlayer,id);
        }
        else if (number1 == 3 && number2 == 1) {
            if (hasLeft) {
                b = GameData.game.thirdPlayerLeft;
            }
            else {
                b = GameData.game.thirdPlayerRight;
            }
            animateSwap1to3(a, b, thePlayer, id);
        }
        else if (number1 == 3 && number2 == 2) {
            if (hasLeft) {
                b = GameData.game.fourthPlayerLeft;
            }
            else {
                b = GameData.game.fourthPlayerRight;
            }
            animateSwap1to4(a, b, thePlayer, id);
        }
        else if (number1 == 3 && number2 == 4) {
            if (hasLeft) {
                b = GameData.game.secondPlayerLeft;
            }
            else {
                b = GameData.game.secondPlayerRight;
            }
            animateSwap1to2(a,b,thePlayer,id);
        }
        else if (number1 == 4 && number2 == 1) {
            if (hasLeft) {
                b = GameData.game.secondPlayerLeft;
            }
            else {
                b = GameData.game.secondPlayerRight;
            }
            animateSwap1to2(a,b,thePlayer,id);
        }
        else if (number1 == 4 && number2 == 2) {
            if (hasLeft) {
                b = GameData.game.thirdPlayerLeft;
            }
            else {
                b = GameData.game.thirdPlayerRight;
            }
            animateSwap1to3(a, b, thePlayer, id);
        }
        else {
            if (hasLeft) {
                b = GameData.game.fourthPlayerLeft;
            }
            else {
                b = GameData.game.fourthPlayerRight;
            }
            animateSwap1to4(a, b, thePlayer, id);
        }

    }

    public void animateSwap1to2 (final ImageButton a, final ImageButton b, final Player thePlayer, final int id) {
        new CountDownTimer(2000, 1000) {
            public void onTick(long millisUntilFinished) {
                int[] bcoordinates = new int[2];
                int[] acoordinates = new int[2];
                a.getLocationOnScreen(acoordinates);
                b.getLocationOnScreen(bcoordinates);
                Animation rotateb = new RotateAnimation(0, -90, b.getPivotX(), b.getPivotY());
                Animation rotatea = new RotateAnimation(0, 90, a.getPivotX(), a.getPivotY());
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
                        success.setMessage("Success. You traded cards with player " + id + ".");
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

    public void animateSwap1to3 (final ImageButton a, final ImageButton b, final Player thePlayer, final int id) {
        new CountDownTimer(2000, 1000) {
            public void onTick(long millisUntilFinished) {
                int[] bcoordinates = new int[2];
                int[] acoordinates = new int[2];
                a.getLocationOnScreen(acoordinates);
                b.getLocationOnScreen(bcoordinates);
                Animation rotateb = new RotateAnimation(0, 180, b.getPivotX(), b.getPivotY());
                Animation rotatea = new RotateAnimation(0, -180, a.getPivotX(), a.getPivotY());
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
                        success.setMessage("Success. You traded cards with player " + id + ".");
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

    public void animateSwap1to4 (final ImageButton a, final ImageButton b, final Player thePlayer, final int id) {
        new CountDownTimer(2000, 1000) {
            public void onTick(long millisUntilFinished) {
                int[] bcoordinates = new int[2];
                int[] acoordinates = new int[2];
                a.getLocationOnScreen(acoordinates);
                b.getLocationOnScreen(bcoordinates);
                Animation rotateb = new RotateAnimation(0, 90, b.getPivotX(), b.getPivotY());
                Animation rotatea = new RotateAnimation(0, -90, a.getPivotX(), a.getPivotY());
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
                        success.setMessage("Success. You traded cards with player " + id + ".");
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
}

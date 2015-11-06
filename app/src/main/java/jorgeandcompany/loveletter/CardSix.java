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
        final ImageButton one;
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
        final ImageButton two;
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
                    trade(id2, thePlayer, one, two);
                }
            });
        }
        id++;
        if (id == 5) id = 1;
        final int id3 = id;
        final ImageButton three;
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
                    trade(id3, thePlayer, one, three);
                }
            });
        }
        id++;
        if (id == 5) id = 1;
        final int id4 = id;
        final ImageButton four;
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
                    trade(id4, thePlayer, one, four);
                }
            });
        }
    }

    private void trade(final int id, final Player thePlayer, final ImageButton player, final ImageButton otherPlayer) {
        AlertDialog.Builder toTrade = new AlertDialog.Builder(GameData.game);
        toTrade.setCancelable(false);
        toTrade.setTitle("Card 6 Effect");
        toTrade.setMessage("Trade with player " + id + "?");
        toTrade.setNegativeButton("No", null);
        toTrade.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tradeAction(id, thePlayer, player, otherPlayer);
            }
        });
        toTrade.show();
    }
    private void tradeAction(final int id, final Player thePlayer, final ImageButton player, final ImageButton otherPlayer) {
        Card card1 = thePlayer.getCard();
        Card card2 = GameData.PlayerList[id].getCard();
        thePlayer.setCard(card2);
        GameData.PlayerList[id].setCard(card1);
        performAnimation(id, thePlayer, player, otherPlayer);

    }


    private void protectedMessage(int p) {
        AlertDialog.Builder protect = new AlertDialog.Builder(GameData.game);
        protect.setMessage("Player " + p + " is protected. Select another player");
        protect.setPositiveButton("OK", null);
        protect.show();
    }

    private void performAnimation (final int id, final Player thePlayer, final ImageButton player, final ImageButton otherPlayer) {
        final ImageButton b;
        int number1 = thePlayer.getPlayerNumber();

        if ((number1 == 1 && id == 2) || (number1 == 2 && id == 1)) {
            swapOneTwo(player, otherPlayer,thePlayer,id);
        }
        else if ((number1 == 1 && id == 3) || (number1 == 3 && id == 1)) {
            swapOneThree(player, otherPlayer, thePlayer, id);
        }
        else if ((number1 == 1 && id == 4) || (number1 == 4 && id == 1)) {
            swapOneFour(player, otherPlayer, thePlayer, id);
        }
        else if ((number1 == 2 && id == 3) || (number1 == 3 && id == 2)) {
            swapTwoThree(player, otherPlayer, thePlayer, id);
        }
        else if ((number1 == 2 && id == 4) || (number1 == 4 && id == 2)) {
            swapTwoFour(player, otherPlayer, thePlayer, id);
        }
        else {
            swapThreeFour(player, otherPlayer, thePlayer, id);
        }

    }

    public void swapOneTwo (final ImageButton a, final ImageButton b, final Player thePlayer, final int id) {
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
    public void swapOneThree (final ImageButton a, final ImageButton b, final Player thePlayer, final int id) {
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
    public void swapOneFour (final ImageButton a, final ImageButton b, final Player thePlayer, final int id) {
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

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Card)) return false;
        Card other = (Card) o;
        if (getValue() == other.getValue()) return true;
        else return false;
    }
}

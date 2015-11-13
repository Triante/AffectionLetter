package jorgeandcompany.loveletter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.CountDownTimer;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;

/**
 * Created by Penguins94 on 11/6/2015.
 */
public class Animation {
    private void performAnimation (final int id, final Player thePlayer, final ImageButton player, final ImageButton otherPlayer) {
        final ImageButton b;
        int number1 = thePlayer.getPlayerNumber();

        if ((number1 == 1 && id == 2) || (number1 == 2 && id == 1)) {
            swapOneTwoOrThreeFour(player, otherPlayer,thePlayer,id);
        }
        else if ((number1 == 1 && id == 3) || (number1 == 3 && id == 1)) {
            swapOneThreeOrTwoFour(player, otherPlayer,thePlayer,id);
        }
        else if ((number1 == 1 && id == 4) || (number1 == 4 && id == 1)) {
            swapOneFourOrTwoThree(player, otherPlayer,thePlayer,id);
        }
        else if ((number1 == 2 && id == 3) || (number1 == 3 && id == 2)) {
            swapOneFourOrTwoThree(player, otherPlayer,thePlayer,id);
        }
        else if ((number1 == 2 && id == 4) || (number1 == 4 && id == 2)) {
            swapOneThreeOrTwoFour(player, otherPlayer,thePlayer,id);
        }
        else {
            swapOneTwoOrThreeFour(player, otherPlayer, thePlayer, id);
        }

    }
    public static void swapOneTwoOrThreeFour (final ImageButton a, final ImageButton b, final Player thePlayer, final int id) {
        new CountDownTimer(2000, 1000) {
            public void onTick(long millisUntilFinished) {
                int[] bcoordinates = new int[2];
                int[] acoordinates = new int[2];
                a.getLocationOnScreen(acoordinates);
                b.getLocationOnScreen(bcoordinates);
                android.view.animation.Animation rotateb = new RotateAnimation(0, -90, b.getPivotX(), b.getPivotY());
                android.view.animation.Animation rotatea = new RotateAnimation(0, 90, a.getPivotX(), a.getPivotY());
                rotatea.setDuration(1000);
                rotateb.setDuration(1000);
                android.view.animation.Animation translateb = new TranslateAnimation(0, acoordinates[0] - bcoordinates[0], 0, acoordinates[1] - bcoordinates[1]);
                android.view.animation.Animation translatea = new TranslateAnimation(0, bcoordinates[0] - acoordinates[0], 0, bcoordinates[1] - acoordinates[1]);
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

    public static void swapOneThreeOrTwoFour (final ImageButton a, final ImageButton b, final Player thePlayer, final int id) {
        new CountDownTimer(2000, 1000) {
            public void onTick(long millisUntilFinished) {
                int[] bcoordinates = new int[2];
                int[] acoordinates = new int[2];
                a.getLocationOnScreen(acoordinates);
                b.getLocationOnScreen(bcoordinates);
                android.view.animation.Animation rotateb = new RotateAnimation(0, 180, b.getPivotX(), b.getPivotY());
                android.view.animation.Animation rotatea = new RotateAnimation(0, -180, a.getPivotX(), a.getPivotY());
                rotatea.setDuration(1000);
                rotateb.setDuration(1000);
                android.view.animation.Animation translateb = new TranslateAnimation(0, acoordinates[0] - bcoordinates[0], 0, acoordinates[1] - bcoordinates[1]);
                android.view.animation.Animation translatea = new TranslateAnimation(0, bcoordinates[0] - acoordinates[0], 0, bcoordinates[1] - acoordinates[1]);
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

    public static void swapOneFourOrTwoThree (final ImageButton a, final ImageButton b, final Player thePlayer, final int id) {
        new CountDownTimer(2000, 1000) {
            public void onTick(long millisUntilFinished) {
                int[] bcoordinates = new int[2];
                int[] acoordinates = new int[2];
                a.getLocationOnScreen(acoordinates);
                b.getLocationOnScreen(bcoordinates);
                android.view.animation.Animation rotateb = new RotateAnimation(0, 90, b.getPivotX(), b.getPivotY());
                android.view.animation.Animation rotatea = new RotateAnimation(0, -90, a.getPivotX(), a.getPivotY());
                rotatea.setDuration(1000);
                rotateb.setDuration(1000);
                android.view.animation.Animation translateb = new TranslateAnimation(0, acoordinates[0] - bcoordinates[0], 0, acoordinates[1] - bcoordinates[1]);
                android.view.animation.Animation translatea = new TranslateAnimation(0, bcoordinates[0] - acoordinates[0], 0, bcoordinates[1] - acoordinates[1]);
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

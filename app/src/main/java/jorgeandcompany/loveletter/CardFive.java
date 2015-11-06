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
public class CardFive implements Card {
    private final int value = 5;

    @Override
    public void drawAffect(Player player) {
        return;
    }

    @Override
    public void cardEffect(final Player player) {
        AlertDialog.Builder effect = new AlertDialog.Builder(GameData.game);
        effect.setCancelable(false);
        effect.setTitle("Card 5 Effect");
        effect.setMessage("Choose a player to discard their hand");
        effect.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                setButtonListeners(player);
            }
        });
        effect.show();
    }

    @Override
    public void discardAffect(Player player) {
        return;
    }

    @Override
    public int getValue() {
        return 5;
    }

    @Override
    public String getDescription(Context c) {
        Resources res = c.getResources();
        String string = res.getString(R.string.Card_Five_Description);
        return string;
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
                select.setTitle("Card 5 Effect?\n");
                select.setMessage("Discard your own hand?");
                select.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        toDiscardAction(thePlayer.getPlayerNumber(), thePlayer, one);
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
                    toDiscard(id2, thePlayer, two);
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
                    toDiscard(id3, thePlayer, three);
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
                    toDiscard(id4, thePlayer, four);
                }
            });
        }
    }
    private void toDiscard(final int id, final Player thePlayer, final ImageButton button) {
        AlertDialog.Builder select = new AlertDialog.Builder(GameData.game);
        select.setTitle("Card 5 Effect?\n");
        select.setMessage("Discard player " + id + "'s hand?");
        select.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                toDiscardAction(id, thePlayer, button);
            }
        });
        select.setNegativeButton("No", null);
        select.setCancelable(false);
        AlertDialog alertDialogObject = select.create();
        alertDialogObject.show();
    }
    private void toDiscardAction(final int id, final Player thePlayer, final ImageButton button) {
        if (GameData.PlayerList[id].getCard().getValue() == 8) {
            GameData.PlayerList[id].discardCard();
        }
        else {
            discardAnimation(button);
            new CountDownTimer(4000, 1000) {
                public void onTick (long time) {

                }
                public void onFinish () {
                    AlertDialog.Builder select = new AlertDialog.Builder(GameData.game);
                    select.setTitle("Card 5 Effect?\n");
                    select.setMessage("Player " + id + "'s discarded card [" + GameData.PlayerList[id].getCard().getValue() +"] and drew a new card.");
                    select.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            GameData.PlayerList[id].discardCard();
                            if (GameData.getDeckCount() == 0) {
                                GameData.PlayerList[id].drawOutCard();
                            } else {
                                GameData.PlayerList[id].drawFirstCard();
                            }
                            GameData.game.endOfTurn(thePlayer);
                        }
                    });
                    select.setCancelable(false);
                    AlertDialog alertDialogObject = select.create();
                    alertDialogObject.show();
                }
            }.start();

        }
    }
    private void protectedMessage(int p) {
        AlertDialog.Builder protect = new AlertDialog.Builder(GameData.game);
        protect.setMessage("Player " + p + " is protected. Select another player");
        protect.setPositiveButton("OK", null);
        protect.show();
    }

    private void discardAnimation (final ImageButton button) {
        final int[] bcoordinates = new int[2];
        final int[] acoordinates = new int[2];
        final Animation rotateb;
        final Animation rotatea;
        final Animation translateb;
        final Animation translatea;
        final AnimationSet rotateandmovea = new AnimationSet(false), rotateandmoveb = new AnimationSet(false);
        GameData.game.deck.getLocationOnScreen(acoordinates);
        button.getLocationOnScreen(bcoordinates);
        if (button == GameData.game.firstPlayerLeft || button == GameData.game.firstPlayerRight) {
            rotateb = new RotateAnimation(0, 0, button.getPivotX(), button.getPivotY());
            rotatea = new RotateAnimation(0, 0, GameData.game.deck.getPivotX(), GameData.game.deck.getPivotY());
        } else if (button == GameData.game.secondPlayerLeft || button == GameData.game.secondPlayerRight) {
            rotateb = new RotateAnimation(0, -90, button.getPivotX(), button.getPivotY());
            rotatea = new RotateAnimation(0, 90, GameData.game.deck.getPivotX(), GameData.game.deck.getPivotY());
        } else if (button == GameData.game.thirdPlayerLeft || button == GameData.game.thirdPlayerRight) {
            rotateb = new RotateAnimation(0, 180, button.getPivotX(), button.getPivotY());
            rotatea = new RotateAnimation(0, 180, GameData.game.deck.getPivotX(), GameData.game.deck.getPivotY());
        } else {
            rotateb = new RotateAnimation(0, 90, button.getPivotX(), button.getPivotY());
            rotatea = new RotateAnimation(0, -90, GameData.game.deck.getPivotX(), GameData.game.deck.getPivotY());
        }

        translateb = new TranslateAnimation(0, acoordinates[0] - bcoordinates[0], 0, acoordinates[1] - bcoordinates[1]);
        translatea = new TranslateAnimation(0, bcoordinates[0] - acoordinates[0], 0, bcoordinates[1] - acoordinates[1]);
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
                        GameData.game.deck.startAnimation(rotateandmovea);
                        new CountDownTimer(1000, 1000) {
                            public void onTick(long millisUntilFinished) {

                            }

                            public void onFinish() {
                                button.setVisibility(View.VISIBLE);
                                if (GameData.deck.getDeckCount() == 0) {
                                    GameData.game.deck.setVisibility(View.INVISIBLE);
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

    @Override
    public int getSkinRes(int skinId) {
        switch (skinId) {
            case 1:
                return R.drawable.hakuryuuver;
            case 2:
                return R.drawable.poisonivyver;
            case 3:
                return R.drawable.ironmanver;
            default:
                return R.drawable.hakuryuuver;
        }
    }
}

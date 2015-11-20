package jorgeandcompany.loveletter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by Firemon123 on 10/1/2015.
 */
public class CardFive implements Card {
    private final int value = 5;

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
        final ImageButton one, oneb;
        if (GameData.PlayerList[id].hasLeftCard()) {
            one = GameData.game.getButton("firstPlayerLeft");
            oneb = GameData.game.getButton("firstPlayerLeft");
        }
        else {
            one = GameData.game.getButton("firstPlayerRight");
            oneb = GameData.game.getButton("firstPlayerLeft");
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
                        toDiscardAction(thePlayer.getPlayerNumber(), thePlayer, one, oneb);
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
        final ImageButton two, twob;
        if (GameData.PlayerList[id].hasLeftCard()) {
            two = GameData.game.getButton("secondPlayerleft");
            twob = GameData.game.getButton("secondPlayerleft");
        }
        else {
            two = GameData.game.getButton("secondPlayerright");
            twob = GameData.game.getButton("secondPlayerleft");
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
                    toDiscard(id2, thePlayer, two, twob);
                }
            });
        }
        id++;
        if (id == 5) id = 1;
        final int id3 = id;
        final ImageButton three, threeb;
        if (GameData.PlayerList[id].hasLeftCard()) {
            three = GameData.game.getButton("thirdPlayerLeft");
            threeb = GameData.game.getButton("thirdPlayerLeft");
        }
        else {
            three = GameData.game.getButton("thirdPlayerRight");
            threeb = GameData.game.getButton("thirdPlayerLeft");
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
                    toDiscard(id3, thePlayer, three, threeb);
                }
            });
        }
        id++;
        if (id == 5) id = 1;
        final int id4 = id;
        final ImageButton four, fourb;
        if (GameData.PlayerList[id].hasLeftCard()) {
            four = GameData.game.getButton("fourthPlayerleft");
            fourb = GameData.game.getButton("fourthPlayerleft");
        }
        else {
            four = GameData.game.getButton("fourthPlayerright");
            fourb = GameData.game.getButton("fourthPlayerleft");
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
                    toDiscard(id4, thePlayer, four, fourb);
                }
            });
        }
    }
    private void toDiscard(final int id, final Player thePlayer, final ImageButton button, final ImageButton button2) {
        AlertDialog.Builder select = new AlertDialog.Builder(GameData.game);
        select.setTitle("Card 5 Effect?\n");
        select.setMessage("Discard player " + id + "'s hand?");
        select.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                toDiscardAction(id, thePlayer, button, button2);
            }
        });
        select.setNegativeButton("No", null);
        select.setCancelable(false);
        AlertDialog alertDialogObject = select.create();
        alertDialogObject.show();
    }
    private void toDiscardAction(final int id, final Player thePlayer, final ImageButton button, final ImageButton button2) {
        if (GameData.PlayerList[id].getCard().getValue() == 8) {
            GameAnimation animation = GameData.game.provideAnimations();
            animation.discardAnimation(button, button2);
            new CountDownTimer(4000, 1000) {
                public void onTick (long time) {

                }
                public void onFinish () {
                    GameData.PlayerList[id].discardCard();
                }
            }.start();
        }
        else {
            GameAnimation animation = GameData.game.provideAnimations();
            animation.discardAnimation(button, button2);
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
                            GameData.game.endOfTurn();
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

    @Override
    public int getSkinRes(String orientation) {
        return SkinRes.skinRes(5, orientation);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof Card)) return false;
        Card other = (Card) o;
        if (getValue() == other.getValue()) return true;
        else return false;
    }
}

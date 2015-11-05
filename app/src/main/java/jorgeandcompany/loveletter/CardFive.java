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
                select.setTitle("Card 5 Effect?\n");
                select.setMessage("Discard your own hand?");
                select.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        toDiscardAction(thePlayer.getPlayerNumber(), thePlayer);
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
                    toDiscard(id2, thePlayer);
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
                    toDiscard(id3, thePlayer);
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
                    toDiscard(id4, thePlayer);
                }
            });
        }
    }
    private void toDiscard(final int id, final Player thePlayer) {
        AlertDialog.Builder select = new AlertDialog.Builder(GameData.game);
        select.setTitle("Card 5 Effect?\n");
        select.setMessage("Discard player " + id + "'s hand?");
        select.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                toDiscardAction(id, thePlayer);
            }
        });
        select.setNegativeButton("No", null);
        select.setCancelable(false);
        AlertDialog alertDialogObject = select.create();
        alertDialogObject.show();
    }
    private void toDiscardAction(final int id, final Player thePlayer) {
        if (GameData.PlayerList[id].getCard().getValue() == 8) {
            GameData.PlayerList[id].discardCard();
        }
        else {
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
    }
    private void protectedMessage(int p) {
        AlertDialog.Builder protect = new AlertDialog.Builder(GameData.game);
        protect.setMessage("Player " + p + " is protected. Select another player");
        protect.setPositiveButton("OK", null);
        protect.show();
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

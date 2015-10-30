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
        if (GameData.PlayerList[id].hasLeft) {
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
        if (GameData.PlayerList[id].hasLeft) {
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
        if (GameData.PlayerList[id].hasLeft) {
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
        if (GameData.PlayerList[id].hasLeft) {
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


    private void protectedMessage(int p) {
        AlertDialog.Builder protect = new AlertDialog.Builder(GameData.game);
        protect.setMessage("Player " + p + " is protected. Select another player");
        protect.setPositiveButton("OK", null);
        protect.show();
    }
}

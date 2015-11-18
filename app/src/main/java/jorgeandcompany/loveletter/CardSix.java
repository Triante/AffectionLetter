package jorgeandcompany.loveletter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by Firemon123 on 10/1/2015.
 */
public class CardSix implements Card {
    private final int value = 6;

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
            one = GameData.game.getButton("firstPlayerLeft");
        } else {
            one = GameData.game.getButton("firstPlayerRight");
        }
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder select = new AlertDialog.Builder(GameData.game);
                select.setTitle("End your turn?\n");
                select.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        GameData.game.endOfTurn();
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
            two = GameData.game.getButton("secondPlayerLeft");
        } else {
            two = GameData.game.getButton("secondPlayerRight");
        }
        if (GameData.PlayerList[id].isProtected()) {
            two.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    protectedMessage(id2);
                }
            });
        } else {
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
            three = GameData.game.getButton("thirdPlayerLeft");
        } else {
            three = GameData.game.getButton("thirdPlayerRight");
        }
        if (GameData.PlayerList[id].isProtected()) {
            three.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    protectedMessage(id3);
                }
            });
        } else {
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
            four = GameData.game.getButton("fourthPlayerLeft");
        } else {
            four = GameData.game.getButton("fourthPlayerRight");
        }
        if (GameData.PlayerList[id].isProtected()) {
            four.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    protectedMessage(id4);
                }
            });
        } else {
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

    private void performAnimation(final int id, final Player thePlayer, final ImageButton player, final ImageButton otherPlayer) {
        int number1 = thePlayer.getPlayerNumber();
        GameAnimation theAnimation = GameData.game.provideAnimations();
        if ((number1 == 1 && id == 2) || (number1 == 2 && id == 3) || (number1 == 3 && id == 4) || (number1 == 4 && id == 1)) {
            theAnimation.swapCard6(player, otherPlayer, thePlayer, id, -90, 90);
        } else if ((number1 == 2 && id == 1) || (number1 == 3 && id == 2) || (number1 == 4 && id == 3) || (number1 == 1 && id == 4)) {
            theAnimation.swapCard6(player, otherPlayer, thePlayer, id, 90, -90);
        } else if ((number1 == 1 && id == 3) || (number1 == 2 && id == 4)) {
            theAnimation.swapCard6(player, otherPlayer, thePlayer, id, -180, 180);
        } else {
            theAnimation.swapCard6(player, otherPlayer, thePlayer, id, 180, -180);
        }

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
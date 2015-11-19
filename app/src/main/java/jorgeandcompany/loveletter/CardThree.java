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
public class CardThree implements Card {
    private final int value = 3;

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
        effect.setTitle("Card 3 Effect");
        effect.setMessage("Select a player to compare cards with.");
        effect.show();
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public String getDescription(Context c) {
        Resources res = c.getResources();
        String string = res.getString(R.string.Card_Three_Description);
        return string;
    }

    @Override
    public int getSkinRes(int skinId) {
        switch (skinId) {
            case 1:
                return R.drawable.scheherazadever;
            case 2:
                return R.drawable.catwomanver;
            case 3:
                return R.drawable.hawkeyever;
            default:
                return R.drawable.scheherazadever;
        }
    }

    private void setButtonListeners(final Player thePlayer) {
        int id = thePlayer.getPlayerNumber();
        ImageButton one;
        if (GameData.PlayerList[id].hasLeftCard()) {
            one = GameData.game.getButton("firstPlayerLeft");
        }
        else {
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
        ImageButton two;
        if (GameData.PlayerList[id].hasLeftCard()) {
            two = GameData.game.getButton("secondPlayerLeft");
        }
        else {
            two = GameData.game.getButton("secondPlayerRight");
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
                    toCompare(id2, thePlayer);
                }
            });
        }
        id++;
        if (id == 5) id = 1;
        final int id3 = id;
        ImageButton three;
        if (GameData.PlayerList[id].hasLeftCard()) {
            three = GameData.game.getButton("thirdPlayerLeft");
        }
        else {
            three = GameData.game.getButton("thirdPlayerRight");
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
                    toCompare(id3, thePlayer);
                }
            });
        }
        id++;
        if (id == 5) id = 1;
        final int id4 = id;
        ImageButton four;
        if (GameData.PlayerList[id].hasLeftCard()) {
            four = GameData.game.getButton("fourthPlayerLeft");
        }
        else {
            four = GameData.game.getButton("fourthPlayerRight");
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
                    toCompare(id4, thePlayer);
                }
            });
        }
    }

    private void toCompare(final int id, final Player thePlayer) {
        AlertDialog.Builder select = new AlertDialog.Builder(GameData.game);
        select.setTitle("Card 3 Effect\n");
        select.setMessage("Compare cards player " + id + "?");
        select.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                toCompareAction(id, thePlayer);
            }
        });
        select.setNegativeButton("No", null);
        select.setCancelable(false);
        AlertDialog alertDialogObject = select.create();
        alertDialogObject.show();
    }
    private void toCompareAction(final int id, final Player thePlayer) {
        int mainPlayerCard = thePlayer.getCard().getValue();
        int otherPlayerCard = GameData.PlayerList[id].getCard().getValue();
        //lose: -1 if main < other, tie: 0 if main = other, win: 1 if main > other
        final int mainOut;
        String winner = "";
        if (mainPlayerCard < otherPlayerCard) {
            mainOut = -1;
            winner = "Player " + thePlayer.getPlayerNumber() + " had card [" + mainPlayerCard +"] and was the lowest card." +
                    " Player " + thePlayer.getPlayerNumber() + " is out";
        }
        else if (mainPlayerCard > otherPlayerCard) {
            mainOut = 1;
            winner = "Player " + GameData.PlayerList[id].getPlayerNumber() + " had card [" + otherPlayerCard +"] and had the lowest card." +
                    " Player " + GameData.PlayerList[id].getPlayerNumber() + " is out";
        }
        else {
            mainOut = 0;
            winner = "Both Player " + thePlayer.getPlayerNumber() + " and Player " + GameData.PlayerList[id].getPlayerNumber() +
                    " had the same card value. No one is out.";
        }

        AlertDialog.Builder select = new AlertDialog.Builder(GameData.game);
        select.setTitle("Card 3 Effect\n");
        select.setMessage(winner);
        DialogInterface.OnClickListener winOrLose = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (mainOut == -1) {
                    GameData.out(thePlayer.getPlayerNumber());
                } else if (mainOut == 1) {
                    GameData.out(id);
                }
                GameData.game.endOfTurn();
            }
        };
        DialogInterface.OnClickListener tie = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                GameData.game.endOfTurn();
            }
        };
        if (mainOut == 0) {
            select.setPositiveButton("OK", tie);
        }
        else {
            select.setPositiveButton("OK", winOrLose);
        }
        select.setCancelable(false);
        AlertDialog alertDialogObject = select.create();
        alertDialogObject.show();
    }
    private void protectedMessage(int p) {
        AlertDialog.Builder protect = new AlertDialog.Builder(GameData.game);
        protect.setMessage("Player " + p + " is protected. Select another player");
        protect.setPositiveButton("OK", null);
        protect.show();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof Card)) return false;
        Card other = (Card) o;
        int thisCard = getValue();
        int otherCard = other.getValue();
        if (thisCard == otherCard) return true;
        else return false;
    }
}

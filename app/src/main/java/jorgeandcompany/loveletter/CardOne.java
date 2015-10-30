package jorgeandcompany.loveletter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Firemon123 on 9/25/2015.
 */
public class CardOne implements Card {

    private final int value = 1;

    @Override
    public void drawAffect(Player player) {
        return;
    }

    /**
     * Name a card (other than a card that has a value of 1) and choose another player.
     * If that player has that card, he or she is out of the round
     * @param player
     */
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
        effect.setTitle("Card 1 Effect");
        effect.setMessage("Select a player to guess card.");
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
        String string = res.getString(R.string.Card_One_Description);
        return string;
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
                    guess(id2, thePlayer);
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
                    guess(id3, thePlayer);
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
                    guess(id4, thePlayer);
                }
            });
        }
    }

    private void success(final int player, int guess, final Player thePlayer) {
        AlertDialog.Builder s = new AlertDialog.Builder(GameData.game);
        s.setTitle("Card 1 Effect");
        s.setMessage("Congrats!\n" +
                "You guessed that player " + player + " had card " + guess +
                "!\nPlayer " + player + " is now out!");
        s.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                GameData.out(player);
                GameData.game.endOfTurn(thePlayer);
            }
        });
        AlertDialog alertDialogObject = s.create();
        alertDialogObject.show();
    }
    private void failure(int player, int guess, final Player thePlayer) {
        AlertDialog.Builder f = new AlertDialog.Builder(GameData.game);
        f.setTitle("Card 1 Effect");
        f.setMessage("Failed!\n" +
                "Player " + player + " does not have card " + guess +
                ".\nPlayer " + player + " is safe.");
        f.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                GameData.game.endOfTurn(thePlayer);
            }
        });
        AlertDialog alertDialogObject = f.create();
        alertDialogObject.show();
    }
    private void guess(int p, final Player player) {
        List<String> players = new ArrayList<String>();
        players.add("Card 2");
        players.add("Card 3");
        players.add("Card 4");
        players.add("Card 5");
        players.add("Card 6");
        players.add("Card 7");
        players.add("Card 8");
        CharSequence[] list = players.toArray(new String[players.size()]);
        final int toCheck = p;
        AlertDialog.Builder select = new AlertDialog.Builder(GameData.game);
        select.setTitle("Which Card does Player " + p + " have?");
        select.setItems(list, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int guess) {
                int theGuess = guess + 2;
                if (GameData.PlayerList[toCheck].getCard().getValue() == theGuess) {
                    success(toCheck, theGuess, player);
                } else {
                    failure(toCheck, theGuess, player);
                }
            }
        });
        AlertDialog alertDialogObject = select.create();
        alertDialogObject.show();
    }
    private void protectedMessage(int p) {
        AlertDialog.Builder protect = new AlertDialog.Builder(GameData.game);
        protect.setMessage("Player " + p + " is protected. Select another player");
        protect.setPositiveButton("OK", null);
        protect.show();
    }



}

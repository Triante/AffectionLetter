package jorgeandcompany.loveletter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Firemon123 on 9/25/2015.
 */
public class CardOne implements Card {

    private final int value = 1;

    /**
     * @param player the player whose card was selected
     */
    @Override
    public void cardEffect(final Player player) {

        ThemedDialog.Builder effect = new ThemedDialog.Builder(GameData.game);
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

    /**
     * @return value of Card
     */
    @Override
    public int getValue() {
        return value;
    }

    /**
     * @param c the Context of the current application
     * @return Returns the description of the card as a string
     */
    @Override
    public String getDescription(Context c) {
        Resources res = c.getResources();
        String string = res.getString(R.string.Card_One_Description);
        return string;
    }

    /**
     * Sets the listeners for the buttons for the player to choose.
     * @param thePlayer the player who is currently its turn.
     */
    private void setButtonListeners(final Player thePlayer) {
        int id = thePlayer.getPlayerNumber();
        boolean noneSelectable = true;
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
        else if (!GameData.PlayerList[id].isOut()){
            two.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    guess(id2);
                }
            });
            noneSelectable = false;
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
        else if (!GameData.PlayerList[id].isOut()){
            three.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    guess(id3);
                }
            });
            noneSelectable = false;
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
        else if (!GameData.PlayerList[id].isOut()){
            four.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    guess(id4);
                }
            });
            noneSelectable = false;
        }
        if (noneSelectable) {
            ThemedDialog.Builder select = new ThemedDialog.Builder(GameData.game);
            select.setTitle("All players are safe.\nNo action can be taken");
            select.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    GameData.game.endOfTurn();
                }
            });
            select.setCancelable(false);
            select.show();
        }

    }

    /**
     * Creates a dialog announcing the player guessed the other players card correctly.
     * This method gets the the player who was chosen out and ends the turn.
     * @param player the player who was chosen
     * @param guess the guess value
     */
    private void success(final int player, int guess) {
        ThemedDialog.Builder s = new ThemedDialog.Builder(GameData.game);
        s.setCancelable(false);
        s.setTitle("Card 1 Effect");
        s.setMessage("Congrats!\n" +
                "You guessed that player " + player + " had card " + guess +
                "!\nPlayer " + player + " is now out!");
        s.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                GameData.out(player);
                GameData.game.endOfTurn();
            }
        });
        s.show();
    }

    /**
     * Creates a dialog announcing the player guessed the other players card incorrectly.
     * This method ends the turn.
     * @param player the player who was chosen
     * @param guess the guess value
     */
    private void failure(int player, int guess) {
        ThemedDialog.Builder f = new ThemedDialog.Builder(GameData.game);
        f.setCancelable(false);
        f.setTitle("Card 1 Effect");
        f.setMessage("Failed!\n" +
                "Player " + player + " does not have card " + guess +
                ".\nPlayer " + player + " is safe.");
        f.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                GameData.game.endOfTurn();
            }
        });
        f.show();
    }

    /**
     * Creates a dialog for the person to guess the card of the player who was selected.
     * @param p the id of the player who was selected
     */
    private void guess(int p) {
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
                Card card = CardFactory.createCard(theGuess);
                if (GameData.PlayerList[toCheck].getCard().equals(card)) {
                    success(toCheck, theGuess);
                } else {
                    failure(toCheck, theGuess);
                }
            }
        });
        AlertDialog alertDialogObject = select.create();
        alertDialogObject.show();
    }

    /**
     * Creates a dialog to tell the current player that the selected player is protected.
     * @param p the id of the player that was chosen
     */
    private void protectedMessage(int p) {
        ThemedDialog.Builder protect = new ThemedDialog.Builder(GameData.game);
        protect.setMessage("Player " + p + " is protected. Select another player");
        protect.setPositiveButton("OK", null);
        protect.setCancelable(false);
        protect.show();
    }

    /**
     * @param orientation a String passed for the orientation
     * @return returns the R.drawable int of the card
     */
    @Override
    public int getSkinRes(String orientation) {
        return SkinRes.skinRes(1, orientation);
    }

    /**
     * @param o The object to compare this Card against
     * @return true if the given object represents a Card equivalent to this cards value, false otherwise
     */
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

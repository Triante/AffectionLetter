package jorgeandcompany.loveletter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

/**
 * Created by Firemon123 on 10/1/2015.
 */
public class CardTwo implements Card {

    private final int value = 2;

    /**
     * @param player the player whose card was selected
     */
    @Override
    public void cardEffect(final Player player) {
        ThemedDialog.Builder effect = new ThemedDialog.Builder(GameData.game);
        effect.setCancelable(false);
        effect.setTitle("Card 2 Effect");
        effect.setMessage("Select a player to view his or her hand");
        effect.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                setButtonListeners(player);
            }
        });
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
        String string = res.getString(R.string.Card_Two_Description);
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
        else if (!GameData.PlayerList[id].isOut()) {
            two.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lookAtCard(id2, thePlayer);
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
        else if (!GameData.PlayerList[id].isOut()) {
            three.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lookAtCard(id3, thePlayer);
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
                    lookAtCard(id4, thePlayer);
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
     * Creates the dialog for confirmation when the player chooses another player.
     * @param id the id of the player that was chosen
     * @param thePlayer the player who is currently its turn.
     */
    private void lookAtCard(final int id, final Player thePlayer) {
        ThemedDialog.Builder look = new ThemedDialog.Builder(GameData.game);
        look.setCancelable(false);
        look.setMessage("Look at Player " + id + "'s hand?");
        look.setNegativeButton("No", null);
        look.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                lookAtCardWarning(id, thePlayer);
            }
        });
        look.show();
    }

    /**
     * Creates a dialog to warn the player that he or she is going look at another players card
     * @param id the id of the player that was chosen
     * @param thePlayer the player who is currently its turn.
     */
    private void lookAtCardWarning(final int id, final Player thePlayer) {
        ThemedDialog.Builder look = new ThemedDialog.Builder(GameData.game);
        look.setCancelable(false);
        look.setMessage("You are about to view Player " + id + "'s hand.\n" +
                "Keep screen to self and continue to proceed.");
        look.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                lookAtCardAction(id, thePlayer);
            }
        });
        look.show();
    }

    /**
     * Creates a dialog with the image of the Card who the player selected. This method also ends
     * the current turn.
     * @param id the id of the player that was chosen
     * @param thePlayer the player who is currently its turn.
     */
    private void lookAtCardAction(final int id, final Player thePlayer) {
        final ThemedDialog.Builder look = new ThemedDialog.Builder(GameData.game);
        look.setCancelable(false);
        look.setTitle("Player " + id + "'s card.");
        ImageView image = new ImageView(GameData.game);
        image.setImageResource(GameData.PlayerList[id].getCard().getSkinRes("up"));
        image.setAdjustViewBounds(true);
        look.setView(image);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameData.game.endOfTurn();
                look.dismiss();

            }
        });
        look.show();
    }

    /**
     * Creates a dialog to tell the current player that the selected player is protected.
     * @param p the id of the player that was chosen
     */
    private void protectedMessage(int p) {
        ThemedDialog.Builder protect = new ThemedDialog.Builder(GameData.game);
        protect.setCancelable(false);
        protect.setMessage("Player " + p + " is protected. Select another player");
        protect.setPositiveButton("OK", null);
        protect.show();
    }

    /**
     * @param orientation a String passed for the orientation
     * @return returns the R.drawable int of the card
     */
    @Override
    public int getSkinRes(String orientation) {
        return SkinRes.skinRes(2, orientation);
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

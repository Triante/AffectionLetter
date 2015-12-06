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

    /**
     * @param player the player whose card was selected
     */
    @Override
    public void cardEffect(final Player player) {
        ThemedDialog.Builder effect = new ThemedDialog.Builder(GameData.game);
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
    /**
     * @return value of Card
     */
    @Override
    public int getValue() {
        return 5;
    }
    /**
     * @param c the Context of the current application
     * @return Returns the description of the card as a string
     */
    @Override
    public String getDescription(Context c) {
        Resources res = c.getResources();
        String string = res.getString(R.string.Card_Five_Description);
        return string;
    }
    /**
     * Sets the listeners for the buttons for the player to choose.
     * @param thePlayer the player who is currently its turn.
     */
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
                ThemedDialog.Builder select = new ThemedDialog.Builder(GameData.game);
                select.setTitle("Card 5 Effect?\n");
                select.setMessage("Discard your own hand?");
                select.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        toDiscardAction(thePlayer.getPlayerNumber(), one, oneb);
                    }
                });
                select.setNegativeButton("No", null);
                select.setCancelable(false);
                select.show();
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
        else if (!GameData.PlayerList[id].isOut()) {
            two.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    toDiscard(id2, two, twob);
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
        else if (!GameData.PlayerList[id].isOut()) {
            three.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    toDiscard(id3, three, threeb);
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
        else if (!GameData.PlayerList[id].isOut()) {
            four.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    toDiscard(id4, four, fourb);
                }
            });
        }
    }
    /**
     * Creates the dialog for confirmation when the player chooses another player.
     * @param id the id of the player that was chosen
     * @param button the button of the card to be discarded
     * @param button2 the button of the card to be drawn
     */
    private void toDiscard(final int id, final ImageButton button, final ImageButton button2) {
        ThemedDialog.Builder select = new ThemedDialog.Builder(GameData.game);
        select.setTitle("Card 5 Effect?\n");
        select.setMessage("Discard player " + id + "'s hand?");
        select.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                toDiscardAction(id, button, button2);
            }
        });
        select.setNegativeButton("No", null);
        select.setCancelable(false);
        select.show();
    }
    /**
     * Does the animation of the card discarding affect then creates a dialog to tell the current
     * player that player chosen discarded their card. This method ends the current turn.
     * @param id the id of the player that was chosen
     * @param button the button of the card to be discarded
     * @param button2 the button of the card to be drawn
     */
    private void toDiscardAction(final int id, final ImageButton button, final ImageButton button2) {
        if (GameData.PlayerList[id].getCard().getValue() == 8) {
            GameAnimation animation = GameData.game.provideAnimations();
            animation.discardAnimation(button, button2, id);
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
            animation.discardAnimation(button, button2, id);
            new CountDownTimer(4000, 1000) {
                public void onTick (long time) {

                }
                public void onFinish () {
                    ThemedDialog.Builder select = new ThemedDialog.Builder(GameData.game);
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
                    select.show();
                }
            }.start();

        }
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
        return SkinRes.skinRes(5, orientation);
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

package jorgeandcompany.loveletter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

/**
 * Created by Firemon123 on 10/1/2015.
 */
public class CardTwo implements Card {

    private final int value = 2;

    @Override
    public void drawAffect(Player player) {
        return;
    }

    @Override
    public void cardEffect(final Player player) {
        AlertDialog.Builder effect = new AlertDialog.Builder(GameData.game);
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
        String string = res.getString(R.string.Card_Two_Description);
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
                    lookAtCard(id2, thePlayer);
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
                    lookAtCard(id3, thePlayer);
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
                    lookAtCard(id4, thePlayer);
                }
            });
        }
    }
    private void lookAtCard(final int id, final Player thePlayer) {
        AlertDialog.Builder look = new AlertDialog.Builder(GameData.game);
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
    private void lookAtCardWarning(final int id, final Player thePlayer) {
        AlertDialog.Builder look = new AlertDialog.Builder(GameData.game);
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
    private void lookAtCardAction(final int id, final Player thePlayer) {
        AlertDialog.Builder look = new AlertDialog.Builder(GameData.game);
        look.setCancelable(false);
        look.setTitle("Card: " + GameData.PlayerList[id].getCard().getValue());
        ImageView image = new ImageView(GameData.game);
        image.setImageResource(R.drawable.background_up);
        look.setView(image);
        look.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                GameData.game.endOfTurn(thePlayer);
            }
        });
        look.show();
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
                return R.drawable.alibabaver;
            case 2:
                return R.drawable.twofacever;
            case 3:
                return R.drawable.quicksilverver;
            default:
                return R.drawable.alibabaver;
        }
    }
}

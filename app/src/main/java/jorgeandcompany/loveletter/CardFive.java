package jorgeandcompany.loveletter;

import android.content.Context;
import android.content.res.Resources;
import android.os.CountDownTimer;

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
        CountDownTimer c = new CountDownTimer(1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                GameData.out(player.getPlayerNumber());
                GameData.game.endOfTurn(player);
            }
        };
        c.start();
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

package jorgeandcompany.loveletter;

import android.content.Context;

/**
 * Created by Firemon123 on 9/25/2015.
 */
public interface Card {

    void cardEffect(Player player);

    int getValue();

    String getDescription(Context c);

    int getSkinRes(int skinId);

    boolean equals(Object o);

}

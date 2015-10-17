package jorgeandcompany.loveletter;

/**
 * Created by Firemon123 on 9/25/2015.
 */
public interface Card {

    void drawAffect(Player player);

    void cardEffect(Player player);

    void discardAffect(Player player);

    int getValue();

    String getDescription();
}

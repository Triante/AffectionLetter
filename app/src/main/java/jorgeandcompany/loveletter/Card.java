package jorgeandcompany.loveletter;

/**
 * Created by Firemon123 on 9/25/2015.
 */
public interface Card {

    public void drawAffect(Player player);

    public void cardEffect(Player player);

    public void discardAffect(Player player);

    public int getValue();

    public String getDescription();
}

package jorgeandcompany.loveletter;

/**
 * Created by Firemon123 on 9/25/2015.
 */
public interface Card {

    public void drawAffect();

    public void cardEffect();

    public void discardAffect();

    public int getValue();

    public String getDescription();
}

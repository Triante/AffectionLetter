package jorgeandcompany.loveletter;

/**
 * Created by Penguins94 on 12/4/2015.
 */
public abstract class ComputerLevelFactory {
    /**
     * creates a computer player based on the keys passed.
     * @param level the level of the computer: 1,2,3. default is 1.
     * @param playerNumber the player id for the computer
     * @return a computer player with player id and level from specified.
     */
    public abstract Player createComputerPlayer(int level, int playerNumber);
}

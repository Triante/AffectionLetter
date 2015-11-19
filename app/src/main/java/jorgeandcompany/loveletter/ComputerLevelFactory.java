package jorgeandcompany.loveletter;

/**
 * Created by Firemon123 on 11/13/2015.
 */
public class ComputerLevelFactory {

    public static Player createComputerPlayer(int level, int playerNumber) {
        switch (level) {
            case 1:
                return new ComPlayerLevelOne(playerNumber);
            case 2:
                return new ComPlayerLevelTwo(playerNumber);
            case 3:
                return new ComPlayerLevelThree(playerNumber);
            default:
                return new ComPlayerLevelOne(playerNumber);
        }
    }

}

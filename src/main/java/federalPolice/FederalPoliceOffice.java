package federalPolice;


import baggage.HandBaggage;
import java.util.Random;

public class FederalPoliceOffice {
    private Robocop[] robocop;
    private FederalPoliceOfficer[] officers;
    public FederalPoliceOffice(){
        robocop = new Robocop[3];
    }

    public char[][] randomRobocopDestroyHandBaggage(HandBaggage handBaggage){
        Random random = new Random();
        int robot = random.nextInt(3);

        return robocop[robot].getRemote().use(handBaggage);
    }
}

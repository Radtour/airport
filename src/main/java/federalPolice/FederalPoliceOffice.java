package federalPolice;


import baggage.HandBaggage;
import java.util.Random;

public class FederalPoliceOffice {
    private Robocop[] robocop;
    private FederalPoliceOfficer[] officers;
    public FederalPoliceOffice(FederalPoliceOfficer officer02, FederalPoliceOfficer officer03){
        robocop = new Robocop[3];
        for(int i = 0; i < 3; i++){
            robocop[i] = new Robocop();
        }
        officers = new FederalPoliceOfficer[2];
        officers[0] = officer02;
        officers[1] = officer03;
    }

    public char[][] randomRobocopDestroyHandBaggage(HandBaggage handBaggage){
        Random random = new Random();
        int robot = random.nextInt(3);

        return robocop[robot].getRemote().use(handBaggage);
    }

    public FederalPoliceOfficer[] getOfficers() {
        return officers;
    }

    public Robocop[] getRobocop() {
        return robocop;
    }
}

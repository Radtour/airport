package federalPolice;


import baggage.HandBaggage;
import baggageScanner.BaggageScanner;

import java.util.Random;
import java.util.SimpleTimeZone;

public class FederalPoliceOffice {
    private Robocop[] robocop;
    private FederalPoliceOfficer[] officers;
    public FederalPoliceOffice(FederalPoliceOfficer officer01, FederalPoliceOfficer officer02, FederalPoliceOfficer officer03){
        robocop = new Robocop[3];
        for(int i = 0; i < 3; i++){
            robocop[i] = new Robocop();
        }
        officers = new FederalPoliceOfficer[3];
        officers[0] = officer02;
        officer02.setOffice(this);
        officers[1] = officer03;
        officer03.setOffice(this);
        officers[2] = officer01;
        officer01.setOffice(this);
    }

    public char[][] randomRobocopDestroyHandBaggage(HandBaggage handBaggage){
        Random random = new Random();
        int robot = random.nextInt(3);

        return robocop[robot].getRemote().use(handBaggage);
    }

    public void sendRandomRobocom(){
        Random random = new Random();
        int robot = random.nextInt(3);
    }

    public FederalPoliceOfficer[] getOfficers() {
        return officers;
    }

    public Robocop[] getRobocop() {
        return robocop;
    }

    public void sendBackUp(BaggageScanner baggageScanner){
        if(baggageScanner.getRecords().get(baggageScanner.getRecords().size()-1).getResult().contains("Glock")){
            officers[2].openBaggage(baggageScanner.getCurrentTrayInScanner().getHandBaggage());
        }
        if(baggageScanner.getRecords().get(baggageScanner.getRecords().size()-1).getResult().contains("Glock")){

        }
    }
}

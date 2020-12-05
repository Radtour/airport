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
        boolean robotsend = false;
        for (int i = 0; i < 3; i++){
            if(robocop[i].isAtAirport()){
                return robocop[i].getRemote().use(handBaggage);
                robotsend = true;
            }
        }
        if (!robotsend){
            return null;
        }
    }

    public boolean sendRandomRobocop(){
        Random random = new Random();
        int robot = random.nextInt(3);

        robocop[robot].setAtAirport(true);
        return officers[2].getBaggageScanner().getManualPostControl().getInspector().swipe();
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
        if(baggageScanner.getRecords().get(baggageScanner.getRecords().size()-1).getResult().contains("Explosive")){
            if(sendRandomRobocop()){
                int i;
                for (i = 0; i < 3 ; i++){
                    if (robocop[i].isAtAirport()){
                        break;
                    }
                }
                //TODO bitte handbaggage uebergeben
                char [][] destroyedHandbaggage = officers[0].controlRobocop(robocop[i].getRemote());

            }
        }
    }
}

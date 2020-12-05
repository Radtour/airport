package federalPolice;


import baggage.HandBaggage;
import baggageScanner.BaggageScanner;

import java.util.Random;
import java.util.SimpleTimeZone;

public class FederalPoliceOffice {
    private Robocop[] robocop;
    private FederalPoliceOfficer[] officers;
    private HandBaggage[] perceivedHandBaggage;
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
        this.perceivedHandBaggage = new HandBaggage[3];
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

    public boolean sendBackUp(BaggageScanner baggageScanner){
        if(baggageScanner.getRecords().get(baggageScanner.getRecords().size()-1).getResult().contains("glock")){
            officers[2].openBaggage(baggageScanner.getTracks()[0].getTrayList().poll().getHandBaggage());
            return true;
        }
        if(baggageScanner.getRecords().get(baggageScanner.getRecords().size()-1).getResult().contains("explosive")){
            if(startExplosiveRemoval(baggageScanner)){
                return true;
            }
        }
        return false;
    }

    public boolean startExplosiveRemoval(BaggageScanner baggageScanner){
        if(sendRandomRobocop()){
            int i;
            for (i = 0; i < 3 ; i++){
                if (robocop[i].isAtAirport()){
                    break;
                }
            }
            HandBaggage handBaggage = baggageScanner.getTracks()[0].getTrayList().poll().getHandBaggage();
            char [][] destroyedHandBaggage = officers[0].controlRobocop(robocop[i].getRemote(), handBaggage);
            handBaggage.setDestroyedHandBaggage(destroyedHandBaggage);
            return true;
        }
        return false;
    }

    public void setPerceivedHandBaggage(HandBaggage[] perceivedHandBaggage) {
        this.perceivedHandBaggage = perceivedHandBaggage;
    }

    public HandBaggage[] getPerceivedHandBaggage() {
        return perceivedHandBaggage;
    }
}

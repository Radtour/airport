package federalPolice;

import baggage.HandBaggage;
import baggage.Passenger;
import baggageScanner.BaggageScanner;
import employee.Employee;
import employee.id.IDCard;
import employee.id.ProfileType;

import java.util.Date;

public class FederalPoliceOfficer extends Employee {
    private String grade;
    private FederalPoliceOffice office;
    public FederalPoliceOfficer(String grade, ProfileType profileType, String name, Date birthDate, IDCard idCard){
        super(profileType, name, birthDate, idCard);
    }

    public void arrest(Passenger passenger){
        passenger.setArrested(true);
        requestBackup();
    }

    public void requestBackup(){
        office.sendBackUp(this.getBaggageScanner());
    }

    public char[][] operationDefuse(HandBaggage handBaggage){
        return office.randomRobocopDestroyHandBaggage(handBaggage);
    }

    public void setOffice(FederalPoliceOffice office){
        this.office = office;
    }

    public void openBaggage(HandBaggage handBaggage){
        takeoutWeapon(handBaggage);
    }

    public void takeoutWeapon(HandBaggage handBaggage){
        int index = this.getBaggageScanner().getRecords().size();
        String result = this.getBaggageScanner().getRecords().get(index - 1).getResult();
        int layer = result.charAt(result.length()-1);
        handBaggage.getLayers()[layer].clearLayer();
        showWeapon();
    }

    public void showWeapon(){
        getBaggageScanner().getSupervision().getSupervisor().lookAtWeapon();
        getBaggageScanner().getManualPostControl().getCurrentPassenger().lookAtWeapon();
        giveWeaponToPoliceOfficer();
    }

    public void giveWeaponToPoliceOfficer(){
        office.getOfficers()[1].takeWeapon(this.getBaggageScanner());
    }

    public void takeWeapon(BaggageScanner baggageScanner){
        //TODO Arthur mach den shit mit dem restlichen scannen
        if(baggageScanner.getManualPostControl().getCurrentPassenger().getHandBaggages().length > 1)
        {
            baggageScanner.getManualPostControl().getInspector().scanRemainingBaggage();
        }
        else {
            this.leavePersonControll();
            office.getOfficers()[0].leavePersonControll();
            baggageScanner.getManualPostControl().getCurrentPassenger().leavePersonControl();
            baggageScanner.getSupervision().getSupervisor().unlockBaggageScanner();
        }
    }

    public void leavePersonControll(){

    }
}

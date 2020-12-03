package federalPolice;

import baggage.HandBaggage;
import baggage.Passenger;
import employee.Employee;
import employee.id.ProfileType;

public class FederalPoliceOfficer extends Employee {
    private String grade;
    private FederalPoliceOffice office;
    public FederalPoliceOfficer(String grade, ProfileType profileType){
        super(profileType);
    }

    public void arrest(Passenger passenger){
        passenger.setArrested(true);
    }

    public void requestBackup(){
        //TODO: wie gehts weiter?
    }

    public char[][] operationDefuse(HandBaggage handBaggage){
        return office.randomRobocopDestroyHandBaggage(handBaggage);
    }
}

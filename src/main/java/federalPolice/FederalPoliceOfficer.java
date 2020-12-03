package federalPolice;

import baggage.HandBaggage;
import baggage.Passenger;
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
    }

    public void requestBackup(){
        //TODO: wie gehts weiter?
    }

    public char[][] operationDefuse(HandBaggage handBaggage){
        return office.randomRobocopDestroyHandBaggage(handBaggage);
    }
}

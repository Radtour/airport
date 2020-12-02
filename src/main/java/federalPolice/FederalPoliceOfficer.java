package federalPolice;

import baggage.HandBaggage;
import employee.Employee;

public class FederalPoliceOfficer extends Employee {
    private String grade;
    private FederalPoliceOffice office;
    public FederalPoliceOfficer(String grade){

    }

    public char[][] operationDefuse(HandBaggage handBaggage){
        return office.randomRobocopDestroyHandBaggage(handBaggage);
    }
}

package employee.id;

import baggage.Passenger;
import employee.Employee;

public class ProfileManager {
    public boolean isAllowedToUseBaggageScanner(Employee employee){
        return employee.getProfileType() != ProfileType.K && employee.getProfileType() != ProfileType.O;
    }

    public boolean isAllowedToUseMoveBeltForward(Employee employee){
        return employee.getProfileType() == ProfileType.I;
    }

    public boolean isAllowedToUseMoveBeltBackward(Employee employee){
        return employee.getProfileType() == ProfileType.I;
    }

    public boolean isAllowedToUseScan(Employee employee){
        return employee.getProfileType() == ProfileType.I;
    }

    public boolean isAllowedToUseAlarm(Employee employee){
        return employee.getProfileType() == ProfileType.I;
    }

    public boolean isAllowedToUseReport(Employee employee){
        return employee.getProfileType() == ProfileType.S;
    }

    public boolean isAllowedToUseMaintenance(Employee employee){
        return employee.getProfileType() == ProfileType.T;
    }
}

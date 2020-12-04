package employee.id;

import baggage.Passenger;
import employee.Employee;

public class ProfileManager {
    public static boolean isAllowedToUseBaggageScanner(Employee employee){
        return employee.getProfileType() != ProfileType.K && employee.getProfileType() != ProfileType.O;
    }

    public static boolean isAllowedToUseMoveBeltForward(Employee employee){
        return employee.getProfileType() == ProfileType.I;
    }

    public static boolean isAllowedToUseMoveBeltBackward(Employee employee){
        return employee.getProfileType() == ProfileType.I;
    }

    public static boolean isAllowedToUseScan(Employee employee){
        return employee.getProfileType() == ProfileType.I;
    }

    public static boolean isAllowedToUseAlarm(Employee employee){
        return employee.getProfileType() == ProfileType.I;
    }

    public static boolean isAllowedToUseReport(Employee employee){
        return employee.getProfileType() == ProfileType.S;
    }

    public static boolean isAllowedToUseMaintenance(Employee employee){
        return employee.getProfileType() == ProfileType.T;
    }
}

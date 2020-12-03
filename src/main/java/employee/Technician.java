package employee;

import employee.id.IDCard;
import employee.id.ProfileType;

import java.util.Date;

public class Technician extends Employee {
    public Technician(ProfileType profileType, String name, Date birthDate, IDCard idCard){
        super(profileType, name, birthDate, idCard);
    }
}

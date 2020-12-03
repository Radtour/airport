package employee;

import employee.id.IDCard;
import employee.id.ProfileType;

import java.util.Date;

public abstract class Employee {
    private String id;
    private String name;
    private Date birthDate;
    private IDCard idCard;
    private ProfileType profileType;
    public Employee(){

    }

    public ProfileType getProfileType() {
        return profileType;
    }
}

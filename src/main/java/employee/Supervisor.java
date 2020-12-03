package employee;

import employee.id.IDCard;
import employee.id.ProfileType;

import java.util.Date;

public class Supervisor extends Employee {
    private final Boolean isExecutive;
    private final Boolean isSenior;
    public Supervisor(ProfileType profileType, String name, Date birthDate, IDCard idCard, boolean isExecutive, boolean isSenior){
        super(profileType, name, birthDate, idCard);
        this.isExecutive = isExecutive;
        this.isSenior = isSenior;
    }

    public Boolean getExecutive() {
        return isExecutive;
    }

    public Boolean getSenior() {
        return isSenior;
    }
}

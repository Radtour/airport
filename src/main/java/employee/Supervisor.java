package employee;

import employee.id.ProfileType;

public class Supervisor extends Employee {
    private Boolean isExecutive;
    private Boolean isSenior;
    public Supervisor(ProfileType profileType){
        super(profileType);

    }
}

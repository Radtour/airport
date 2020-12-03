package employee;

import employee.id.IDCard;
import employee.id.ProfileType;

import java.util.Date;

public class HouseKeeper extends Employee{
    public HouseKeeper(ProfileType profileType, String name, Date birthDate, IDCard idCard) {
        super(profileType, name, birthDate, idCard);
    }
}

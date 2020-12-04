package employee;

import baggageScanner.BaggageScanner;
import employee.id.IDCard;
import employee.id.ProfileType;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

public abstract class Employee {
    private final String name;
    private final Date birthDate;
    private final ProfileType profileType;
    private final String id;
    private final IDCard idCard;
    private BaggageScanner baggageScanner;
    public Employee(ProfileType profileType, String name, Date birthDate, IDCard idCard){
        this.profileType = profileType;
        this.name = name;
        this.birthDate = birthDate;
        this.id = UUID.randomUUID().toString();
        this.idCard = idCard;
    }

    public ProfileType getProfileType() {
        return profileType;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public IDCard getIdCard() {
        return idCard;
    }

    public BaggageScanner getBaggageScanner() {
        return baggageScanner;
    }

    public void setBaggageScanner(BaggageScanner baggageScanner) {
        this.baggageScanner = baggageScanner;
    }
}

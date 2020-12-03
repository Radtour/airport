package employee;

import baggageScanner.BaggageScannerStatus;
import baggageScanner.Supervision;
import employee.id.IDCard;
import employee.id.ProfileType;

import java.util.Date;

public class Supervisor extends Employee {
    private final Boolean isExecutive;
    private final Boolean isSenior;
    private Supervision supervision;
    public Supervisor(ProfileType profileType, String name, Date birthDate, IDCard idCard, boolean isExecutive, boolean isSenior){
        super(profileType, name, birthDate, idCard);
        this.isExecutive = isExecutive;
        this.isSenior = isSenior;
    }

    public void pushStartButton(){
        supervision.startBaggageScanner();
    }

    public void unlockBaggageScanner(){
        supervision.getBaggageScanner().setStatus(BaggageScannerStatus.activated);
    }

    public Boolean getExecutive() {
        return isExecutive;
    }

    public Boolean getSenior() {
        return isSenior;
    }

    public void setSupervision(Supervision supervision) {
        this.supervision = supervision;
    }
}

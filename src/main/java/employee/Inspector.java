package employee;

import baggage.HandBaggage;
import baggageScanner.TestStripe;
import baggageScanner.conveyingComponents.Tray;
import employee.id.IDCard;
import employee.id.ProfileType;

import java.util.Date;

public class Inspector extends Employee {
    private final Boolean isSenior;

    public Inspector(ProfileType profileType, String name, Date birthDate, IDCard idCard, boolean isSenior){
        super(profileType, name, birthDate, idCard);
        this.isSenior = isSenior;

    }

    public void push(Tray tray){

    }

    public void disposeKnife(){

    }

    public void inform(Inspector inspector){

    }

    public void swipe(HandBaggage handBaggage){
        TestStripe testStripe = new TestStripe();

        testStripe.setEXP();
    }

    public Boolean getSenior() {
        return isSenior;
    }
}

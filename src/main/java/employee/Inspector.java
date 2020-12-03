package employee;

import baggage.HandBaggage;
import baggageScanner.TestStripe;
import baggageScanner.conveyingComponents.Tray;
import employee.id.ProfileType;

public class Inspector extends Employee {
    private Boolean isSenior;

    public Inspector(ProfileType profileType){
        super(profileType);

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
}

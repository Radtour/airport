package employee;

import baggage.HandBaggage;
import baggageScanner.TestStripe;
import baggageScanner.conveyingComponents.Tray;
import baggageScanner.operatingStation.Scanner;
import configuration.Configuration;
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

    public void swipeCard() {
        Scanner scanner = this.getBaggageScanner().getOperatingStation().getScanner();
        boolean isAllowed = scanner.swipeCard(this.getIdCard());
        if(isAllowed){
            String magnetStripeContent =Configuration.instance.aes.decrypt(getIdCard().getMagnetStripe().getContent(), Configuration.instance.secretKey);
            String[] magnetStripeContentArray = magnetStripeContent.replace("***", "*").split("\\*");

            boolean pinValidated = scanner.inputPIN(magnetStripeContentArray[3]);
        }
    }
}

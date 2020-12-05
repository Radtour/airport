package employee;

import baggage.HandBaggage;
import baggageScanner.BaggageScannerStatus;
import baggageScanner.ExplosiveTraceDetector;
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
        getBaggageScanner().getBelt().addTrayQueue(tray);
    }

    public void disposeKnife(){

    }

    public void inform(Inspector inspector){

    }

    public boolean swipe(){
        TestStripe testStripe = new TestStripe();

        testStripe.setEXP();
        return insertIntoDetector(testStripe);
    }

    public boolean insertIntoDetector(TestStripe testStripe){
        ExplosiveTraceDetector explosiveTraceDetector = new ExplosiveTraceDetector();
        return explosiveTraceDetector.insert(testStripe);
    }

    public Boolean getSenior() {
        return isSenior;
    }

    public boolean swipeCard() {
        Scanner scanner = this.getBaggageScanner().getOperatingStation().getScanner();
        boolean isAllowed = scanner.swipeCard(this.getIdCard());
        if(isAllowed){
            String magnetStripeContent =Configuration.instance.aes.decrypt(getIdCard().getMagnetStripe().getContent(), Configuration.instance.secretKey);
            String[] magnetStripeContentArray = magnetStripeContent.replace("***", "*").split("\\*");

            if(scanner.isIDCardLocked(this.getIdCard())){
                boolean pinValidated = scanner.inputPIN(magnetStripeContentArray[3]);
                if(pinValidated){
                    this.getBaggageScanner().activate(this);
                }
            }
        }

        return false;
    }

    public void scanRemainingBaggage(){
        //TODO mach hier auch deinen shit
        getBaggageScanner().
    }
}

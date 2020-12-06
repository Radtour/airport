package employee;

import baggage.HandBaggage;
import baggageScanner.Record;
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

            if(!scanner.isIDCardLocked(this.getIdCard())){
                boolean pinValidated = scanner.inputPIN(magnetStripeContentArray[2]);
                if(pinValidated){
                    return this.getBaggageScanner().activate(this);
                }
            }
        }

        return false;
    }

    public void scanRemainingBaggage(){
        for(int i = 0; i < getBaggageScanner().getBelt().getTrays().size(); i++){
            getBaggageScanner().getOperatingStation().pushRightButton();
            getBaggageScanner().getOperatingStation().pushRectagleButton();
            Record record = getBaggageScanner().getRecords().get(getBaggageScanner().getRecords().size() - 1);
            if(record.getResult().contains("clean")){
                getBaggageScanner().getTracks()[1].addTrayToList(getBaggageScanner().getCurrentTrayInScanner());
                getBaggageScanner().setCurrentTrayInScanner(null);
            }
            else if(record.getResult().contains("knife")){
                getBaggageScanner().getTracks()[0].addTrayToList(getBaggageScanner().getCurrentTrayInScanner());
                getBaggageScanner().setCurrentTrayInScanner(null);
                getBaggageScanner().getManualPostControl().getInspector().inspect();
                Record recordRetry = getBaggageScanner().getRecords().get(getBaggageScanner().getRecords().size() - 1);
                if(recordRetry.getResult().contains("clean")){
                    getBaggageScanner().getTracks()[1].addTrayToList(getBaggageScanner().getCurrentTrayInScanner());
                    getBaggageScanner().setCurrentTrayInScanner(null);
                }
            }
            else if(record.getResult().contains("weapon")){
                Tray tray = getBaggageScanner().getCurrentTrayInScanner();
                getBaggageScanner().getTracks()[0].addTrayToList(tray);
                getBaggageScanner().setCurrentTrayInScanner(null);
                getBaggageScanner().getOfficer().takeoutWeapon(tray.getHandBaggage());
            }
            else if(record.getResult().contains("explosive")){
                Tray tray = getBaggageScanner().getCurrentTrayInScanner();
                getBaggageScanner().getTracks()[0].addTrayToList(tray);
                getBaggageScanner().setCurrentTrayInScanner(null);
                getBaggageScanner().getOfficer().getOffice().startExplosiveRemoval(getBaggageScanner());
            }
        }
    }

    public void inspect() {
        Tray tray = getBaggageScanner().getTracks()[0].getTrayList().peek();
        takeOutKnife(tray.getHandBaggage());
        disposeKnife();
        getBaggageScanner().getOperatingStation().pushLeftButton();
        getBaggageScanner().getOperatingStation().pushRectagleButton();
    }

    private void takeOutKnife(HandBaggage handBaggage){
        int index = this.getBaggageScanner().getRecords().size();
        String result = this.getBaggageScanner().getRecords().get(index - 1).getResult();
        char layerChar = result.charAt(result.length()-1);
        int layer = Integer.parseInt(String.valueOf(layerChar));
        handBaggage.getLayers()[layer].clearLayer();
    }
}

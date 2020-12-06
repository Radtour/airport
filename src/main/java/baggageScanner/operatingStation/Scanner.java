package baggageScanner.operatingStation;

import configuration.Configuration;
import employee.id.IDCard;
import employee.id.ProfileManager;

public class Scanner {
    private final OperatingStation operatingStation;
    private IDCard currentIDCard;

    public Scanner(OperatingStation operatingStation){
        this.operatingStation = operatingStation;
    }

    public OperatingStation getOperatingStation() {
        return operatingStation;
    }

    public boolean swipeCard(IDCard idCard) {
        this.currentIDCard = idCard;
        return ProfileManager.isAllowedToUseBaggageScanner(idCard.getEmployee());
    }

    public boolean inputPIN(String pin) {

        String decryptedMagnetStripe = Configuration.instance.aes.decrypt(currentIDCard.getMagnetStripe().getContent(), Configuration.instance.secretKey);
        if(!currentIDCard.getLocked()){
            if(decryptedMagnetStripe.contains(pin)){
                return true;
            }
            else {
                currentIDCard.addFailedAttempts();
                if(currentIDCard.getFailedAttempts() == 3){
                    currentIDCard.setLocked(true);
                }
                return false;
            }
        }
        return false;
    }

    public boolean isIDCardLocked(IDCard idCard){
        return idCard.getLocked();
    }
}

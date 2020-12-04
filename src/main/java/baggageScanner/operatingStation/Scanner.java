package baggageScanner.operatingStation;

import configuration.Configuration;
import employee.id.IDCard;
import employee.id.ProfileManager;

public class Scanner {
    private OperatingStation operatingStation;
    private IDCard currentIDCard;

    public Scanner(OperatingStation operatingStation){
        this.operatingStation = operatingStation;
    }

    public OperatingStation getOperatingStation() {
        return operatingStation;
    }

    public void setOperatingStation(OperatingStation operatingStation) {
        this.operatingStation = operatingStation;
    }

    public boolean swipeCard(IDCard idCard) {
        this.currentIDCard = idCard;
        return ProfileManager.isAllowedToUseBaggageScanner(idCard.getEmployee());
    }

    public boolean inputPIN(String pin) {

        String decryptedMagnetStripe = Configuration.instance.aes.decrypt(currentIDCard.getMagnetStripe().getContent(), Configuration.instance.secretKey);
        if(decryptedMagnetStripe.contains(pin)){
            return true;
        }
        else {
            currentIDCard.addFailedAttempts();
            return false;
        }
    }
}

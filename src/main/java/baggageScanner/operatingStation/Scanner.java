package baggageScanner.operatingStation;

import employee.id.IDCard;

public class Scanner {
    private OperatingStation operatingStation;

    public Scanner(OperatingStation operatingStation){
        this.operatingStation = operatingStation;
    }

    public OperatingStation getOperatingStation() {
        return operatingStation;
    }

    public void setOperatingStation(OperatingStation operatingStation) {
        this.operatingStation = operatingStation;
    }

    private Boolean swipeCard(IDCard idCard,int pin){
        boolean valid = false;

        return valid;
    }
}

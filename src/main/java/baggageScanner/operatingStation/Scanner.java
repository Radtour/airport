package baggageScanner.operatingStation;

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
}

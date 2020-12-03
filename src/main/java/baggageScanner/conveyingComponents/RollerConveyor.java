package baggageScanner.conveyingComponents;

import baggageScanner.BaggageScanner;
import employee.Inspector;

public class RollerConveyor {
    private final BaggageScanner baggageScanner;
    private final Inspector inspector;

    public RollerConveyor(Inspector inspector, BaggageScanner baggageScanner){
        this.inspector = inspector;
        this.baggageScanner = baggageScanner;
    }

    public BaggageScanner getBaggageScanner() {
        return baggageScanner;
    }

    public Inspector getInspector() {
        return inspector;
    }
}

package baggageScanner;

import baggage.HandBaggage;
import baggage.Passenger;
import employee.Inspector;

public class ManualPostControl {
    private final BaggageScanner baggageScanner;
    private Passenger currentPassenger;
    private Inspector inspector;

    public ManualPostControl(Inspector inspector, BaggageScanner baggageScanner) {
        this.inspector = inspector;
        this.baggageScanner = baggageScanner;
    }

    public Inspector getInspector() {
        return inspector;
    }

    public BaggageScanner getBaggageScanner() {
        return baggageScanner;
    }

    public Passenger getCurrentPassenger() {
        return currentPassenger;
    }
}

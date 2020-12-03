package baggageScanner;

import baggage.HandBaggage;
import baggage.Passenger;
import employee.Inspector;

public class ManualPostControl {
    private Passenger currentPassenger;
    private Inspector inspector;

    public ManualPostControl(Passenger currentPassenger, Inspector inspector) {
        this.currentPassenger = currentPassenger;
        this.inspector = inspector;
    }

}

package baggageScanner;

import baggageScanner.conveyingComponents.Belt;
import baggageScanner.conveyingComponents.RollerConveyor;
import baggageScanner.conveyingComponents.Track;
import federalPolice.FederalPoliceOfficer;

public class BaggageScanner {
    private BaggageScannerStatus status;
    private FederalPoliceOfficer officer;
    private Supervision supervision;
    private Belt belt;
    private RollerConveyor rollerConveyor;
    private Track[] tracks;

    public BaggageScanner(){

    }
}

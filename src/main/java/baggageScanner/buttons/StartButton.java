package baggageScanner.buttons;

import baggageScanner.BaggageScanner;
import baggageScanner.BaggageScannerStatus;

public class StartButton extends Button{
    @Override
    public void push(BaggageScanner baggageScanner) {
        baggageScanner.setStatus(BaggageScannerStatus.deactivated);
    }
}

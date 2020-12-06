package baggageScanner.conveyingComponents;

import baggage.HandBaggage;


public class Tray {

    private HandBaggage handBaggage;

    public Tray(){

    }

    public void setHandBaggage(HandBaggage handBaggage) {
        this.handBaggage = handBaggage;
    }

    public HandBaggage getHandBaggage() {
        return handBaggage;
    }
}

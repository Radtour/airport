package baggageScanner.conveyingComponents;

import baggage.HandBaggage;

import java.util.Queue;

public class Tray {

    private HandBaggage handBaggage;

    public Tray(){

    }

    public void setHandBaggage(HandBaggage handBaggage) {
        this.handBaggage = handBaggage;
    }

    public void removeHandBaggage(){
        this.handBaggage = null;
    }

    public HandBaggage getHandBaggage() {
        return handBaggage;
    }
}

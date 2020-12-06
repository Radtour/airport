package baggageScanner.conveyingComponents;

import baggageScanner.BaggageScanner;

import java.util.LinkedList;
import java.util.Queue;

public class Belt {
    private final BaggageScanner baggageScanner;

    private final Queue<Tray> trays;

    public Belt(BaggageScanner baggageScanner){
        this.baggageScanner = baggageScanner;
        this.trays = new LinkedList<>();
    }

    public Queue<Tray> getTrays() {
        return trays;
    }

    public void addTrayQueue(Tray tray){
        this.trays.add(tray);
    }

    public BaggageScanner getBaggageScanner() {
        return baggageScanner;
    }
}

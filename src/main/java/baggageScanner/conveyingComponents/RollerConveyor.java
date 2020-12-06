package baggageScanner.conveyingComponents;

import baggageScanner.BaggageScanner;
import employee.Inspector;

import java.util.LinkedList;
import java.util.Queue;

public class RollerConveyor {
    private final BaggageScanner baggageScanner;
    private final Inspector inspector;
    private final Queue<Tray> trays;

    public RollerConveyor(Inspector inspector, BaggageScanner baggageScanner){
        this.inspector = inspector;
        this.baggageScanner = baggageScanner;
        this.trays = new LinkedList<>();
    }

    public BaggageScanner getBaggageScanner() {
        return baggageScanner;
    }

    public Inspector getInspector() {
        return inspector;
    }

    public Queue<Tray> getTrays() {
        return trays;
    }

    public void addTrayQueue(Tray tray){
        this.trays.add(tray);
    }
}

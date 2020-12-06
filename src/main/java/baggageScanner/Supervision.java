package baggageScanner;

import baggageScanner.buttons.ShutdownButton;
import baggageScanner.buttons.StartButton;
import employee.Supervisor;

public class Supervision {
    private final Supervisor supervisor;
    private final BaggageScanner baggageScanner;
    private final StartButton startButton;
    private final ShutdownButton shutdownButton;

    public Supervision(Supervisor supervisor,BaggageScanner baggageScanner) {
        this.supervisor = supervisor;
        supervisor.setSupervision(this);
        startButton = new StartButton();
        shutdownButton = new ShutdownButton();
        this.baggageScanner = baggageScanner;
    }

    public void startBaggageScanner(){
        startButton.push(supervisor);
    }

    public void shutdownBaggageScanner(){
        shutdownButton.push(supervisor);
    }

    public Supervisor getSupervisor() {
        return supervisor;
    }

    public StartButton getStartButton() {
        return startButton;
    }

    public ShutdownButton getShutdownButton() {
        return shutdownButton;
    }

    public BaggageScanner getBaggageScanner() {
        return baggageScanner;
    }
}

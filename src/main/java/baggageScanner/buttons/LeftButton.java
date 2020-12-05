package baggageScanner.buttons;

import baggageScanner.BaggageScanner;
import baggageScanner.conveyingComponents.Tray;
import employee.Employee;

public class LeftButton extends Button{
    @Override
    public void push(Employee employee) {
        employee.getBaggageScanner().setCurrentTrayInScanner(employee.getBaggageScanner().getTracks()[0].getTrayList().poll());
    }
}

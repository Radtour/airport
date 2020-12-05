package baggageScanner.buttons;

import baggageScanner.BaggageScanner;
import employee.Employee;

public class RightButton extends Button{
    @Override
    public void push(Employee employee) {
        employee.getBaggageScanner().setCurrentTrayInScanner(employee.getBaggageScanner().getBelt().getTrays().poll());
    }
}

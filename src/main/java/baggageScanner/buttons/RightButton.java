package baggageScanner.buttons;

import employee.Employee;

public class RightButton extends Button{
    @Override
    public void push(Employee employee) {
        employee.getBaggageScanner().moveBeltForward(employee);
    }
}

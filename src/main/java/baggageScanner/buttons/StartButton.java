package baggageScanner.buttons;

import employee.Employee;

public class StartButton extends Button{
    @Override
    public void push(Employee employee) {
        employee.getBaggageScanner().start(employee);
    }
}

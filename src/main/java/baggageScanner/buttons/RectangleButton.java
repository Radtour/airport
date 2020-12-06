package baggageScanner.buttons;

import employee.Employee;

public class RectangleButton extends Button{
    @Override
    public void push(Employee employee) {
        employee.getBaggageScanner().scan(employee.getBaggageScanner().getCurrentTrayInScanner().getHandBaggage());
    }
}

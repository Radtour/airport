package baggageScanner.buttons;

import employee.Employee;

public class LeftButton extends Button{
    @Override
    public void push(Employee employee) {
        employee.getBaggageScanner().setCurrentTrayInScanner(employee.getBaggageScanner().getTracks()[0].getTrayList().poll());
    }
}

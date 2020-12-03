package baggageScanner.operatingStation;

import baggageScanner.buttons.Button;
import baggageScanner.buttons.LeftButton;
import baggageScanner.buttons.RectangleButton;
import baggageScanner.buttons.RightButton;
import employee.Inspector;

public class OperatingStation {
    private final Button[] buttons;
    private Inspector inspector;
    private Scanner scanner;

    public OperatingStation(){
        this.buttons = new Button[3];
        this.buttons[0] = new LeftButton();
        this.buttons[1] = new RightButton();
        this.buttons[2] = new RectangleButton();

        this.scanner = new Scanner(this);
    }

    public Button[] getButtons() {
        return buttons;
    }

    public Inspector getInspector() {
        return inspector;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setInspector(Inspector inspector) {
        this.inspector = inspector;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }
}

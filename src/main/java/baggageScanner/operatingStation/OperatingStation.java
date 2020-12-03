package baggageScanner.operatingStation;

import baggageScanner.BaggageScanner;
import baggageScanner.buttons.Button;
import baggageScanner.buttons.LeftButton;
import baggageScanner.buttons.RectangleButton;
import baggageScanner.buttons.RightButton;
import employee.Inspector;

public class OperatingStation {
    private final LeftButton leftButton;
    private final RightButton rightButton;
    private final RectangleButton rectangleButton;
    private final Inspector inspector;
    private Scanner scanner;
    private final BaggageScanner baggageScanner;

    public OperatingStation(Inspector inspector, BaggageScanner baggageScanner){
        scanner = new Scanner(this);
        this.inspector = inspector;
        this.baggageScanner = baggageScanner;

        leftButton = new LeftButton();
        rightButton = new RightButton();
        rectangleButton = new RectangleButton();
    }

    public void pushRectagleButton(){
        rectangleButton.push(baggageScanner);
    }

    public void pushLeftButton(){
        leftButton.push(baggageScanner);
    }

    public void pushRightButton(){
        rightButton.push(baggageScanner);
    }

    public LeftButton getLeftButton() {
        return leftButton;
    }

    public RightButton getRightButton() {
        return rightButton;
    }

    public RectangleButton getRectangleButton() {
        return rectangleButton;
    }

    public Inspector getInspector() {
        return inspector;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public BaggageScanner getBaggageScanner() {
        return baggageScanner;
    }
}

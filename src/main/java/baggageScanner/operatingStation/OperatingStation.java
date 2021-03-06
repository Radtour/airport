package baggageScanner.operatingStation;

import baggageScanner.BaggageScanner;
import baggageScanner.buttons.LeftButton;
import baggageScanner.buttons.RectangleButton;
import baggageScanner.buttons.RightButton;
import employee.Inspector;

public class OperatingStation {
    private final LeftButton leftButton;
    private final RightButton rightButton;
    private final RectangleButton rectangleButton;
    private final Inspector inspector;
    private final Scanner scanner;
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
        rectangleButton.push(inspector);
    }

    public void pushLeftButton(){
        leftButton.push(inspector);
    }

    public void pushRightButton(){
        rightButton.push(inspector);
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

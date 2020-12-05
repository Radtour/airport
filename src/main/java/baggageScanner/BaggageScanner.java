package baggageScanner;

import baggage.HandBaggage;
import baggage.Passenger;
import baggageScanner.conveyingComponents.Belt;
import baggageScanner.conveyingComponents.RollerConveyor;
import baggageScanner.conveyingComponents.Track;
import baggageScanner.conveyingComponents.Tray;
import baggageScanner.operatingStation.OperatingStation;
import configuration.Configuration;
import employee.*;
import employee.id.ProfileManager;
import federalPolice.FederalPoliceOffice;
import federalPolice.FederalPoliceOfficer;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BaggageScanner {
    private BaggageScannerStatus status;
    private FederalPoliceOfficer officer;
    private Supervision supervision;
    private Belt belt;
    private RollerConveyor rollerConveyor;
    private OperatingStation operatingStation;
    private ManualPostControl manualPostControl;
    private Track[] tracks;
    private List<Record> record;
    private Technician technician;
    private HouseKeeper houseKeeper;
    private Tray currentTrayInScanner;

    public BaggageScanner(Supervisor supervisorS0, Inspector inspectorI1, Inspector inspectorI2, Inspector inspectorI3, Technician technician, FederalPoliceOfficer federalPoliceOfficerO1, HouseKeeper houseKeeper){
        this.technician = technician;
        this.houseKeeper = houseKeeper;
        this.officer = federalPoliceOfficerO1;
        inspectorI1.setBaggageScanner(this);
        inspectorI2.setBaggageScanner(this);
        inspectorI3.setBaggageScanner(this);
        technician.setBaggageScanner(this);
        federalPoliceOfficerO1.setBaggageScanner(this);
        supervisorS0.setBaggageScanner(this);
        houseKeeper.setBaggageScanner(this);


        record = new ArrayList<>();

        supervision = new Supervision(supervisorS0,this);
        operatingStation = new OperatingStation(inspectorI2,this);
        manualPostControl = new ManualPostControl(inspectorI3, this);


        rollerConveyor = new RollerConveyor(inspectorI1,this);

        belt = new Belt(this);

        tracks = new Track[2];
        tracks[0] = new Track(1);
        tracks[1] = new Track(2);

    }

    public BaggageScannerStatus getStatus() {
        return status;
    }

    public FederalPoliceOfficer getOfficer() {
        return officer;
    }

    public Supervision getSupervision() {
        return supervision;
    }

    public Belt getBelt() {
        return belt;
    }

    public RollerConveyor getRollerConveyor() {
        return rollerConveyor;
    }

    public OperatingStation getOperatingStation() {
        return operatingStation;
    }

    public Track[] getTracks() {
        return tracks;
    }

    public Technician getTechnician() {
        return technician;
    }

    public HouseKeeper getHouseKeeper() {
        return houseKeeper;
    }

    public ManualPostControl getManualPostControl() {
        return manualPostControl;
    }

    public List<Record> getRecords() {
        return record;
    }

    public void scan(HandBaggage handBaggage){
        boolean clean = true;
        for(int i = 0; i < 5; i++){
            for (int j = 0; j < 9996; j++){
                if(handBaggage.getLayers()[i].getContent()[j] == 'k'
                        && handBaggage.getLayers()[i].getContent()[j+1] == 'n'
                        && handBaggage.getLayers()[i].getContent()[j+2] == '!'
                        && handBaggage.getLayers()[i].getContent()[j+3] == 'f'
                        && handBaggage.getLayers()[i].getContent()[j+4] == 'e'){
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm:ss,SSS");
                    LocalDateTime now = LocalDateTime.now();
                    record.add(new Record(dtf.format(now),"prohibited item | knife detected at position" + i));
                    clean = false;
                }
            }
            for (int j = 0; j < 9994; j++){
                if(handBaggage.getLayers()[i].getContent()[j] == 'g'
                        && handBaggage.getLayers()[i].getContent()[j+1] == 'l'
                        && handBaggage.getLayers()[i].getContent()[j+2] == 'o'
                        && handBaggage.getLayers()[i].getContent()[j+3] == 'c'
                        && handBaggage.getLayers()[i].getContent()[j+4] == 'k'
                        && handBaggage.getLayers()[i].getContent()[j+5] == '|'
                        && handBaggage.getLayers()[i].getContent()[j+6] == '7'){
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm:ss,SSS");
                    LocalDateTime now = LocalDateTime.now();
                    record.add(new Record(dtf.format(now),"prohibited item | weapon - glock7 detected at position" + i));
                    clean = false;
                }
            }
            for (int j = 0; j < 9992; j++){
                if(handBaggage.getLayers()[i].getContent()[j] == 'e'
                        && handBaggage.getLayers()[i].getContent()[j+1] == 'x'
                        && handBaggage.getLayers()[i].getContent()[j+2] == 'p'
                        && handBaggage.getLayers()[i].getContent()[j+3] == '|'
                        && handBaggage.getLayers()[i].getContent()[j+4] == 'o'
                        && handBaggage.getLayers()[i].getContent()[j+5] == 's'
                        && handBaggage.getLayers()[i].getContent()[j+6] == '!'
                        && handBaggage.getLayers()[i].getContent()[j+7] == 'v'
                        && handBaggage.getLayers()[i].getContent()[j+8] == 'e'){
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm:ss,SSS");
                    LocalDateTime now = LocalDateTime.now();
                    record.add(new Record(dtf.format(now),"prohibited item | explosive detected at position" + i));
                    clean = false;
                }
            }
        }
        if(clean){
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm:ss,SSS");
            LocalDateTime now = LocalDateTime.now();
            record.add(new Record(dtf.format(now),"clean"));
        }
    }

    public boolean moveBeltForward(Employee employee) {
        if(ProfileManager.isAllowedToUseMoveBeltForward(employee) && ProfileManager.isAllowedToUseBaggageScanner(employee)){


            return true;
        }
        return false;
    }

    public boolean moveBeltBackwards(Employee employee) {
        if(ProfileManager.isAllowedToUseMoveBeltBackward(employee) && ProfileManager.isAllowedToUseBaggageScanner(employee)){


            return true;
        }
        return false;
    }

    public boolean alarm(Employee employee) {
        if(ProfileManager.isAllowedToUseAlarm(employee) && ProfileManager.isAllowedToUseBaggageScanner(employee)){


            return true;
        }
        return false;
    }
    public boolean report(Employee employee) {
        if (ProfileManager.isAllowedToUseReport(employee) && ProfileManager.isAllowedToUseBaggageScanner(employee)) {


            return true;
        }
        return false;
    }

    public boolean maintenance(Employee employee) {
        if(ProfileManager.isAllowedToUseMaintenance(employee) && ProfileManager.isAllowedToUseBaggageScanner(employee)){


            return true;
        }
        return false;
    }

    public boolean unlock(Employee employee){
        if(ProfileManager.isAllowedToUnlock(employee) && ProfileManager.isAllowedToUseBaggageScanner(employee)){
            status = BaggageScannerStatus.activated;
            return true;
        }
        return false;
    }

    public boolean activate(Employee employee) {
        if(ProfileManager.isAllowedToActivate(employee) && ProfileManager.isAllowedToUseBaggageScanner(employee)){
            status = BaggageScannerStatus.activated;
            return true;
        }
        return false;
    }

    public boolean start(Employee employee) {
        if(ProfileManager.isAllowedToStart(employee) && ProfileManager.isAllowedToUseBaggageScanner(employee)){
            status = BaggageScannerStatus.deactivated;
            return true;
        }
        return false;
    }

    public void setStatus(BaggageScannerStatus status) {
        this.status = status;
    }

    public Tray getCurrentTrayInScanner() {
        return currentTrayInScanner;
    }

    public void setCurrentTrayInScanner(Tray currentTrayInScanner) {
        this.currentTrayInScanner = currentTrayInScanner;
    }

    public void simulation(Passenger passenger){
        getSupervision().getSupervisor().pushStartButton();
        if(getStatus() == BaggageScannerStatus.deactivated){
            boolean validated = getOperatingStation().getInspector().swipeCard();
            if(validated){
                getManualPostControl().setCurrentPassenger(passenger);
                var handBaggages = getManualPostControl().getCurrentPassenger().getHandBaggages();
                for(HandBaggage handBaggage : handBaggages){
                    Tray tray = new Tray();
                    tray.setHandBaggage(handBaggage);
                    getRollerConveyor().addTrayQueue(tray);
                }

                for(Tray tray : getRollerConveyor().getTrays()){
                    getOperatingStation().getInspector().push(tray);
                    getOperatingStation().pushRightButton();
                    getOperatingStation().pushRectagleButton();
                    Record record = getRecords().get(getRecords().size());
                }
            }
        }
    }
}

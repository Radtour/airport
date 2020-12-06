package baggageScanner;

import baggage.HandBaggage;
import baggage.Layer;
import baggage.Passenger;
import baggageScanner.conveyingComponents.Belt;
import baggageScanner.conveyingComponents.RollerConveyor;
import baggageScanner.conveyingComponents.Track;
import baggageScanner.conveyingComponents.Tray;
import baggageScanner.operatingStation.OperatingStation;
import configuration.AlgorithmType;
import configuration.Configuration;
import employee.*;
import employee.id.ProfileManager;
import federalPolice.FederalPoliceOfficer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BaggageScanner {
    private BaggageScannerStatus status;
    private final FederalPoliceOfficer officer;
    private final Supervision supervision;
    private final Belt belt;
    private final RollerConveyor rollerConveyor;
    private final OperatingStation operatingStation;
    private final ManualPostControl manualPostControl;
    private final Track[] tracks;
    private final List<Record> record;
    private final Technician technician;
    private final HouseKeeper houseKeeper;
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

        for(int j = 0; j < handBaggage.getLayers().length; j++){
            Layer layer = handBaggage.getLayers()[j];
            StringBuilder layerSequentialBuilder = new StringBuilder();
            for(int i = 0; i < layer.getContent().length; i++){
                layerSequentialBuilder.append(layer.getContent()[i]);
            }
            String layerSequential = layerSequentialBuilder.toString();
            if(Configuration.instance.algorithm == AlgorithmType.BoyerMoore){
                if(Configuration.instance.boyerMoore.search(layerSequential, Configuration.instance.knife) != -1){
                    if(createKnifeRecord(j)) {
                        clean = false;
                        break;
                    }
                }
                else if(Configuration.instance.boyerMoore.search(layerSequential, Configuration.instance.weapon)  != -1){
                    if(createWeaponRecord(j)) {
                        clean = false;
                        break;
                    }
                }
                else if(Configuration.instance.boyerMoore.search(layerSequential, Configuration.instance.explosive)  != -1){
                    if(createExplosiveRecord(j)) {
                        clean = false;
                        break;
                    }
                }
            }
            else if(Configuration.instance.algorithm == AlgorithmType.KnuthMorrisPratt){
                if(Configuration.instance.knuthMorrisPratt.search(layerSequential, Configuration.instance.knife)  != -1){
                    if(createKnifeRecord(j)) {
                        clean = false;
                        break;
                    }
                }
                else if(Configuration.instance.knuthMorrisPratt.search(layerSequential, Configuration.instance.weapon)  != -1){
                    if(createWeaponRecord(j)) {
                        clean = false;
                        break;
                    }
                }
                else if(Configuration.instance.knuthMorrisPratt.search(layerSequential, Configuration.instance.explosive)  != -1){
                    if(createExplosiveRecord(j)) {
                        clean = false;
                        break;
                    }
                }
            }
        }

        if(clean){
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm:ss,SSS");
            LocalDateTime now = LocalDateTime.now();
            record.add(new Record(dtf.format(now),"clean"));
        }
    }

    private boolean createKnifeRecord(int position){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm:ss,SSS");
        LocalDateTime now = LocalDateTime.now();
        return record.add(new Record(dtf.format(now),"prohibited item | knife detected at position " + position));
    }

    private boolean createWeaponRecord(int position){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm:ss,SSS");
        LocalDateTime now = LocalDateTime.now();
        return record.add(new Record(dtf.format(now),"prohibited item | weapon - glock7 detected at position " + position));
    }

    private boolean createExplosiveRecord(int position){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm:ss,SSS");
        LocalDateTime now = LocalDateTime.now();
        return record.add(new Record(dtf.format(now),"prohibited item | explosive detected at position " + position));
    }

    public boolean moveBeltForward(Employee employee) {
        if(ProfileManager.isAllowedToUseMoveBeltForward(employee) && ProfileManager.isAllowedToUseBaggageScanner(employee)){
            employee.getBaggageScanner().setCurrentTrayInScanner(employee.getBaggageScanner().getBelt().getTrays().poll());
            return true;
        }
        return false;
    }

    public boolean moveBeltBackwards(Employee employee) {
        return ProfileManager.isAllowedToUseMoveBeltBackward(employee) && ProfileManager.isAllowedToUseBaggageScanner(employee);
    }

    public boolean alarm(Employee employee) {
        if(ProfileManager.isAllowedToUseAlarm(employee) && ProfileManager.isAllowedToUseBaggageScanner(employee)){
            setStatus(BaggageScannerStatus.locked);
            getOfficer().arrest(getManualPostControl().getCurrentPassenger());
            return true;
        }
        return false;
    }
    public boolean report(Employee employee) {
        return ProfileManager.isAllowedToUseReport(employee) && ProfileManager.isAllowedToUseBaggageScanner(employee);
    }

    public boolean maintenance(Employee employee) {
        return ProfileManager.isAllowedToUseMaintenance(employee) && ProfileManager.isAllowedToUseBaggageScanner(employee);
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

    public boolean simulation(Passenger passenger){
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

                while(!getRollerConveyor().getTrays().isEmpty()){
                    Tray tray = getRollerConveyor().getTrays().poll();
                    getOperatingStation().getInspector().push(tray);
                    getOperatingStation().pushRightButton();
                    getOperatingStation().pushRectagleButton();
                    Record record = getRecords().get(getRecords().size() - 1);
                    if(record.getResult().contains("clean")){
                        tracks[1].addTrayToList(tray);
                        setCurrentTrayInScanner(null);

                    }
                    else if(record.getResult().contains("knife")){
                        tracks[0].addTrayToList(tray);
                        setCurrentTrayInScanner(null);
                        getManualPostControl().getInspector().inspect();
                        Record recordRetry = getRecords().get(getRecords().size() - 1);
                        if(recordRetry.getResult().contains("clean")){
                            tracks[1].addTrayToList(tray);
                            setCurrentTrayInScanner(null);
                        }
                    }
                    else{
                        tracks[0].addTrayToList(tray);
                        setCurrentTrayInScanner(null);
                        alarm(getOperatingStation().getInspector());
                        return true;
                    }
                }
                return true;
            }
        }
        return false;
    }
}

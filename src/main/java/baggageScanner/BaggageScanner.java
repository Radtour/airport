package baggageScanner;

import baggageScanner.conveyingComponents.Belt;
import baggageScanner.conveyingComponents.RollerConveyor;
import baggageScanner.conveyingComponents.Track;
import baggageScanner.operatingStation.OperatingStation;
import employee.HouseKeeper;
import employee.Inspector;
import employee.Supervisor;
import employee.Technician;
import federalPolice.FederalPoliceOffice;
import federalPolice.FederalPoliceOfficer;

public class BaggageScanner {
    private BaggageScannerStatus status;
    private FederalPoliceOfficer officer;
    private Supervision supervision;
    private Belt belt;
    private RollerConveyor rollerConveyor;
    private OperatingStation operatingStation;
    private ManualPostControl manualPostControl;
    private Track[] tracks;
    private Technician technician;
    private HouseKeeper houseKeeper;

    public BaggageScanner(Supervisor supervisorS0, Inspector inspectorI1, Inspector inspectorI2, Inspector inspectorI3, Technician technician, FederalPoliceOfficer federalPoliceOfficer, HouseKeeper houseKeeper){
        this.technician = technician;
        this.houseKeeper = houseKeeper;
        this.officer = federalPoliceOfficer;

        supervision = new Supervision(supervisorS0,this);
        operatingStation = new OperatingStation(inspectorI2,this);
        manualPostControl = new ManualPostControl(inspectorI3, this);


        rollerConveyor = new RollerConveyor(inspectorI1,this);

        belt = new Belt(this);

        tracks = new Track[2];
        tracks[0] = new Track(1);
        tracks[1] = new Track(2);

    }

    public void setStatus(BaggageScannerStatus status) {
        this.status = status;
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
}

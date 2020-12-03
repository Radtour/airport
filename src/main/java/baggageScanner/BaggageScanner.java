package baggageScanner;

import baggageScanner.conveyingComponents.Belt;
import baggageScanner.conveyingComponents.RollerConveyor;
import baggageScanner.conveyingComponents.Track;
import baggageScanner.operatingStation.OperatingStation;
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
    private Track[] tracks;
    private Technician technician;

    public BaggageScanner(Supervisor supervisorS0, Inspector inspectorI1, Inspector inspectorI2, Inspector inspectorI3, Technician technician, FederalPoliceOfficer federalPoliceOfficer){
        this.technician = technician;

        supervision = new Supervision(supervisorS0,this);
        operatingStation = new OperatingStation(inspectorI2,this);


        rollerConveyor = new RollerConveyor(inspectorI1,this);

        belt = new Belt(this);

        tracks[0] = new Track(1);
        tracks[1] = new Track(2);

    }

    public void setStatus(BaggageScannerStatus status) {
        this.status = status;
    }
}

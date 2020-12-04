import baggage.HandBaggage;
import baggage.Passenger;
import baggageScanner.BaggageScanner;
import baggageScanner.BaggageScannerStatus;
import baggageScanner.operatingStation.Scanner;
import configuration.Configuration;
import employee.HouseKeeper;
import employee.Inspector;
import employee.Supervisor;
import employee.Technician;
import employee.id.IDCard;
import employee.id.IDType;
import employee.id.ProfileManager;
import employee.id.ProfileType;
import federalPolice.FederalPoliceOffice;
import federalPolice.FederalPoliceOfficer;
import org.junit.jupiter.api.*;

import java.io.*;
import java.text.ParseException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class AirportTest {
    @BeforeEach
    public void cleanup(){
        HandBaggage.setHandBaggageHashSet(new HashSet<>());
    }

    BaggageScanner baggageScanner;
    FederalPoliceOffice federalPoliceOffice;
    IDCard idCardI1;
    IDCard idCardI2;
    IDCard idCardI3;
    IDCard idCardS;
    IDCard idCardO1;
    IDCard idCardO2;
    IDCard idCardO3;
    IDCard idCardT;
    IDCard idCardK;
    Inspector inspectorI1;
    Inspector inspectorI2;
    Inspector inspectorI3;
    Supervisor supervisorS;
    FederalPoliceOfficer officerO1;
    FederalPoliceOfficer officerO2;
    FederalPoliceOfficer officerO3;
    Technician technician;
    HouseKeeper houseKeeper;

    private void initOfficeAndBaggageScanner(){
        try {
            idCardI1 = new IDCard(UUID.randomUUID().toString(), IDType.staff);
            idCardI2 = new IDCard(UUID.randomUUID().toString(), IDType.staff);
            idCardI3 = new IDCard(UUID.randomUUID().toString(), IDType.staff);
            idCardS = new IDCard(UUID.randomUUID().toString(), IDType.staff);
            idCardO1 = new IDCard(UUID.randomUUID().toString(), IDType.external);
            idCardO2 = new IDCard(UUID.randomUUID().toString(), IDType.external);
            idCardO3 = new IDCard(UUID.randomUUID().toString(), IDType.external);
            idCardT = new IDCard(UUID.randomUUID().toString(), IDType.external);
            idCardK = new IDCard(UUID.randomUUID().toString(), IDType.external);
            inspectorI1 = new Inspector(ProfileType.I, "Clint Eastwood", Configuration.instance.dateFormatShort.parse("31.05.1930"), idCardI1, true);
            inspectorI2 = new Inspector(ProfileType.I, "Natalie Portman", Configuration.instance.dateFormatShort.parse("09.06.1981"), idCardI2, false);
            inspectorI3 = new Inspector(ProfileType.I, "Bruce Willis", Configuration.instance.dateFormatShort.parse("19.03.1955"), idCardI3, true);
            supervisorS = new Supervisor(ProfileType.S, "Jodie Foster", Configuration.instance.dateFormatShort.parse("19.11.1962"), idCardS, false, false);
            officerO1 = new FederalPoliceOfficer("Officer", ProfileType.O, "Wesley Snipes", Configuration.instance.dateFormatShort.parse("31.07.1962"), idCardO1);
            officerO2 = new FederalPoliceOfficer("Officer", ProfileType.O, "Toto", Configuration.instance.dateFormatShort.parse("01.01.1969"), idCardO2);
            officerO3 = new FederalPoliceOfficer("Officer", ProfileType.O, "Harry", Configuration.instance.dateFormatShort.parse("01.01.1969"), idCardO3);
            technician = new Technician(ProfileType.T, "Jason Statham", Configuration.instance.dateFormatShort.parse("26.07.1967"), idCardT);
            houseKeeper = new HouseKeeper(ProfileType.K, "Jason Clarke", Configuration.instance.dateFormatShort.parse("17.07.1969"), idCardK);
            idCardI1.writeMagnetStripe(inspectorI1);
            idCardI2.writeMagnetStripe(inspectorI2);
            idCardI3.writeMagnetStripe(inspectorI3);
            idCardK.writeMagnetStripe(houseKeeper);
            idCardO1.writeMagnetStripe(officerO1);
            idCardO2.writeMagnetStripe(officerO2);
            idCardO3.writeMagnetStripe(officerO3);
            idCardS.writeMagnetStripe(supervisorS);
            idCardT.writeMagnetStripe(technician);

            baggageScanner = new BaggageScanner(supervisorS, inspectorI1, inspectorI2, inspectorI3, technician, officerO1, houseKeeper);
            federalPoliceOffice = new FederalPoliceOffice(officerO2, officerO3);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(1)
    public void correctInit(){
        List<Passenger> passengers = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("data/passenger_baggage.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String give;

            //TODO zeile für zeile einlesen bis datei leer ist
            for(int i=0; i<568; i++){
                String line = bufferedReader.readLine();
                String[] split = line.split(";");
                if(split.length>3){
                    split[2]= split[2] + ";" + split[3];
                }
                if(split[2].equals("-")){
                    give = "";
                }
                else {
                    give = split[2].substring(1,split[2].length()-1);
                }
                passengers.add(new Passenger(split[0],Integer.parseInt(split[1]),give));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(568, passengers.size());
        Assertions.assertEquals(609, HandBaggage.getHandBaggageHashSet().size());
    }

    @Test
    @Order(2)
    public void setBaggageScannerAndEmployees(){
        initOfficeAndBaggageScanner();

        assertEquals(technician, baggageScanner.getTechnician());
        assertEquals(inspectorI1, baggageScanner.getRollerConveyor().getInspector());
        assertEquals(inspectorI2, baggageScanner.getOperatingStation().getInspector());
        assertEquals(inspectorI3, baggageScanner.getManualPostControl().getInspector());
        assertEquals(officerO1, baggageScanner.getOfficer());
        assertEquals(supervisorS, baggageScanner.getSupervision().getSupervisor());
        assertEquals(houseKeeper, baggageScanner.getHouseKeeper());
        assertEquals(officerO2, federalPoliceOffice.getOfficers()[0]);
        assertEquals(officerO3, federalPoliceOffice.getOfficers()[1]);
    }

    @Test
    @Order(3)
    public void threeTimeWrongPin(){
        initOfficeAndBaggageScanner();
        Scanner scanner = baggageScanner.getOperatingStation().getScanner();
        scanner.swipeCard(inspectorI1.getIdCard());
        assertFalse(inspectorI1.getIdCard().getLocked());
        scanner.inputPIN("WRONG");
        assertFalse(inspectorI1.getIdCard().getLocked());
        scanner.inputPIN("SCHING SCHONG DEIN PIN IS WRONG");
        assertFalse(inspectorI1.getIdCard().getLocked());
        scanner.inputPIN("WRONG");
        assertTrue(inspectorI1.getIdCard().getLocked());
    }

    @Test
    @Order(4)
    public void profileKO(){
        initOfficeAndBaggageScanner();

        assertTrue(ProfileManager.isAllowedToUseBaggageScanner(inspectorI1));
        assertTrue(ProfileManager.isAllowedToUseBaggageScanner(supervisorS));
        assertTrue(ProfileManager.isAllowedToUseBaggageScanner(technician));
        assertFalse(ProfileManager.isAllowedToUseBaggageScanner(officerO1));
        assertFalse(ProfileManager.isAllowedToUseBaggageScanner(houseKeeper));
        Scanner scanner = baggageScanner.getOperatingStation().getScanner();
        assertTrue(scanner.swipeCard(inspectorI1.getIdCard()));
        assertTrue(scanner.swipeCard(supervisorS.getIdCard()));
        assertTrue(scanner.swipeCard(technician.getIdCard()));
        assertFalse(scanner.swipeCard(officerO1.getIdCard()));
        assertFalse(scanner.swipeCard(houseKeeper.getIdCard()));
    }

    @Test
    @Order(5)
    public void onlyFans(){

    }

    @Test
    @Order(6)
    public void unlockBaggageScanner(){
        initOfficeAndBaggageScanner();
        baggageScanner.setStatus(BaggageScannerStatus.locked);
        assertFalse(baggageScanner.unlock(inspectorI1));
        assertEquals(BaggageScannerStatus.locked, baggageScanner.getStatus());
        assertFalse(baggageScanner.unlock(inspectorI2));
        assertEquals(BaggageScannerStatus.locked, baggageScanner.getStatus());
        assertFalse(baggageScanner.unlock(inspectorI3));
        assertEquals(BaggageScannerStatus.locked, baggageScanner.getStatus());
        assertFalse(baggageScanner.unlock(officerO1));
        assertEquals(BaggageScannerStatus.locked, baggageScanner.getStatus());
        assertFalse(baggageScanner.unlock(officerO2));
        assertEquals(BaggageScannerStatus.locked, baggageScanner.getStatus());
        assertFalse(baggageScanner.unlock(officerO3));
        assertEquals(BaggageScannerStatus.locked, baggageScanner.getStatus());
        assertFalse(baggageScanner.unlock(technician));
        assertEquals(BaggageScannerStatus.locked, baggageScanner.getStatus());
        assertFalse(baggageScanner.unlock(houseKeeper));
        assertEquals(BaggageScannerStatus.locked, baggageScanner.getStatus());
        assertTrue(baggageScanner.unlock(supervisorS));
        assertEquals(BaggageScannerStatus.activated, baggageScanner.getStatus());
        assertFalse(ProfileManager.isAllowedToUnlock(inspectorI1));
        assertFalse(ProfileManager.isAllowedToUnlock(inspectorI2));
        assertFalse(ProfileManager.isAllowedToUnlock(inspectorI3));
        assertFalse(ProfileManager.isAllowedToUnlock(officerO1));
        assertFalse(ProfileManager.isAllowedToUnlock(officerO2));
        assertFalse(ProfileManager.isAllowedToUnlock(officerO3));
        assertFalse(ProfileManager.isAllowedToUnlock(technician));
        assertFalse(ProfileManager.isAllowedToUnlock(houseKeeper));
        assertTrue(ProfileManager.isAllowedToUnlock(supervisorS));
    }

    @Test
    @Order(7)
    public void findKnife(){
        initOfficeAndBaggageScanner();

        Passenger passenger = new Passenger("Klaus Mayer Max Mustermann",1,"K,1,3");
        baggageScanner.scan(passenger.getHandBaggages()[0]);
        assertTrue(baggageScanner.getRecord().get(0).getResult().contains("knife"));
    }

    @Test
    @Order(8)
    public void findGun(){
        initOfficeAndBaggageScanner();

        Passenger passenger = new Passenger("Klaus Mayer Max Mustermann",1,"W,1,3");
        baggageScanner.scan(passenger.getHandBaggages()[0]);
        assertTrue(baggageScanner.getRecord().get(0).getResult().contains("glock"));
    }

    @Test
    @Order(9)
    public void findExlosive(){
        initOfficeAndBaggageScanner();

        Passenger passenger = new Passenger("Klaus Mayer Max Mustermann",1,"E,1,3");
        baggageScanner.scan(passenger.getHandBaggages()[0]);
        assertTrue(baggageScanner.getRecord().get(0).getResult().contains("explosive"));
    }

    @Test
    @Order(10)
    public void logScans(){

    }

    @Test
    @Order(11)
    public void noIllegalItems(){

    }

    @Test
    @Order(12)
    public void knifeFound(){

    }

    @Test
    @Order(13)
    public void gunFound(){

    }

    @Test
    @Order(14)
    public void explosiveFound(){

    }
}

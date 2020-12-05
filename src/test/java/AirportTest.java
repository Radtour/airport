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
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

public class AirportTest {
    @BeforeEach
    public void cleanup(){
        HandBaggage.setHandBaggageHashSet(new HashSet<>());
        DataSet.setPassengerNumberIncrement(1);
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

    @Order(1)
    @TestFactory
    Stream<DynamicTest> correctInit(){
        return DataSet.parsePassengerBaggageFile("passenger_baggage.txt")
                .map(dataSet -> dynamicTest(getDisplayName(dataSet), () -> {
                    var passenger = new Passenger(dataSet.getPassengerName(), dataSet.getHandBaggageAmount(), dataSet.getHandBaggageContent());
                    assertTrue(passenger.getName().equals(dataSet.getPassengerName()));
                    assertEquals(dataSet.getHandBaggageAmount(), passenger.getHandBaggages().length);
                }));
    }

    String getDisplayName(DataSet dataSet){
        return dataSet.getPassengerNumber() + " " + dataSet.getPassengerName() + " " + dataSet.getHandBaggageAmount() + " " + dataSet.getHandBaggageContent();
    }

    @Order(2)
    @Test
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

    @Order(3)
    @Test
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

    @Order(4)
    @Test
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

    @Order(5)
    @Test
    public void onlyFans(){
        initOfficeAndBaggageScanner();

        assertTrue(ProfileManager.isAllowedToUseMoveBeltBackward(inspectorI1));
        assertTrue(ProfileManager.isAllowedToUseMoveBeltForward(inspectorI1));
        assertTrue(ProfileManager.isAllowedToUseBaggageScanner(inspectorI1));
        assertTrue(ProfileManager.isAllowedToUseAlarm(inspectorI1));
        assertFalse(ProfileManager.isAllowedToUseReport(inspectorI1));
        assertFalse(ProfileManager.isAllowedToUseMaintenance(inspectorI1));

        assertFalse(ProfileManager.isAllowedToUseMoveBeltBackward(officerO1));
        assertFalse(ProfileManager.isAllowedToUseMoveBeltForward(officerO1));
        assertFalse(ProfileManager.isAllowedToUseBaggageScanner(officerO1));
        assertFalse(ProfileManager.isAllowedToUseAlarm(officerO1));
        assertFalse(ProfileManager.isAllowedToUseReport(officerO1));
        assertFalse(ProfileManager.isAllowedToUseMaintenance(officerO1));

        assertFalse(ProfileManager.isAllowedToUseMoveBeltBackward(supervisorS));
        assertFalse(ProfileManager.isAllowedToUseMoveBeltForward(supervisorS));
        assertTrue(ProfileManager.isAllowedToUseBaggageScanner(supervisorS));
        assertFalse(ProfileManager.isAllowedToUseAlarm(supervisorS));
        assertTrue(ProfileManager.isAllowedToUseReport(supervisorS));
        assertFalse(ProfileManager.isAllowedToUseMaintenance(supervisorS));

        assertFalse(ProfileManager.isAllowedToUseMoveBeltBackward(houseKeeper));
        assertFalse(ProfileManager.isAllowedToUseMoveBeltForward(houseKeeper));
        assertFalse(ProfileManager.isAllowedToUseBaggageScanner(houseKeeper));
        assertFalse(ProfileManager.isAllowedToUseAlarm(houseKeeper));
        assertFalse(ProfileManager.isAllowedToUseReport(houseKeeper));
        assertFalse(ProfileManager.isAllowedToUseMaintenance(houseKeeper));

        assertFalse(ProfileManager.isAllowedToUseMoveBeltBackward(technician));
        assertFalse(ProfileManager.isAllowedToUseMoveBeltForward(technician));
        assertTrue(ProfileManager.isAllowedToUseBaggageScanner(technician));
        assertFalse(ProfileManager.isAllowedToUseAlarm(technician));
        assertFalse(ProfileManager.isAllowedToUseReport(technician));
        assertTrue(ProfileManager.isAllowedToUseMaintenance(technician));
    }

    @Order(6)
    @Test
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

    @Order(7)
    @Test
    public void findKnife(){
        initOfficeAndBaggageScanner();

        Passenger passenger = new Passenger("Klaus Mayer Max Mustermann",1,"K,1,3");
        baggageScanner.scan(passenger.getHandBaggages()[0]);
        assertTrue(baggageScanner.getRecords().get(0).getResult().contains("knife"));
    }

    @Order(8)
    @Test
    public void findGun(){
        initOfficeAndBaggageScanner();

        Passenger passenger = new Passenger("Klaus Mayer Max Mustermann",3,"W,2,5");
        baggageScanner.scan(passenger.getHandBaggages()[0]);
        assertTrue(baggageScanner.getRecords().get(0).getResult().contains("clean"));
        baggageScanner.scan(passenger.getHandBaggages()[1]);
        assertTrue(baggageScanner.getRecords().get(1).getResult().contains("glock"));
        baggageScanner.scan(passenger.getHandBaggages()[2]);
        assertTrue(baggageScanner.getRecords().get(2).getResult().contains("clean"));
    }

    @Order(9)
    @Test
    public void findExlosive(){
        initOfficeAndBaggageScanner();

        Passenger passenger = new Passenger("Klaus Mayer Max Mustermann",1,"E,1,3");
        baggageScanner.scan(passenger.getHandBaggages()[0]);
        assertTrue(baggageScanner.getRecords().get(0).getResult().contains("explosive"));
    }

    @Order(10)
    @TestFactory
    Stream<DynamicTest> logScans(){
            initOfficeAndBaggageScanner();

            /*Passenger passenger1 = new Passenger("Mustaf Ali Baba",2,"-");
            Passenger passenger2 = new Passenger("Meister Propper",1,"E,1,3");
            Passenger passenger3 = new Passenger("Jensy Boy",3,"-");
            Passenger passenger4 = new Passenger("Not nothacker",1,"-");
            Passenger passenger5 = new Passenger("Krisch",3,"-");

            baggageScanner.scan(passenger1.getHandBaggages()[0]);
            baggageScanner.scan(passenger1.getHandBaggages()[1]);
            baggageScanner.scan(passenger2.getHandBaggages()[0]);
            baggageScanner.scan(passenger3.getHandBaggages()[0]);
            baggageScanner.scan(passenger3.getHandBaggages()[1]);
            baggageScanner.scan(passenger3.getHandBaggages()[2]);
            baggageScanner.scan(passenger4.getHandBaggages()[0]);
            baggageScanner.scan(passenger5.getHandBaggages()[0]);
            baggageScanner.scan(passenger5.getHandBaggages()[1]);
            baggageScanner.scan(passenger5.getHandBaggages()[2]);

            assertEquals(passenger1.getHandBaggages().length
                    +passenger2.getHandBaggages().length
                    +passenger3.getHandBaggages().length
                    +passenger4.getHandBaggages().length
                    +passenger5.getHandBaggages().length
                    ,baggageScanner.getRecords().size());*/

        return DataSet.parsePassengerBaggageFile("passenger_baggage.txt")
                .map(dataSet -> dynamicTest(getDisplayNameLogScans(dataSet), () -> {
                    var passenger = new Passenger(dataSet.getPassengerName(), dataSet.getHandBaggageAmount(), dataSet.getHandBaggageContent());

                    List<String[]> baggageInfoList = new ArrayList<>();
                    var baggageInfo = dataSet.getHandBaggageContent().split(";");

                    for (int i = 0; i < baggageInfo.length; i++){
                        baggageInfoList.add(baggageInfo[i].split(","));
                    }

                    for (int i = 0; i < dataSet.getHandBaggageAmount(); i++) {
                        baggageScanner.scan(passenger.getHandBaggages()[i]);
                        if(dataSet.getHandBaggageContent().contains("-")){
                            assertTrue(baggageScanner.getRecords().get(baggageScanner.getRecords().size() - 1).getResult().contains("clean"));
                        }
                        for(String[] baggageInfoUnique : baggageInfoList){
                            if(baggageInfoUnique[0].equals("K") && Integer.parseInt(baggageInfoUnique[1]) == (i - 1)){
                                assertTrue(baggageScanner.getRecords().get(baggageScanner.getRecords().size() - 1).getResult().contains("knife"));
                                assertTrue(baggageScanner.getRecords().get(baggageScanner.getRecords().size() - 1).getResult().contains(baggageInfoUnique[2]));
                            }
                            if(baggageInfoUnique[0].equals("W") && Integer.parseInt(baggageInfoUnique[1]) == (i - 1)){
                                assertTrue(baggageScanner.getRecords().get(baggageScanner.getRecords().size() - 1).getResult().contains("weapon"));
                                assertTrue(baggageScanner.getRecords().get(baggageScanner.getRecords().size() - 1).getResult().contains(baggageInfoUnique[2]));

                            }
                            if(baggageInfoUnique[0].equals("E") && Integer.parseInt(baggageInfoUnique[1]) == (i - 1)){
                                assertTrue(baggageScanner.getRecords().get(baggageScanner.getRecords().size() - 1).getResult().contains("explosive"));
                                assertTrue(baggageScanner.getRecords().get(baggageScanner.getRecords().size() - 1).getResult().contains(baggageInfoUnique[2]));
                            }
                        }
                    }
                }));
    }

    private String getDisplayNameLogScans(DataSet dataSet) {
        return dataSet.getPassengerNumber() + " " + dataSet.getPassengerName() + " " + dataSet.getHandBaggageAmount() + " " + dataSet.getHandBaggageContent();
    }

    @Order(11)
    @Test
    public void noIllegalItems(){

    }

    @Order(12)
    @Test
    public void knifeFound(){

    }

    @Order(13)
    @Test
    public void gunFound(){

    }

    @Order(14)
    @Test
    public void explosiveFound(){

    }
}

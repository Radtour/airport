import baggage.Passenger;
import baggageScanner.BaggageScanner;
import baggageScanner.BaggageScannerStatus;
import configuration.Configuration;
import employee.HouseKeeper;
import employee.Inspector;
import employee.Supervisor;
import employee.Technician;
import employee.id.IDCard;
import employee.id.IDType;
import employee.id.ProfileType;
import federalPolice.FederalPoliceOffice;
import federalPolice.FederalPoliceOfficer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Application {
    public static void main(String [] args) throws Exception {
        Queue<Passenger> passengers = new LinkedList<>();
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

        IDCard idCardI1 = new IDCard(UUID.randomUUID().toString(), IDType.staff);
        IDCard idCardI2 = new IDCard(UUID.randomUUID().toString(), IDType.staff);
        IDCard idCardI3 = new IDCard(UUID.randomUUID().toString(), IDType.staff);
        IDCard idCardS = new IDCard(UUID.randomUUID().toString(), IDType.staff);
        IDCard idCardO1 = new IDCard(UUID.randomUUID().toString(), IDType.external);
        IDCard idCardO2 = new IDCard(UUID.randomUUID().toString(), IDType.external);
        IDCard idCardO3 = new IDCard(UUID.randomUUID().toString(), IDType.external);
        IDCard idCardT = new IDCard(UUID.randomUUID().toString(), IDType.external);
        IDCard idCardK = new IDCard(UUID.randomUUID().toString(), IDType.external);
        Inspector inspectorI1 = new Inspector(ProfileType.I,"Clint Eastwood", Configuration.instance.dateFormatShort.parse("31.05.1930"), idCardI1, true);
        Inspector inspectorI2 = new Inspector(ProfileType.I,"Natalie Portman", Configuration.instance.dateFormatShort.parse("09.06.1981"), idCardI2, false);
        Inspector inspectorI3 = new Inspector(ProfileType.I,"Bruce Willis", Configuration.instance.dateFormatShort.parse("19.03.1955"), idCardI3, true);
        Supervisor supervisorS = new Supervisor(ProfileType.S, "Jodie Foster", Configuration.instance.dateFormatShort.parse("19.11.1962"), idCardS, false, false);
        FederalPoliceOfficer officerO1 = new FederalPoliceOfficer("Officer", ProfileType.O, "Wesley Snipes", Configuration.instance.dateFormatShort.parse("31.07.1962"),  idCardO1);
        FederalPoliceOfficer officerO2 = new FederalPoliceOfficer("Officer", ProfileType.O, "Toto", Configuration.instance.dateFormatShort.parse("01.01.1969"),  idCardO2);
        FederalPoliceOfficer officerO3 = new FederalPoliceOfficer("Officer", ProfileType.O, "Harry", Configuration.instance.dateFormatShort.parse("01.01.1969"),  idCardO3);
        Technician technician = new Technician(ProfileType.T, "Jason Statham", Configuration.instance.dateFormatShort.parse("26.07.1967"), idCardT);
        HouseKeeper houseKeeper = new HouseKeeper(ProfileType.K, "Jason Clarke", Configuration.instance.dateFormatShort.parse("17.07.1969"), idCardK);
        idCardI1.writeMagnetStripe(inspectorI1);
        idCardI2.writeMagnetStripe(inspectorI2);
        idCardI3.writeMagnetStripe(inspectorI3);
        idCardK.writeMagnetStripe(houseKeeper);
        idCardO1.writeMagnetStripe(officerO1);
        idCardO2.writeMagnetStripe(officerO2);
        idCardO3.writeMagnetStripe(officerO3);
        idCardS.writeMagnetStripe(supervisorS);
        idCardT.writeMagnetStripe(technician);

        BaggageScanner baggageScanner = new BaggageScanner(supervisorS,inspectorI1,inspectorI2,inspectorI3,technician,officerO1,houseKeeper);
        FederalPoliceOffice federalPoliceOffice = new FederalPoliceOffice(officerO2, officerO3);

        supervisorS.pushStartButton();
        if(baggageScanner.getStatus() == BaggageScannerStatus.deactivated){
            inspectorI2.swipeCard();
        }
        else{
            throw new Exception("Terminate Application in Application");
        }
    }
}

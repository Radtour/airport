import baggage.HandBaggage;
import baggage.Passenger;
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
import org.junit.jupiter.api.*;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class AirportTest {
    @Test
    @Order(1)
    public void init(){
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
        try{
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


        }
        catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(3)
    public void threeTimeWrongPin(){

    }

    @Test
    @Order(4)
    public void profileKO(){

    }

    @Test
    @Order(5)
    public void onlyFans(){

    }

    @Test
    @Order(6)
    public void unlockBaggageScanner(){

    }

    @Test
    @Order(7)
    public void detectKnife(){

    }

    @Test
    @Order(8)
    public void findKnife(){

    }

    @Test
    @Order(9)
    public void findGun(){

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

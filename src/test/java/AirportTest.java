import baggage.Passenger;
import org.junit.jupiter.api.*;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

public class AirportTest {
    @Test
    @Order(1)
    public void init(){
        Passenger passenger;
        try {
            FileReader fileReader = new FileReader("data/passenger_baggage.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String give;

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
                passenger = new Passenger(split[0],Integer.parseInt(split[1]),give);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(2)
    public void setBaggageScannerAndEmployees(){

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

import java.io.IOException;
import java.lang.reflect.UndeclaredThrowableException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class DataSet {
    private static int passengerNumberIncrement = 1;
    private final String passengerName;
    private final int handBaggageAmount;
    private final String handBaggageContent;
    private final int passengerNumber;

    public DataSet(String passengerName, int handBaggageAmount, String handBaggageContent, int passengerNumber) {
        this.passengerName = passengerName;
        this.handBaggageAmount = handBaggageAmount;
        this.handBaggageContent = handBaggageContent;
        this.passengerNumber = passengerNumber;
    }

    public static Stream<DataSet> parsePassengerBaggageFile(String filePath){
        try{
            URI resource = DataSet.class.getClassLoader().getResource(filePath).toURI();
            return Files.lines(Paths.get(resource)).map(DataSet::parseRule);
        } catch (URISyntaxException | IOException e) {
            throw new UndeclaredThrowableException(e);
        }
    }

    private static DataSet parseRule(String rule) {
        String[] dataSetSplit = rule.split(";");
        String passengerName = dataSetSplit[0];
        int handBaggageAmount = Integer.parseInt(dataSetSplit[1]);
        String handBaggageContent = dataSetSplit[2].replace("[", "").replace("]", "");


        DataSet dataSet = new DataSet(passengerName,handBaggageAmount, handBaggageContent, passengerNumberIncrement);
        passengerNumberIncrement++;
        return dataSet;
    }

    public int getHandBaggageAmount() {
        return handBaggageAmount;
    }

    public String getHandBaggageContent() {
        return handBaggageContent;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public int getPassengerNumber() {
        return passengerNumber;
    }
}

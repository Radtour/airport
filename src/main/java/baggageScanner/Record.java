package baggageScanner;

import java.text.DateFormat;
import java.util.Date;
import java.util.UUID;

public class Record {
    private String id;
    private String timeStamp;
    private String result;

    public Record(String timeStamp, String result) {
        this.id = UUID.randomUUID().toString();
        this.timeStamp = timeStamp;
        this.result = result;
    }

    public String getId() {
        return id;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public String getResult() {
        return result;
    }

}

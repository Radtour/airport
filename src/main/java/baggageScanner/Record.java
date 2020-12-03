package baggageScanner;

import java.util.Date;
import java.util.UUID;

public class Record {
    private String id;
    private Date timeStamp;
    private String result;

    public Record(Date timeStamp, String result) {
        this.id = UUID.randomUUID().toString();
        this.timeStamp = timeStamp;
        this.result = result;
    }

    public String getId() {
        return id;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public String getResult() {
        return result;
    }

}

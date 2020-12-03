package baggageScanner;

import java.util.Date;

public class Record {
    private String id;
    private Date timeStamp;
    private String result;

    public Record(String id, Date timeStamp, String result) {
        this.id = id;
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

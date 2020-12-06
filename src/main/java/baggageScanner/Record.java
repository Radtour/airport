package baggageScanner;

import java.util.UUID;

public class Record {
    private final String id;
    private final String timeStamp;
    private final String result;

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

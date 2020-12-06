package baggageScanner.conveyingComponents;

import java.util.LinkedList;
import java.util.Queue;

public class Track {
    int id;
    Queue<Tray> trayList;

    public Track(int id){
        this.id = id;
        this.trayList = new LinkedList<>();
    }

    public int getId() {
        return id;
    }

    public Queue<Tray> getTrayList() {
        return trayList;
    }

    public void addTrayToList(Tray tray){
        this.trayList.add(tray);
    }
}

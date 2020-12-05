package baggage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Passenger {

    private final String name;
    private HandBaggage[] handBaggages;
    private boolean isArrested;

    public Passenger(String name,int handBaggageAmount, String baggageInfoString){

        List<String[]> baggageInfoList = new ArrayList<>();

        this.name = name;

        this.handBaggages = new HandBaggage[handBaggageAmount];

        if(!baggageInfoString.contains("-")){

            var baggageInfo = baggageInfoString.split(";");

            for (int i = 0; i < baggageInfo.length; i++){
                baggageInfoList.add(baggageInfo[i].split(","));
            }
        }

        for (int i = 0; i < handBaggageAmount; i++){

            boolean hasIllegalItem = false;
            String[] information = new String[2];

            if(baggageInfoList.size() > 0) {
                for (String[] info : baggageInfoList) {
                    if (Integer.parseInt(info[1]) == i + 1) {
                        hasIllegalItem = true;
                        information[0] = info[0];
                        information[1] = info[2];
                    }
                }
            }

            if(hasIllegalItem){
                HandBaggage handBaggageTemp = new HandBaggage(information);
                this.handBaggages[i] = handBaggageTemp;
                HandBaggage.addHandBaggageToHashSet(handBaggageTemp);
            }
            else {
                HandBaggage handBaggageTemp = new HandBaggage();
                this.handBaggages[i] = handBaggageTemp;
                HandBaggage.addHandBaggageToHashSet(handBaggageTemp);
            }
        }
    }

    public HandBaggage[] getHandBaggages() {
        return handBaggages;
    }

    public void setHandBaggages(HandBaggage[] handBaggages) {
        this.handBaggages = handBaggages;
    }

    public String getName() {
        return name;
    }

    public void setArrested(boolean arrested) {
        isArrested = arrested;
    }

    public boolean isArrested() {
        return isArrested;
    }
}

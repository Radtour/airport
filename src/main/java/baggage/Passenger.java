package baggage;

import java.util.HashMap;

public class Passenger {

    private HandBaggage[] handBaggages;

    private String[][] baggageInfoMap;

    private String name;

    public Passenger(String name,int handBaggageAmount, String baggageInfoString){

        this.handBaggages = new HandBaggage[handBaggageAmount];

        if(!baggageInfoString.isEmpty()){
            this.baggageInfoMap = new String[handBaggageAmount][3];

            var baggageInfo = baggageInfoString.split(";");

            for (int i = 0; i < baggageInfo.length; i++){
                baggageInfoMap[i] = baggageInfo[i].split(",");
            }
        }

        for (int i = 0; i < handBaggageAmount; i++){

            boolean hasIllegalItem = false;
            String[] information = new String[2];

            if(baggageInfoMap != null) {
                for (String[] info : baggageInfoMap) {
                    if (Integer.parseInt(info[1]) == i) {
                        hasIllegalItem = true;
                        information[0] = info[0];
                        information[1] = info[2];
                    }
                }
            }

            if(hasIllegalItem)
                this.handBaggages[i] = new HandBaggage(information);
            else
                this.handBaggages[0] = new HandBaggage();
        }
    }

    public HandBaggage[] getHandBaggages() {
        return handBaggages;
    }

    public void setHandBaggages(HandBaggage[] handBaggages) {
        this.handBaggages = handBaggages;
    }
}

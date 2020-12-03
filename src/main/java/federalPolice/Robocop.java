package federalPolice;

import baggage.HandBaggage;

public class Robocop {
    private HighPressureWaterJet highPressureWaterJet;
    private Remote remote;
    public Robocop() {
        highPressureWaterJet = new HighPressureWaterJet();
        remote = new Remote(this);
    }

    public char[][] destroyHandBaggage(HandBaggage handbaggage){
        int x = 0;
        int y = 0;
        int z = 0;
        char[][] destroyedHandbaggage = new char[1000][50];

        for(int i=0; i<5; i++){
            z = 0;
            for(int j=0;j<10;j++){
                for(int k=0;k<1000;k++){
                    destroyedHandbaggage[k][y] = handbaggage.getLayers()[i].getContent()[z];
                    z++;
                }
                y++;
            }
        }
        return destroyedHandbaggage;
    }

    public Remote getRemote() {
        return remote;
    }
}

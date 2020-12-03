package baggageScanner;

public class ExplosiveTraceDetector {
    public ExplosiveTraceDetector() {

    }
    public boolean insert(TestStripe testStripe) {
        boolean hasExplosive = false;
        for (int i = 0; i<30; i++){
            for (int j = 0; j<8;j++){
                if(testStripe.getContent()[i][j]=='e'&& testStripe.getContent()[i][j+1]=='x'&& testStripe.getContent()[i][j+2]=='p'){
                    hasExplosive = true;
                    break;
                }
            }
        }

        for (int i = 0; i<10; i++){
            for (int j = 0; j<28;j++){
                if(testStripe.getContent()[j][i]=='e'&& testStripe.getContent()[j+1][i]=='x'&& testStripe.getContent()[j+2][i]=='p'){
                    hasExplosive = true;
                    break;
                }
            }
        }
        return hasExplosive;
    }
}

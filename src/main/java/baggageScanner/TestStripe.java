package baggageScanner;

import java.util.Random;

public class TestStripe {
    private char[][] content;
    public TestStripe() {
        content = new char[30][10];
        for (int i = 0; i < 30; i++){
            for (int j = 0; j < 10; j++){
                content[i][j]='p';
            }
        }
    }

    public void setEXP(){
        Random random = new Random();
        int horOrVer = random.nextInt(2);


        if(horOrVer==0){
            int layer = random.nextInt(30);
            int position = random.nextInt(8);

            content[layer][position]='e';
            content[layer][position+1]='x';
            content[layer][position+2]='p';
        }
        else {
            int layer = random.nextInt(28);
            int position = random.nextInt(10);

            content[layer][position]='e';
            content[layer+1][position]='x';
            content[layer+2][position]='p';
        }
    }

    public char[][] getContent() {
        return content;
    }
}

package baggage;

import java.util.Random;

public class Layer {
    private char[] content;

    public Layer(){
        this.content = new char[10000];
        Random r = new Random();

        for(int i = 0; i < 10000; i++){
            char c = (char)(r.nextInt(26) + 'a');
            content[i] = c;
        }
    }

    public void setContent(char[] content) {
        this.content = content;
    }

    public char[] getContent() {
        return content;
    }
}

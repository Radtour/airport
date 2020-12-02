package baggage;

public class Layer {
    private char[] content;

    public Layer(){
        this.content = new char[10000];
    }

    public void setContent(char[] content) {
        this.content = content;
    }

    public char[] getContent() {
        return content;
    }
}

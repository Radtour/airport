package federalPolice;

import baggage.HandBaggage;

public class Remote {
    private final Robocop robocop;
    public Remote(Robocop robocop){
        this.robocop = robocop;
    }
    public char[][] use(HandBaggage handBaggage){
        return robocop.destroyHandBaggage(handBaggage);
    }
}

package baggage;

import configuration.Configuration;

import java.util.HashSet;
import java.util.Random;

public class HandBaggage {

    private static HashSet<HandBaggage> handBaggageHashSet = new HashSet<>();
    private Layer[] layers;
    private char [][] destroyedHandBaggage;

    public HandBaggage(String[] baggageInfo){
        this.layers = new Layer[Configuration.instance.layerAmount];

        for(int i = 0; i < Configuration.instance.layerAmount; i++){
            this.layers[i] = new Layer();
        }

        Random random = new Random();

        switch (baggageInfo[0]) {
            case "K" -> {
                int layerLength = layers[Integer.parseInt(baggageInfo[1]) - 1].getContent().length;
                int illegalItemLength = Configuration.instance.knife.length();
                int r = random.nextInt(layerLength)- illegalItemLength;
                if(r < 0){
                    r = 0;
                }

                var content = layers[Integer.parseInt(baggageInfo[1]) - 1].getContent();

                for (int i = 0; i < Configuration.instance.knife.length(); i++) {
                    content[r + i] = Configuration.instance.knife.charAt(i);
                }

                layers[Integer.parseInt(baggageInfo[1]) - 1].setContent(content);

                break;
            }
            case "W" -> {
                int layerLength = layers[Integer.parseInt(baggageInfo[1]) - 1].getContent().length;
                int illegalItemLength = Configuration.instance.weapon.length();
                int r = random.nextInt(layerLength)- illegalItemLength;
                if(r < 0){
                    r = 0;
                }

                var content = layers[Integer.parseInt(baggageInfo[1]) - 1].getContent();

                for (int i = 0; i < Configuration.instance.weapon.length(); i++) {
                    content[r + i] = Configuration.instance.weapon.charAt(i);
                }

                layers[Integer.parseInt(baggageInfo[1]) - 1].setContent(content);

                break;
            }
            case "E" -> {
                int layerLength = layers[Integer.parseInt(baggageInfo[1]) - 1].getContent().length;
                int illegalItemLength = Configuration.instance.explosive.length();
                int r = random.nextInt(layerLength)- illegalItemLength;
                if(r < 0){
                    r = 0;
                }

                var content = layers[Integer.parseInt(baggageInfo[1]) - 1].getContent();

                for (int i = 0; i < Configuration.instance.explosive.length(); i++) {
                    content[r + i] = Configuration.instance.explosive.charAt(i);
                }

                layers[Integer.parseInt(baggageInfo[1]) - 1].setContent(content);

                break;
            }
        }
    }

    public HandBaggage() {
        this.layers = new Layer[Configuration.instance.layerAmount];

        for(int i = 0; i < Configuration.instance.layerAmount; i++){
            this.layers[i] = new Layer();
        }
    }

    public Layer[] getLayers() {
        return layers;
    }

    public void setLayers(Layer[] layers) {
        this.layers = layers;
    }

    public static HashSet<HandBaggage> getHandBaggageHashSet() {
        return handBaggageHashSet;
    }

    public static void addHandBaggageToHashSet(HandBaggage handBaggage){
        handBaggageHashSet.add(handBaggage);
    }

    public static void setHandBaggageHashSet(HashSet<HandBaggage> handBaggageHashSet) {
        HandBaggage.handBaggageHashSet = handBaggageHashSet;
    }

    public void setDestroyedHandBaggage(char[][] destroyedHandBaggage) {
        this.layers = null;
        this.destroyedHandBaggage = destroyedHandBaggage;
    }

    public char[][] getDestroyedHandBaggage() {
        return destroyedHandBaggage;
    }
}

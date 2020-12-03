package baggage;

import configuration.Configuration;

import java.util.Random;

public class HandBaggage {
    private Layer[] layers;

    public HandBaggage(String[] baggageInfo){
        this.layers = new Layer[Configuration.instance.layerAmount];

        Random random = new Random();

        int r = random.nextInt((layers[Integer.parseInt(baggageInfo[1])].getContent().length - Configuration.instance.knife.length()));
        if(r < 0){
            r = 0;
        }

        switch (baggageInfo[0]) {
            case "K" -> {
                var content = layers[Integer.parseInt(baggageInfo[1])].getContent();

                for (int i = 0; i < Configuration.instance.knife.length(); i++) {
                    content[r + i] = Configuration.instance.knife.charAt(i);
                }
                break;
            }
            case "W" -> {
                var content = layers[Integer.parseInt(baggageInfo[1])].getContent();

                for (int i = 0; i < Configuration.instance.weapon.length(); i++) {
                    content[r + i] = Configuration.instance.weapon.charAt(i);
                }
                break;
            }
            case "E" -> {
                var content = layers[Integer.parseInt(baggageInfo[1])].getContent();

                for (int i = 0; i < Configuration.instance.explosive.length(); i++) {
                    content[r + i] = Configuration.instance.explosive.charAt(i);
                }
                break;
            }
        }
    }

    public HandBaggage() {
        this.layers = new Layer[Configuration.instance.layerAmount];
    }

    public Layer[] getLayers() {
        return layers;
    }

    public void setLayers(Layer[] layers) {
        this.layers = layers;
    }
}

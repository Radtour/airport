package baggage;

import configuration.Configuration;

public class HandBaggage {
    private Layer[] layers;

    public HandBaggage(String[] baggageInfo){

        this.layers = new Layer[Configuration.instance.layerAmount];
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

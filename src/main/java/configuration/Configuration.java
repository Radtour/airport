package configuration;

public enum Configuration {
    instance;

    public final AlgorithmType algorithm = AlgorithmType.BoyerMoore; //sortingAlgorithm
    //KnuthMorrisPratt
    public final int layerAmount = 5;
}

package configuration;

public enum Configuration {
    instance;

    public final AlgorithmType algorithm = AlgorithmType.BoyerMoore; //sortingAlgorithm
    //KnuthMorrisPratt
    public final int layerAmount = 5;

    public final String knife = "kn!fe";
    public final String weapon = "glock|7";
    public final String explosive = "exp|os!ve";
}

package configuration;

import algorithms.AES;

public enum Configuration {
    instance;

    public final AlgorithmType algorithm = AlgorithmType.BoyerMoore; //sortingAlgorithm
    //KnuthMorrisPratt
    public final int layerAmount = 5;

    public final String knife = "kn!fe";
    public final String weapon = "glock|7";
    public final String explosive = "exp|os!ve";

    public final algorithms.AES aes = new AES();
    public final String secretKey = "dhbw$20^20_";
}

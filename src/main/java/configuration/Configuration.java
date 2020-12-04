package configuration;

import algorithms.AES;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    public final SimpleDateFormat dateFormatFull= new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
    public final SimpleDateFormat dateFormatShort= new SimpleDateFormat("dd.MM.yyyy");
    public final DateFormat dateFormatMegaFull = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss,SSS");
}

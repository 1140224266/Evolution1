package evl.razier.AminoAcid;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public enum HumanAminoAcid {
    PHE("Phenylalanine", new HashSet<String>(Arrays.asList("UUU", "UUC"))),
    LEU("Leucine", new HashSet<String>(Arrays.asList("UUA", "UUG"))),
    ILE("Isoleucine", new HashSet<String>(Arrays.asList("AUU", "AUC", "AUA"))),
    MET("Methionine", new HashSet<String>(List.of("AUG"))),
    VAL("Valine", new HashSet<String>(Arrays.asList("GUU", "GUC", "GUA", "GUG"))),
    SER("Serine", new HashSet<String>(Arrays.asList("UCU", "UCC", "UCA", "UCG", "AGU", "AGC"))),
    PRO("Proline", new HashSet<String>(Arrays.asList("CCU", "CCC", "CCA", "CCG"))),
    THR("Threonine", new HashSet<String>(Arrays.asList("ACU", "ACC", "ACA", "ACG"))),
    ALA("Alanine", new HashSet<String>(Arrays.asList("GCU", "GCC", "GCA", "GCG"))),
    TYR("Tyrosine", new HashSet<String>(Arrays.asList("UAU", "UAC"))),
    HIS("Histidine", new HashSet<String>(Arrays.asList("CAU", "CAC"))),
    GLN("Glutamine", new HashSet<String>(Arrays.asList("CAA", "CAG"))),
    ASN("Asparagine", new HashSet<String>(Arrays.asList("AAU", "AAC"))),
    LYS("Lysine", new HashSet<String>(Arrays.asList("AAA", "AAG"))),
    ASP("Aspartic acid", new HashSet<String>(Arrays.asList("GAU", "GAC"))),
    GLU("Glutamic acid", new HashSet<String>(Arrays.asList("GAA", "GAG"))),
    CYS("Cysteine", new HashSet<String>(Arrays.asList("UGU", "UGC"))),
    ARG("Arginine", new HashSet<String>(Arrays.asList("CGU", "CGC", "CGA", "CGG", "AGA", "AGG"))),
    TRP("Tryptophan", new HashSet<String>(List.of("UGG"))),
    GLY("Glycine", new HashSet<String>(Arrays.asList("GGU", "GGC", "GGA", "GGG"))),
    STOP("Stop codon", new HashSet<String>(Arrays.asList("UAA", "UAG", "UGA")));

    public String fullname;
    public HashSet<String> key;
    public static HashSet<HumanAminoAcid> allAminoAcids = new HashSet<HumanAminoAcid>(Arrays.asList(HumanAminoAcid.values()));

    HumanAminoAcid(String fullname, HashSet<String> key){
        this.fullname = fullname;
        this.key = key;
    }
}

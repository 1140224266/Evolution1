package temp.sequenceSumon.Gene.Human;

import evl.razier.AminoAcid.HumanAminoAcid;

import java.util.LinkedList;
import java.util.Random;

public class HumanDNA {
    private Random random = new Random();
    public static String RANDOM_INIT = "RandomInit";
    public static String HEALTH_INIT = "HealthInit";


    public LinkedList<HumanAminoAcid> DNA = new LinkedList<HumanAminoAcid>();

    public HumanDNA(String type){
        this(type,-1);
    }
    
    public HumanDNA(String type , int size){
        if(type.equals(RANDOM_INIT)){
            if(size == -1){
                for(int i = 0; i < random.nextInt(); i++){
                    DNA.add(HumanAminoAcid.values()[random.nextInt(HumanAminoAcid.values().length)]);
                }
            }else{
                for(int i = 0; i < size; i++){
                    DNA.add(HumanAminoAcid.values()[random.nextInt(HumanAminoAcid.values().length)]);
                }
            }
        }else if(type.equals(HEALTH_INIT)){
            healthInit();
        }
    }

    public void healthInit(){

        
    }

}

package evl.razier;

import evl.razier.Attribute.Attack;
import evl.razier.Attribute.Defense;
import evl.razier.EntityModel.HumanModel;

import java.util.HashSet;
import java.util.logging.Logger;

public class Main {
    public static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) {
        LOGGER.info("We have not developed the output yet! Please wait!");
        test();
    }

    public static void test(){
        System.out.println("Test");
        HumanModel humanModel = new HumanModel(1000,1000,new Attack(0,0,0,0,0,new HashSet<>()),new Defense(0,0,0),0,0,0,0,0,0,0,0);
        humanModel.setAgility(100);
    }
}
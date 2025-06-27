package evl.razier;

import evl.razier.Attribute.Attack;
import evl.razier.Attribute.Defense;
import evl.razier.EntityModel.HumanModel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.logging.Logger;

public class Main {
    //在日志前加上时间，格式为yyyy-MM-dd HH:mm:ss:SSS
    public static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss:SSS";
    public static final String LOG_FORMAT = "[%s] %s";
    public static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    public static final String TIME = LocalDateTime.now().format(DateTimeFormatter.ofPattern(TIME_FORMAT));
    public static void main(String[] args) {
        
        log("We have not developed the output yet! Please wait!");
        test();
    }

    public static void test(){
        log("Test");
    }

    public static void log(String message){
        LOGGER.info(String.format(LOG_FORMAT, TIME, message));
    }
}
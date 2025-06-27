package temp.sequenceSumon;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.*;
import java.text.SimpleDateFormat;
import java.security.SecureRandom;
import java.util.logging.Logger;

import com.google.gson.*;

import org.apache.commons.io.FileUtils;
import evl.razier.AminoAcid.HumanAminoAcid;


/*
*
* {
  "1": {
    "size": 100,
    "type": ["health"],
    "sequence":[
      "MET",
      "VAL",
      "LEU",
      "ILE",
      "MET",
      "VAL",
      "LEU",
      "ILE",
      "MET",
      "VAL",
      "LEU",
      "ILE",
      "MET",
      "VAL",
      "LEU",
      "ILE",
      "MET",
      "VAL",
      "LEU",
      "ILE",
      "MET",
      "VAL",
      "LEU",
      "ILE",
      "MET",
      "VAL",
      "LEU",
      "ILE",
      "MET",
      "VAL",
      "LEU",
      "ILE",
      "MET",
      "VAL",
      "LEU",
      "ILE",
      "MET",
      "VAL",
      "LEU",
      "ILE",
      "MET",
      "VAL",
      "LEU",
      "ILE",
      "MET",
      "VAL",
      "LEU",
      "ILE",
      "MET",
      "VAL",
      "LEU",
      "ILE",
      "MET",
      "VAL",
      "LEU",
      "ILE",
      "MET",
      "VAL",
      "LEU",
      "ILE",
      "MET",
      "VAL",
      "LEU",
      "ILE",
      "MET",
      "VAL",
      "LEU",
      "ILE",
      "MET",
      "VAL",
      "LEU",
      "ILE",
      "MET",
      "VAL",
      "LEU",
      "ILE",
      "MET",
      "VAL",
      "LEU",
      "ILE",
      "MET",
      "VAL",
      "LEU",
      "ILE",
      "MET",
      "VAL",
      "LEU",
      "ILE",
      "MET",
      "VAL",
      "LEU",
      "ILE",
      "MET",
      "VAL",
      "LEU",
      "ILE",
      "MET",
      "VAL",
      "LEU",
      "ILE",
      "MET"
    ],
    "strength_value_type": "random",
    "strength_value_range": [0, 100]
  }
}
*
* */


public class sumon {
  private static Random random = new SecureRandom();
  private static Logger logger = Logger.getLogger(sumon.class.getName());

    public static void main(String[] args) {

      //让控制台输出日志精确到毫秒 格式为：2025-06-25 10:00:00.000INFO 
      System.setProperty("java.util.logging.SimpleFormatter.format", "%1$tF %1$tT %4$s %2$s %5$s%6$s%n");
      


      while (true){
        //获取输入
        Scanner scanner = new Scanner(System.in);
        logger.info("Please input type(health, attack, defense) (\"exit\" to exit)");
        String input = scanner.nextLine();
        if (input.equals("exit"))
        {
          break;
        }
        else {
          
          JsonArray type = new JsonArray();//input="health defense..."
          for (String typeName : input.split(" "))
          {
            type.add(typeName);
          }
          //遍历type，检查是否为health, attack, defense
            logger.info("type:"+type.toString());
          logger.info("Check type");
          boolean continueFlag = false;
          JsonArray typeCheck = new JsonArray();
          typeCheck.add("health");
          typeCheck.add("attack");
          typeCheck.add("defense");
          for (int i = 0; i < type.size(); i++)
          {
            if (!typeCheck.contains(type.get(i)))
            {
              continueFlag = false;
            }
          }
          if (continueFlag)
          {
              logger.info("type range error, please input again");
              continue;
          }
          logger.info("Please input size(1-1000000)");
          input = scanner.nextLine();
          int size = Integer.parseInt(input);
          logger.info("Check size");
          if (size < 1 || size > 1000000)
          {
            logger.info("size range error, please input again");
            continue;
          }
          //summon time
          logger.info("Please input summonTime(1-1000000)");
          input = scanner.nextLine();
          int summonTime = Integer.parseInt(input);
          logger.info("Check summonTime");
          if (summonTime < 1 || summonTime > 1000000)
          {
            logger.info("summonTime range error, please input again");
            continue;
          }

          try {//生成
            
            for (int i = 0; i < summonTime; i++)
            {
              summon(random.nextInt(size), type);
            }
          } catch (IOException e) {
              logger.info("generate failed, please input again");
            continue;
          }
        }
      }

        /*
         * 
         * JsonArray type = new JsonArray();
        type.add("health");
        int size = 10;
        for (int i = 0 ; i < 10 ; i++)
        {
            try {
                summon(random.nextInt(size), type);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        type.remove(0);
        type.add("attack");
        for (int i = 0 ; i < 10 ; i++)
        {
            try {
                summon(random.nextInt(size), type);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        type.remove(0);
        type.add("defense");
        for (int i = 0 ; i < 10 ; i++)
        {
            try {
                summon(random.nextInt(5), type);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        type.remove(0);
        type.add("attack");
        type.add("defense");
        for (int i = 0 ; i < 10 ; i++)
        {
            try {
                summon(random.nextInt(5), type);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
         */
    }

    protected static void summon(int size, JsonArray type) throws IOException
    {
        if (size == 0)
            return;
        logger.info("Summon(" + size + ", " + type.toString() + ")");
        //仿照模板，生成一个模板，保存到文件"summonFile/序号.json"
        JsonArray sequence = sumonSequence(size);
        String strength_value_type = "random";
        JsonArray strength_value_range = new JsonArray();
        strength_value_range.add(0);
        strength_value_range.add(100);
        //使用Gson，序列化数据，保存到json
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("size", new JsonPrimitive(size));
        if (type.size() == 1)
          jsonObject.add("type", new JsonPrimitive(type.get(0).getAsString()));
        else
          jsonObject.add("type", type);
        jsonObject.add("sequence", sequence);
        jsonObject.add("strength_value_type", new JsonPrimitive(strength_value_type));
        jsonObject.add("strength_value_range", strength_value_range);


        /*String json = "{\n" +
                "  \"size\": \"" + size + "\",\n" +
                "  \"type\": " + type.toString() + ",\n" +
                "  \"sequence\": " + sequence.toString() + ",\n" +
                "  \"strength_value_type\": \"" + strength_value_type + "\",\n" +
                "  \"strength_random_value_range\": [" + strength_value_range[0] + ", " + strength_value_range[1] + "]\n" +
                "}";*/
        try {
            //获取当前时间戳
            long timestamp = System.currentTimeMillis();
            //格式化时间戳为2025-06-25_10_00_00_000
            String formattedTimestamp = new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss_SSS").format(new Date(timestamp));

            if (type.size() == 1)
            {
                FileWriter fileWriter = new FileWriter("D:/code/Evolution/src/main/java/temp/sequenceSumon/summonFile/"+ getFileTypeName(type) + size +"_" +formattedTimestamp+ ".json");
                fileWriter.write(gson.toJson(jsonObject));
                fileWriter.close();
            }
            else
            {
                FileWriter fileWriter = new FileWriter("D:/code/Evolution/src/main/java/temp/sequenceSumon/summonFile/"+ "mix" + size +"_" +formattedTimestamp+ ".json");
                fileWriter.write(gson.toJson(jsonObject));
                fileWriter.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // for(int i = 0; i < size; i++){
        //     System.out.println(HumanAminoAcid.values()[random.nextInt(HumanAminoAcid.values().length)]);
        // }
    }

    protected static JsonArray sumonSequence(int size)
    {
        logger.info("SummonSequence");
        JsonArray sequence = new JsonArray();
        for(int i = 0; i < size; i++){
            //遇到STOP则跳过
            String aminoAcid = HumanAminoAcid.values()[random.nextInt(HumanAminoAcid.values().length)].toString();
            if(aminoAcid.equals("STOP")){
                i--;
                continue;
            }
            sequence.add(aminoAcid);
        }
        if (isDuplicate(sequence)) {
          return sumonSequence(size);
        } else {
          return sequence;
        }
    }

    protected static String getFileTypeName(JsonArray type)
    {
        logger.info("getFileTypeName");
        if (type.size() == 1)
          return type.get(0).getAsString();
        else 
          return "mix";
    }


    //查重
    protected static boolean isDuplicate(JsonArray sequence)
    {
      logger.info("Duplicate");
      //获取summonFile目录下的所有文件，读取其中的序列值，与sequence进行比较
      File[] files = new File("D:/code/Evolution/src/main/java/temp/sequenceSumon/summonFile").listFiles();
        if (files != null) {
            for(File file : files){
              //读取文件内容
                String json = null;
                try {
                    json = FileUtils.readFileToString(file, "UTF-8");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                //使用库Gson
              Gson gson = new Gson();
              JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
              //读取json中的sequence值 使用库Gson
              JsonArray fileSequence = jsonObject.get("sequence").getAsJsonArray();
              if(sequence.equals(fileSequence))
              {
                  System.out.println("is duplicate");
                  return true;
              }
            }
        }
        return false;
    }
}

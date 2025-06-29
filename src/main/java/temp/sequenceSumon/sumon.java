package temp.sequenceSumon;

import java.io.FileReader;
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
import evl.razier.Attribute.damageType;

public class sumon {
  private static final Random random = new SecureRandom();
  private static final Logger logger = Logger.getLogger(sumon.class.getName());
  private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
  private static final String CONFIG_FILE_PATH = "src/main/resources/summon_config.json";
  private static SummonConfig config;

  private static final Set<JsonArray> existingSequences = new HashSet<>();

    //配置类 用于读取配置文件
    private static class SummonConfig {
        String summonFileDir;
        BonusCalculation bonusCalculation;
        Map<String, Double> priorities;
        Map<String, Double> attackPriorities;
        Map<String, Double> defensePriorities;
        Map<String, Double> suitableTemperaturePriorities;

        //奖励计算类 用于计算奖励
        static class BonusCalculation {
            int baseMultiplier;
            int randomMultiplier;
        }
    }

    //加载配置文件
    private static void loadConfig() {
        try (FileReader reader = new FileReader(CONFIG_FILE_PATH)) {
            config = gson.fromJson(reader, SummonConfig.class);
            logger.info("[LoadConfig]: Configuration loaded successfully from " + CONFIG_FILE_PATH);
        } catch (IOException e) {
            logger.severe("[LoadConfig]: Error reading configuration file: " + e.getMessage());
            throw new RuntimeException("Could not load configuration.", e);
        }
    }

    public static void main(String[] args) {
      //让控制台输出日志精确到毫秒 格式为：2025-06-25 10:00:00.000 INFO
      System.setProperty("java.util.logging.SimpleFormatter.format", "%1$tF %1$tT %4$s %2$s %5$s%6$s%n");
      loadConfig();
      loadExistingSequences();
      logger.info("[Main]: Loaded " + existingSequences.size() + " existing sequences.");

      Scanner scanner = new Scanner(System.in);
      while (true) {
          logger.info("\nEnter command ('summon <size_range> <times> <types...|random>' or 'exit'):");
          String input = scanner.nextLine();

          if ("exit".equalsIgnoreCase(input.trim())) {
              logger.info("[Main]: Exiting application.");
              break;
          }

          String[] parts = input.trim().split("\\s+");
          if (parts.length < 4 || !"summon".equalsIgnoreCase(parts[0])) {
              logger.warning("[Main]: Invalid command format. Use 'summon <size_range> <times> <types...|random>'.");
              continue;
          }

          try {
              String sizeRangeStr = parts[1];
              int times = Integer.parseInt(parts[2]);
              boolean isRandomTypes = "random".equalsIgnoreCase(parts[3]);

              JsonArray specificTypeArray = null;
              if (!isRandomTypes) {
                  specificTypeArray = new JsonArray();
                  for (int i = 3; i < parts.length; i++) {
                      String type = parts[i];
                      if (config.priorities.containsKey(type)) {
                          specificTypeArray.add(type);
                      } else {
                          logger.warning("[Main]: Ignoring unknown attribute type: " + type);
                      }
                  }
                  if (specificTypeArray.size() == 0) {
                      logger.warning("[Main]: No valid attribute types specified. Please check available types.");
                      continue;
                  }
              }

              for (int i = 0; i < times; i++) {
                  int size;
                  if (sizeRangeStr.contains("-")) {
                      String[] rangeParts = sizeRangeStr.split("-");
                      int min = Integer.parseInt(rangeParts[0]);
                      int max = Integer.parseInt(rangeParts[1]);
                      if (min > max) {
                          logger.warning("[Main]: Invalid size range. Min cannot be greater than max.");
                          break;
                      }
                      size = random.nextInt(max - min + 1) + min;
                  } else {
                      size = Integer.parseInt(sizeRangeStr);
                  }

                  JsonArray currentTypeArray;
                  if (isRandomTypes) {
                      List<String> allTypes = new ArrayList<>(config.priorities.keySet());
                      int numTypesToPick = random.nextInt(allTypes.size()) + 1;
                      Collections.shuffle(allTypes);
                      currentTypeArray = new JsonArray();
                      for (int j = 0; j < numTypesToPick; j++) {
                          currentTypeArray.add(allTypes.get(j));
                      }
                  } else {
                      currentTypeArray = specificTypeArray;
                  }
                  
                  summon(getSize(size), currentTypeArray);
                  logger.info("[Main]: Successfully generated gene file #" + (i + 1) + "/" + times + " with size " + size + " and types " + currentTypeArray);
              }

          } catch (NumberFormatException e) {
              logger.warning("[Main]: Invalid number format for size or times.");
          } catch (IOException e) {
              logger.severe("[Main]: An error occurred while generating a file: " + e.getMessage());
          } catch (Exception e) {
              logger.severe("[Main]: An unexpected error occurred: " + e.getMessage());
          }
      }
      scanner.close();
    }

    private static int getSize(int size) {
        if (size>=1 && size<=3) {
            return random.nextInt(size+1);
        }
        else if (size>=4 && size<=5) {
            return 4+random.nextInt(2);
        }
        else if (size>=6 && size<=10) {
            return 6+random.nextInt(5);
        }
        else if (size>=11 && size<=20) {
            return 11+random.nextInt(10);
        }
        else if (size>=21 && size<=30) {
            return 21+random.nextInt(10);
        }
        else if (size>=31 && size<=40) {
            return 31+random.nextInt(10);
        }
        else if (size>=41 && size<=50) {
            return 41+random.nextInt(10);
        }
        else if (size>=51 && size<=60) {
            return 51+random.nextInt(10);
        }
        else if (size>=61 && size<=70) {    
            return 61+random.nextInt(10);
        }
        else if (size>=71 && size<=80) {
            return 71+random.nextInt(10);
        }
        else if (size>=81 && size<=90) {
            return 81+random.nextInt(10);
        }
        else if (size>=91 && size<=100) {
            return 91+random.nextInt(10);
        }

        logger.warning("[getSize]: Invalid size: " + size);
        return -1;
    }

    private static void loadExistingSequences() {
        logger.info("[LoadExistingSequences]: Loading existing sequences...");
        File dir = new File(config.summonFileDir);
        if (!dir.exists()) {
            dir.mkdirs();
            logger.info("[LoadExistingSequences]: Created summon file directory: " + config.summonFileDir);
            return;
        }

        File[] files = dir.listFiles();
        if (files != null) {
            for(File file : files){
                if (file.isFile() && file.getName().endsWith(".json")) {
                    try {
                        String jsonContent = FileUtils.readFileToString(file, "UTF-8");
                        Gson parserGson = new Gson(); // non-pretty gson for parsing
                        JsonObject jsonObject = parserGson.fromJson(jsonContent, JsonObject.class);
                        if (jsonObject != null && jsonObject.has("sequence")) {
                            JsonArray fileSequence = jsonObject.get("sequence").getAsJsonArray();
                            existingSequences.add(fileSequence);
                        }
                    } catch (IOException e) {
                        logger.warning("[LoadExistingSequences]: Could not read or parse file: " + file.getName() + " - " + e.getMessage());
                    } catch (JsonSyntaxException e) {
                        logger.warning("[LoadExistingSequences]: Invalid JSON in file: " + file.getName() + " - " + e.getMessage());
                    }
                }
            }
        }
    }
    
    protected static void summon(int size, JsonArray type) throws IOException
    {
        if (size == 0)
            return;
        logger.info("[Summon]: Summoning a new sequence of size " + size);

        JsonArray sequence = sumonSequence(size);
        if (sequence == null) {
            logger.warning("[Summon]: Could not generate a unique sequence of size " + size + ". Too many duplicates exist.");
            return;
        }

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", "change");
        jsonObject.addProperty("parts", "all");
        jsonObject.addProperty("size", size);
        jsonObject.add("change_type", type);
        jsonObject.add("sequence", sequence);

        JsonObject valuesObject = new JsonObject();
        valuesObject.addProperty("health", 0.0);

        JsonObject attackObject = new JsonObject();
        attackObject.addProperty("temperature", 0.0);
        attackObject.addProperty("maxForce", 0.0);
        attackObject.addProperty("energy", 0.0);
        attackObject.addProperty("frequency", 0.0);
        attackObject.addProperty("contactArea", 0.0);
        valuesObject.add("attack", attackObject);
        
        JsonObject defenseObject = new JsonObject();
        defenseObject.addProperty("surfaceHardness", 0.0);
        defenseObject.addProperty("thickness", 0.0);
        defenseObject.addProperty("toughness", 0.0);
        valuesObject.add("defense", defenseObject);

        JsonObject tempObject = new JsonObject();
        tempObject.addProperty("MinTemperatureIncrease", 0.0);
        tempObject.addProperty("MaxTemperatureIncrease", 0.0);
        valuesObject.add("suitable_temperature", tempObject);

        int specificFragmentCount = getSpecificFragmentCount(size);
        if (specificFragmentCount == 0) {
            logger.warning("[Summon]: No specific fragment count defined for size " + size + ". Cannot calculate bonus value.");
            return;
        }

        double probabilityOfOccurrence = ((double) config.bonusCalculation.baseMultiplier / specificFragmentCount) + random.nextInt(config.bonusCalculation.randomMultiplier / specificFragmentCount);
        
        for(int i = 0; i < type.size(); i++)
        {
            int used = 0 ;
            if (i==type.size()-1)
            {
                used = (int) probabilityOfOccurrence;
            }
            else used = random.nextInt((int)probabilityOfOccurrence);

            String attributeName = type.get(i).getAsString();
            
            if (!config.priorities.containsKey(attributeName)) {
                logger.warning("[Summon]: Unknown attribute in type: " + attributeName);
                continue;
            }
            double value = (double)used*config.priorities.get(attributeName);
            
            switch (attributeName) {
                case "health":
                    valuesObject.addProperty("health", valuesObject.get("health").getAsDouble() + value);
                    break;
                case "attack":
                    JsonObject currentAttack = valuesObject.getAsJsonObject("attack");
                    currentAttack.addProperty("temperature", currentAttack.get("temperature").getAsDouble() + value * config.attackPriorities.get("temperature"));
                    currentAttack.addProperty("maxForce", currentAttack.get("maxForce").getAsDouble() + value * config.attackPriorities.get("maxForce"));
                    currentAttack.addProperty("energy", currentAttack.get("energy").getAsDouble() + value * config.attackPriorities.get("energy"));
                    currentAttack.addProperty("frequency", currentAttack.get("frequency").getAsDouble() + value * config.attackPriorities.get("frequency"));
                    currentAttack.addProperty("contactArea", currentAttack.get("contactArea").getAsDouble() + value * config.attackPriorities.get("contactArea"));
                    break;
                case "defense":
                    JsonObject currentDefense = valuesObject.getAsJsonObject("defense");
                    currentDefense.addProperty("surfaceHardness", currentDefense.get("surfaceHardness").getAsDouble() + value * config.defensePriorities.get("surfaceHardness"));
                    currentDefense.addProperty("thickness", currentDefense.get("thickness").getAsDouble() + value * config.defensePriorities.get("thickness"));
                    currentDefense.addProperty("toughness", currentDefense.get("toughness").getAsDouble() + value * config.defensePriorities.get("toughness"));
                    break;
                case "suitable_temperature":
                    JsonObject currentTemp = valuesObject.getAsJsonObject("suitable_temperature");
                    currentTemp.addProperty("MinTemperatureIncrease", currentTemp.get("MinTemperatureIncrease").getAsDouble() + value * config.suitableTemperaturePriorities.get("MinTemperatureIncrease"));
                    currentTemp.addProperty("MaxTemperatureIncrease", currentTemp.get("MaxTemperatureIncrease").getAsDouble() + value * config.suitableTemperaturePriorities.get("MaxTemperatureIncrease"));
                    break;
                default:
                    valuesObject.addProperty(attributeName, value);
                    break;
            }
            probabilityOfOccurrence-=used;
        }
        jsonObject.add("values", valuesObject);
        
        try {
            //获取当前时间戳
            long timestamp = System.currentTimeMillis();
            //格式化时间戳为2025-06-25_10_00_00_000
            String formattedTimestamp = new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss_SSS").format(new Date(timestamp));

            if (type.size() == 1)
            {
                FileWriter fileWriter = new FileWriter(config.summonFileDir+ "/" + getFileTypeName(type) + size +"_" +formattedTimestamp+ ".json");
                fileWriter.write(gson.toJson(jsonObject));
                fileWriter.close();
            }
            else
            {
                FileWriter fileWriter = new FileWriter(config.summonFileDir+ "/" + "mix" + size + "_" + type.size() + "_" +formattedTimestamp+ ".json");
                fileWriter.write(gson.toJson(jsonObject));
                fileWriter.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    protected static JsonArray sumonSequence(int size)
    {
        logger.info("[SumonSequence]: Generating a unique sequence of size " + size);
        JsonArray sequence;
        int maxAttempts = 100; // To prevent infinite loop
        int attempts = 0;
        do {
            sequence = new JsonArray();
            for(int i = 0; i < size; i++){
                //遇到STOP则跳过
                String aminoAcid;
                do {
                    aminoAcid = HumanAminoAcid.values()[random.nextInt(HumanAminoAcid.values().length)].toString();
                } while(aminoAcid.equals("STOP"));
                sequence.add(aminoAcid);
            }
            attempts++;
        } while (existingSequences.contains(sequence) && attempts < maxAttempts);

        if (existingSequences.contains(sequence)) {
            return null; // Failed to generate a unique one
        }
        existingSequences.add(sequence);
        return sequence;
    }

    protected static String getFileTypeName(JsonArray type)
    {
        logger.info("[getFileTypeName]: getFileTypeName");
        if (type.size() == 1)
          return type.get(0).getAsString();
        else 
          return "mix";
    }

    private static int getSpecificFragmentCount(int size) {
        if (size >= 1 && size <= 3) return 30;
        if (size >= 4 && size <= 5) return 100;
        if (size >= 6 && size <= 10) return 200;
        if (size >= 11 && size <= 20) return 500;
        if (size >= 21 && size <= 30) return 1000;
        if (size >= 31 && size <= 40) return 2000;
        if (size >= 41 && size <= 50) return 3000;
        if (size >= 51 && size <= 60) return 4000;
        if (size >= 61 && size <= 70) return 5000;
        if (size >= 71 && size <= 80) return 6000;
        if (size >= 81 && size <= 90) return 7000;
        if (size >= 91 && size <= 100) return 8000;
        return 0; // Not in range
    }

    //查重
    protected static boolean isDuplicate(JsonArray sequence)
    {
      logger.info("[isDuplicate]: Duplicate");
      //获取summonFile目录下的所有文件，读取其中的序列值，与sequence进行比较
      File[] files = new File(config.summonFileDir).listFiles();
        if (files != null) {
            for(File file : files){
              //读取文件内容
                String json = null;
                try {
                    json = FileUtils.readFileToString(file, "UTF-8");
                } catch (IOException e) {
                    logger.warning("[isDuplicate]: Could not read file: " + file.getName() + " - " + e.getMessage());
                }
                //使用库Gson
              Gson gson = new Gson();
              JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
              //读取json中的sequence值 使用库Gson
              JsonArray fileSequence = jsonObject.get("sequence").getAsJsonArray();
              if(sequence.equals(fileSequence))
              {
                  logger.info("[isDuplicate]: is duplicate");
                  return true;
              }
            }
        }
        return false;
    }
}

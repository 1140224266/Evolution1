package evl.razier.Attribute;

import java.util.HashSet;

import static evl.razier.Main.LOGGER;

/*
* 属性系统：生命，攻击力（由温度，力，能量，频率【电磁波、机械波】，接触面积），
* 适宜温度、防御（表面硬度、厚度以及韧性【减免内伤】），
* 能量，代谢能力（决定能量回复和生命恢复），精神力，敏捷度，战斗经验（伤害倍数与减免倍数）,战斗经验上限
* */
public class Attack {

    public float temperature ;//温度
    public float maxForce;//力
    public float energy ;//能量
    public float frequency ;//频率
    public float contactArea ;//接触面积
    //伤害类型
    HashSet<String> damageType ;

    public Attack(float temperature, float maxForce, float energy, float frequency, float contactArea, HashSet<String> damageType){
        this.temperature = temperature;
        this.maxForce = maxForce;
        this.energy = energy;
        this.frequency = frequency;
        this.contactArea = contactArea;
        this.damageType = damageType;
    }

    public void setTemperature(float temperature){
        LOGGER.info("Original temperature :" + this.temperature);
        LOGGER.info("Set temperature to :" + temperature);
        this.temperature = temperature;
    }

    public void setMaxForce(float maxForce){
        LOGGER.info("Original max force :" + this.maxForce);
        LOGGER.info("Set max force to :" + maxForce);
        this.maxForce = maxForce;
    }

    public void setEnergy(float energy){
        LOGGER.info("Original energy :" + this.energy);
        LOGGER.info("Set energy to :" + energy);
        this.energy = energy;
    }

    public void setFrequency(float frequency){
        LOGGER.info("Original frequency :" + this.frequency);
        LOGGER.info("Set frequency to :" + frequency);
        this.frequency = frequency;
    }
    
    public void setContactArea(float contactArea){
        LOGGER.info("Original contact area :" + this.contactArea);
        LOGGER.info("Set contact area to :" + contactArea);
        this.contactArea = contactArea;
    }


}

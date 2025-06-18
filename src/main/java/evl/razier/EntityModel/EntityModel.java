package evl.razier.EntityModel;


import java.util.HashSet;
import java.util.logging.Logger;

import evl.razier.Attribute.Attack;
import evl.razier.Attribute.Defense;
import evl.razier.Main;

import static evl.razier.Main.LOGGER;

/*
* 属性系统：生命，攻击力（由温度，力，能量，频率【电磁波、机械波】，接触面积），
* 适宜温度、防御（表面硬度、厚度以及韧性【减免内伤】），
* 能量，代谢能力（决定能量回复和生命恢复），精神力，敏捷度，战斗经验（伤害倍数与减免倍数）,战斗经验上限
* */
public abstract class EntityModel {
    public float health = 0;
    public float maxHealth = 0;
    //攻击力
    public Attack attack = new Attack(0,0,0,0,0,new HashSet<>());
    //防御
    public Defense defense = new Defense(0,0,0);
    //能量
    public float energy = 0;
    //能量上限
    public float maxEnergy = 0;
    //适宜温度
    public float suitableTemperature = 0;
    //代谢能力
    public float metabolism = 0;
    //精神力
    public float mentalPower = 0;
    //敏捷度
    public float agility = 0;
    //战斗经验
    public float combatExperience = 0;
    //战斗经验上限
    public float combatExperienceLimit = 0;

    public void setHealth(float health){
        LOGGER.info("Original health :" + this.health);
        //设置生命，同时增加日志
        LOGGER.info("Set health to :" + health);
        this.health = health;

    }

    public void setMaxHealth(float maxHealth){
        LOGGER.info("Original max health :" + this.maxHealth);
        LOGGER.info("Set max health to :" + maxHealth);
        this.maxHealth = maxHealth;
    }

    public void setEnergy(float energy){
        LOGGER.info("Original energy :" + this.energy);
        LOGGER.info("Set energy to :" + energy);
        this.energy = energy;
    }

    public void setSuitableTemperature(float suitableTemperature){
        LOGGER.info("Original suitable temperature :" + this.suitableTemperature);
        LOGGER.info("Set suitable temperature to :" + suitableTemperature);
        this.suitableTemperature = suitableTemperature;
    }

    public void setMetabolism(float metabolism){
        LOGGER.info("Original metabolism :" + this.metabolism);
        LOGGER.info("Set metabolism to :" + metabolism);
        this.metabolism = metabolism;
    }

    public void setMentalPower(float mentalPower){
        LOGGER.info("Original mental power :" + this.mentalPower);
        LOGGER.info("Set mental power to :" + mentalPower);
        this.mentalPower = mentalPower;
    }

    public void setAgility(float agility){
        LOGGER.info("Original agility :" + this.agility);
        LOGGER.info("Set agility to :" + agility);
        this.agility = agility;
    }

    public void setCombatExperience(float combatExperience){
        LOGGER.info("Original combat experience :" + this.combatExperience);
        LOGGER.info("Set combat experience to :" + combatExperience);
        this.combatExperience = combatExperience;
    }
}

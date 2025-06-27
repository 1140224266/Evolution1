package evl.razier.EntityModel;


import java.util.HashSet;
import java.util.logging.Logger;

import evl.razier.Attribute.Attack;
import evl.razier.Attribute.Defense;
import evl.razier.Attribute.SuitableTemperature;
import evl.razier.Main;

import static evl.razier.Main.LOGGER;

/*
* 属性系统：生命，攻击力（由温度，力，能量，频率【电磁波、机械波】，接触面积），
* 适宜温度、防御（表面硬度、厚度以及韧性【减免内伤】），
* 能量，代谢能力（决定能量回复和生命恢复），精神力，敏捷度，战斗经验（伤害倍数与减免倍数）,战斗经验上限，体积，重量，密度，比热容
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
    public SuitableTemperature suitableTemperature = new SuitableTemperature(0,0);
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
    //体积
    public float volume = 0;
    //重量
    public float weight = 0;
    //密度
    public float density = 0;
    //比热容
    public float specificHeatCapacity = 0;

    public void setVolume(float volume){
        Main.log("Original volume :" + this.volume);
        Main.log("Set volume to :" + volume);
        this.volume = volume;
    }

    public void setWeight(float weight){
        Main.log("Original weight :" + this.weight);
        Main.log("Set weight to :" + weight);
        this.weight = weight;
    }

    public void setDensity(float density){
        Main.log("Original density :" + this.density);
        Main.log("Set density to :" + density);
        this.density = density;
    }

    public void setSpecificHeatCapacity(float specificHeatCapacity){
        Main.log("Original specific heat capacity :" + this.specificHeatCapacity);
        Main.log("Set specific heat capacity to :" + specificHeatCapacity);
        this.specificHeatCapacity = specificHeatCapacity;
    }

    public void setHealth(float health){
        Main.log("Original health :" + this.health);
        Main.log("Set health to :" + health);
        this.health = health;

    }

    public void setMaxHealth(float maxHealth){
        Main.log("Original max health :" + this.maxHealth);
        Main.log("Set max health to :" + maxHealth);
        this.maxHealth = maxHealth;
    }

    public void setEnergy(float energy){
        Main.log("Original energy :" + this.energy);
        Main.log("Set energy to :" + energy);
        this.energy = energy;
    }

    public void setSuitableTemperature(float MinTemperature, float MaxTemperature){
        Main.log("Original suitable temperature :" + this.suitableTemperature);
        Main.log("Set suitable temperature to :" + MinTemperature + " " + MaxTemperature);
        this.suitableTemperature.setMinTemperature(MinTemperature);
        this.suitableTemperature.setMaxTemperature(MaxTemperature);
    }

    public void setMetabolism(float metabolism){
        Main.log("Original metabolism :" + this.metabolism);
        Main.log("Set metabolism to :" + metabolism);
        this.metabolism = metabolism;
    }

    public void setMentalPower(float mentalPower){
        Main.log("Original mental power :" + this.mentalPower);
        Main.log("Set mental power to :" + mentalPower);
        this.mentalPower = mentalPower;
    }

    public void setAgility(float agility){
        Main.log("Original agility :" + this.agility);
        Main.log("Set agility to :" + agility);
        this.agility = agility;
    }

    public void setCombatExperience(float combatExperience){
        Main.log("Original combat experience :" + this.combatExperience);
        Main.log("Set combat experience to :" + combatExperience);
        this.combatExperience = combatExperience;
    }
}

package evl.razier.EntityModel;

import evl.razier.Attribute.Attack;
import evl.razier.Attribute.Defense;

import java.util.HashSet;

/*
 * 属性系统：生命，攻击力（由温度，力，能量，频率【电磁波、机械波】，接触面积），
 * 适宜温度、防御（表面硬度、厚度以及韧性【减免内伤】），
 * 能量，代谢能力（决定能量回复和生命恢复），精神力，敏捷度，战斗经验（伤害倍数与减免倍数）,战斗经验上限
 * */
public class HumanModel extends EntityModel{
    public HumanModel humanModel = new HumanModel(1000, 1000, new Attack(0, 0, 0, 0, 0, new HashSet<String>()), new Defense(0, 0,  0), 100, 100, 0, 0, 0, 0, 0, 0);
    public HumanModel(float maxHealth, float health , Attack attack, Defense defense, float maxEnergy, float energy, float suitableTemperature, float metabolism, float mentalPower, float agility, float combatExperience, float combatExperienceLimit){
        this.maxHealth = maxHealth;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.maxEnergy = maxEnergy;
        this.energy = energy;
        this.suitableTemperature = suitableTemperature;
        this.metabolism = metabolism;
        this.mentalPower = mentalPower;
        this.agility = agility;
    }
}

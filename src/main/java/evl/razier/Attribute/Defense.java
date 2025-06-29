package evl.razier.Attribute;


import evl.razier.Main;



/*
* 属性系统：生命，攻击力（由温度，力，能量，频率【电磁波、机械波】，接触面积），
* 适宜温度、防御（表面硬度、厚度以及韧性【减免内伤】），
* 能量，代谢能力（决定能量回复和生命恢复），精神力，敏捷度，战斗经验（伤害倍数与减免倍数）,战斗经验上限
* */
public class Defense {
    public float surfaceHardness ;//表面硬度
    public float thickness ;//厚度
    public float toughness ;//韧性

    public Defense(float surfaceHardness, float thickness, float toughness){
        this.surfaceHardness = surfaceHardness;
        this.thickness = thickness;
        this.toughness = toughness;
    }

    public void setSurfaceHardness(float surfaceHardness){
        Main.log("Original surface hardness :" + this.surfaceHardness);
        Main.log("Set surface hardness to :" + surfaceHardness);
        this.surfaceHardness = surfaceHardness;
    }

    public void setThickness(float thickness){
        Main.log("Original thickness :" + this.thickness);
        Main.log("Set thickness to :" + thickness);
        this.thickness = thickness;
    }

    public void setToughness(float toughness){
        Main.log("Original toughness :" + this.toughness);
        Main.log("Set toughness to :" + toughness);
        this.toughness = toughness;
    }
    
}

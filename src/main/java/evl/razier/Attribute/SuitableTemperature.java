package evl.razier.Attribute;

import evl.razier.Main;

public class SuitableTemperature {
    public float MinTemperature;
    public float MaxTemperature;

    public SuitableTemperature(float MinTemperature, float MaxTemperature){
        this.MinTemperature = MinTemperature;
        this.MaxTemperature = MaxTemperature;
    }
    
    public void setMinTemperature(float MinTemperature){
        Main.log("Original min temperature :" + this.MinTemperature);
        Main.log("Set min temperature to :" + MinTemperature);
        this.MinTemperature = MinTemperature;
    }
    
    public void setMaxTemperature(float MaxTemperature){
        Main.log("Original max temperature :" + this.MaxTemperature);
        Main.log("Set max temperature to :" + MaxTemperature);
        this.MaxTemperature = MaxTemperature;
    }
}

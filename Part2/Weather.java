
import java.awt.Color;

public class Weather {
    private String weatherName;
    private int speedModifier;
    private double fallingModifier;
    private Color color;

    public Weather(String weatherName, int speedModifier, double fallingModifier, Color color) {
        this.weatherName = weatherName;
        this.speedModifier = speedModifier;
        this.fallingModifier = fallingModifier;
        this.color = color;
    }

    public String getName(){
        return weatherName;
    }
    public int getSpeedModifier(){
        return speedModifier;
    }
    public double getFallingModifier(){
        return fallingModifier;
    }
    public Color getColor(){
        return color;
    }
}

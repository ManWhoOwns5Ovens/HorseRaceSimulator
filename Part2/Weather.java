
import java.awt.Color;

public class Weather extends RaceCondition{
    private String weatherName;
    private Color color;

    public Weather(double speedModifier, double fallingModifier, String weatherName,Color color) {
        super(speedModifier, fallingModifier);
        this.weatherName = weatherName;
        this.color = color;
    }

    public String getName(){
        return weatherName;
    }
    public Color getColor(){
        return color;
    }
}

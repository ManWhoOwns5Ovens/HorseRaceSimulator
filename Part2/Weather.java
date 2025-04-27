
import java.awt.Color;

public class Weather extends RaceCondition{
    private double fallingModifier;
    private Color color;

    public static final Weather SUNNY = new Weather( "Sunny",0, 1.0, Color.YELLOW);
    public static final Weather RAINY = new Weather( "Rainy", -2.0, 1.0, Color.CYAN);
    public static final Weather SNOWY = new Weather( "Snowy", 0, 2.0, Color.WHITE);

    public Weather(String name, double speedModifier, double fallingModifier,Color color) {
        super(name,speedModifier);
        this.fallingModifier=fallingModifier;
        this.color = color;
    }
    public Color getColor(){
        return color;
    }
    public double getFallingModifier(){
        return fallingModifier;
    }
}

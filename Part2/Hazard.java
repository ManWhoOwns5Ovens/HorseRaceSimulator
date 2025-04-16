//anything that can affect horse performance
abstract class Hazard {
    private int speedModifier;
    private double fallingModifier;

    public Hazard(int speedModifier, double fallingModifier) {
        this.speedModifier = speedModifier;
        this.fallingModifier = fallingModifier;
    }

    public int getSpeedModifier(){
        return speedModifier;
    }
    public double getFallingModifier(){
        return fallingModifier;
    }
    
}

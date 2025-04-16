//anything that can affect horse performance
// 1m/s = 1 movement/second
abstract class RaceCondition {
    private double speedModifier;
    private double fallingModifier;

    public RaceCondition(double speedModifier, double fallingModifier) {
        this.speedModifier = speedModifier;
        this.fallingModifier = fallingModifier;
    }

    public double getSpeedModifier(){
        return speedModifier;
    }
    public double getFallingModifier(){
        return fallingModifier;
    }
    
}

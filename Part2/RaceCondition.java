//anything that can affect horse performance
// 1m/s = 1 movement/second
abstract class RaceCondition {
    private String name;
    private double speedModifier;

    public RaceCondition(String name,double speedModifier) {
        this.name=name;
        this.speedModifier = speedModifier;
    }

    public double getSpeedModifier(){
        return speedModifier;
    }

    @Override
    public String toString(){
        return this.name;
    }
    
}

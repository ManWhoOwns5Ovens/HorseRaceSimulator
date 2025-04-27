abstract class HorseCondition extends RaceCondition{
    private double confidenceModifier;
    private int enduranceModifier;

    public HorseCondition(String name, double speedModifier,double confidenceModifier,int enduranceModifier){
        super(name, speedModifier);
        this.confidenceModifier=confidenceModifier;
        this.enduranceModifier=enduranceModifier;
    }

    public double getConfidenceModifier(){
        return this.confidenceModifier;
    }

    public int getEnduranceModifier(){
        return this.enduranceModifier;
    }
}

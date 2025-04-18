public class Saddle extends HorseCondition{

    public static final Saddle RACING = new Saddle("Racing",1.0, 1.0, -5);
    public static final Saddle ENDURANCE = new Saddle("Endurance",0.0, 1.5, 5); 
    public static final Saddle WESTERN = new Saddle("Western",-1.0, 1.5, 0); 
    public static final Saddle JUMPING = new Saddle("Jumping",0.0, 1.0, 0); 
    public static final Saddle NO_SADDLE = new Saddle("No Saddle",1.0, 0.67, -5);  

    public static final Saddle[] ALL_SADDLES=new Saddle[]{
        Saddle.RACING,
        Saddle.ENDURANCE,
        Saddle.WESTERN,
        Saddle.JUMPING,
        Saddle.NO_SADDLE
    };

    public Saddle(String name, double speedModifier,double confidenceModifier,int enduranceModifier){
        super(name, speedModifier,confidenceModifier,enduranceModifier);
    }
}

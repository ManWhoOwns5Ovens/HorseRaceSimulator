public class Accessory extends HorseCondition{

    public static final Accessory BLINDERS = new Accessory("Blinders",-1.0, 1.5, 0);
    public static final Accessory NOSEBAND = new Accessory("Noseband",0.0, 1.0, 5); 
    public static final Accessory EARPLUGS = new Accessory("Earplugs",0.0, 1.5, 0); 
    public static final Accessory LEG_WRAPS = new Accessory("Leg Wraps",1.0, 1.0, 5); 
    public static final Accessory TOP_HAT = new Accessory("Top Hat",0.0, 2.0, 0); 

    public static final Accessory[] ALL_ACCESSORIES=new Accessory[]{
        Accessory.BLINDERS,
        Accessory.NOSEBAND,
        Accessory.EARPLUGS,
        Accessory.LEG_WRAPS,
        Accessory.TOP_HAT
    };

    public Accessory(String name, double speedModifier,double confidenceModifier,int enduranceModifier){
        super(name, speedModifier,confidenceModifier,enduranceModifier);
    }
}

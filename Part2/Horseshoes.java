public class Horseshoes extends HorseCondition{

    public static final Horseshoes LIGHTWEIGHT = new Horseshoes("Lightweight",1.0, 1.0, -5);
    public static final Horseshoes STEEL = new Horseshoes("Steel",0.0, 1.5, 5); 
    public static final Horseshoes RUBBER = new Horseshoes("Rubber",-1.0, 1.5, 0); 
    public static final Horseshoes SPIKED = new Horseshoes("Spiked",1.0, 0.67, 0); 
    public static final Horseshoes THERAPEUTIC = new Horseshoes("Therapeutic",-1.0, 1.0, 5);  

    public static final Horseshoes[] ALL_HORSESHOES=new Horseshoes[]{
        Horseshoes.LIGHTWEIGHT,
        Horseshoes.STEEL,
        Horseshoes.RUBBER,
        Horseshoes.SPIKED,
        Horseshoes.THERAPEUTIC
    };

    public Horseshoes(String name, double speedModifier,double confidenceModifier,int enduranceModifier){
        super(name, speedModifier,confidenceModifier,enduranceModifier);
    }
}

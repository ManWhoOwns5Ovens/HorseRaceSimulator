public class Breed extends HorseCondition{

    public static final Breed THOROUGHBRED = new Breed("Thoroughbred",1.0, 1.0, -5);
    public static final Breed ARABIAN = new Breed("Arabian",0.0, 1.5, 5); 
    public static final Breed CLYDESDALE = new Breed("Clydesdale",-1.0, 0.67, 5); 
    public static final Breed MUSTANG = new Breed("Mustang",-1.0, 1.5, 0); 
    public static final Breed QUARTER_HORSE = new Breed("Quarter Horse",1.0, 0.67, -5); 

    public static final Breed[] ALL_BREEDS=new Breed[]{
        Breed.CLYDESDALE,
        Breed.THOROUGHBRED,
        Breed.ARABIAN, 
        Breed.MUSTANG,
        Breed.QUARTER_HORSE
    };

    public Breed(String name, double speedModifier,double confidenceModifier,int enduranceModifier){
        super(name, speedModifier,confidenceModifier,enduranceModifier);
    }
}

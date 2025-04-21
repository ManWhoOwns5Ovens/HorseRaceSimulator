
import java.awt.Color;


/**
 * Class that represents a horse in the simulator. Each attribute/stat impacts how the horse looks, behaves and performs in the simulator.
 * This class is the core data model for each horse competitor in the race.
 * 
 * @author David Valsan
 * @version 1.1
 */
public class Horse
{
    //Fields of class Horse
    private String horseName;
    private char horseSymbol;
    private int distanceTravelled;
    private boolean fallen;
    private double horseConfidence;

    private double horseSpeed;
    private int horseEndurance;

    private Breed horseBreed;
    private Saddle horseSaddle;
    private Horseshoes horseshoes;
    private Accessory horseAccessory;

    private Color horseCoat;
      
    //Constructor of class Horse
    /**
     * Constructor for objects of class Horse
     */
    public Horse(char horseSymbol, String horseName, double horseConfidence, double horseSpeed, Breed horseBreed, Saddle horseSaddle, 
    Horseshoes horseshoes, Accessory horseAccessory, Color horseCoat)
    {
        this.horseName = horseName;
        this.horseSymbol = horseSymbol;
        this.distanceTravelled = 0;
        this.fallen = false;
        setConfidence(horseConfidence);

        setSpeed(horseSpeed);
        this.horseEndurance=1;

        this.horseBreed=horseBreed;
        this.horseSaddle=horseSaddle;
        this.horseshoes=horseshoes;
        this.horseAccessory=horseAccessory;

        this.horseCoat=horseCoat;
    }

    //Other methods of class Horse
    public void fall()
    {
        this.fallen = true;
    }

    public double getConfidence()
    {
        return this.horseConfidence;
    }
    public int getDistanceTravelled()
    {
        return this.distanceTravelled;
    }
    public String getName()
    {
        return this.horseName;
    }
    public char getSymbol()
    {
        return this.horseSymbol;
    }
    public double getSpeed(){
        return this.horseSpeed;
    }
    public int getEndurance(){
        return this.horseEndurance;
    }
    public Color getCoatColor(){
        return this.horseCoat;
    }
    public Breed getBreed(){
        return this.horseBreed;
    }
    public Saddle getSaddle(){
        return this.horseSaddle;
    }
    public Horseshoes getHorseshoes(){
        return this.horseshoes;
    }
    public Accessory getAccessory(){
        return this.horseAccessory;
    }

    //used by Race, returns movement interval after the user has input the weather
    //takes the final speed
    public int getMovementInterval(Weather raceWeather){
        double finalSpeed=getFinalSpeed() + raceWeather.getSpeedModifier();
        if(finalSpeed<=0.0){
            return 10000;
        }
        else{
            return (int)(1000/finalSpeed);
        }
    }

    //final= stat after all modifiers from the equipment and breed are applied
    public double getFinalSpeed(){
        double finalSpeed=this.horseSpeed
        +this.horseBreed.getSpeedModifier()
        +this.horseSaddle.getSpeedModifier()
        +this.horseshoes.getSpeedModifier()
        +this.horseAccessory.getSpeedModifier();

        if(finalSpeed<=0.0){
            return 0.0;
        }
        else{
            return finalSpeed;
        }
    }

    public double getFinalConfidence(){
        double finalConfidence=this.horseConfidence
        *this.horseBreed.getConfidenceModifier()
        *this.horseSaddle.getConfidenceModifier()
        *this.horseshoes.getConfidenceModifier()
        *this.horseAccessory.getConfidenceModifier();

        if(finalConfidence>1.0){
            return 1.0;
        }
        else{
            return finalConfidence;
        }
    }

    
    public void goBackToStart(int raceLength)
    {
        this.distanceTravelled = 0;
        this.fallen = false;
        setInitialEndurance(raceLength);
    }
    
    public boolean hasFallen()
    {
        if(this.fallen){
            return true;
        }
        return false;
    }

    public void moveForward()
    {
        this.distanceTravelled++;
    }

    public final void setConfidence(double newConfidence)
    {
        if(newConfidence <=1.0 && newConfidence > 0.0){
            this.horseConfidence = newConfidence;
        }
        else if(newConfidence <= 0.0){
            this.horseConfidence = 0.01;
        }
        else if(newConfidence > 1.0){
            this.horseConfidence = 1.0;
        }
    }
    
    public void setSymbol(char newSymbol)
    {
        this.horseSymbol = newSymbol;
    }

    public final void setSpeed(double newSpeed){
        if(newSpeed>0.0){
            this.horseSpeed=newSpeed;
        }
        else{
            this.horseSpeed=0.0001;
        }
    }

    //endurance scales with the race length
    public final void setInitialEndurance(int raceLength){
        int newEndurance=raceLength
        +this.horseBreed.getEnduranceModifier()
        +this.horseSaddle.getEnduranceModifier()
        +this.horseshoes.getEnduranceModifier()
        +this.horseAccessory.getEnduranceModifier();

        newEndurance*=5;

        if(newEndurance<=0){
            this.horseEndurance=1;
        }
        else{
            this.horseEndurance=newEndurance;
        }
    }

    public void changeEndurance(int modifier){
        this.horseEndurance=this.horseEndurance+modifier;
    }
    
}



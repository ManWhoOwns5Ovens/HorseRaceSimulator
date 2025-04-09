
/**
 * Write a description of class Horse here.
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
      
    //Constructor of class Horse
    /**
     * Constructor for objects of class Horse
     */
    public Horse(char horseSymbol, String horseName, double horseConfidence)
    {
        this.horseName = horseName;
        this.horseSymbol = horseSymbol;
        this.distanceTravelled = 0;
        this.fallen = false;
        setConfidence(horseConfidence);
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
    
    public void goBackToStart()
    {
        this.distanceTravelled = 0;
        this.fallen = false;
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
    
}



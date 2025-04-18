import java.util.ArrayList;
import javax.swing.Timer;

/**
 * A three-horse race, each horse running in its own lane
 * for a given distance
 * 
 * @author McRaceface
 * @version 1.0
 */
public class Race
{
    private static Race instance;

    private int raceLength;
    private int laneCount;
    private LaneType laneType;
    private Weather weather;

    private ArrayList<Horse> horses;

    private Timer[] horseTimers;
   
    /**
     * Constructor for objects of class Race
     * Initially there are no horses in the lanes
     * 
     * @param distance the length of the racetrack (in metres/yards...)
     */
    public Race(int distance, int laneCount,LaneType laneType, Weather weather)
    {
        if(instance==null){
            this.horses= new ArrayList<>();
        }
        else{
            this.horses= instance.getHorses();
        }
        // initialise instance variables
        setRaceLength(distance);
        setLaneCount(laneCount);
        this.laneType=laneType;
        this.weather=weather;
    }

    /**
     * Mutator with validation for the race length
     * 
     * @param raceLength user input for the race length
     */
    private void setRaceLength(int raceLength){
        final int MINIMUM_LENGTH=10; // min/default length of race
        final int MAXIMUM_LENGTH=60; //max. length of race

        if(raceLength<MINIMUM_LENGTH || raceLength>MAXIMUM_LENGTH){
            this.raceLength=MINIMUM_LENGTH;
        }
        else{
            this.raceLength=raceLength;
        }
    }

    /**
     * Mutator with validation for the number of lanes
     * 
     * @param laneCount user input for the number of lanes
     */
    private void setLaneCount(int laneCount){
        if(laneCount<3){
            this.laneCount=3;
        }
        else{
            this.laneCount=laneCount;
        }
        
    }

    public int getRaceLength(){
        return this.raceLength;
    }
    public int getLaneCount(){
        return this.laneCount;
    }
    public LaneType getLaneType(){
        return this.laneType;
    }
    public Weather getWeather(){
        return this.weather;
    }
    public ArrayList<Horse> getHorses(){
        return this.horses;
    }
    
    /**
     * Adds a horse to the race in a given lane
     * 
     * @param theHorse the horse to be added to the race
     * @param laneNumber the lane that the horse will be added to
     */
    public void addHorse(Horse theHorse)
    {
        if(instance==null){
            this.horses.add(theHorse);
        }   
    }
    
    /**
     * Start the race
     * The horses are brought to the start and then repeatedly moved forward until the race is finished.
     * 
     */
    public void startRace()
    {
        instance=this;
        ArrayList<Horse> winners=new ArrayList<>();//number of horses in a tie can differ

        resetHorses();
        horseTimers=new Timer[horses.size()];

        for(int i=0; i<horses.size();i++){
            Horse currentHorse=horses.get(i);
            horseTimers[i]=new Timer(currentHorse.getMovementInterval(this.weather), e->{
                moveHorse(currentHorse);
                if(raceWonBy(currentHorse)){ //check every land at the end of a state
                    winners.add(currentHorse);
                    updateHorseConfidence(currentHorse, true);//increase confidence of winner
                }
                //if race has ended (there are winners, or all horses have fallen)
                if(!winners.isEmpty() || !canRaceContinue()){
                    stopAllTimers(); //stop the timer
                    RaceSettingsGUI.raceEnd(winners);
                }
            });
            horseTimers[i].start(); //start the timer to move the horses
        }
    }

    private void stopAllTimers(){
        for(Timer timer: horseTimers){
            timer.stop();
        }
    }
    
    /**
     * Randomly make a horse move forward or fall depending
     * on its confidence rating
     * A fallen horse cannot move
     * 
     * @param theHorse the horse to be moved
     */
    private void moveHorse(Horse theHorse)
    {
        //if the horse has fallen it cannot move, 
        //so only run if it has not fallen
        if  (!theHorse.hasFallen())
        {
            //the probability that the horse will move forward depends on the confidence;
            if (Math.random() < theHorse.getFinalConfidence())
            {
               theHorse.moveForward();
            }
            
            //the probability that the horse will fall is very small (max is 0.1)
            //but will also will depends exponentially on confidence 
            //so if you double the confidence, the probability that it will fall is *2
            if (Math.random() < (0.1*theHorse.getFinalConfidence()*theHorse.getFinalConfidence()*weather.getFallingModifier()))
            {
                theHorse.fall();
                updateHorseConfidence(theHorse, false);
            }
        }
    }
        
    /** 
     * Determines if a horse has won the race
     *
     * @param theHorse The horse we are testing
     * @return true if the horse has won, false otherwise.
     */
    private boolean raceWonBy(Horse theHorse)
    {
        if (theHorse.getDistanceTravelled() == raceLength)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
     
    /**
    * Checks if the race can continue on by checking if the horses are in a condition to continue.
    *
    *@param horses contains refrences to all horse objects
     */
    private boolean canRaceContinue(){
        int fallenHorses=0;
        for (int i=0; i<horses.size();i++){
            if (horses.get(i).hasFallen()){
                fallenHorses++; 
            }  
        }
        if(fallenHorses>=horses.size()){
            return false;
        }
        return true;
    }

    /***
     * Applies goBackToStart to every horse, ensuring they are all reset
     * 
     * @param horseArr array holding refrences to every horse object
     */
    private void resetHorses(){
        //reset all the lanes (all horses not fallen and back to 0). 
        for (int i=0; i<horses.size();i++){
            horses.get(i).goBackToStart();
        }
    }

    /**
     * Modify a horse's confidence rating
     * 
     * @param theHorse horse object to have its confidence modified
     * @param isIncrease true if the confidence is to be increased, false if it is to be decreased
     */
    private void updateHorseConfidence(Horse theHorse, boolean isIncrease){
        final double MODIFIFER=0.1;
        if(isIncrease){
            theHorse.setConfidence(App.roundDouble((theHorse.getConfidence()+MODIFIFER),1));
        }
        else{
            theHorse.setConfidence(App.roundDouble((theHorse.getConfidence()-MODIFIFER),1));
        }
    }

    
}


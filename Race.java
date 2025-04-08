import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * A three-horse race, each horse running in its own lane
 * for a given distance
 * 
 * @author McRaceface
 * @version 1.0
 */
public class Race
{
    private int raceLength;
    private int laneCount;
    private Horse lane1Horse;
    private Horse lane2Horse;
    private Horse lane3Horse;
    
    /**
     * Constructor for objects of class Race
     * Initially there are no horses in the lanes
     * 
     * @param distance the length of the racetrack (in metres/yards...)
     */
    public Race(int distance)
    {
        // initialise instance variables
        setRaceLength(distance);
        laneCount=3; 
        lane1Horse = null;
        lane2Horse = null;
        lane3Horse = null;
    }

    private void setLaneCount(int newLaneCount){
        if(newLaneCount<3){
            this.laneCount=3;
        }
        else{
            this.laneCount=newLaneCount;
        }
        
    }
    
    /**
     * Adds a horse to the race in a given lane
     * 
     * @param theHorse the horse to be added to the race
     * @param laneNumber the lane that the horse will be added to
     */
    public void addHorse(Horse theHorse, int laneNumber)
    {
        if (laneNumber == 1)
        {
            lane1Horse = theHorse;
        }
        else if (laneNumber == 2)
        {
            lane2Horse = theHorse;
        }
        else if (laneNumber == 3)
        {
            lane3Horse = theHorse;
        }
        else
        {
            System.out.println("Cannot add horse to lane " + laneNumber + " because there is no such lane");
        }
    }
    
    /**
     * Start the race
     * The horses are brought to the start and then repeatedly moved forward until the race is finished.
     * 
     */
    public void startRace()
    {
        //declare a local variable to tell us when the race is finished
        boolean finished = false;

        Horse[] horseArr= {lane1Horse,lane2Horse,lane3Horse}; //keep refrences to objects in one place to avoid repetitive code by iterating through array
        ArrayList<Horse> winners=new ArrayList<>();//number of horses in a tie can differ
            
        resetHorses(horseArr);

        setLaneCount(App.adjustRaceSetting("How many lanes would you like? (3 or more)")); //ask user how many lanes they want

        while (!finished)
        {
            //move each horse
            for (int i=0; i<horseArr.length;i++){
                moveHorse(horseArr[i]);
            }
                        
            //print the race positions
            printRace();
            
            //check for and record winners
            for(int i=0; i<horseArr.length;i++){
                if(raceWonBy(horseArr[i])){ //check every land at the end of a state
                    winners.add(horseArr[i]);
                    updateHorseConfidence(horseArr[i], true);//increase confidence of winner
                }
            }

            //if race has ended (there are winners, or all horses have fallen)
            if(!winners.isEmpty() || !canRaceContinue(horseArr)){
                printWinners(winners);
                finished=true;   
            }
            //wait for 300 milliseconds between "frames"
            try{ 
                TimeUnit.MILLISECONDS.sleep(300);
            }
            catch(InterruptedException e){
                System.out.println("Process intrerupted. Aborting race.");
                return;
            }
        }
    }

    /**
     * If there is one winner recorded will anounce their win
     * If there are more than one winners will output a statement anouncing all of them
     * 
     * @param winners are horses who arrived at the finshing line first in the same state
     * Recorded and called by startRace()
     */
    private void printWinners(ArrayList<Horse> winners){
        if(winners.size()>=2){
            System.out.print("\n It's a tie between ");
            for(int i=0; i<winners.size();i++){
                if(i!=0){System.out.print(" AND ");}// add AND in front if not the first horse
                System.out.print(winners.get(i).getName());
            }
        }
        else if(winners.size()==1){
            System.out.println("And the winner is... "+winners.get(0).getName()+"!");//Prints race winner;
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
            if (Math.random() < theHorse.getConfidence())
            {
               theHorse.moveForward();
            }
            
            //the probability that the horse will fall is very small (max is 0.1)
            //but will also will depends exponentially on confidence 
            //so if you double the confidence, the probability that it will fall is *2
            if (Math.random() < (0.1*theHorse.getConfidence()*theHorse.getConfidence()))
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
    
    /***
     * Print the race on the terminal
     */
    private void printRace()
    {
        System.out.print("\033\143"); //clear the terminal window
        //replaced to properly clear terminal
        
        multiplePrint('=',raceLength+3); //top edge of track
        System.out.println();
        
        printLane(lane1Horse);
        System.out.println();
        
        printLane(lane2Horse);
        System.out.println();
        
        printLane(lane3Horse);
        System.out.println();

        for(int i=0; i<laneCount-3;i++){
            printLane(null);
            System.out.println();
        }
        
        multiplePrint('=',raceLength+3); //bottom edge of track
        System.out.println();    
    }
    
    /**
     * print a horse's lane during the race
     * for example
     * |           X                      |
     * to show how far the horse has run
     */
    private void printLane(Horse theHorse)
    {
        if(theHorse == null)
        {
            System.out.print('|');
            multiplePrint(' ',raceLength);
            System.out.print('|');
            return; 
        }

        //calculate how many spaces are needed before
        //and after the horse
        int spacesBefore = theHorse.getDistanceTravelled();
        int spacesAfter = raceLength - theHorse.getDistanceTravelled();
        
        //print a | for the beginning of the lane
        System.out.print('|');
        
        //print the spaces before the horse
        multiplePrint(' ',spacesBefore);
        
        //if the horse has fallen then print dead
        //else print the horse's symbol
        if(theHorse.hasFallen())
        {
            System.out.print('❌');
        }
        else
        {
            System.out.print(theHorse.getSymbol());
        }
        
        //print the spaces after the horse
        multiplePrint(' ',spacesAfter);
        
        //print the | for the end of the track
        System.out.print('|');

        System.out.println(" "+theHorse.getName()+" (Current confidence "+theHorse.getConfidence()+")");
    }
     
    /**
    * Checks if the race can continue on by checking if the horses are in a condition to continue.
    *
    *@param horses contains refrences to all horse objects
     */
    private boolean canRaceContinue(Horse[] horses){
        int fallenHorses=0;
        for (int i=0; i<horses.length;i++){
            if (horses[i].hasFallen()){
                fallenHorses++; 
            }  
        }
        if(fallenHorses>=horses.length){
            System.out.println("Horses cannot continue anymore. Suspending the race.");
            return false;
        }
        return true;
    }
    
    /***
     * print a character a given number of times.
     * e.g. printmany('x',5) will print: xxxxx
     * 
     * @param aChar the character to Print
     */
    private void multiplePrint(char aChar, int times)
    {
        int i = 0;
        while (i < times)
        {
            System.out.print(aChar);
            i = i + 1;
        }
    }

    /***
     * Applies goBackToStart to every horse, ensuring they are all reset
     * 
     * @param horseArr array holding refrences to every horse object
     */
    private void resetHorses(Horse[] horseArr){
        //reset all the lanes (all horses not fallen and back to 0). 
        for (int i=0; i<horseArr.length;i++){
            horseArr[i].goBackToStart();
        }
    }

    /**
     * Mutator with validation for the race length
     * 
     * @param raceLength user input for the race length
     */
    public final void setRaceLength(int raceLength){
        final int MINIMUM_LENGTH=3; //min. length of race
        final int DEFAULT_LENGTH=10; //ideal and recommended race length

        if(raceLength<MINIMUM_LENGTH){
            this.raceLength=DEFAULT_LENGTH;
        }
        else{
            this.raceLength=raceLength;
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


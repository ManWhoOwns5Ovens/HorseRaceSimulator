import java.util.Scanner;

public class App {
    public static void main(String[] a){
        boolean finished= false;
        Race race= new Race(adjustRaceSetting("What length should the track be? (min. 2)"));
        race.addHorse(new Horse('♘', "PIPPI LONGSTOCKING", 0.6), 1);
        race.addHorse(new Horse('♞', "KOKOMO", 0.5), 2);
        race.addHorse(new Horse('♛', "EL JEFE", 0.4), 3);

        while(!finished){
            race.startRace();
            if(!input("Would you like to run another race?(y/n)").equals("y")){
                finished=true;
                System.exit(0);// close frame, end program
            }
            else{
                race.setRaceLength(adjustRaceSetting("What length should the track be? (min. 2)"));
            }
        }
    }

    /**
     * Generic method used to collect information from the user on how the race should be set up.
     * Used for the number of lanes and the length of the track.
     * 
     * @param message message requesting input
     * @return the user input as an integer - this input is given to mutator methods, however the function ensures it is kept numeric
     */
    public static int adjustRaceSetting(String message){
        boolean validInput=false;
        int userInput=0;
        while(!validInput){
            try{
                userInput=Integer.parseInt(App.input(message));
                validInput=true;
            }
            catch (NumberFormatException e){
                System.out.println("Invalid input. Please try again.");
            }
        }
        return userInput;
    }

    /**
     * Generic method for collecting user input as a string
     * 
     * @param message message requesting input
     */
    public static String input(String message){
        Scanner s= new Scanner(System.in);
        System.out.println(message);
        return s.nextLine();
    }   

    /**
     * Rounds double to a given number of decimal places
     * 
     * @param number the number to be rounded
     * @param dp the number of decimal places to round to
     */
    public static double roundDouble(double number, int dp){
        double factor=Math.pow(10, dp);
        return (double)(((int)(number*factor))/factor);
    }
}

import java.util.Scanner;

public class App {
    public static void main(String[] a){
        boolean finished= false;
        Race race= new Race(10);
        race.addHorse(new Horse('♘', "PIPPI LONGSTOCKING", 0.6), 1);
        race.addHorse(new Horse('♞', "KOKOMO", 0.5), 2);
        race.addHorse(new Horse('♛', "EL JEFE", 0.4), 3);

        while(!finished){
            race.startRace();
            if(!input("Would you like to run another race?(y/n)").equals("y")){
                finished=true;
            }
        }
        
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

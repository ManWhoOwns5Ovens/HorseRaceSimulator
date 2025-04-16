
public class App {

    public static final int interval = 100;
    
    public static void main(String[] a){
        RaceSettingsGUI.createFrame();
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

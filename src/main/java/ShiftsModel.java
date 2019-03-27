import java.util.ArrayList;

public class ShiftsModel {

    private ArrayList<ShiftModel> allShifts;

    private static final double HOURLY_RATE = 8.70;
    private static final double BREAK_LENGTH = 0.25;


    /**
     * Shifts Model Constructor
     */
    public ShiftsModel(){
        allShifts = new ArrayList<>();
    }


    /**
     * Add a shift to the ArrayList containing all shifts
     * @param shift
     */
    public void addShift(ShiftModel shift){
        allShifts.add(shift);
    }


    /**
     * Returns an an arraylist containing all shift information
     * @return allShifts
     */
    public ArrayList<ShiftModel> getAllShifts(){
        return allShifts;
    }


    /**
     * Get the total time in hours worked at the company
     * @return totalTime
     */
    public int getTotalTimeWorked(){

        int totalTime = 0;

        for(ShiftModel shift : allShifts){
            totalTime += shift. getDuration();
        }

        return totalTime;
    }


    /**
     * Get the total pay that should be earned
     * @return Pay
     */
    public double getTotalAmountEarned(){

        double breakDeficit = getAllShifts().size() * BREAK_LENGTH;
        double totalTime = getTotalTimeWorked() - breakDeficit;

        return totalTime * HOURLY_RATE;
    }

































}

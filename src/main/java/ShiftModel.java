import java.util.Date;
import java.text.SimpleDateFormat;

public class ShiftModel {

    private String startFormat;
    private String finishFormat;
    private SimpleDateFormat format;

    private Date startTime;
    private Date finishTime;


    /**
     * Constructor for Shift Model
     * Strings passed in MUST be of the format dd-MMM-yyyy HH:mm:ss
     * @param start
     * @param finish
     */
    public ShiftModel( String start, String finish ){

        //Read in date as string
        format = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        startFormat = start;
        finishFormat = finish;

        //Then convert to date data type
        try {

            startTime = format.parse(startFormat);
            finishTime = format.parse(finishFormat);

        } catch(Exception e){

            System.out.println("An Exception has been thrown suggesting that the supplied input is not of the right format");
        }
    }


    public String getDate(){
        return startTime.toString();
    }

    /**
     * Getter method for start time of a shift
     * @return the start time of a shift
     */
    public int getStartTime(){
        return startTime.getHours();

    }


    /**
     * Getter method for finish time of a shift
     * @return the finish time of a shift
     */
    public int getFinishTime(){
        return finishTime.getHours();
    }


    /**
     * Getter method for the duartion of a shift
     * @return the length of the shift in hours
     */
    public int getDuration(){
        int duration = getFinishTime() - getStartTime();
        return duration;
    }

    public String getStartFormat() {
        return startFormat;
    }

    public String getFinishFormat() {
        return finishFormat;
    }
}



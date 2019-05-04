import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;

public class ShiftModel extends Date {

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


    public Date getDateObject(){
        return startTime;
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


    public String getWeekDay(){
        Calendar c = Calendar.getInstance();
        c.setTime(startTime);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        String weekDay ="";


        switch(dayOfWeek){
            case 1: weekDay = "Sunday";
                    break;
            case 2: weekDay = "Monday";
                break;
            case 3: weekDay = "Tuesday";
                break;
            case 4: weekDay = "Wednesday";
                break;
            case 5: weekDay = "Thursday";
                break;
            case 6: weekDay = "Friday";
                break;
            case 7: weekDay = "Saturday";
                break;
        }
        return weekDay;
    }

    public String getStartFormat() {
        return startFormat;
    }

    public String getFinishFormat() {
        return finishFormat;
    }



    //This fucked because it does not take into consideration that months have different ammounts of days fucksake
    //This some how needs to make a month into consideration
    public int getDateImproved(int input){

        if(input==31){
            return input - 30;
        }
        if(input>=32){
            return input -30;
        }
        return input;


    }
}



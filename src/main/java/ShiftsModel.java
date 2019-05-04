import java.io.*;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;


public class ShiftsModel{

    private ArrayList<ShiftModel> allShifts;

    private static final double HOURLY_RATE = 8.70;
    private static final double BREAK_LENGTH = 0.25;

    private static final String SHIFT_FILE = "shift_file.txt";


    /**
     * Shifts Model Constructor
     */
    public ShiftsModel(){
        allShifts = loadExistingShifts();


        File shiftFile = new File(SHIFT_FILE);

        try {
            if (!shiftFile.exists()) {
                shiftFile.createNewFile();
            }
        } catch (IOException ex) {
            System.out.println("Error unable to write file");
        }

    }


    /**
     * Add a shift to the ArrayList containing all shifts
     * It will ensure that a shift can only be added to this ArrayList once.
     *
     * @param shift
     */
    public void addShift(ShiftModel shift) {

        boolean exists = false;

        for (ShiftModel x : allShifts) {
            if (x.getStartFormat().equals(shift.getStartFormat())) {
                exists = true;
            }
        }

        if (!exists) {
            allShifts.add(shift);
            writeShiftToFile(shift);
        }

    }


    /**
     * Returns an an arraylist containing all shift information
     *
     * @return allShifts
     */
    public ArrayList<ShiftModel> getAllShifts() {
        return allShifts;
    }


    /**
     * Get the total time in hours worked at the company
     *
     * @return totalTime
     */
    public int getTotalTimeWorked() {

        int totalTime = 0;

        for (ShiftModel shift : allShifts) {
            totalTime += shift.getDuration();
        }

        return totalTime;
    }


    /**
     * Get the total pay that should be earned
     *
     * @return Pay
     */
    public double getTotalAmountEarned() {

        double breakDeficit = getAllShifts().size() * BREAK_LENGTH;
        double totalTime = getTotalTimeWorked() - breakDeficit;

        return Math.round((totalTime * HOURLY_RATE) * 100.0) / 100.0;
    }

    /**
     * Get the total pay that should be earned
     *
     * @return Pay
     */
    public double getMonthlyAmountEarned(int y, double x) {

        double breakDeficit = y * BREAK_LENGTH;
        double totalTime = x - breakDeficit;

        return Math.round((totalTime * HOURLY_RATE) * 100.0) / 100.0;
    }


    /**
     * @return
     */
    public HashMap<Date, ArrayList<String>> splitIntoWeeks() {

        HashMap<Date, ArrayList<String>> splitToWeeks = new HashMap<>();
        ArrayList<ShiftModel> weekStartsAL = loadWeekStarts();

        int max = 0; //Holds length of all shifts(i.e. total number of shifts)
        Date currentDate = allShifts.get(max).getDateObject(); // Current date being looked at
        int counter = getClosestMonday(weekStartsAL, currentDate);

        try {
            while (max != allShifts.size()) {

                ArrayList<ShiftModel> currentWeek = new ArrayList<>();

                Date weekStart = weekStartsAL.get(counter).getDateObject();
                Date NextWeekStart = weekStartsAL.get(counter + 1).getDateObject();

                if (currentDate.equals(NextWeekStart)) {
                    currentWeek.add(allShifts.get(max));
                }

                while (currentDate.before(NextWeekStart)) {

                    currentWeek.add(allShifts.get(max));

                    max++;
                    if (currentDate.after(NextWeekStart) || max == allShifts.size()) {
                        break;
                    }
                    currentDate = allShifts.get(max).getDateObject();
                }
                splitToWeeks.put(weekStartsAL.get(counter).getDateObject(), indentifyDayOfWeek(currentWeek));
                counter++;


            }
        } catch (NullPointerException ex) {
            System.out.println("A NPE has been thrown from splitWeek Method");
        }
        return splitToWeeks;
    }


    /**
     * @param dateFormatArray
     * @return
     */
    public ArrayList<String> indentifyDayOfWeek(ArrayList<ShiftModel> dateFormatArray) {

        HashMap<String, String> identifyWeekDay = new HashMap<>();

        ArrayList<String> weekDayArr = new ArrayList<>();


        identifyWeekDay.put("Monday", "OFF");
        identifyWeekDay.put("Tuesday", "OFF");
        identifyWeekDay.put("Wednesday", "OFF");
        identifyWeekDay.put("Thursday", "OFF");
        identifyWeekDay.put("Friday", "OFF");
        identifyWeekDay.put("Saturday", "OFF");
        identifyWeekDay.put("Sunday", "OFF");


        for (ShiftModel x : dateFormatArray) {

            int dayOfWeek = x.getDateObject().getDay();

            switch (dayOfWeek) {

                case 1:
                    identifyWeekDay.replace("Monday", x.getStartTime() + "-" + x.getFinishTime());
                    break;
                case 2:
                    identifyWeekDay.replace("Tuesday", x.getStartTime() + "-" + x.getFinishTime());
                    break;
                case 3:
                    identifyWeekDay.replace("Wednesday", x.getStartTime() + "-" + x.getFinishTime());
                    break;
                case 4:
                    identifyWeekDay.replace("Thursday", x.getStartTime() + "-" + x.getFinishTime());
                    break;
                case 5:
                    identifyWeekDay.replace("Friday", x.getStartTime() + "-" + x.getFinishTime());
                    break;
                case 6:
                    identifyWeekDay.replace("Saturday", x.getStartTime() + "-" + x.getFinishTime());
                    break;
                case 0:
                    identifyWeekDay.replace("Sunday", x.getStartTime() + "-" + x.getFinishTime());
                    break;
            }

        }

        weekDayArr.add(identifyWeekDay.get("Monday"));
        weekDayArr.add(identifyWeekDay.get("Tuesday"));
        weekDayArr.add(identifyWeekDay.get("Wednesday"));
        weekDayArr.add(identifyWeekDay.get("Thursday"));
        weekDayArr.add(identifyWeekDay.get("Friday"));
        weekDayArr.add(identifyWeekDay.get("Saturday"));
        weekDayArr.add(identifyWeekDay.get("Sunday"));

        return weekDayArr;
    }


    /**
     * @param offset
     * @return
     */
    public ShiftModel getCurrentWeekMondayAdaptable(int offset) {

        Date current = new Date();
        ArrayList<ShiftModel> weekStartsAL = loadWeekStarts();

        int i = 0;
        while (current.after(weekStartsAL.get(i).getDateObject())) {
            i++;
        }

        i--;

        return weekStartsAL.get(i - offset);
    }


    /**
     * @param weekStarts
     * @param currentDate
     * @return
     */
    private int getClosestMonday(ArrayList<ShiftModel> weekStarts, Date currentDate) {

        int counter = 0;
        int pos = 0;

        while (weekStarts.get(pos).getDateObject().before(currentDate)) {
            counter++;
            pos++;

        }
        return counter-1;
    }

    /**
     * @return
     */
    public ArrayList<ShiftModel> loadWeekStarts() {

        ArrayList<ShiftModel> weekStarts = new ArrayList<>();

        ShiftModel startDate = new ShiftModel("27-Mar-2017 16:00:00", "27-Mar-2017 20:00:00");
        ShiftModel endDate = new ShiftModel("27-Mar-2020 16:00:00", "27-Mar-2020 20:00:00");

        Calendar calendarStart = Calendar.getInstance();
        Calendar calendarEnd = Calendar.getInstance();

        calendarStart.setTime(startDate.getDateObject());
        calendarEnd.setTime(endDate.getDateObject());


        //  ShiftModel testShift1 = new ShiftModel("27-Mar-2019 16:00:00","27-Mar-2019 20:00:00");
        while (!calendarStart.equals(calendarEnd)) {
            if (calendarStart.getTime().getDay() == 0) {
                String[] fixed = convertDate(calendarStart.getTime().toString());
                weekStarts.add(new ShiftModel(fixed[0],fixed[1]));
            }
            calendarStart.add(Calendar.DATE, 1);

        }
        return weekStarts;
    }

    private String[] convertDate(String inputDate){
        //Sun Feb 16 16:00:00 GMT 2020
        //27-Mar-2019 16:00:00
        String[] dateFormatted = new String[2];

        String[] inputComponents = inputDate.split(" ");

            dateFormatted[0] = inputComponents[2]+"-"+inputComponents[1]+"-"+inputComponents[5]+" " + inputComponents[3];
            dateFormatted[1]= inputComponents[2]+"-"+inputComponents[1]+"-"+inputComponents[5]+" " + inputComponents[3];

        return dateFormatted;

    }

    /**
     * Load all previous shift information from the file
     *
     * @return an ArrayList containing all previous shift data
     */
    private ArrayList<ShiftModel> loadExistingShifts() {

        ArrayList<ShiftModel> temp = new ArrayList<>();
        String tempStart;
        String tempEnd;

        File f = new File(SHIFT_FILE);
        if (f.length() != 0) {

            try {
                FileReader fileReader = new FileReader(SHIFT_FILE);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String line = null;

                while ((line = bufferedReader.readLine()) != null) {
                    String[] tokens = line.split(" ");

                    tempStart = tokens[0] + " " + tokens[1];
                    tempEnd = tokens[2] + " " + tokens[3];


                    temp.add(new ShiftModel(tempStart, tempEnd));


                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return temp;
    }


    /**
     * Write new shift data to the file
     * @param shift
     */
    public void writeShiftToFile(ShiftModel shift) {

        try {
            FileWriter fr = new FileWriter(SHIFT_FILE, true);
            BufferedWriter bw = new BufferedWriter(fr);
            PrintWriter pr = new PrintWriter(bw);
            pr.println(shift.getStartFormat() + " " + shift.getFinishFormat());
            pr.close();
            bw.close();
            fr.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Error");
        } catch (IOException ex) {
            System.out.println("Error");
        }
    }


    /**
     * Method which will take a string and format it into a desirable way for the GUI
     * This is really shit
     *
     * @param offset
     * @return goodFormat for the GUI
     */
    public String dateFormatedForGUI(int offset) {
        ArrayList<Date> weekStarts = new ArrayList<>();

        ShiftModel startDate = new ShiftModel("27-Mar-2017 16:00:00", "27-Mar-2017 20:00:00");
        ShiftModel endDate = new ShiftModel("27-Mar-2020 16:00:00", "27-Mar-2020 20:00:00");

        Calendar calendarStart = Calendar.getInstance();
        Calendar calendarEnd = Calendar.getInstance();

        calendarStart.setTime(startDate.getDateObject());
        calendarEnd.setTime(endDate.getDateObject());

        while (!calendarStart.equals(calendarEnd)) {
            if (calendarStart.getTime().getDay() == 1) {
                weekStarts.add(calendarStart.getTime());
            }
            calendarStart.add(Calendar.DATE, 1);

        }

        Date current = new Date();

        int i = 0;
        while (current.after(weekStarts.get(i))) {
            i++;
        }

        i--;

        i = i - offset;

        String year = "" + weekStarts.get(i).getYear();
        year = year.substring(1, year.length());

        String goodFormat = "" + weekStarts.get(i).getDate() + "/" + (weekStarts.get(i).getMonth() + 1) + "/" + year;

        return goodFormat;

    }


    /**
     * Getter method for shift file
     * @return SHIFT_FILE
     */
    public String getShiftFile() {
        return SHIFT_FILE;
    }


    public String calculateExpectedPay(String startShift,String endShift){

        ShiftModel startDate = new ShiftModel("08-Apr-2019 16:00:00", "08-Apr-2019 20:00:00");
        ShiftModel endDate = new ShiftModel("05-May-2019 16:00:00", "05-May-2019 20:00:00");

        Calendar calendarStart = Calendar.getInstance();
        Calendar calendarEnd = Calendar.getInstance();

        calendarStart.setTime(startDate.getDateObject());
        calendarEnd.setTime(endDate.getDateObject());


        int i = 0;
        Integer total = 0;
        int numberOfShifts=0;

        while(allShifts.get(i).getDateObject().before(calendarStart.getTime())){
            i++;
        }
        i--;


        while (!calendarStart.equals(allShifts.get(allShifts.size()-1).getDateObject())) {

            if (i == allShifts.size()) {
                break;
            }
            if (calendarStart.getTime().before(allShifts.get(i).getDateObject())) {
                total += allShifts.get(i).getDuration();
                numberOfShifts++;
            }
            calendarStart.add(Calendar.DATE, 1);
            i++;

        }


        Double test = getMonthlyAmountEarned(numberOfShifts,total);

        return test.toString();


    }


    public String calculateExpectedHours(String s, String s1) {

        ShiftModel startDate = new ShiftModel("08-Apr-2019 16:00:00", "08-Apr-2019 20:00:00");
        ShiftModel endDate = new ShiftModel("05-May-2019 16:00:00", "05-May-2019 20:00:00");

        Calendar calendarStart = Calendar.getInstance();
        Calendar calendarEnd = Calendar.getInstance();

        calendarStart.setTime(startDate.getDateObject());
        calendarEnd.setTime(endDate.getDateObject());


        int i = 0;
        Integer total = 0;
        int numberOfShifts=0;

        while(allShifts.get(i).getDateObject().before(calendarStart.getTime())){
            i++;
        }
        i--;


        while (!calendarStart.equals(allShifts.get(allShifts.size()-1).getDateObject())) {

            if (i == allShifts.size()) {
                break;
            }
            if (calendarStart.getTime().before(allShifts.get(i).getDateObject())) {
                total += allShifts.get(i).getDuration();
                numberOfShifts++;
            }
            calendarStart.add(Calendar.DATE, 1);
            i++;
        }
        return total.toString();


    }



}
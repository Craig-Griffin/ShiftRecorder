import java.io.*;
import java.util.ArrayList;

public class ShiftsModel {

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

        try{
            if(!shiftFile.exists()) {
                shiftFile.createNewFile();
             }
        }catch(IOException ex){
            System.out.println("Error unable to write file");
        }

    }


    /**
     * Add a shift to the ArrayList containing all shifts
     * It will ensure that a shift can only be added to this ArrayList once.
     * @param shift
     */
    public void addShift(ShiftModel shift){

        boolean exists = false;

        for(ShiftModel x : allShifts){
            if(x.getStartFormat().equals(shift.getStartFormat())) {
                exists = true;
            }
        }

        if(!exists){
            allShifts.add(shift);
            writeShiftToFile(shift);
        }

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

    /**
     * Load all previous shift information from the file
     * @return an ArrayList containing all previous shift data
     */
    private ArrayList<ShiftModel> loadExistingShifts() {

            ArrayList < ShiftModel > temp = new ArrayList < > ();

            try {
                FileReader fileReader = new FileReader(SHIFT_FILE);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String line = null;

                while ((line = bufferedReader.readLine()) != null) {
                    String[] tokens = line.split(" ");


                    System.out.println(tokens[0]+ "  "+ tokens[1]);
                   temp.add(new ShiftModel((tokens[0]+" "+tokens[1]), (tokens[2]+ " "+ tokens[3])));


                }
            } catch (IOException e) {
                e.printStackTrace();
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
            pr.println(shift.getStartFormat()+ " " + shift.getFinishFormat());
            pr.close();
            bw.close();
            fr.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Error");
        } catch (IOException ex) {
            System.out.println("Error");
        }
    }


    }




































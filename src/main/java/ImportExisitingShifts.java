import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ImportExisitingShifts {
    private static final String EXISTING_SHIFTS_FILE = "MorrisonsShifts.txt";

    private ArrayList<String> initialDateStore;
    private ArrayList<String> initialShiftTimeStore;

    private ArrayList<String> correctedDateFormat;
    private ArrayList<String[]> correcteTimesFormat;

    private ArrayList<String[]> finalCorrectFormat;



    private ShiftsModel shiftsModel;

    public ImportExisitingShifts(){

        shiftsModel = new ShiftsModel();
        initialDateStore = new ArrayList<>();
        initialShiftTimeStore =  new ArrayList<>();

        correctedDateFormat =  new ArrayList<>();
        correcteTimesFormat = new ArrayList<>();

        finalCorrectFormat = new ArrayList<>();


        loadFile();

        System.out.println(initialShiftTimeStore);


        setCorrectedDateFormat();
        setCorrecteTimesFormat();
        makeCompleteDate();
        makeShiftModelObjects();


    }

    /* Example Format....
    07/10/17
    12pm – 4pm (4 Hours)
    Yes
    08/10/17
    10am – 2pm (4 Hours)
    Yes
    11/10/17
    4pm – 8pm (4 Hours)
    Yes
    14/10/17
    10am – 4pm (6 Hours)
    Yes
     */
    public void loadFile(){
            try {
                FileReader fileReader = new FileReader(EXISTING_SHIFTS_FILE);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String line = null;

                int position =1;

                while ((line = bufferedReader.readLine()) != null) {

                    if(position % 3 == 1){
                        initialDateStore.add(line);
                        position++;

                    }else if((position % 3) == 2){
                        initialShiftTimeStore.add(line);
                        position++;
                    }
                    else{
                        position++;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /*
        **Target Format**
        08-Apr-2019
        */

        public void setCorrectedDateFormat(){
        String day = "";
        String month = "";
        String year = "";

        for(String date : initialDateStore){

            String tokens[] = date.split("/");

            day = tokens[0];
            year = 20+ tokens[2];

            switch(tokens[1]){
                case "01": month = "Jan";
                    break;
                case "02": month = "Feb";
                    break;
                case "03": month = "Mar";
                    break;
                case "04": month = "Apr";
                    break;
                case "05": month = "May";
                    break;
                case "06": month = "Jun";
                    break;
                case "07": month = "Jul";
                    break;
                case "08": month = "Aug";
                    break;
                case "09": month = "Sep";
                    break;
                case "10": month = "Oct";
                    break;
                case "11": month = "Nov";
                    break;
                case "12": month = "Dec";
                    break;

            }

            correctedDateFormat.add(day +"-" + month + "-" + year );

        }

        }

        public void setCorrecteTimesFormat(){

            String startTime="";
            String finishTime="";



            for(String time : initialShiftTimeStore){


                //First remove the "-"
                String tokens[];
                if(time.contains("�")){
                     tokens = time.split("�");
                     //tokens[0] = tokens[0].substring(0, tokens[0].length() - 1);

                } else{
                    tokens = time.split("-");

                }
                if(tokens[0].contains(" ")){
                    tokens[0]=tokens[0].replaceAll(" ", "");
                }
                if(tokens[1].contains(" ")){
                    tokens[1]=tokens[1].replaceAll(" ", "");
                }

                String furtherSplit[] = tokens[1].split("\\(");

                startTime = convertTo24Hour(tokens[0]);
                finishTime = convertTo24Hour(furtherSplit[0]);

                String[]  startAndFinish = new String[2] ;
                startAndFinish[0] = startTime;
                startAndFinish[1] = finishTime;

                correcteTimesFormat.add(startAndFinish);

            }

        }


    private String convertTo24Hour(String input){
            String output="";
            switch(input){
                case "1am": output = "01:00:00";
                    break;
                case "2am": output = "02:00:00";
                    break;
                case "3am": output = "03:00:00";
                    break;
                case "4am": output = "04:00:00";
                    break;
                case "5am": output = "05:00:00";
                    break;
                case "6am": output = "06:00:00";
                    break;
                case "7am": output = "07:00:00";
                    break;
                case "8am": output = "08:00:00";
                    break;
                case "9am": output = "09:00:00";
                    break;
                case "10am": output = "10:00:00";
                    break;
                case "11am": output = "11:00:00";
                    break;
                case "12pm": output = "12:00:00";
                    break;
                case "1pm": output = "13:00:00";
                    break;
                case "2pm": output = "14:00:00";
                    break;
                case "3pm": output = "15:00:00";
                    break;
                case "4pm": output = "16:00:00";
                    break;
                case "5pm": output = "17:00:00";
                    break;
                case "6pm": output = "18:00:00";
                    break;
                case "7pm": output = "19:00:00";
                    break;
                case "8pm": output = "20:00:00";
                    break;
                case "9pm": output = "21:00:00";
                    break;
                case "10pm": output = "22:00:00";
                    break;
                case "11pm": output = "23:00:00";
                    break;
                case "12am": output = "00:00:00";
                    break;

            }

            return output;
    }

    /*08-Apr-2019 14:00:00 08-Apr-2019 20:00:00*/
    public void makeCompleteDate(){




           for(int i=0; i<correctedDateFormat.size(); i++){
               String[] correct = new String[2];
               correct[0] = correctedDateFormat.get(i) + " " + correcteTimesFormat.get(i)[0];
               correct[1] = correctedDateFormat.get(i) + " " + correcteTimesFormat.get(i)[1];




               finalCorrectFormat.add(correct);
        }


    }

    public void makeShiftModelObjects(){
        for(String[] x: finalCorrectFormat){
            System.out.println(x[0] + " " + x[1]);
            shiftsModel.addShift(new ShiftModel(x[0],x[1]));

        }
    }


}

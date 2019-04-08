import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    public static void main(String args[]) {


        /*GUIUserController test = new GUIUserController();
       test.main(args);
       */

       ShiftsModel x = new ShiftsModel();






     System.out.println(x.splitIntoWeeks().get(x.getCurrentWeekMonday()));


    }
}

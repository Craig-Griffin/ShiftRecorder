import javafx.scene.text.Text;

public class GuiHelpers {

    private boolean dateToggle;
    private Text mon;
    private Text tue;
    private Text wed;
    private  Text thu;
    private Text fri;
    private  Text sat;
    private  Text sun;
    private ShiftsModel shiftsModel;
    private ShiftModel weekStart;




    public GuiHelpers(){

    }


    public void setValidWeekText(boolean dateToggle, Text mon, Text tue, Text wed, Text thu, Text fri, Text sat, Text sun, ShiftsModel shiftsModel, ShiftModel weekStart) {

        if (dateToggle) {
            mon.setText("Monday " + (weekStart.getDateImproved(weekStart.getDateObject().getDate() + 1)) + addDateEnd((weekStart.getDateImproved(weekStart.getDateObject().getDate() + 1))) + ": " + shiftsModel.splitIntoWeeks().get(weekStart.getDateObject()).get(0));
            tue.setText("Tuesday " + (weekStart.getDateImproved(weekStart.getDateObject().getDate() + 2)) + addDateEnd((weekStart.getDateImproved(weekStart.getDateObject().getDate() + 2))) + ": " + shiftsModel.splitIntoWeeks().get(weekStart.getDateObject()).get(1));
            wed.setText("Wednesday " + (weekStart.getDateImproved(weekStart.getDateObject().getDate() + 3)) + addDateEnd((weekStart.getDateImproved(weekStart.getDateObject().getDate() + 3))) + ": " + shiftsModel.splitIntoWeeks().get(weekStart.getDateObject()).get(2));
            thu.setText("Thursday " + (weekStart.getDateImproved(weekStart.getDateObject().getDate() + 4)) + addDateEnd((weekStart.getDateImproved(weekStart.getDateObject().getDate() + 4))) + ": " + shiftsModel.splitIntoWeeks().get(weekStart.getDateObject()).get(3));
            fri.setText("Friday " + (weekStart.getDateImproved(weekStart.getDateObject().getDate()+ 5)) + addDateEnd((weekStart.getDateImproved(weekStart.getDateObject().getDate() + 5))) + ": " + shiftsModel.splitIntoWeeks().get(weekStart.getDateObject()).get(4));
            sat.setText("Saturday " + (weekStart.getDateImproved(weekStart.getDateObject().getDate() + 6)) + addDateEnd((weekStart.getDateImproved(weekStart.getDateObject().getDate() + 6))) + ": " + shiftsModel.splitIntoWeeks().get(weekStart.getDateObject()).get(5));
            sun.setText("Sunday " + (weekStart.getDateImproved(weekStart.getDateObject().getDate() + 7)) + addDateEnd((weekStart.getDateImproved(weekStart.getDateObject().getDate() + 7))) + ": " + shiftsModel.splitIntoWeeks().get(weekStart.getDateObject()).get(6));


        } else {
            mon.setText("Monday:    " + shiftsModel.splitIntoWeeks().get(weekStart.getDateObject()).get(0));
            tue.setText("Tuesday:   " + shiftsModel.splitIntoWeeks().get(weekStart.getDateObject()).get(1));
            wed.setText("Wednesday: " + shiftsModel.splitIntoWeeks().get(weekStart.getDateObject()).get(2));
            thu.setText("Thursday:  " + shiftsModel.splitIntoWeeks().get(weekStart.getDateObject()).get(3));
            fri.setText("Friday:    " + shiftsModel.splitIntoWeeks().get(weekStart.getDateObject()).get(4));
            sat.setText("Saturday:  " + shiftsModel.splitIntoWeeks().get(weekStart.getDateObject()).get(5));
            sun.setText("Sunday:    " + shiftsModel.splitIntoWeeks().get(weekStart.getDateObject()).get(6));
        }

    }


    public void setOFFweekText(boolean dateToggle, Text mon, Text tue, Text wed, Text thu, Text fri, Text sat, Text sun, ShiftsModel shiftsModel, ShiftModel weekStart) {

        if (dateToggle) {
            mon.setText("Monday " + weekStart.getDateImproved(weekStart.getDateObject().getDate() + 1) + addDateEnd((weekStart.getDateImproved(weekStart.getDateObject().getDate() + 1))) + ": OFF ");
            tue.setText("Tuesday " + weekStart.getDateImproved(weekStart.getDateObject().getDate() + 2) + addDateEnd((weekStart.getDateImproved(weekStart.getDateObject().getDate() + 2))) + ": OFF  ");
            wed.setText("Wednesday " + weekStart.getDateImproved(weekStart.getDateObject().getDate() + 3) + addDateEnd((weekStart.getDateImproved(weekStart.getDateObject().getDate() + 3))) + ": OFF ");
            thu.setText("Thursday " + weekStart.getDateImproved(weekStart.getDateObject().getDate() + 4) + addDateEnd((weekStart.getDateImproved(weekStart.getDateObject().getDate() + 4))) + ": OFF ");
            fri.setText("Friday " + weekStart.getDateImproved(weekStart.getDateObject().getDate() + 5) + addDateEnd((weekStart.getDateImproved(weekStart.getDateObject().getDate() + 5))) + ": OFF ");
            sat.setText("Saturday " + weekStart.getDateImproved(weekStart.getDateObject().getDate() + 6) + addDateEnd((weekStart.getDateImproved(weekStart.getDateObject().getDate() + 6))) + ": OFF ");
            sun.setText("Sunday " + weekStart.getDateImproved(weekStart.getDateObject().getDate() + 7) + addDateEnd((weekStart.getDateImproved(weekStart.getDateObject().getDate() + 7))) + ": OFF ");
        } else {
            mon.setText("Monday: OFF");
            tue.setText("Tuesday: OFF");
            wed.setText("Wednesday: OFF");
            thu.setText("Thursday: OFF");
            fri.setText("Friday: OFF");
            sat.setText("Saturday: OFF");
            sun.setText("Sunday: OFF");
        }

    }


    public String addDateEnd(int day) {

        if (day == 1) {
            return "st";
        }
        if (day == 2) {
            return "nd";
        }
        if (day == 3) {
            return "rd";
        }

        return "th";


    }


}

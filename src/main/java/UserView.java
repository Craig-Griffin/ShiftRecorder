
public class UserView {

    private ShiftsModel shiftsModel;

    public UserView(ShiftsModel shiftsModel){
        this.shiftsModel = shiftsModel;
    }


    public void displayAllShifts(){

        for(ShiftModel x : shiftsModel.getAllShifts()){
            System.out.println(x.getDate());
        }

    }

    public void displayTotalHoursWorked(){
        System.out.println("Total Number of hours worked: "+shiftsModel.getTotalTimeWorked());
    }

    public void displayTotalAmountEarned(){
        System.out.println("Total Amount Earned: " +shiftsModel.getTotalAmountEarned());
    }




}

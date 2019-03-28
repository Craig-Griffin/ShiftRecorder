
public class UserView {

    private ShiftsModel shiftsModel;

    public UserView(ShiftsModel shiftsModel){
        this.shiftsModel = shiftsModel;
    }


    public void displayAllShifts(){
        System.out.println();
        for(ShiftModel x : shiftsModel.getAllShifts()){
            System.out.println(x.getDate());
        }
        System.out.println();

    }

    public void displayTotalHoursWorked(){
        System.out.println("\nTotal Number of hours worked: "+shiftsModel.getTotalTimeWorked()+"\n");
    }

    public void displayTotalAmountEarned(){
        System.out.println("\nTotal Amount Earned: " +shiftsModel.getTotalAmountEarned()+"\n");
    }

    public void displayUserOptions(){
        System.out.println("**Please select ONE of the following options**\n"+
                "View All Shifts     <1>\n"+
                "View Money Earned   <2>\n"+
                "View Total Time     <3>\n"+
                "Add a Shift         <4>\n"+
                "Remove a shift      <5>\n"+
                "Exit Application    <6>\n"+
                "=>");
    }




}

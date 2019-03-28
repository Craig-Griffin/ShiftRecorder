import java.util.Scanner;

public class UserController {

    private ShiftsModel allShifts;
    private UserView view;

    public UserController(){
        allShifts = new ShiftsModel();
        view = new UserView(allShifts);

        mainLoop();
    }



    public void mainLoop(){

        boolean done = false;
        String userChoice;

        while(!done){
            view.displayUserOptions();
            userChoice = promptUser();

            switch(userChoice){
                case "1":
                    view.displayAllShifts();
                    break;
                case "2":
                    view.displayTotalAmountEarned();
                    break;
                case "3":
                    view.displayTotalHoursWorked();
                    break;
                 case "4":

                     System.out.println("Enter a date [DD-MMM-YYYY]");
                     String day = promptUser();
                     System.out.println("Enter a START time in 24 hour notation");
                     String startTime = promptUser();
                     System.out.println("Enter a FINISH time in 24 hour notation");
                     String finishTime = promptUser();

                     System.out.println((day +" " + startTime+":00:00" ) + " " + (day +" " + finishTime+":00:00" ));

                     allShifts.addShift(new ShiftModel((day +" " + startTime+":00:00" ), (day +" " + finishTime+":00:00" )));



                default:
                    System.out.println("Not a valid input!!");
            }





        }


    }

    public String promptUser() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public void addSomeDummyShifts(){
        allShifts.addShift(new ShiftModel("27-Mar-2019 16:00:00","27-Mar-2019 20:00:00"));
        allShifts.addShift(new ShiftModel("27-Mar-2019 12:00:00","27-Mar-2019 20:00:00"));
        allShifts.addShift(new ShiftModel("27-Mar-2019 13:00:00","27-Mar-2019 20:00:00"));
        allShifts.addShift(new ShiftModel("23-Mar-2019 13:00:00","23-Mar-2019 20:00:00"));
    }











































}

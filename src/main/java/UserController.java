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

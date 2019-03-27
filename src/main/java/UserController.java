


public class UserController {

    private ShiftsModel allShifts;
    private UserView view;

    public UserController(){
        allShifts = new ShiftsModel();
        view = new UserView(allShifts);

        addSomeDummyShifts();
        view.displayAllShifts();
        view.displayTotalHoursWorked();
        view.displayTotalAmountEarned();
    }

    public void addSomeDummyShifts(){
        allShifts.addShift(new ShiftModel("27-Mar-2019 16:00:00","27-Mar-2019 20:00:00"));
        allShifts.addShift(new ShiftModel("27-Mar-2019 12:00:00","27-Mar-2019 20:00:00"));
        allShifts.addShift(new ShiftModel("27-Mar-2019 13:00:00","27-Mar-2019 20:00:00"));
    }











































}

public class Main {

    public static void main(String args[]) {

        ShiftModel shift = new ShiftModel("27-Mar-2019 19:00:00", "27-Mar-2019 21:00:00");
        ShiftsModel allShifts = new ShiftsModel();


        allShifts.addShift(shift);
        System.out.println(allShifts.getAllShifts().get(0).getDate());


    }
}

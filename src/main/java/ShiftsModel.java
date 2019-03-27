import java.util.ArrayList;

public class ShiftsModel {

    private ArrayList<ShiftModel> allShifts;

    public ShiftsModel(){
        allShifts = new ArrayList<ShiftModel>();
    }

    public void addShift(ShiftModel shift){
        allShifts.add(shift);
    }

    public ArrayList<ShiftModel> getAllShifts(){
        return allShifts;
    }
































}

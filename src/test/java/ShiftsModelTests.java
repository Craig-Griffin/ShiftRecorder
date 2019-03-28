import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class ShiftsModelTests {


    /*Maybe a redundant test because it is testing to sure java is working (Which im sure it is...)*/
    @Test
    public void makeSureShiftsAreBeingAddedToArrayList(){
        ShiftModel testShift1 = new ShiftModel("27-Mar-2019 16:00:00","27-Mar-2019 20:00:00");
        ShiftModel testShift2 = new ShiftModel("27-Mar-2019 16:00:00","27-Mar-2019 20:00:00");

        ShiftsModel testShiftsModel1 = new ShiftsModel();

        testShiftsModel1.addShift(testShift1);
        testShiftsModel1.addShift(testShift2);

        Assertions.assertEquals(testShiftsModel1.getAllShifts().size(), 2);

    }

    /*Test to make sure that all that shift lengths are being added up correctly.*/
    @Test
    public void makeSureThatTotalTimeIsBeingCalculatedCorrectly(){
        ShiftModel testShift1 = new ShiftModel("27-Mar-2019 16:00:00","27-Mar-2019 20:00:00");
        ShiftModel testShift2 = new ShiftModel("27-Mar-2019 16:00:00","27-Mar-2019 20:00:00");

        ShiftsModel testShiftsModel2 = new ShiftsModel();

        testShiftsModel2.addShift(testShift1);
        testShiftsModel2.addShift(testShift2);

        ShiftModel testShift3 = new ShiftModel("27-Mar-2019 16:00:00","27-Mar-2019 20:00:00");
        Assertions.assertEquals(testShiftsModel2.getTotalTimeWorked(),8);

        testShiftsModel2.addShift(testShift3);
        Assertions.assertEquals(testShiftsModel2.getTotalTimeWorked(),12);

    }

    @Test
    public void makeSureAFIleIsBeingCreated(){

    }

    @Test void makeSureThatAShiftCanNotBeDuplicatedInTheFile(){

    }
}

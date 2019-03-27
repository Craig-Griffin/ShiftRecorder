
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class ShiftModelTests {


    /*A test to make sure that a time can be extracted from a date object*/
    @Test
    public void makeSureAShiftCanBeAdded(){
        ShiftModel testShift1 = new ShiftModel("27-Mar-2019 16:00:00","27-Mar-2019 20:00:00");
        Assertions.assertEquals(testShift1.getStartTime(),16);
        Assertions.assertEquals(testShift1.getFinishTime(), 20);

    }


    /*A Test to make sure that the correct duration is being returned from a shift object*/
    @Test
    public void makeSureDurationIsBeingCalculatedCorrectly(){
        ShiftModel testShift2 = new ShiftModel("27-Mar-2019 16:00:00","27-Mar-2019 20:00:00");
        Assertions.assertEquals(testShift2.getDuration(),4);
    }


}

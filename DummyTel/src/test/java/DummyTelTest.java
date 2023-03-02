import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.DateTimeException;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class DummyTelTest {
    private DummyTel dummyTel = new DummyTel();

    @Test
    @DisplayName("Test get phone charges total for standard cost and duration less than an hour")
    public void testGetPhoneChargesTotal_Standard_LessThanAnHour() {

        LocalTime startTime = LocalTime.of(10, 0);
        int minutes = 30;
        double expectedTotal = 15.8;
        double actualTotal = dummyTel.getPhoneChargesTotal(startTime, minutes);
        assertEquals(expectedTotal, actualTotal);
    }

    @Test
    @DisplayName("Test get phone charges total for discount cost and duration less than an hour")
    public void testGetPhoneChargesTotal_Discount_LTHours() {

        LocalTime startTime = LocalTime.of(18, 0);
        int minutes = 30;
        double expectedTotal = 7.9;
        double actualTotal = dummyTel.getPhoneChargesTotal(startTime, minutes);
        assertEquals(expectedTotal, actualTotal);
    }

    @Test
    @DisplayName("Test get phone charges total for standard cost and duration greater than an hour")
    public void testGetPhoneChargesTotal_Standard_GTHours() {

        LocalTime startTime = LocalTime.of(10, 30);
        int minutes = 90;
        double expectedTotal = 40.2;
        double actualTotal = dummyTel.getPhoneChargesTotal(startTime, minutes);
        assertEquals(expectedTotal, actualTotal);
    }

    @Test
    @DisplayName("Test get phone charges total for discount cost and duration greater than an hour")
    public void testGetPhoneChargesTotal_Discount_GTHours() {

        LocalTime startTime = LocalTime.of(18, 0);
        int minutes = 90;
        double expectedTotal = 20.1;
        double actualTotal = dummyTel.getPhoneChargesTotal(startTime, minutes);
        assertEquals(expectedTotal, actualTotal);
    }


    @Test
    @DisplayName("Test get phone charges total for standard cost to discount cost and duration greater than an hour")
    public void testGetPhoneChargesTotal_StandardToDiscount_GTHours() {

        LocalTime startTime = LocalTime.of(17, 30);
        int minutes = 90;
        double expectedTotal = 26.8;
        double actualTotal = dummyTel.getPhoneChargesTotal(startTime, minutes);
        assertEquals(expectedTotal, actualTotal);
    }

    @Test
    @DisplayName("Test get phone charges total for discount cost to standard cost and duration greater than an hour")
    public void testGetPhoneChargesTotal_DiscountToStandard_GTHours() {

        LocalTime startTime = LocalTime.of(7, 30);
        int minutes = 90;
        double expectedTotal = 33.5;
        double actualTotal = dummyTel.getPhoneChargesTotal(startTime, minutes);
        assertEquals(expectedTotal, actualTotal);
    }


    @Test
    @DisplayName("Test get phone charges total for standard cost to discount cost and duration less than an hour")
    public void testGetPhoneChargesTotal_StandardToDiscount_LTHours() {

        LocalTime startTime = LocalTime.of(17, 45);
        int minutes = 30;
        double expectedTotal = 11.8;
        double actualTotal = dummyTel.getPhoneChargesTotal(startTime, minutes);
        assertEquals(expectedTotal, actualTotal);


    }

    @Test
    @DisplayName("Test get phone charges total for discount cost to standard cost and duration less than an hour")
    public void testGetPhoneChargesTotal_DiscountToStandard_LTHours() {

        LocalTime startTime = LocalTime.of(7, 30);
        int minutes = 45;
        double expectedTotal = 15.8;
        double actualTotal = dummyTel.getPhoneChargesTotal(startTime, minutes);
        assertEquals(expectedTotal, actualTotal);
    }


    @Test
    @DisplayName("Test get phone charges total for  standard cost and duration greater than one day")
    public void testGetPhoneChargesTotal_Standard_GT1Day() {
        LocalTime startTime = LocalTime.of(8, 30);
        int minutes = 1485;
        double expectedTotal = 475.3;
        double actualTotal = dummyTel.getPhoneChargesTotal(startTime, minutes);
        assertEquals(expectedTotal, actualTotal);
    }

    @Test
    @DisplayName("Test get phone charges total for invalid minute parameter")
    public void testGetPhoneChargesTotal_ForInvalidMinute() {
        LocalTime startTime = LocalTime.of(8, 30);
        int minutes = -5;
        double expectedTotal = -1;
        double actualTotal = dummyTel.getPhoneChargesTotal(startTime, minutes);
        assertEquals(expectedTotal, actualTotal);
    }

    @Test
    @DisplayName("Strong boundary testing 1")
    public void testGetPhoneChargesTotal_StrongBoundaryTesting1() {
        Exception exception = assertThrows(DateTimeException.class, () -> {
            dummyTel.getPhoneChargesTotal(LocalTime.of(-1, 30), 5000);
        });

        String expectedMessage = "Invalid value for HourOfDay (valid values 0 - 23)";
        String actualMessage = exception.getMessage();


        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("Strong boundary testing 2")
    public void testGetPhoneChargesTotal_StrongBoundaryTesting2() {

        LocalTime startTime = LocalTime.of(0, 30);
        int minutes = 5000;
        double expectedTotal = 1568.6;
        double actualTotal = dummyTel.getPhoneChargesTotal(startTime, minutes);

        assertEquals(expectedTotal, actualTotal);

    }

    @Test
    @DisplayName("Strong boundary testing 3")
    public void testGetPhoneChargesTotal_StrongBoundaryTesting3() {

        LocalTime startTime = LocalTime.of(1, 30);
        int minutes = 5000;
        double expectedTotal = 1582.0;
        double actualTotal = dummyTel.getPhoneChargesTotal(startTime, minutes);

        assertEquals(expectedTotal, actualTotal);

    }

    @Test
    @DisplayName("Strong boundary testing 4")
    public void testGetPhoneChargesTotal_StrongBoundaryTesting4() {

        LocalTime startTime = LocalTime.of(7, 30);
        int minutes = 5000;
        double expectedTotal = 1517.3;
        double actualTotal = dummyTel.getPhoneChargesTotal(startTime, minutes);

        assertEquals(expectedTotal, actualTotal);

    }

    @Test
    @DisplayName("Strong boundary testing 5")
    public void testGetPhoneChargesTotal_StrongBoundaryTesting5() {

        LocalTime startTime = LocalTime.of(8, 30);
        int minutes = 5000;
        double expectedTotal = 1644.4;
        double actualTotal = dummyTel.getPhoneChargesTotal(startTime, minutes);

        assertEquals(expectedTotal, actualTotal);
    }

    @Test
    @DisplayName("Strong boundary testing 6")
    public void testGetPhoneChargesTotal_StrongBoundaryTesting6() {
        LocalTime startTime = LocalTime.of(9, 30);
        int minutes = 5000;
        double expectedTotal = 1631.0;
        double actualTotal = dummyTel.getPhoneChargesTotal(startTime, minutes);

        assertEquals(expectedTotal, actualTotal);

    }

    @Test
    @DisplayName("Strong boundary testing 7")
    public void testGetPhoneChargesTotal_StrongBoundaryTesting7() {
        LocalTime startTime = LocalTime.of(12, 30);
        int minutes = 5000;
        double expectedTotal = 1590.9;
        double actualTotal = dummyTel.getPhoneChargesTotal(startTime, minutes);

        assertEquals(expectedTotal, actualTotal);

    }

    @Test
    @DisplayName("Strong boundary testing 8")
    public void testGetPhoneChargesTotal_StrongBoundaryTesting8() {
        LocalTime startTime = LocalTime.of(17, 30);
        int minutes = 5000;
        double expectedTotal = 1523.9;
        double actualTotal = dummyTel.getPhoneChargesTotal(startTime, minutes);

        assertEquals(expectedTotal, actualTotal);

    }

    @Test
    @DisplayName("Strong boundary testing 9")
    public void testGetPhoneChargesTotal_StrongBoundaryTesting9() {
        LocalTime startTime = LocalTime.of(18, 30);
        int minutes = 5000;
        double expectedTotal = 1517.3;
        double actualTotal = dummyTel.getPhoneChargesTotal(startTime, minutes);

        assertEquals(expectedTotal, actualTotal);

    }

    @Test
    @DisplayName("Strong boundary testing 10")
    public void testGetPhoneChargesTotal_StrongBoundaryTesting10() {
        LocalTime startTime = LocalTime.of(19, 30);
        int minutes = 5000;
        double expectedTotal = 1517.3;
        double actualTotal = dummyTel.getPhoneChargesTotal(startTime, minutes);

        assertEquals(expectedTotal, actualTotal);

    }

    @Test
    @DisplayName("Strong boundary testing 11")
    public void testGetPhoneChargesTotal_StrongBoundaryTesting11() {
        LocalTime startTime = LocalTime.of(22, 30);
        int minutes = 5000;
        double expectedTotal = 1541.8;
        double actualTotal = dummyTel.getPhoneChargesTotal(startTime, minutes);

        assertEquals(expectedTotal, actualTotal);

    }

    @Test
    @DisplayName("Strong boundary testing 12")
    public void testGetPhoneChargesTotal_StrongBoundaryTesting12() {
        LocalTime startTime = LocalTime.of(23, 30);
        int minutes = 5000;
        double expectedTotal = 1555.2;
        double actualTotal = dummyTel.getPhoneChargesTotal(startTime, minutes);
        assertEquals(expectedTotal, actualTotal);
    }

    @Test
    @DisplayName("Strong boundary testing 13")
    public void testGetPhoneChargesTotal_StrongBoundaryTesting13() {
        Exception exception = assertThrows(DateTimeException.class, () -> {
            dummyTel.getPhoneChargesTotal(LocalTime.of(24, 30), 5000);
        });

        String expectedMessage = "Invalid value for HourOfDay (valid values 0 - 23)";
        String actualMessage = exception.getMessage();


        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("Strong boundary testing 14")
    public void testGetPhoneChargesTotal_StrongBoundaryTesting14() {
        Exception exception = assertThrows(DateTimeException.class, () -> {
            dummyTel.getPhoneChargesTotal(LocalTime.of(12, -1), 5000);
        });

        String expectedMessage = "Invalid value for MinuteOfHour (valid values 0 - 59)";
        String actualMessage = exception.getMessage();


        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("Strong boundary testing 15")
    public void testGetPhoneChargesTotal_StrongBoundaryTesting15() {
        LocalTime startTime = LocalTime.of(12, 0);
        int minutes = 5000;
        double expectedTotal = 1597.6;
        double actualTotal = dummyTel.getPhoneChargesTotal(startTime, minutes);
        assertEquals(expectedTotal, actualTotal);
    }

    @Test
    @DisplayName("Strong boundary testing 16")
    public void testGetPhoneChargesTotal_StrongBoundaryTesting16() {
        LocalTime startTime = LocalTime.of(12, 1);
        int minutes = 5000;
        double expectedTotal = 1597.4;
        double actualTotal = dummyTel.getPhoneChargesTotal(startTime, minutes);
        assertEquals(expectedTotal, actualTotal);
    }

    @Test
    @DisplayName("Strong boundary testing 17")
    public void testGetPhoneChargesTotal_StrongBoundaryTesting17() {
        LocalTime startTime = LocalTime.of(12, 59);
        int minutes = 5000;
        double expectedTotal = 1584.4;
        double actualTotal = dummyTel.getPhoneChargesTotal(startTime, minutes);
        assertEquals(expectedTotal, actualTotal);
    }

    @Test
    @DisplayName("Strong boundary testing 18")
    public void testGetPhoneChargesTotal_StrongBoundaryTesting18() {
        Exception exception = assertThrows(DateTimeException.class, () -> {
            dummyTel.getPhoneChargesTotal(LocalTime.of(12, 60), 5000);
        });

        String expectedMessage = "Invalid value for MinuteOfHour (valid values 0 - 59)";
        String actualMessage = exception.getMessage();


        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("Strong boundary testing 19")
    public void testGetPhoneChargesTotal_StrongBoundaryTesting19() {
        Exception exception = assertThrows(DateTimeException.class, () -> {
            dummyTel.getPhoneChargesTotal(LocalTime.of(12, 61), 5000);
        });

        String expectedMessage = "Invalid value for MinuteOfHour (valid values 0 - 59)";
        String actualMessage = exception.getMessage();


        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("Strong boundary testing 20")
    public void testGetPhoneChargesTotal_StrongBoundaryTesting20() {
        LocalTime startTime = LocalTime.of(12, 30);
        int minutes = -1;
        double expectedTotal = -1;
        double actualTotal = dummyTel.getPhoneChargesTotal(startTime, minutes);
        assertEquals(expectedTotal, actualTotal);
    }

    @Test
    @DisplayName("Strong boundary testing 21")
    public void testGetPhoneChargesTotal_StrongBoundaryTesting21() {
        LocalTime startTime = LocalTime.of(12, 30);
        int minutes = 0;
        double expectedTotal = 0;
        double actualTotal = dummyTel.getPhoneChargesTotal(startTime, minutes);
        assertEquals(expectedTotal, actualTotal);
    }

    @Test
    @DisplayName("Strong boundary testing 22")
    public void testGetPhoneChargesTotal_StrongBoundaryTesting22() {
        LocalTime startTime = LocalTime.of(12, 30);
        int minutes = 1;
        double expectedTotal = 0.5;
        double actualTotal = dummyTel.getPhoneChargesTotal(startTime, minutes);
        assertEquals(expectedTotal, actualTotal);
    }

    @Test
    @DisplayName("Strong boundary testing 23")
    public void testGetPhoneChargesTotal_StrongBoundaryTesting23() {
        LocalTime startTime = LocalTime.of(12, 30);
        int minutes = 9999;
        double expectedTotal = 3337.5;
        double actualTotal = dummyTel.getPhoneChargesTotal(startTime, minutes);
        assertEquals(expectedTotal, actualTotal);
    }

    @Test
    @DisplayName("Strong boundary testing 24")
    public void testGetPhoneChargesTotal_StrongBoundaryTesting24() {
        LocalTime startTime = LocalTime.of(12, 30);
        int minutes = 10000;
        double expectedTotal = 3337.9;
        double actualTotal = dummyTel.getPhoneChargesTotal(startTime, minutes);
        assertEquals(expectedTotal, actualTotal);
    }

    @Test
    @DisplayName("Strong boundary testing 25")
    public void testGetPhoneChargesTotal_StrongBoundaryTesting25() {
        LocalTime startTime = LocalTime.of(12, 30);
        int minutes = 10001;
        double expectedTotal = 3338.4;
        double actualTotal = dummyTel.getPhoneChargesTotal(startTime, minutes);
        assertEquals(expectedTotal, actualTotal);
    }
}
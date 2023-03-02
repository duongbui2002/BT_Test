import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

/**
 * A class representing a phone company that provides telephone services.
 */
public class DummyTel {
    /**
     * The base price per minute for phone calls.
     */
    public static final double PHONE_CHARGES = 0.5;

    /**
     * The discount price per minute for phone calls.
     */
    public static final double PHONE_CHARGES_DISCOUNT = 0.25;

    /**
     * The total price per day for phone calls.
     */
    public static final double TOTAL_PRICE_PER_DAY = TimeUnit.HOURS.toMinutes(10) * PHONE_CHARGES + TimeUnit.HOURS.toMinutes(14) * PHONE_CHARGES_DISCOUNT;

    /**
     * The total number of nanoseconds in a day.
     */
    public static final long TOTAL_NANO_SECONDS_EACH_DAY = TimeUnit.HOURS.toNanos(24);

    /**
     * The price per minute for phone calls greater than 60 minutes.
     */
    public static final double TELEPHONE_CHARGES_FOR_CALL_MORE_60_MINUTES = 0.85;

    /**
     * The time when the discounted price for phone calls begins.
     */
    public static final LocalTime SIX_PM = LocalTime.of(18, 0);

    /**
     * The time when the discounted price for phone calls ends.
     */
    public static final LocalTime EIGHT_AM = LocalTime.of(8, 0);

    /**
     * The time at midnight.
     */
    public static final LocalTime MIDNIGHT = LocalTime.of(0, 0);

    /**
     * Calculates the total charges for a phone call given its start time and duration.
     *
     * @param startTime the time at which the call started
     * @param minutes   the duration of the call in minutes
     * @return the total charges for the call, rounded to one decimal place
     */
    public double getPhoneChargesTotal(LocalTime startTime, int minutes) {
        if (minutes < 0) {
            return -1;
        }
        double result = 0;
        long callNanos = TimeUnit.MINUTES.toNanos(minutes);
        long totalDay = callNanos / TOTAL_NANO_SECONDS_EACH_DAY;
        long remainTime = callNanos - totalDay * TOTAL_NANO_SECONDS_EACH_DAY;
        long totalTimeDiscount = 0;
        long totalTimeNormal = 0;

        if (startTime.isAfter(SIX_PM) || startTime.equals(SIX_PM) || startTime.isBefore(EIGHT_AM)) {
            if (startTime.plusNanos(remainTime).isAfter(SIX_PM) || startTime.plusNanos(remainTime).isBefore(EIGHT_AM)) {
                totalTimeDiscount += remainTime;
            } else {
                if (startTime.isAfter(SIX_PM) || startTime.equals(SIX_PM)) {
                    totalTimeNormal = remainTime - (TimeUnit.HOURS.toNanos(6) - (startTime.toNanoOfDay() - SIX_PM.toNanoOfDay())) - TimeUnit.HOURS.toNanos(8);
                    totalTimeDiscount = remainTime - totalTimeNormal;
                } else if (startTime.isBefore(EIGHT_AM)) {
                    totalTimeNormal = remainTime - (EIGHT_AM.toNanoOfDay() - startTime.toNanoOfDay());
                    totalTimeDiscount = remainTime - totalTimeNormal;
                }
            }
        } else {
            if (startTime.plusNanos(remainTime).isAfter(EIGHT_AM) && startTime.plusNanos(remainTime).isBefore(SIX_PM)) {
                totalTimeNormal += remainTime;
            } else {
                totalTimeDiscount = remainTime - (SIX_PM.toNanoOfDay() - startTime.toNanoOfDay());
                totalTimeNormal = remainTime - totalTimeDiscount;
            }
        }

        double totalMinuteNormal = TimeUnit.NANOSECONDS.toMinutes(totalTimeNormal);
        double totalMinuteDiscount = TimeUnit.NANOSECONDS.toMinutes(totalTimeDiscount);
        if (totalDay == 0) {
            if (totalMinuteDiscount + totalMinuteNormal > 60) {
                double resultBeforeTax = (totalMinuteNormal * PHONE_CHARGES + totalMinuteDiscount * PHONE_CHARGES_DISCOUNT) * TELEPHONE_CHARGES_FOR_CALL_MORE_60_MINUTES;
                result = resultBeforeTax + resultBeforeTax * 0.05;
            } else {
                double resultBeforeTax = (totalMinuteNormal * PHONE_CHARGES + totalMinuteDiscount * PHONE_CHARGES_DISCOUNT);
                result = resultBeforeTax + resultBeforeTax * 0.05;
            }
        } else {
            double resultBeforeTax = (totalMinuteNormal * PHONE_CHARGES + totalMinuteDiscount * PHONE_CHARGES_DISCOUNT + totalDay * TOTAL_PRICE_PER_DAY) * TELEPHONE_CHARGES_FOR_CALL_MORE_60_MINUTES;
            result = resultBeforeTax + resultBeforeTax * 0.05;
        }


        return formatToOneDecimal(result);
    }

    /**
     * Formats a number to one decimal place.
     *
     * @param number the number to format
     * @return the formatted number
     */
    public static double formatToOneDecimal(double number) {
        DecimalFormat df = new DecimalFormat("#.#"); // specify the format pattern
        df.setRoundingMode(RoundingMode.HALF_UP); // specify the rounding mode
        String result = df.format(number); // format the number as a string
        return Double.parseDouble(result); // convert the str;
    }
}
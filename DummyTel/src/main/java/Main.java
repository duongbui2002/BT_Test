import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        DummyTel test = new DummyTel();
        System.out.println(TimeUnit.HOURS.toMinutes(10) * DummyTel.PHONE_CHARGES + TimeUnit.HOURS.toMinutes(14) * DummyTel.PHONE_CHARGES_DISCOUNT);
    }
}

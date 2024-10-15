package lk.ijse.gdse68.nike_spring.util;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateTimeUtil {
    public static LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now();
    }

    public static LocalDate getCurrentDate() {
        return LocalDate.now();
    }
}

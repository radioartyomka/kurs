package Application.util;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    private static String DATE_PATTERN = "dd.MM.yyyy";
    private static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);

    public static String format(LocalDate date) {
        if (date == null){
            return null;
        }
        return DATE_FORMATTER.format(date);
    }

    public static LocalDate parse(String dateString) {
        try{
            return DATE_FORMATTER.parse(dateString, LocalDate::from);
        }catch (DateTimeException e) {
            return null;
        }
    }

    /**
     * Проверяет, является ли строка корректной датой.
     *
     * @param dateString
     * @return true, если строка является корректной датой
     */
    public static boolean validDate(String dateString) {
        return DateUtil.parse(dateString) != null;
    }
}

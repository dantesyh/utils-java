package dantesyh.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 时间相关工具类
 *
 * @author dante
 * @since 2023/8/31
 */
public class TimeUtils {
    private TimeUtils() {
    }

    public static final String DATE_FORMAT = "yyyy/MM/dd";
    public static final String DATE_TIME_FORMAT = "yyyy/MM/dd HH:mm:ss";
    public static final String TIME_FORMAT = "HH:mm:ss";

    public static String convertDateTimeFormat(String inputTime, String inputFormat, String outputFormat) throws ParseException {
        SimpleDateFormat inputDateFormat = new SimpleDateFormat(inputFormat);
        Date date = inputDateFormat.parse(inputTime);

        SimpleDateFormat outputDateFormat = new SimpleDateFormat(outputFormat);
        return outputDateFormat.format(date);
    }

    public static String convertDateTimeFormat(Date inputTime, String outputFormat) {
        SimpleDateFormat outputDateFormat = new SimpleDateFormat(outputFormat);
        return outputDateFormat.format(inputTime);
    }

    public static String convertDateTimeFormat(LocalDateTime inputTime, String outputFormat) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(outputFormat);
        return inputTime.format(formatter);
    }

    public static String convertDateFormat(LocalDate inputTime, String outputFormat) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(outputFormat);
        return inputTime.format(formatter);
    }

    public static String convertTimeFormat(LocalTime inputTime, String outputFormat) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(outputFormat);
        return inputTime.format(formatter);
    }


    public static String convertDateTimeFormat(Date inputTime) {
        return convertDateTimeFormat(inputTime, DATE_TIME_FORMAT);
    }

    public static String convertDateTimeFormat(LocalDateTime inputTime) {
        return convertDateTimeFormat(inputTime, DATE_TIME_FORMAT);
    }

    public static String convertDateFormat(LocalDate inputTime) {
        return convertDateFormat(inputTime, DATE_FORMAT);
    }

    public static String convertTimeFormat(LocalTime inputTime) {
        return convertTimeFormat(inputTime, TIME_FORMAT);
    }

}

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

    /**
     * 日期格式字符串，格式为"年/月/日"，用于日期的格式化和解析
     */
    public static final String DATE_FORMAT = "yyyy/MM/dd";

    /**
     * 日期时间格式字符串，格式为"年/月/日 时:分:秒"，用于日期时间的格式化和解析
     */
    public static final String DATE_TIME_FORMAT = "yyyy/MM/dd HH:mm:ss";

    /**
     * 时间格式字符串，格式为"时:分:秒"，用于时间的格式化和解析
     */
    public static final String TIME_FORMAT = "HH:mm:ss";


    /**
     * 字符串时间格式生成
     *
     * @param inputTime    输入时间
     * @param inputFormat  输入时间格式
     * @param outputFormat 输出时间格式
     * @return 输出时间
     * @throws ParseException 格式不匹配
     */
    public static String convertDateTimeFormat(String inputTime, String inputFormat, String outputFormat) throws ParseException {
        SimpleDateFormat inputDateFormat = new SimpleDateFormat(inputFormat);
        Date date = inputDateFormat.parse(inputTime);

        SimpleDateFormat outputDateFormat = new SimpleDateFormat(outputFormat);
        return outputDateFormat.format(date);
    }

    /**
     * 将Date对象格式化为指定格式的时间字符串
     *
     * @param inputTime    输入Date对象
     * @param outputFormat 输出时间的格式
     * @return 格式化后的时间字符串
     */
    public static String convertDateTimeFormat(Date inputTime, String outputFormat) {
        SimpleDateFormat outputDateFormat = new SimpleDateFormat(outputFormat);
        return outputDateFormat.format(inputTime);
    }

    /**
     * 将Date对象格式化为默认日期时间格式的字符串
     *
     * @param inputTime 输入Date对象
     * @return 格式化后的日期时间字符串
     */
    public static String convertDateTimeFormat(Date inputTime) {
        return convertDateTimeFormat(inputTime, DATE_TIME_FORMAT);
    }

    /**
     * 将Date对象格式化为默认日期格式的字符串
     *
     * @param inputTime 输入Date对象
     * @return 格式化后的日期字符串
     */
    public static String convertDateFormat(Date inputTime) {
        return convertDateTimeFormat(inputTime, DATE_FORMAT);
    }

    /**
     * 将LocalDate对象格式化为指定格式的日期字符串
     *
     * @param inputTime    输入LocalDate对象
     * @param outputFormat 输出日期的格式
     * @return 格式化后的日期字符串
     */
    public static String convertDateFormat(LocalDate inputTime, String outputFormat) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(outputFormat);
        return inputTime.format(formatter);
    }

    /**
     * 将LocalDate对象格式化为默认日期格式的字符串
     *
     * @param inputTime 输入LocalDate对象
     * @return 格式化后的日期字符串
     */
    public static String convertDateFormat(LocalDate inputTime) {
        return convertDateFormat(inputTime, DATE_FORMAT);
    }


    /**
     * 将LocalTime对象格式化为指定格式的时间字符串
     *
     * @param inputTime    输入LocalTime对象
     * @param outputFormat 输出时间的格式
     * @return 格式化后的时间字符串
     */
    public static String convertTimeFormat(LocalTime inputTime, String outputFormat) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(outputFormat);
        return inputTime.format(formatter);
    }

    /**
     * 将LocalTime对象格式化为默认时间格式的字符串
     *
     * @param inputTime 输入LocalTime对象
     * @return 格式化后的时间字符串
     */
    public static String convertTimeFormat(LocalTime inputTime) {
        return convertTimeFormat(inputTime, TIME_FORMAT);
    }

    /**
     * 将LocalDateTime对象格式化为指定格式的时间字符串
     *
     * @param inputTime    输入LocalDateTime对象
     * @param outputFormat 输出时间的格式
     * @return 格式化后的时间字符串
     */
    public static String convertDateTimeFormat(LocalDateTime inputTime, String outputFormat) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(outputFormat);
        return inputTime.format(formatter);
    }

    /**
     * 将LocalDateTime对象格式化为默认日期时间格式的字符串
     *
     * @param inputTime 输入LocalDateTime对象
     * @return 格式化后的日期时间字符串
     */
    public static String convertDateTimeFormat(LocalDateTime inputTime) {
        return convertDateTimeFormat(inputTime, DATE_TIME_FORMAT);
    }

}

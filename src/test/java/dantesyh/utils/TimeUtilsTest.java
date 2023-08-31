package dantesyh.utils;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TimeUtilsTest {

    private static final Logger logger = LoggerFactory.getLogger(TimeUtils.class);

    @Test
    void testConvertDateTimeFormatString() throws ParseException {
        String inputTime = "2023-08-31 15:30:00";
        String inputFormat = "yyyy-MM-dd HH:mm:ss";
        String outputFormat = "yyyy年MM月dd日 HH时mm分ss秒";

        String expectedResult = "2023年08月31日 15时30分00秒";
        String actualResult = TimeUtils.convertDateTimeFormat(inputTime, inputFormat, outputFormat);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testConvertDateTimeFormatDate() {
        Date inputTime = new Date(); // 使用当前时间
        logger.debug(String.valueOf(inputTime));

        String expectedResult = TimeUtils.convertDateTimeFormat(inputTime, TimeUtils.DATE_TIME_FORMAT);
        logger.debug(expectedResult);

        String actualResult = TimeUtils.convertDateTimeFormat(inputTime);
        logger.debug(actualResult);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testConvertDateTimeFormatLocalDateTime() {
        LocalDateTime inputTime = LocalDateTime.now(); // 使用当前时间
        logger.debug(String.valueOf(inputTime));

        String expectedResult = TimeUtils.convertDateTimeFormat(inputTime, TimeUtils.DATE_TIME_FORMAT);
        logger.debug(expectedResult);

        String actualResult = TimeUtils.convertDateTimeFormat(inputTime);
        logger.debug(actualResult);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testConvertDateFormatLocalDate() {
        LocalDate inputTime = LocalDate.now(); // 使用当前日期
        logger.debug(String.valueOf(inputTime));

        String expectedResult = TimeUtils.convertDateFormat(inputTime, TimeUtils.DATE_FORMAT);
        logger.debug(expectedResult);

        String actualResult = TimeUtils.convertDateFormat(inputTime);
        logger.debug(actualResult);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testConvertTimeFormatLocalTime() {
        LocalTime inputTime = LocalTime.now(); // 使用当前时间
        logger.debug(String.valueOf(inputTime));

        String expectedResult = TimeUtils.convertTimeFormat(inputTime, TimeUtils.TIME_FORMAT);
        logger.debug(expectedResult);

        String actualResult = TimeUtils.convertTimeFormat(inputTime);
        logger.debug(actualResult);
        assertEquals(expectedResult, actualResult);
    }
}

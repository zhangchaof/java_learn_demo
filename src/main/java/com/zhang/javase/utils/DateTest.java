package com.zhang.javase.utils;

import java.time.*;
import java.util.Date;

/**
 * @author clark
 * @Description:
 * @date 2020/5/27 18:01
 */
public class DateTest {
    public static void main(String[] args) {

    }

    public static void dateToLocalDate() {
        Date date = new Date();
        //An instantaneous point on the time-line.(时间线上的一个瞬时点。)
        Instant instant = date.toInstant();
        //A time-zone ID, such as {@code Europe/Paris}.(时区)
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = instant.atZone(zoneId);
        LocalDateTime localDateTime = zonedDateTime.toLocalDateTime();
        LocalDate localDate = zonedDateTime.toLocalDate();
        System.out.println("LocalDateTime = " + localDateTime);
        System.out.println("localDate = " + localDate);
    }
}

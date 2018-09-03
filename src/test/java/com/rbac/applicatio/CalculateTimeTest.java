package com.rbac.applicatio;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * 艾宾浩斯记忆曲线制定 获取记忆周期时间
 * @auther ttm
 * @date 2018/9/3 0003
 **/
public class CalculateTimeTest {

    public List<Long> cycleTime = new ArrayList<>();

    private static final String FORMAT = "yyyy-MM-dd HH:mm:ss";

    @Before
    public void initCycle() {
        cycleTime.add(5L);
        cycleTime.add(30L);
        cycleTime.add(12L * 60);
        //一天
        cycleTime.add(1L * 24 * 60);
        //两天
        cycleTime.add(2L * 24 * 60);
        //四天
        cycleTime.add(4L * 24 * 60);
        //七天
        cycleTime.add(7L * 24 * 60);
        //十五天
        cycleTime.add(15L * 24 * 60);
    }

    /**
     * 根据一个时间计算
     * @param dateTime  YYYY-MM-DD hh:mm:ss
     */
    public void calculate(String dateTime) {
//        for (int x = 0; x < cycleTime.size(); x++) {
//            LocalDateTime localDateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(FORMAT));
//            Integer cycle = cycleTime.get(x);
//            localDateTime.plusMinutes(cycle);
//            System.out.println(localDateTime);
//        }

    }

    @Test
    public void calculateTest() {
        String currentDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern(FORMAT));
//        calculate(currentDateTime);
//        for (int x = 0; x < cycleTime.size(); x++) {
//            LocalDateTime localDateTime = LocalDateTime.parse(currentDateTime, DateTimeFormatter.ofPattern(FORMAT));
//            Long cycle = cycleTime.get(x);
//            localDateTime.plusMinutes(cycle);
//            System.out.println(localDateTime);
//        }
        LocalDateTime localDateTime = LocalDateTime.parse("2018-09-03 06:30:00", DateTimeFormatter.ofPattern(FORMAT));
        System.out.println("5分钟 " + localDateTime.plusMinutes(5).format(DateTimeFormatter.ofPattern(FORMAT)));
        System.out.println("30分钟 " + localDateTime.plusMinutes(30).format(DateTimeFormatter.ofPattern(FORMAT)));
        System.out.println("12小时 " + localDateTime.plusMinutes(12 * 60).format(DateTimeFormatter.ofPattern(FORMAT)));
        System.out.println("一天 " + localDateTime.plusMinutes(1 * 24 * 60).format(DateTimeFormatter.ofPattern(FORMAT)));
        System.out.println("二天 " + localDateTime.plusMinutes(2 * 24 * 60).format(DateTimeFormatter.ofPattern(FORMAT)));
        System.out.println("四天 " + localDateTime.plusMinutes(4 * 24 * 60).format(DateTimeFormatter.ofPattern(FORMAT)));
        System.out.println("七天 " + localDateTime.plusMinutes(7 * 24 * 60).format(DateTimeFormatter.ofPattern(FORMAT)));
        System.out.println("十五天 " + localDateTime.plusMinutes(15 * 24 * 60).format(DateTimeFormatter.ofPattern(FORMAT)));
    }




}

package com.bettem.modules.tourism.utils;

import org.apache.commons.lang.time.FastDateFormat;
import org.apache.commons.lang.time.StopWatch;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class OrderCodecUtil {

    private static long orderNum = 0l;
    private static String date ;

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10000; i++) {
            System.out.println(OrderCodecUtil.getOrderNo());
            Thread.sleep(1000);
        }
    }

    /**
     * 生成订单编号
     * @return
     */
    public static synchronized String getOrderNo() {
        String str = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
        if(date==null||!date.equals(str)){
            date = str;
            orderNum  = 0l;
        }
        orderNum ++;
        long orderNo = Long.parseLong((date)) * 10000;
        orderNo += orderNum;;
        return orderNo+"";
    }

}

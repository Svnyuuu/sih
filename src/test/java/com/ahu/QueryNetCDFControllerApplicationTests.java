package com.ahu;

import com.ahu.controller.QueryNetCDFController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
public class QueryNetCDFControllerApplicationTests {

    @Autowired
    QueryNetCDFController controller = new QueryNetCDFController();

    @Test
    public void runNetCDFController() throws Exception {
        System.out.println("----------开始查询-----------");
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        String s1="2023-3-01 00:00:00";
        String s2="2023-4-04 03:00:00";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date beginTime=dateFormat.parse(s1);
        Date endTime=dateFormat.parse(s2);

        controller.findNetCDF(beginTime,endTime,4.25f,1.25f,2);

        stopWatch.stop();
        System.out.println("---------查询共耗时："+stopWatch.getTotalTimeSeconds()+"秒");

    }


}

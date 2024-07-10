package com.ahu;

import com.ahu.controller.ParseNetCDFController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ucar.ma2.InvalidRangeException;

import java.io.IOException;

@SpringBootTest
class ParseNetCDFControllerApplicationTests {

    @Autowired
    private ParseNetCDFController parseNetCDFController;

    @Test
    void contextLoads() throws InvalidRangeException, IOException {
        String filePath = "F:/workspace-idea/spring-code/ParseNetCDF/2023年3-4月全球气象数据.nc";
        int days = 7;//表示存储前后3天的数据
        int first=1;//表示是第一次执行程序
        parseNetCDFController.parseNetCDF(filePath,days,first);
        System.out.println("结束");
    }

}

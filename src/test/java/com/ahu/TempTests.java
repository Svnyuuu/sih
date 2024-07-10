package com.ahu;

import com.ahu.utils.TransferUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
public class TempTests {

    @Test
    public void  Temp() throws Exception {
        //获取时间戳
        long l = System.currentTimeMillis();
        System.out.println("l="+l/(60*60*1000));
        //格式化
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String s = format.format(l);
        System.out.println(s);

        TransferUtil transferUtil=new TransferUtil();
        Date time= format.parse(s);
        long tt=transferUtil.DateToTimestamp(time);
        System.out.println("tt="+tt);




    }



}

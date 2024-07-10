package com.ahu.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TransferUtil {

    private long timestamp;//毫秒,1900-1970之间的时间戳，负数

    //计算1900到1970之间的时间戳，因为java是从1970开始计算
    public TransferUtil() throws ParseException {
        //本nc文件计算的时间是从hours 1900-1-1 00：00：00
        String dateString="1900-01-01 00:00:00";
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date= dateFormat.parse(dateString);
        // System.out.println("日期："+dateString);
        // System.out.println("时间戳："+timestamp);//1900到1970之间的时间戳，毫秒为单位,算出来是负数
        // System.out.println("时间戳(天):"+timestamp/(1000*60*60*24));
        this.timestamp=date.getTime();
    }

    //将时间戳（此处是小时为单位）转换成时间
    public Date TimestampToDate(long timestamp) throws Exception {
        long newTimestamp = timestamp*60*60*1000+this.timestamp;//时间戳，单位为毫秒
        Instant instant = Instant.ofEpochMilli(newTimestamp); // 将时间戳转换为Instant
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault()); //转换为本地时区的时间
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); //定义日期时间格式
        String formattedDateTime = zonedDateTime.format(formatter); //格式化日期时间
        System.out.println(formattedDateTime); //输出格式化后的日期时间
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return dateFormat.parse(formattedDateTime);
    }

    //将时间（以小时为单位）转换成时间戳
    public long DateToTimestamp(Date time) throws Exception {
        long tmp=time.getTime()-this.timestamp;//这个是以1970开始计算的时间戳，需要加上1900-1970之间的时间戳（负数）
        tmp=tmp/(60*60*1000);//以小时为单位
        return tmp;
    }

public static void main(String[] args) throws Exception {
    TransferUtil t=new TransferUtil();
    String dateString="2023-03-01 00:00:00";
    SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date time= dateFormat.parse(dateString);
    System.out.println(t.DateToTimestamp(time));

    long timestamp1=1079616;
    long timestamp2=1079616+24*6;
    System.out.println(t.TimestampToDate(timestamp1));
    System.out.println(t.TimestampToDate(timestamp2));

}

}

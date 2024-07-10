package com.ahu.service;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ucar.ma2.InvalidRangeException;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class FileCheckService {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    // 每3分钟检查一次
    @Scheduled(fixedRate = 3 * 60 * 1000) // 6小时x60分钟x60秒x1000毫秒
    public void checkForNewFiles() throws InvalidRangeException, IOException {
        File directory = new File("F:/ncFile"); // 扫描的固定目录路径
        File[] files = directory.listFiles();
        long currentTime = System.currentTimeMillis();

        if (files != null) {
            for (File file : files) {
                if (file.lastModified() > currentTime - 3 * 60 * 1000) {
                    // 新文件的最后修改时间在过去6小时内
                    System.out.println("发现新文件: " + file.getName());
                    // 处理新文件的代码...，解析该NC文件
                    //ParseNetCDFController controller = new ParseNetCDFController();
                    //controller.parseNetCDF("F:/ncFile/"+file.getName());
                }
            }
        }
        System.out.println("Check for new files completed at " + dateFormat.format(new Date()));
    }


}

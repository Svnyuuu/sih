package com.ahu.controller;

import com.ahu.pojo.NetCDF;
import com.ahu.pojo.Result;
import com.ahu.service.QueryNetCDFService;
import com.ahu.utils.TransferUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("sys")
public class QueryNetCDFController {

    @Autowired
    private QueryNetCDFService findNetCDFService;

    // 气象数据查询模块controllerr接口
    @GetMapping("/getNC")
    public Result<List<NetCDF>> findNetCDF(@DateTimeFormat(pattern = "yyyy-DD-mm HH:mm:ss") Date beginTime,
            @DateTimeFormat(pattern = "yyyy-DD-mm HH:mm:ss") Date endTime,
            float longitude, // 纬度
            float latitude, // 经度
            float radius) throws Exception {

        @SuppressWarnings("unused")
        Integer serialNo = 1;// 系统自动产生
        // 将时间转成时间戳
        TransferUtil t = new TransferUtil();
        long b = t.DateToTimestamp(beginTime);
        long e = t.DateToTimestamp(endTime);
        List<NetCDF> res = findNetCDFService.getNC(b, e, longitude, latitude, radius);
        // 将res重新组装到Result中去
        return Result.success("搜索满足气象条件的数据成功", res);

    }

}

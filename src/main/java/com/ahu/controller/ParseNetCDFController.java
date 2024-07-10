package com.ahu.controller;

import com.ahu.pojo.NetCDF;
import com.ahu.pojo.Result;
import com.ahu.service.ParseNetCDFService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ucar.ma2.InvalidRangeException;

import java.io.IOException;

@RestController
@RequestMapping("/sys")
public class ParseNetCDFController {

    @Autowired
    public ParseNetCDFService parseNetCDFService;

    //气象数据解析模块接口
    @GetMapping("/parseNC")
    public Result<NetCDF> parseNetCDF(String filePath,int days,int first) throws InvalidRangeException, IOException {
        System.out.println("文件名：" + filePath);
        Result<NetCDF> res = null;
        if (filePath != null || !"".equals(filePath)) {
            res = parseNetCDFService.parseNC(filePath,days,first);
        }
        return res;
    }


}

package com.ahu.service;

import com.ahu.pojo.NetCDF;
import com.ahu.pojo.Result;
import ucar.ma2.InvalidRangeException;

import java.io.IOException;

public interface ParseNetCDFService {

    /**
     * 保存经度对应的所有数据
     * @return
     */
    Result<NetCDF> parseNC(String filePath,int days,int first) throws InvalidRangeException, IOException;


    /**
     * 查找全部经度对应数据
     * @return
     */
    //List<NetCDF> findAll(String tableName);



}

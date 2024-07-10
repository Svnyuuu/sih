package com.ahu.service;

import com.ahu.pojo.NetCDF;
import com.ahu.pojo.Result;

import java.util.Date;
import java.util.List;

public interface QueryNetCDFService {

    List<NetCDF> getNC(long beginTime, long endTime, float longitude, float latitude, float radius);

}

package com.ahu.service.impl;

import com.ahu.mapper.QueryNetCDFMapper;
import com.ahu.pojo.NetCDF;
import com.ahu.pojo.Result;
import com.ahu.service.QueryNetCDFService;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.nio.ch.Net;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class QueryNetCDFServiceImpl implements QueryNetCDFService {

    @Autowired
    private QueryNetCDFMapper queryNetCDFMapper;

    //获取8个点的longitude,8个点顺序不能变
    private ArrayList<Float> get8Longitude(float longitude, float radius) {
        //以查询点为中点，以radius为半径查周围8个点，如若没有，找最近的8个点
        ArrayList<Float> point_lon = new ArrayList<Float>();
        //点1
        point_lon.add(longitude - radius);
        //点2
        point_lon.add(longitude + radius);
        //点3
        point_lon.add(longitude - radius);
        //点4
        point_lon.add(longitude);
        //点5
        point_lon.add(longitude + radius);
        //点6
        point_lon.add(longitude + radius);
        //点7
        point_lon.add(longitude);
        //点8
        point_lon.add(longitude - radius);
        //点9，自己本身
        point_lon.add(longitude);
        return point_lon;
    }

    //获取8个点的latitude
    private ArrayList<Float> get8latitude(float latitude, float radius) {
        //以查询点为中点，以radius为半径查周围8个点，如若没有，找最近的8个点
        ArrayList<Float> point_lat = new ArrayList<Float>();
        //点1
        point_lat.add(latitude);
        //点2
        point_lat.add(latitude);
        //点3
        point_lat.add(latitude + radius);
        //点4
        point_lat.add(latitude + radius);
        //点5
        point_lat.add(latitude + radius);
        //点6
        point_lat.add(latitude);
        //点7
        point_lat.add(latitude - radius);
        //点8
        point_lat.add(latitude - radius);
        //点9，自己本身
        point_lat.add(latitude);
        return point_lat;
    }

    @Override
    public List<NetCDF> getNC(long beginTime, long endTime, float longitude, float latitude, float radius) {
        //获取9个点坐标，包括自己
        ArrayList<Float> point_lon = get8Longitude(longitude, radius);
        ArrayList<Float> point_lat = get8latitude(latitude, radius);
        //将9次的查询结果整合到一起返回
        List<NetCDF> allData = new ArrayList<NetCDF>();
        for (int i = 0; i < point_lon.size(); i++) {
            //point_lon里的数据即为所要查的表名，先做转化，表名用经度(小数点换成_)
            String tableName = "tbl_lon_" + String.valueOf(point_lon.get(i)).replace(".", "_");
            List<NetCDF> res = queryNetCDFMapper.getNC(beginTime, endTime, point_lat.get(i), tableName);
            allData.addAll(res);
        }
        return allData;
    }
}

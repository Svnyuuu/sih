package com.ahu.utils;

import com.ahu.mapper.ParseNetCDFMapper;
import com.ahu.pojo.NetCDF;
import org.apache.ibatis.session.SqlSession;
import ucar.ma2.Array;
import ucar.ma2.Index;
import ucar.ma2.InvalidRangeException;
import ucar.nc2.NetcdfFile;

import java.io.IOException;
import java.util.*;

//解析nc文件专用类
public class ParseNetCDF {

    //解析过后进行封装
    public void parse(String tableName, NetcdfFile dataFile, int lon, int hours, int lats,
                      ParseNetCDFMapper lonMapper, SqlSession sqlSession,int first) throws IOException, InvalidRangeException {

        //获取所有的变量及其值
        Array time_data = dataFile.findVariable("time").read();
        Array lat_data = dataFile.findVariable("latitude").read();
        Array lon_data = dataFile.findVariable("longitude").read();

        //一个经度hours/24=14天的数据量
        int[] org = {0, 0, lon};
        int[] sha = {hours, lats, 1};
        Array u_data = dataFile.findVariable("u10").read(org, sha);
        Array v_data = dataFile.findVariable("v10").read(org, sha);
        Array mwd_data = dataFile.findVariable("mwd").read(org, sha);
        Array mwp_data = dataFile.findVariable("mwp").read(org, sha);
        Array swh_data = dataFile.findVariable("swh").read(org, sha);




        //获取参数和索引
        Index index = u_data.getIndex();
        long count=1;
        List<NetCDF> nclist = new ArrayList<NetCDF>();
        //按时间
        for (int time = 0; time < hours; time++) {
            //按纬度
            for (int lat = 0; lat < lats; lat++) {
                //对气象分量进行封装,此处可以将数据做过转换后再封装
                NetCDF nc = new NetCDF();
                nc.setId(count++);
                nc.setMeteorTime((long)time_data.getInt(time));
                nc.setLatitude(lat_data.getFloat(lat));
                nc.setLongitude(lon_data.getFloat(lon));//每次经度只取一个，所以取气象分量应该始终索引是0
                nc.setU10(u_data.getFloat(index.set(time, lat, 0)));
                nc.setV10(v_data.getFloat(index.set(time, lat, 0)));
                nc.setMwd(mwd_data.getFloat(index.set(time, lat, 0)));
                nc.setMwp(mwp_data.getFloat(index.set(time, lat, 0)));
                nc.setSwh(swh_data.getFloat(index.set(time, lat, 0)));
                nclist.add(nc);
            }
            //一批存储一次
            saveDBUtil sdb = new saveDBUtil();
            sdb.saveDB(tableName, nclist,lonMapper,sqlSession);
            nclist.clear();
        }
    }

    public static void main(String[] args) throws IOException, InvalidRangeException {
        int hours=12;
        int lats=4;
        ParseNetCDF parseNetCDF = new ParseNetCDF();
        String filePath="F:\\workspace-idea\\spring-code\\ParseNetCDF\\2023年3-4月全球气象数据.nc";
        //List<NetCDF> nclist = parseNetCDF.parse(filePath, 0,hours,lats);//一次处理多少小时，几个纬度数据量
    }




}

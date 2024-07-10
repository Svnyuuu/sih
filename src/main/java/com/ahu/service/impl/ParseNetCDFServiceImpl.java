package com.ahu.service.impl;

import com.ahu.mapper.ParseNetCDFMapper;
import com.ahu.pojo.Result;
import com.ahu.service.ParseNetCDFService;
import com.ahu.utils.ParseNetCDF;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;
import ucar.ma2.Array;
import ucar.ma2.InvalidRangeException;
import ucar.nc2.NetcdfFile;

import java.io.IOException;



@Service
public class ParseNetCDFServiceImpl implements ParseNetCDFService {

    @Autowired
    private ParseNetCDFMapper lonMapper;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    private ParseNetCDF parseNetCDF=new ParseNetCDF();

    int hours=7*24;
    int lats=721;//纬度
    int minTimstamp=1079616;//2023.3.1
    int maxTimstamp=1079616+24*6;//2023.3.7
    @Override
    public Result parseNC(String filePath,int days,int first) throws InvalidRangeException, IOException {
        //hours=days*24;
        //1.先调用utils中ParseNetCDF类去解析
        NetcdfFile dataFile = NetcdfFile.open(filePath, null);
        //long lon_dim=dataFile.findVariable("longitude").getSize();
        // 关闭session的自动提交
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
        lonMapper = sqlSession.getMapper(ParseNetCDFMapper.class);
        for (int longitude = 0; longitude < 6; longitude++) {
            //定义表名，每一个经度对应一张表，表名用经度(小数点换下划线),记录时间
            String tableName = "tbl_lon_" + String.valueOf(dataFile.findVariable("longitude").read().
                    getFloat(longitude)).replace(".", "_");
            //查询经度表不存在
            if (lonMapper.existTable(tableName) == 0) {
                //新创建表
                lonMapper.createTable(tableName);
//               //删除经度表
//               lonMapper.dropTable(tableName);
            }
            System.out.print("正在处理第"+(longitude+1)+"个经度数据，");
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            parseNetCDF.parse(tableName,dataFile, longitude, hours, lats,lonMapper,sqlSession, first);//一次处理hours小时，lats个纬度数据量
            stopWatch.stop();
            System.out.println("耗时："+stopWatch.getTotalTimeSeconds());
        }
        dataFile.close();
        return Result.success("成功解析数据！");
    }

}

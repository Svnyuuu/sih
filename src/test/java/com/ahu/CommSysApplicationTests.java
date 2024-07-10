package com.ahu;

import com.ahu.mapper.ParseNetCDFMapper;
import com.ahu.pojo.NetCDF;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class CommSysApplicationTests {

    @Autowired
    private ParseNetCDFMapper lonMapper;
    @Autowired
    private SqlSessionFactory sqlSessionFactory;


    @Test
    void contextLoads() {

        System.out.println("开始批量插入======");
        long start = System.currentTimeMillis();
        int count = 250000;
        int tempCount = 0;
        // 关闭session的自动提交
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
        lonMapper = sqlSession.getMapper(ParseNetCDFMapper.class);
        List<NetCDF> nclist = new ArrayList<NetCDF>(count);
        for(int i = 0; i < count; i++){
            if(tempCount == 9999){
                lonMapper.batchInsert("tbl_lon_0",nclist);
                // 提交事务
                sqlSession.commit();
                tempCount = 0;
                nclist.clear();
            }

            NetCDF nc = new NetCDF();
            nc.setId((long)i++);
            nc.setMeteorTime(2L);
            nc.setLatitude(3.5f);
            nc.setLongitude(4.6f);
            nc.setU10(5.7f);
            nc.setV10(5.5f);
            nc.setMwd(6.5f);
            nc.setMwp(2.3f);
            nc.setSwh(3.4f);
            nclist.add(nc);

        }
        lonMapper.batchInsert("tbl_lon_0",nclist);
        // 提交事务
        sqlSession.commit();
        System.out.println("共插入"+count+"条数据,耗时："+(System.currentTimeMillis()-start));






//        List<NetCDF> nclist = new ArrayList<NetCDF>(count);
//        for(int i = 0; i < count; i++){
//            if(tempCount == 9999){
//                lonMapper.batchInsert("tbl_lon_0",nclist);
//                tempCount = 0;
//                nclist.clear();
//            }
//            NetCDF nc = new NetCDF();
//            nc.setId(BigInteger.valueOf(i++));
//            nc.setTime(2);
//            nc.setLatitude(3.5f);
//            nc.setLongitude(4.6f);
//            nc.setU10(5.7f);
//            nc.setV10(5.5f);
//            nc.setMwd(6.5f);
//            nc.setMwp(2.3f);
//            nc.setSwh(3.4f);
//            nclist.add(nc);
//        }
//        lonMapper.batchInsert("tbl_lon_0",nclist);
//        System.out.println("共插入"+count+"条数据,耗时："+(System.currentTimeMillis()-start));













//        System.out.println("开始批量插入======");
//        long start = System.currentTimeMillis();
//        int count = 1000000;
//        int tempCount = 0;
//        List<NetCDF> nclist = new ArrayList<NetCDF>(count);
//        for(int i = 0; i < count; i++){
//            if(tempCount == 9999){
//                lonMapper.batchInsert("tbl_lon_0",nclist);
//                tempCount = 0;
//                nclist.clear();
//            }
//            NetCDF nc = new NetCDF();
//            nc.setId(BigInteger.valueOf(i++));
//            nc.setTime(2);
//            nc.setLatitude(3.5f);
//            nc.setLongitude(4.6f);
//            nc.setU10(5.7f);
//            nc.setV10(5.5f);
//            nc.setMwd(6.5f);
//            nc.setMwp(2.3f);
//            nc.setSwh(3.4f);
//            nclist.add(nc);
//        }
//        lonMapper.batchInsert("tbl_lon_0",nclist);
//        System.out.println("共插入"+count+"条数据,耗时："+(System.currentTimeMillis()-start));



//        System.out.println("开始插入======");
//        long start = System.currentTimeMillis();
//        int count = 1000;
//        for(int i = 0; i < count; i++){
//            NetCDF nc = new NetCDF();
//            nc.setId(BigInteger.valueOf(i++));
//            nc.setTime(2);
//            nc.setLatitude(3.5f);
//            nc.setLongitude(4.6f);
//            nc.setU10(5.7f);
//            nc.setV10(5.5f);
//            nc.setMwd(6.5f);
//            nc.setMwp(2.3f);
//            nc.setSwh(3.4f);
//            lonMapper.insertData("tbl_lon_0",nc);
//        }
//        System.out.println("共插入"+count+"条数据,耗时："+(System.currentTimeMillis()-start));
    }

}

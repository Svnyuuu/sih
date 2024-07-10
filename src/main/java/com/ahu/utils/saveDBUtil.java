package com.ahu.utils;

import com.ahu.mapper.ParseNetCDFMapper;
import com.ahu.pojo.NetCDF;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class saveDBUtil {


    public void saveDB(String tableName, List<NetCDF> nclist, ParseNetCDFMapper lonMapper, SqlSession sqlSession){
        //批量保存数据
        lonMapper.batchInsert(tableName,nclist);
        // 提交事务
        sqlSession.commit();
        nclist.clear();
    }








}

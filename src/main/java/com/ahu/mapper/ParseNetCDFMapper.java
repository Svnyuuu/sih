package com.ahu.mapper;

import com.ahu.pojo.NetCDF;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ParseNetCDFMapper {

    //插入数据
    void insertData(@Param("tableName") String tableName,@Param("nc") NetCDF nc);

    //批量插入数据
    void batchInsert(@Param("tableName") String tableName,@Param("nclist") List<NetCDF> nclist);

    /**
     * 查找全部
     * @param tableName
     * @return
     */
    List<NetCDF> selectAll(@Param("tableName") String tableName);

    /**
     * 是否存在表
     * @param tableName
     * @return
     */
    int existTable(@Param("tableName") String tableName);

    /**
     * 删除表
     * @param tableName
     * @return
     */
    int dropTable(@Param("tableName") String tableName);

    /**
     * 创建表
     * @param tableName
     * @return
     */
    int createTable(@Param("tableName") String tableName);

    /**
     * 创建时间表
     * @param tableName
     * @return
     */
    int createTimeTable(@Param("tableName") String tableName);

}

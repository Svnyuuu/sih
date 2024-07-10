package com.ahu.mapper;

import com.ahu.pojo.NetCDF;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QueryNetCDFMapper {

        @Select("select * from ${tableName} where meteorTime between #{beginTime} and #{endTime} " +
                        "and latitude=#{latitude}")
        List<NetCDF> getNC(@Param("beginTime") long beginTime,
                        @Param("endTime") long endTime,
                        @Param("latitude") float latitude,
                        @Param("tableName") String tableName);
}

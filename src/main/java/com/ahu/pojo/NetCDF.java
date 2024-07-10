package com.ahu.pojo;

import lombok.Data;

//nc数据实体类
@Data
public class NetCDF {

    private Long id;

    private Long meteorTime;// 气象时间

    private Float latitude;

    private Float longitude;

    private Float u10;

    private Float v10;

    private Float mwd;

    private Float mwp;

    private Float swh;

}

package org.example;

import parameter.SetParameter;
import ucar.ma2.InvalidRangeException;
import ucar.nc2.NetcdfFile;
import ucar.nc2.Variable;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

//导入数据，将维度和变量导入数据库
public class DataImporter {

    private NetcdfDataProcessor dataProcessor;

    public DataImporter(Connection connection) {
        // 构造函数，初始化数据处理器
        this.dataProcessor = new NetcdfDataProcessor(connection);
    }

    public void processData(String ncPath) throws SQLException, InvalidRangeException, IOException {
        //处理数据
        NetcdfFile ncFile = null;
        SetParameter today = new SetParameter(2023,3,15,0);
        try {
            ncFile = NetcdfFile.open(ncPath);
            List<Variable> variables = ncFile.getVariables();



            /*目前latitude与longitude表固定在一张表中，time独自建表，如果再次添加表，是单独建表，还是与经纬度共用表*/
            Variable latVar = ncFile.findVariable("latitude");
            Variable lonVar = ncFile.findVariable("longitude");
            Variable timeVar = ncFile.findVariable("time");
            dataProcessor.createFullTablesByLongitude(timeVar,latVar,lonVar,variables.get(3),variables.get(4),variables.get(5),variables.get(6),variables.get(7));
            dataProcessor.createTimeTable(timeVar);
            dataProcessor.createLat_Lon_table(latVar,lonVar);


//            for (int i = 3; i < variables.size(); i++) {
//                // 将剩余变量写入数据库
//                dataProcessor.writeVariableToLon_table(variables.get(i));
//            }







        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (ncFile != null) {
                ncFile.close();
            }
        }
    }
}
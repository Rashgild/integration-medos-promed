package ru.integration.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SQLUtils {

    public static ResultSet select (String reqSQL) {

        Connection connection;
        ResultSet resultSet = null;
        try {
            Class.forName(GlobalVariables.dbdriver);
            connection = DriverManager.getConnection(GlobalVariables.dbhost, GlobalVariables.dblogin, GlobalVariables.dbpassword);
            Statement statement;

            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            resultSet = statement.executeQuery(reqSQL);
            if(!GlobalVariables.dbdriver.equals("com.intersys.jdbc.CacheDriver")){
                connection.close();
            }
            return resultSet;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return resultSet;
    }

    /**
     * @param SQL
     * @return
     * SQL="select num1,num2 from numbers" was returning list of lists with num1 and num2
     */
    public static List<List<String>> getResult(String SQL){
        try {
            ResultSet resultSet = select(SQL);
            int columnCount = resultSet.getMetaData().getColumnCount();


            //ResultSetMetaData rsmd = resultSet.getMetaData();
            /* for (int j = 1; j <= columnCount; j++) {
                headers.add(rsmd.getColumnName(j));
            }*/
            List<String> list = new ArrayList<>();
            List<List<String>> lists = new ArrayList<>();
            while (resultSet.next()) {
                list = new ArrayList<>();
                for (int j = 1; j <= columnCount; j++) {
                    String s = resultSet.getString(j);
                    list.add(s);
                }
                lists.add(list);
            }
            return lists;
        }catch (Exception e){e.printStackTrace();}
        return null;
    }


    public static List<Map<String, String>> getResult(String SQL,List<String> key){
        try {
            ResultSet resultSet = select(SQL);
            int columnCount = resultSet.getMetaData().getColumnCount();
            Map<String, String> params = new HashMap<String, String>();
            List<Map<String,String>> lists = new ArrayList<>();
            while (resultSet.next()) {
                params = new HashMap<String, String>();
                for (int j = 1; j <= columnCount; j++) {
                    String s = resultSet.getString(j);
                    params.put(key.get(j-1),s);
                }
                lists.add(params);
            }
            return lists;
        }catch (Exception e){e.printStackTrace();}
        return null;
    }
}

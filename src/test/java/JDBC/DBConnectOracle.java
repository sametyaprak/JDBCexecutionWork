package JDBC;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utilities.DatabaseConnectorV1;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBConnectOracle {
    static String URL = "jdbc:oracle:thin:@localhost:1521/ORCLCDB.localdomain";
    static String USERNAME = "samet";
    static String PASSWORD = "Ankara06";
    static Connection connection;
    static Statement statement;
    static ResultSet resultSet;

    String query = "select * from bolumler";
    String query2 = "select personel_isim, maas from personel where bolum_id in(10,30) order by maas";
    String query3 ="select PERSONEL_ISIM,BOLUM_ISIM,MAAS from PERSONEL\n" +
            "join BOLUMLER B on PERSONEL.BOLUM_ID = B.BOLUM_ID";


    @BeforeTest
    public void beforeTest() throws SQLException {
        connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        statement = connection.createStatement();
    }

    @Test
    void soru2() throws SQLException {
        resultSet = statement.executeQuery(query2);
        while(resultSet.next()) {
            System.out.println("personel verileri: "+resultSet.getString(1)+ " maas: " + resultSet.getInt(2));
        }
    }
    @Test
    void soru3() throws SQLException {

        List<String> myData = new ArrayList<>();
        resultSet = statement.executeQuery(query3);
        while(resultSet.next()){
            myData.add(resultSet.getString(1));
        }
        System.out.println("myData = " + myData);
        List<Map<String,String>>myAllData = DatabaseConnectorV1.getQueryResultWithAListMap(query3);
        //birinci satirdaki maas nedir?
        System.out.println("myReturnData " + myAllData.get(1).get("MAAS"));
        //butun maaslari alalim ve ortalamasini bulalim
        int allLoans =0;
        for (Map<String, String> myAllDatum : myAllData) {
            System.out.println(myAllDatum.get("MAAS"));
            allLoans = allLoans + Integer.parseInt(myAllDatum.get("MAAS"));
        }
        System.out.println("ortalama: " +allLoans/myAllData.size());

        for (Map<String, String> myAllDatum : myAllData) {
            if(Integer.parseInt(myAllDatum.get("MAAS"))>allLoans/myAllData.size()){
                System.out.println("personel isimleri: "+myAllDatum.get("PERSONEL_ISIM"));
            }
        }

    }




}


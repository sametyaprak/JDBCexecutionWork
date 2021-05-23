package JDBC;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utilities.DatabaseConnectorV1;

import java.sql.*;
import java.util.*;

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
    @Test
    void ddl() throws SQLException {
        /*
        A) CREATE TABLE, DROP TABLE, ALTER TABLE gibi DDL ifadeleri icin sonuc kümesi (ResultSet)
        dondurmeyen metotlar kullanilmalidir. Bunun icin JDBC'de 2 alternatif bulunmaktadir.
                1) execute() metodu
                2) excuteUpdate() metodu.

        B) - execute() metodu hertur SQL ifadesiyle kullanibilen genel bir komuttur.
           - execute(), Boolean bir deger dondurur. DDL islemlerin false dondururken,
           - DML islemlerinde true deger dondurur.
           - Ozellikle hangi tip SQL ifadesinin kullanilmasinin gerektiginin belli olmadigi
        durumlarda tercih edilmektedir.

       C) - executeUpdate() metodu ise INSERT, Update gibi DML islemlerinde yaygin kullanilir.
          - bu islemlerde islemden etkilenen satir sayisini dondurur.
          - Ayrıca, DDL islemlerinde de kullanilabilir ve bu islemlerde 0 dondurur.*/
        String queryTableCreate = "create table isciler(\n" +
                "    id number(3),\n" +
                "    birim varchar2(10),\n" +
                "    maas number(5)\n" +
                ")";
        //1.yontem execute komutu ile
        //boolean result = statement.execute(queryTableCreate);
        //false dondurecektir. DDL komutlarinda false dondurur
        //System.out.println(result);
        //2.yontem executeUpdate
        //ddl islemleri icin sifir dondurur.
        //ddl islemleri....tablo delete, create, add column, delete column....
        int result2 = statement.executeUpdate(queryTableCreate);
        System.out.println(result2);
        //drop.da bir DDL islemi oldugu icin sifir dondurur.
        System.out.println(statement.executeUpdate("drop table isciler"));
        //alter table"da bir DDL islemidir.
        int result3CreateTable = statement.executeUpdate(queryTableCreate);
        System.out.println(result3CreateTable);
        int result4AlterTable = statement.executeUpdate("alter table isciler add isim varchar2(10)");
        System.out.println(result4AlterTable);
    }
    @Test
    void dml () throws SQLException {
//        String deleteQuery = "INSERT INTO bolumler VALUES(90, 'ARGE2','CORLU')";
//        int deletedRows = statement.executeUpdate(deleteQuery);
//        System.out.println(deletedRows + " rows updated");


        //bircok veriyi for dongusu ile ekleyebilirsin
        String [] add = {   "insert into bolumler values (44,'arge','izmir')",
                            "insert into bolumler values (55,'ofis','istanbul')",
                            "insert into bolumler values (66,'yemekhane','bursa')"};
        int sum = 0;
        for (String w :add) {
            //sum = sum + statement.executeUpdate(w);
            //add batch icine tum ifadeleri ekledik
            statement.addBatch(w);
        }
        System.out.println(sum+ " rows updated");
        int batchResult [] = statement.executeBatch();
        System.out.println(Arrays.toString(batchResult));


    }



}


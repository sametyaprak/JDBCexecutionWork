package teamtrycatch;

import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.DatabaseConnectorV1;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class US001 {

    String tc0101Query ="select count(sirket_id)\n" +
            "from sirketler";
    String tc0102Query ="select count(sirket_id)\n" +
            "from sirketler\n" +
            "where merkez_ulke = 'germany'";
    String tc0103Query ="select sum(abone_Sayisi)\n" +
            "from sirketler\n" +
            "where merkez_ulke='turkey'";
    String tc0104Query ="select round(avg(abone_Sayisi))\n" +
            "from sirketler\n" +
            "where merkez_ulke='germany' and abone_Sayisi>0";
    String tc0105Query ="select sirket_adi\n" +
            "from sirketler\n" +
            "where abone_Sayisi is not null\n" +
            "order by abone_Sayisi desc offset 1 rows fetch next 1 rows only";

    List<Map<String,String>> myAllData = new ArrayList<>();
    ResultSet resultSet;

    @Test
    void tc0101() throws SQLException {
        resultSet = DatabaseConnectorV1.getResultSet(tc0101Query);
        String data ="";
        while (resultSet.next()){
            data = resultSet.getString(1);
        }
        Assert.assertEquals(data,"15");
    }
    @Test
    void tc0102() throws SQLException {
        resultSet = DatabaseConnectorV1.getResultSet(tc0102Query);
        String data ="";
        while (resultSet.next()){
            data = resultSet.getString(1);
        }
        Assert.assertEquals(data,"4");
    }
    @Test
    void tc0103() throws SQLException {
        resultSet = DatabaseConnectorV1.getResultSet(tc0103Query);
        String data ="";
        while (resultSet.next()){
            data = resultSet.getString(1);
        }
        Assert.assertEquals(data,"50750000");
    }
    @Test
    void tc0104() throws SQLException {
        resultSet = DatabaseConnectorV1.getResultSet(tc0104Query);
        String data ="";
        while (resultSet.next()){
            data = resultSet.getString(1);
        }
        Assert.assertEquals(data,"3351667");
    }
    @Test
    void tc0105() throws SQLException {
        resultSet = DatabaseConnectorV1.getResultSet(tc0105Query);
        String data ="";
        while (resultSet.next()){
            data = resultSet.getString(1);
        }
        Assert.assertEquals(data,"ali expres");
    }

}

package teamtrycatch;

import org.junit.Assert;
import org.junit.Test;
import utilities.DatabaseConnectorV1;
import java.sql.ResultSet;
import java.sql.SQLException;

public class US004 {

    String tc0401Query = "select round(avg(maas),2)from personel";
    String tc0402Query = "select count(calisan_id) from personel";
    String tc0403Query = "select max(maas)-min(maas) from personel";

    ResultSet resultSet;

    @Test
    public void tc0401() throws SQLException {
        resultSet = DatabaseConnectorV1.executeQuery(tc0401Query);
        while (resultSet.next()){
            double a = (resultSet.getDouble(1));
            Assert.assertEquals(2266.21, a);
        }
    }
    @Test
    public void tc0402() throws SQLException {
        resultSet = DatabaseConnectorV1.executeQuery(tc0402Query);
        while (resultSet.next()){
            int a = (resultSet.getInt(1));
            Assert.assertEquals(29, a);
        }
    }
    @Test
    public void tc0403() throws SQLException {
        resultSet = DatabaseConnectorV1.executeQuery(tc0403Query);
        while (resultSet.next()){
            int a = (resultSet.getInt(1));
            Assert.assertEquals(3060, a);
        }
    }



}



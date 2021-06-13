package teamtrycatch;

import io.cucumber.java.sl.In;
import org.junit.Assert;
import org.junit.Test;
import utilities.DatabaseConnectorV1;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class US003 {

    String tc0301Query = "select merkez_ulke,sum(abone_Sayisi)\n" +
            "from sirketler\n" +
            "group by merkez_ulke\n" +
            "having sum(abone_Sayisi) is not null\n" +
            "order by sum(abone_Sayisi) desc";
    String tc0302QueryUpdate = "update sirketler\n" +
            "set sirket_adi='Trendyol'\n" +
            "where sirket_id = 1210";
    String tc0302Query = "select sirket_adi from sirketler\n" +
            "where sirket_id = 1210";
    String tc0303Query = "select merkez_ulke\n" +
            "from sirketler\n" +
            "where kurulus_tarih is not null\n" +
            "order by kurulus_tarih desc";

    ResultSet resultSet;
    List<Object> myAllData = new ArrayList<>();

    @Test
    public void tc0301() throws SQLException {
        resultSet = DatabaseConnectorV1.getResultSet(tc0301Query);
        while (resultSet.next()){
            myAllData.add(resultSet.getInt(2));
        }
        Assert.assertEquals(myAllData.get(0),325502500);
    }
    @Test
    public void tc0302() throws SQLException {
        resultSet = DatabaseConnectorV1.getResultSet(tc0302QueryUpdate);
        resultSet = DatabaseConnectorV1.getResultSet(tc0302Query);
        while (resultSet.next()){
            myAllData.add(resultSet.getString(1));
        }
        Assert.assertEquals("Trendyol",myAllData.get(0));
    }
    @Test
    public void tc0303() throws SQLException {
        resultSet = DatabaseConnectorV1.getResultSet(tc0303Query);
        while (resultSet.next()){
            myAllData.add(resultSet.getString(1));
        }
        Assert.assertEquals("turkey",myAllData.get(0));
    }



}



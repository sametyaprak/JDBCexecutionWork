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

public class US002 {

    String tc0201Query = "select sirket_id from sirketler\n" +
            "where merkez_ulke in ('turkey', 'usa') and kurulus_tarih> date '2000-01-01'";
    String tc0202Query = "select sirket_adi from sirketler\n" +
            "where sirket_adi not like '%a%'";
    String tc0203Query = "select kurulus_tarih from sirketler";
    String tc0204Query = "select sirket_adi,abone_Sayisi\n" +
            "from sirketler\n" +
            "where abone_Sayisi >10000 and abone_Sayisi<99999";
    String tc0205Query = "select abone_Sayisi\n" +
            "from sirketler\n" +
            "where odeme_turu ='nakit'\n" +
            "order by abone_Sayisi";
    String tc0206Query = "select odeme_turu, count(odeme_turu)\n" +
            "from sirketler\n" +
            "group by odeme_turu\n" +
            "order by count(odeme_turu) desc";

    ResultSet resultSet;
    List<Object> myAllData = new ArrayList<>();

    @Test
    public void tc0201() throws SQLException {
        resultSet = DatabaseConnectorV1.getResultSet(tc0201Query);
        while (resultSet.next()){
            myAllData.add(resultSet.getInt(1));
        }
        Assert.assertEquals(myAllData.size(),5);
    }
    @Test
    public void tc0202() throws SQLException {
        resultSet = DatabaseConnectorV1.getResultSet(tc0202Query);
        while (resultSet.next()){
            myAllData.add(resultSet.getString(1));
        }
        Assert.assertEquals(myAllData.size(),7);
    }
    @Test
    public void tc0203() throws SQLException {
        List<String> myAllData2 = new ArrayList<>();
        resultSet = DatabaseConnectorV1.getResultSet(tc0203Query);
        while (resultSet.next()){
            myAllData2.add(resultSet.getString(1));
            myAllData2.remove(null);
        }
        long size2 = myAllData2.stream().map(x->Integer.parseInt(x.substring(0, 4))).filter(z-> z %2==0).count();
        Assert.assertEquals(size2,6);
    }
    @Test
    public void tc0204() throws SQLException {
        resultSet = DatabaseConnectorV1.getResultSet(tc0204Query);
        while (resultSet.next()){
            myAllData.add(resultSet.getString(1));
        }
        Assert.assertEquals(myAllData.size(),3);
    }
    @Test
    public void tc0205() throws SQLException {
        resultSet = DatabaseConnectorV1.getResultSet(tc0205Query);
        while (resultSet.next()){
            myAllData.add(resultSet.getInt(1));
        }
        Assert.assertEquals(myAllData.get(0),1);
        Assert.assertEquals(myAllData.get(5),20000000);
    }
    @Test
    public void tc0206() {
        List<Map<String,String>> dataList;
        dataList = DatabaseConnectorV1.getQueryResultWithAListMap(tc0206Query);
        Assert.assertEquals(dataList.get(0).get("ODEME_TURU"),"hepsi");
        Assert.assertEquals(dataList.get(1).get("ODEME_TURU"),"nakit");
        Assert.assertEquals(dataList.get(2).get("ODEME_TURU"),"kredi kart");
        Assert.assertEquals(dataList.get(0).get("COUNT(ODEME_TURU)"),"8");
        Assert.assertEquals(dataList.get(1).get("COUNT(ODEME_TURU)"),"6");
        Assert.assertEquals(dataList.get(2).get("COUNT(ODEME_TURU)"),"6");
    }


}



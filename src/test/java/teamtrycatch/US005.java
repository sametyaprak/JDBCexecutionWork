package teamtrycatch;

import org.junit.Assert;
import org.junit.Test;
import utilities.DatabaseConnectorV1;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class US005 {

    String tc0501Query = "update personel\n" +
            "set maas = maas+300\n" +
            "where maas<3000";
    String tc0501QueryForAssertion = "select calisan_id,maas\n" +
            "from personel";
    String tc0502Query = "select count(calisan_id) from personel";
    String tc0503Query = "select max(maas)-min(maas) from personel";
    String tc0503Query2 = "select round(avg(maas),2)from personel";
    List<Map<String,String>> myAllDataBeforeUpdate = new ArrayList<>();
    List<Map<String,String>> myAllDataAfterUpdate = new ArrayList<>();

    ResultSet resultSet;
    ResultSet resultSet2;

    @Test
    public void tc0501() throws SQLException {
        myAllDataBeforeUpdate = DatabaseConnectorV1.getQueryResultWithAListMap(tc0501QueryForAssertion);
        int totalSalaryBeforeUpdate = 0;
        for(int i=0;i<myAllDataBeforeUpdate.size();i++){
            totalSalaryBeforeUpdate = totalSalaryBeforeUpdate + Integer.parseInt(myAllDataBeforeUpdate.get(i).get("MAAS"));
        }
        int totalNumber = DatabaseConnectorV1.executeUpdateQuery(tc0501Query);
        myAllDataAfterUpdate = DatabaseConnectorV1.getQueryResultWithAListMap(tc0501QueryForAssertion);
        int totalSalaryAfterUpdate = 0;
        for(int i=0;i<myAllDataAfterUpdate.size();i++){
            totalSalaryAfterUpdate = totalSalaryAfterUpdate + Integer.parseInt(myAllDataBeforeUpdate.get(i).get("MAAS"));
        }
        Assert.assertEquals(totalSalaryBeforeUpdate+totalNumber*300,totalSalaryAfterUpdate);
    }
    @Test
    public void tc0503() throws SQLException {
        resultSet = DatabaseConnectorV1.executeQuery(tc0503Query);
        resultSet2 = DatabaseConnectorV1.executeQuery(tc0503Query2);
        while (resultSet.next() && resultSet2.next()){
            int a = (resultSet.getInt(1));
            Assert.assertTrue(resultSet.getInt(1)< resultSet2.getInt(1));
        }
    }
}

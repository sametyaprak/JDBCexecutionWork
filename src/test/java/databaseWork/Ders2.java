package databaseWork;

import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.DatabaseConnectorV1;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Ders2 {
    ResultSet resultSet;
    String queryTest1 = "select *\n" +
            "from suppliers\n" +
            "join products p on suppliers.supplier_id = p.supplier_id\n" +
            "where region='OR'";
    String queryTest2 = "select region\n" +
            "from suppliers\n" +
            "where contact_title='Owner'";
    String queryTest3 = "select city\n" +
            "from customers\n" +
            "group by city";

    List<Map<String,String>> dataList = new ArrayList<>();

    @Test
    public void test1() {
        dataList = DatabaseConnectorV1.getQueryResultWithAListMap(queryTest1);
        Assert.assertEquals(dataList.size(),3);
    }
    @Test
    public void test2() {
        dataList = DatabaseConnectorV1.getQueryResultWithAListMap(queryTest2);
        Assert.assertNull(dataList.get(0).get("region"));
    }
    @Test
    public void test3() {
        dataList = DatabaseConnectorV1.getQueryResultWithAListMap(queryTest3);
        Assert.assertEquals(dataList.size(),69);
    }

}



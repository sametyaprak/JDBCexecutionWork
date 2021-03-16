package databaseWork;

import org.testng.*;
import org.testng.annotations.*;
import utilities.DatabaseConnectorV1;
import java.sql.*;
import java.util.*;

public class Ders1 {
    ResultSet resultSet;
    String queryTest1 = "select \"public\".products.category_id, count (category_id) as total_product\n" +
            "from \"public\".products\n" +
            "group by category_id\n" +
            "order by count (category_id) desc";
    String queryTest2 = "select region from \"public\".suppliers where contact_title ='Owner'";
    String queryTest3 = "select product_name, category_name from \"public\".categories\n" +
            "join \"public\".products \n" +
            "on \"public\".categories.category_id = \"public\".products.category_id\n" +
            "where category_name = 'Seafood'";
    List<Map<String,String>> dataList = new ArrayList<>();

    @Test
    public void test1() throws SQLException {
    dataList = DatabaseConnectorV1.getQueryResultWithAListMap(queryTest1);
        Assert.assertEquals(dataList.get(0).get("total_product"),"13");
    }
    @Test
    public void test2() throws SQLException {
        dataList = DatabaseConnectorV1.getQueryResultWithAListMap(queryTest2);
        Assert.assertNull(dataList.get(0).get("total_product"));
    }
    @Test
    public void test3() throws SQLException {
        dataList = DatabaseConnectorV1.getQueryResultWithAListMap(queryTest3);
        Assert.assertEquals(dataList.size(),12);
    }
}

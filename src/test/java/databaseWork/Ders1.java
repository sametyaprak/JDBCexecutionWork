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
    String queryTest4 = "insert into customers (contact_name,company_name,customer_id)\n" +
            "(select first_name, title, employee_id from em" + "ployees ORDER BY random() LIMIT 1)";

    String queryTest4Control = "select contact_name,company_name,customer_id from customers";
    List<Map<String,String>> dataList = new ArrayList<>();
    List<Map<String,String>> dataList2 = new ArrayList<>();

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
    @Test
    public void test4() throws SQLException {
        dataList = DatabaseConnectorV1.getQueryResultWithAListMap(queryTest4);
        dataList2 = DatabaseConnectorV1.getQueryResultWithAListMap(queryTest4Control);
        long number = dataList2.stream()
                .filter(x->x.get("contact_name")
                .equals(dataList.get(0)
                .get("first_name"))).count();
        System.out.printf("", number);
    }

}

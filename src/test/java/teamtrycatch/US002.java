package teamtrycatch;

import org.junit.Test;

import java.sql.ResultSet;

public class US002 {

    String tc0201Query = "select sirket_id from sirketler\n" +
            "where merkez_ulke in ('turkey', 'usa') and kurulus_tarih> date '2000-01-01'";
    String tc0202Query = "";
    String tc0203Query = "";
    String tc0204Query = "";
    String tc0205Query = "";
    String tc0206Query = "";

    ResultSet resultSet;

    @Test
    void tc0201(){

    }

}



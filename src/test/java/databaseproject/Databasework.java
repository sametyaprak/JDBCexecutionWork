package databaseproject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class Databasework {
    Connection connection;
    Statement statement;
    ResultSet resultSet;

    static String URL = "jdbc:oracle:thin:@localhost:1521/ORCLCDB.localdomain";
    static String USERNAME = "samet";
    static String PASSWORD = "Ankara06";

    String getAllDataQuery = "select * from book_project";
    List<Book> myAllDatabase = new ArrayList<>();


    List<Book> getAllBoooks() throws SQLException {
        connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        statement = connection.createStatement();
        resultSet = statement.executeQuery(getAllDataQuery);
        while (resultSet.next()){
            myAllDatabase.add(new Book(resultSet.getInt(1),
                                        resultSet.getString(2),
                                        resultSet.getString(3),
                                        resultSet.getInt(4)));
        }
        return myAllDatabase;
    }

    public static void main(String[] args) throws SQLException {
        Databasework databasework = new Databasework();
        do{
            System.out.println("tum kitaplari listelemek icin....1 \n " +
                                   "kitap ismi sorgulamak icin....2 \n " +
                                   "yazar ismi sorgulamak icin....3 \n " +
                                   "kitap id sorgulamak icin......4 \n " +
                                   "cikis icin....................5");
            Scanner scan = new Scanner(System.in);
            String selection = scan.nextLine();
            String searchData="";
            if(selection.equals("2")||selection.equals("3")||selection.equals("4") ){
                System.out.println("arama icin veri giriniz");
                searchData = scan.nextLine();
            }
            switch (selection){
                case "1" : System.out.println(databasework.getAllBoooks());
                    break;
                case "2" : System.out.println(databasework.searchForBookName(searchData));
                    break;
                case "3" : System.out.println(databasework.searchForWriterName(searchData));
                    break;
                case "4" : System.out.println(databasework.searchForBookId(Integer.parseInt(searchData)));
                    break;
                case "5" : System.exit(1);
                    break;
            }
            databasework.myAllDatabase.clear();
            databasework.resultSet.close();
            databasework.statement.close();
            databasework.connection.close();
        } while (true);
    }

    List<Book> searchForBookName(String bookName) throws SQLException {
        return getAllBoooks().stream().filter(x->x.getBookName().equals(bookName)).collect(Collectors.toList());
    }
    List<Book> searchForWriterName(String writerName) throws SQLException {
        return getAllBoooks().stream().filter(x->x.getWriterName().equals(writerName)).collect(Collectors.toList());
    }
    List<Book> searchForBookId(int bookId) throws SQLException {
        return getAllBoooks().stream().filter(x->x.getBookId()==bookId).collect(Collectors.toList());
    }
}

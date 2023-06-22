package packages.service;

import lombok.Getter;
import lombok.Setter;
import org.postgresql.ds.PGPoolingDataSource;

import java.sql.*;
@Getter
@Setter
public class JDBCService {
    private PGPoolingDataSource ds;
    private Connection connection;
    private Statement statement;
    private ResultSet rs;

     JDBCService(){
         ds = new PGPoolingDataSource();
         ds.setServerName("localhost");
         ds.setDatabaseName("postgres");
         ds.setUser("postgres");
         ds.setPassword("password");
         ds.setMaxConnections(100);
         ds.setInitialConnections(20);
     }
    public void setConnection() {       //set connection to database
        /*String url = "jdbc:postgresql://localhost:5432/postgres";
        String name = "postgres";
        String password = "password";*/
        try {
            /*Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, name, password);*/
            connection = ds.getConnection();
            statement = connection.createStatement();
            System.out.println("Database connected\n");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void createDB(){
        try {
            statement.execute("CREATE TABLE if not exists Garage (Id SERIAL PRIMARY KEY NOT NULL, Year INT NOT NULL,Brand varchar(30) NOT NULL CHECK(trim(brand)!=''), Model varchar(30) NOT NULL CHECK(trim(model)!=''), Cost INT NOT NULL);");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void writeDB(){
        try {
            statement.execute("INSERT INTO Garage (Year, Brand, Model, Cost)" +
                    "VALUES" +
                    "(2002,'Mercedes', 'w140', 15000), " +
                    "(1996,'Mercedes', 'w140', 10000), " +
                    "(2019,'VW', 'Polo', 10000), " +
                    "(2016,'VW', 'Golf', 12000), " +
                    "(2012,'VW', 'Passat', 8000)," +
                    "(1998,'Audi', '100', 500)," +
                    "(2010,'Audi', 'A4', 8000)," +
                    "(2012,'Audi', 'A4', 9000)," +
                    "(2020,'Audi', 'A5', 30000)," +
                    "(2005,'Volvo', 'S90', 8000)," +
                    "(2014,'Volvo', 'S80', 18000)," +
                    "(2020,'Volvo', 'S60', 20000)," +
                    "(2022,'Volvo', 'S60', 23000)," +
                    "(2006,'Suzuki', 'Baleno', 3000)," +
                    "(2019,'Suzuki', 'Swift', 14000);");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void setStatement(String query){
        try {
            statement.execute(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void readDB(String query){
        try {
            rs=statement.executeQuery(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void closeDB()
    {
        /*connection.close();
        statement.close();
        rs.close();*/
        try {
            connection.close();
            statement.close();
            rs.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        /*if ( ds != null ) {
            ds.close();
        }*/
        System.out.println("Connections were closed\n");
    }
}

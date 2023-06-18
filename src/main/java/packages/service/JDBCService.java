package packages.service;

import lombok.Getter;
import lombok.Setter;

import java.sql.*;
@Getter
@Setter
public class JDBCService {
    private Connection connection;
    private Statement statement;
    private ResultSet rs;
    public void setConnection() {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String name = "postgres";
        String password = "password";
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, name, password);
            statement = connection.createStatement();
            System.out.println("Database connected\n");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void createDB(){
        try {
            statement.execute("CREATE TABLE if not exists Garage (Id SERIAL PRIMARY KEY NOT NULL, Year INT NOT NULL,Brand varchar(30) NOT NULL CHECK(trim(brand)!=''), Model varchar(30) NOT NULL CHECK(trim(model)!=''), Cost INT NOT NULL);");
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
            throw new RuntimeException(e);
        }
    }

    public void readDB(String query){
        try {
            rs=statement.executeQuery(query);
        } catch (SQLException e) {
            System.out.println(e);;
        }
    }
    public void closeDB()
    {
        try {
            connection.close();
            statement.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("Connections were not closed\n");;
        }
        System.out.println("Connections were closed");
    }
}

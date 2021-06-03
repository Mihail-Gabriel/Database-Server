package Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class Connection {

    private static final String url ="postgres://dumbo.db.elephantsql.com:5432/pibuxzdl ";
    private static final String user="pibuxzdl";
    private static final String password="gKOAyIxcWsPFE62vKEUlTXZ1IBpyHwYK ";

    public Connection()
    {
        try{
            DriverManager.registerDriver(new org.postgresql.Driver());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public java.sql.Connection getConnection()  throws SQLException
    {
        return DriverManager.getConnection(url,user,password);
    }
}

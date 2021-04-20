package Persistence.PlaceHolderDAO;


import Connection.Connection;
import java.sql.SQLException;

public class PlaceHolderDAOImpl extends Connection implements  IPlaceHolderDAO {
    public java.sql.Connection getConnection() throws SQLException
    {
        return super.getConnection();
    }

    @Override
    public String getResult(String message) {
        return "The message was " + message;
    }
}

package Persistence.PlaceHolderDAO;


import Connection.Connection;

import java.beans.PropertyChangeListener;
import java.sql.SQLException;

public class PlaceHolderDAOImpl extends Connection implements  IPlaceHolderDAO {

    @Override
    public String getResult(String message) {
        return "The message was " + message;
    }

}

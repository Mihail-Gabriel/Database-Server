package Persistence.PlaceHolderDAO;

import Util.PropertyChangeSubject;

public interface IPlaceHolderDAO extends PropertyChangeSubject {

    String getResult(String message);
}

package Persistence.Repository.Implementation;

import Persistence.PlaceHolderDAO.IPlaceHolderDAO;
import Persistence.PlaceHolderDAO.PlaceHolderDAOImpl;
import Persistence.Repository.Repository;

public class RepositoryImpl implements Repository {

    private IPlaceHolderDAO placeHolderDAO = new PlaceHolderDAOImpl();

    @Override
    public void getPlaceHolder(String message) {

        String result =  "The message was received by the repository and it was: " + message;
        //After the proof of concept this will get expanded and it will have different classes for each of the requests that will be
        //received from the client -> which is actually the server of the blazor web page.
    }
}

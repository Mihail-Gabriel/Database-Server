import networking.DatabaseServer;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.UnknownHostException;

public class RunServer {

    public static void main(String[] args) {
        DatabaseServer dbs = new DatabaseServer();
        try{
            dbs.startServer();
        } catch (IOException e) {
            System.out.println("Complete failure to launch");
            e.printStackTrace();
        }
    }
}

package networking;

import Util.Request;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLOutput;

public class ServerSocketHandler implements Runnable {
    private Socket socket;
    private ObjectOutputStream outToClient;
    private ObjectInputStream inFromClient;
    //Future implementation of the model aka the DAO to send stuff into the database
    public ServerSocketHandler(Socket socket)
    {
        this.socket = socket;
        try{
            outToClient = new ObjectOutputStream(socket.getOutputStream());
            inFromClient = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try{
            while(true)
            {
                Request request = (Request) inFromClient.readObject();
                System.out.println(request.getEventType().toString());
                switch (request.getEventType())
                {
                    case PLACEHOLDER_REQUEST:
                }

            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

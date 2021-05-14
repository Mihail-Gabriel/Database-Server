package networking;

import Models.Users;
import Persistence.Repository.UserDAO.Implementation.UserDAOImpl;
import Util.Request;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.net.Socket;
import java.sql.SQLOutput;

public class ServerSocketHandler implements Runnable {
    private Socket socket;
    private OutputStream outToClient;
    private InputStream inFromClient;
    private String jsonResponse;     //Future implementation of the model aka the DAO to send stuff into the database

    public ServerSocketHandler(Socket socket) {
        String jsonResponse = new String();
        this.socket = socket;
        try {
            outToClient = socket.getOutputStream();
            inFromClient = socket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                byte[] jsonByte = new byte[256];
                int bytesRead;
                do {
                    bytesRead = inFromClient.read(jsonByte);

                    jsonResponse = new String(jsonByte, 0, bytesRead);
                    System.out.println(jsonResponse);
                }
                while (inFromClient.available() > 0);

                Request request;

                ObjectMapper objectMapper = new ObjectMapper();
                request = objectMapper.readValue(jsonResponse, Request.class);

                System.out.println(request.getEventType().toString());
                switch (request.getEventType()) {
                    case PLACEHOLDER_REQUEST:
                        String jsonResponse = "Message passing test";

                        outToClient.write(jsonResponse.getBytes());
                        System.out.println("Sent to java server --> " + jsonResponse.toString());
                        break;
                    case PLACEHOLDER_REQUEST_REGISTER_USER:
                        UserDAOImpl userDAO = new UserDAOImpl();

                        System.out.println(request.getObject().toString());

                        Object obj = request.getObject();

                        System.out.println(obj.toString());

                        //userDAO.RegisterUserAsync(user);

                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


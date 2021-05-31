package networking;

import Models.Users;
import Persistence.Repository.UserDAO.IUserDAO;
import Persistence.Repository.UserDAO.Implementation.UserDAOImpl;
import Util.Request;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.Socket;
import java.sql.SQLOutput;
import java.util.concurrent.ExecutionException;

public class ServerSocketHandler implements Runnable {
    private IUserDAO userDAO;
    private Socket socket;
    private OutputStream outToClient;
    private InputStream inFromClient;
    private String jsonResponse;

    public ServerSocketHandler(Socket socket) {
        String jsonResponse = new String();
        userDAO = new UserDAOImpl();
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
                    System.out.println(bytesRead);
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
                        String jsonResponse = "jdlkajhsdahs";
                        outToClient.write(jsonResponse.getBytes());
                        System.out.println("Sent to java server --> " + jsonResponse.toString());
                    case REGISTER_REQUEST:
                        try {
                            jsonResponse = userDAO.RegisterUserAsync((Users) request.getObject());
                            outToClient.write(jsonResponse.getBytes());
                        } catch (ExecutionException | InterruptedException e) {
                            e.printStackTrace();
                        }
                    case LOGIN_REQUEST:
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


package networking;

import Models.Users;
import Persistence.Repository.UserDAO.Implementation.UserDAOImpl;
import Util.Request;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.sql.Array;
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
                System.out.println("json response ...."+jsonResponse);


                ObjectMapper objectMapper = new ObjectMapper();
                request = objectMapper.readValue(jsonResponse, Request.class);


                UserDAOImpl userDAO = new UserDAOImpl();

                Gson gson = new Gson();
                String objJson= gson.toJson(request.getObject());


                switch (request.getEventType()) {
                    case PLACEHOLDER_REQUEST:
                        String jsonResponse = "Message passing test";

                        outToClient.write(jsonResponse.getBytes());
                        System.out.println("Sent to java server --> " + jsonResponse.toString());
                        break;
                    case PLACEHOLDER_REQUEST_REGISTER_USER:
                        Users userJs = gson.fromJson(objJson,Users.class);
                        userDAO.RegisterUserAsync(userJs);
                        break;
                    case PLACEHOLDER_REQUEST_LOGIN_USER:
                        String sLogin = request.getObject().toString();
                        System.out.println(sLogin);
                        String[] userPass = sLogin.split(" ");

                        Users userLoggedin = userDAO.ValidateUserAsync(userPass[1],userPass[3]);
                        outToClient.write(gson.toJson(userLoggedin).getBytes());
                        System.out.println(userLoggedin.getUsername().toString());
                        System.out.println("www");
                        break;

                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


package networking;

import Models.Branch;
import Models.Food;
import Models.Users;
import Persistence.Repository.BranchDAO.IBranchDAO;
import Persistence.Repository.BranchDAO.Implementation.BranchDAOImpl;
import Persistence.Repository.UserDAO.IUserDAO;
import Persistence.Repository.UserDAO.Implementation.UserDAOImpl;
import Util.Request;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import java.io.*;
import java.net.Socket;
import java.sql.SQLOutput;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ServerSocketHandler implements Runnable {
    private IUserDAO userDAO;
    private IBranchDAO branchDAO;
    private Socket socket;
    private OutputStream outToClient;
    private InputStream inFromClient;
    private String jsonResponse;

    public ServerSocketHandler(Socket socket) {
        String jsonResponse = new String();
        userDAO = new UserDAOImpl();
        branchDAO = new BranchDAOImpl();
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
                    case REGISTER_REQUEST:
                        Users userJs = gson.fromJson(objJson,Users.class);
                        userDAO.RegisterUserAsync(userJs);
                        break;
                    case LOGIN_REQUEST:
                        String sLogin = request.getObject().toString();
                        System.out.println(sLogin);
                        String[] userPass = sLogin.split(" ");

                        Users userLoggedin = (Users) userDAO.ValidateUserAsync(userPass[1],userPass[3]);
                        outToClient.write(gson.toJson(userLoggedin).getBytes());
                        break;
                    case BRANCHES_GET_REQUEST:
                        List<Branch> branches = branchDAO.GetAllBranches();
                        String j = gson.toJson(branches);
                        outToClient.write(j.getBytes());
                        break;
                    case BRANCH_GET_REQUEST:
                        int id = gson.fromJson(objJson, int.class);
                        Object b = branchDAO.GetBranchAsync(id);
                        outToClient.write(gson.toJson(b).getBytes());
                        break;
                    case BRANCH_CREATE_REQUEST:
                        Branch branch = gson.fromJson(objJson,Branch.class);
                        String s = branchDAO.AddBranchAsync(branch);
                        outToClient.write(gson.toJson(s).getBytes());
                        break;


                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


package networking;

import Models.*;
import Persistence.Repository.BranchDAO.IBranchDAO;
import Persistence.Repository.BranchDAO.Implementation.BranchDAOImpl;
import Persistence.Repository.FoodDAO.IFoodDAO;
import Persistence.Repository.FoodDAO.Implementation.FoodDAOImpl;
import Persistence.Repository.OrderDAO.IOrderDAO;
import Persistence.Repository.OrderDAO.Implementation.OrderDAOImpl;
import Persistence.Repository.OrderFoodDAO.IOrderFoodDAO;
import Persistence.Repository.OrderFoodDAO.Implementation.OrderFoodDAOImpl;
import Persistence.Repository.UserDAO.IUserDAO;
import Persistence.Repository.UserDAO.Implementation.UserDAOImpl;
import Util.Request;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;


import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ServerSocketHandler implements Runnable {
    private final IBranchDAO branchDAO;
    private final IFoodDAO foodDAO;
    private final IOrderDAO orderDAO;
    private final IOrderFoodDAO orderFoodDAO;
    private OutputStream outToClient;
    private InputStream inFromClient;

    public ServerSocketHandler(Socket socket) {
        String jsonResponse;
        IUserDAO userDAO = new UserDAOImpl();
        branchDAO = new BranchDAOImpl();
        foodDAO = new FoodDAOImpl();
        orderDAO = new OrderDAOImpl();
        orderFoodDAO = new OrderFoodDAOImpl();
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
                String jsonResponse1;
                do {
                    bytesRead = inFromClient.read(jsonByte);

                    jsonResponse1 = new String(jsonByte, 0, bytesRead);
                    System.out.println(jsonResponse1);
                }
                while (inFromClient.available() > 0);


                Request request;
                System.out.println("json response ...."+ jsonResponse1);


                ObjectMapper objectMapper = new ObjectMapper();
                request = objectMapper.readValue(jsonResponse1, Request.class);


                UserDAOImpl userDAO = new UserDAOImpl();

                Gson gson = new Gson();
                String objJson= gson.toJson(request.getObject());


                switch (request.getEventType()) {
                    case PLACEHOLDER_REQUEST:
                        String jsonResponse = "Message passing test";

                        outToClient.write(jsonResponse.getBytes());
                        System.out.println("Sent to java server --> " + jsonResponse);
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
                    case BRANCH_REMOVE_REQUEST:
                        int idBranch = gson.fromJson(objJson, int.class);
                        Object removeBranchAsync = branchDAO.RemoveBranchAsync(idBranch);
                        outToClient.write(gson.toJson(removeBranchAsync).getBytes());
                        break;
                    case BRANCH_CREATE_REQUEST:
                        Branch branch = gson.fromJson(objJson,Branch.class);
                        String s = branchDAO.AddBranchAsync(branch);
                        outToClient.write(gson.toJson(s).getBytes());
                        break;
                    case FOOD_GET_REQUEST:
                        List<Object> foods = foodDAO.GetFoodByBranchAsync();
                        outToClient.write(gson.toJson(foods).getBytes());
                        break;
                    case FOOD_ADD_REQUEST:
                        Food f = gson.fromJson(objJson,Food.class);
                        String s1 = foodDAO.AddFoodAsync(f);
                        outToClient.write(gson.toJson(s1).getBytes());
                        break;
                    case ORDER_ADD_REQUEST:
                        Orders o = gson.fromJson(objJson, Orders.class);
                        String s2 = orderDAO.AddOrderAsync(o);
                        outToClient.write(gson.toJson(s2).getBytes());
                        break;
                    case ORDER_GET_REQUEST:
                        String s3 = gson.fromJson(objJson, String.class);
                        Object o1 = orderDAO.GetOrderByUser(s3);
                        outToClient.write(gson.toJson(o1).getBytes());
                        break;
                    case ORDERFOOD_ADD_REQUEST:
                        OrderFood of = gson.fromJson(objJson, OrderFood.class);
                        String s4 = orderFoodDAO.AddOrderFoodAsync(of);
                        outToClient.write(gson.toJson(s4).getBytes());
                    case ORDERFOOD_GET_REQUEST:
                        List<Object> o2 = orderFoodDAO.GetOrderFoodByOrder();
                        outToClient.write(gson.toJson(o2).getBytes());
                        break;


                }

            }
        } catch (IOException | ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}


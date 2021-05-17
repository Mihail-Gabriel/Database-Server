package networking;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class DatabaseServer {

    //Future model initialization ->repository
    public DatabaseServer()
    {
    }

    public void startServer() throws IOException
    {
        ServerSocket serverSocket = new ServerSocket(2910);
        while(true)
        {
            try{
                System.out.println("Waiting for client...");
                Socket client = serverSocket.accept();
                ServerSocketHandler ssh = new ServerSocketHandler(client);
                new Thread(ssh).start();
                System.out.println("Client connected");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

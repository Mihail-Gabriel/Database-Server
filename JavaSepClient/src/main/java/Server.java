
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server
{
    public  void startServer() throws IOException {

        System.out.println("Opening server socket...");
        final int PORT = 4444;
        ServerSocket welcomeSocket = new ServerSocket(PORT);


        while(true)
        {
            try
            {
                System.out.println("Waiting for a connection..");

                Socket client = welcomeSocket.accept();

                Thread connectionThread = new Thread(new MessagesServer(client));
                connectionThread.start();
                System.out.println("Client connected");
            }

            catch(IOException ioe)
            {
                ioe.printStackTrace();
            }
        }
    }
}

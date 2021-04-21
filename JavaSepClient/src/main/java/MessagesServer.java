import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MessagesServer implements Runnable {

    private InputStream inFromSocket;
    private OutputStream outToSocket;
    private String message = new String("dfgsgf");
    private Socket socket;

    public MessagesServer(Socket socket) throws IOException {

        this.socket = socket;
        System.out.println("Connected!");

        try {
            inFromSocket = socket.getInputStream();
            outToSocket = socket.getOutputStream();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            byte[] jsonByte = new byte[256];
            int bytesRead;
            String jsonString = "";
            String jsonResponse = "";


            do {
                bytesRead = inFromSocket.read(jsonByte);
                jsonString += new String(jsonByte, 0, bytesRead);
            }
            while (inFromSocket.available() > 0);

            System.out.println("Client received: " + jsonString);


            switch (jsonString) {

                case "getmessage":
                    jsonResponse = message;
                    outToSocket.write(jsonResponse.getBytes());
                    System.out.println(jsonResponse);
                    outToSocket.flush();
                    break;

                    case "savemessage":
                    message = jsonString;
                    outToSocket.write(message.getBytes());
                    System.out.println(message);
                    outToSocket.flush();
                    break;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
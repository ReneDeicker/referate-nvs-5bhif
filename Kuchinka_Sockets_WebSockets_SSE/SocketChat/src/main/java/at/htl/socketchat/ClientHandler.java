package at.htl.socketchat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientHandler extends  Thread{
    Socket socket;
    PrintWriter out;
    BufferedReader in;
    String name;

    public ClientHandler(Socket s) throws IOException
    {
        socket = s;
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void run() {
        try {
            out.println("Username:");
            name = in.readLine();
            out.println("Connected to chat");
            String input;
            while(!(input = in.readLine()).equals("quit"))
            {
                ChatServer.sendMessage(name, input, LocalDateTime.now());
            }
            socket.close();
            ChatServer.quit(this);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void sendMessage(String userName, String message, LocalDateTime time) {
        out.println(time.format(DateTimeFormatter.ofPattern("HH:mm")) + " "+ userName + ": " + message);
    }


}


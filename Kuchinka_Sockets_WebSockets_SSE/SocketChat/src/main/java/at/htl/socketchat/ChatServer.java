package at.htl.socketchat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

public class ChatServer {

    private static List<ClientHandler> connectedClients;

    public static void main(String[] args){
        connectedClients = new LinkedList<>();
        try(ServerSocket listener = new ServerSocket(9001)){
            while(true){
                Socket socket = listener.accept();
                ClientHandler newClient = new ClientHandler(socket);
                connectedClients.add(newClient);
                newClient.setDaemon(true);
                newClient.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendMessage(String name, String message, LocalDateTime time){
        connectedClients.stream().forEach(client -> client.sendMessage(name, message, time));
        System.out.println(time.format(DateTimeFormatter.ofPattern("HH:mm")) + " "+ name + ": " + message);
    }

    public static void quit(ClientHandler client){
        connectedClients.remove(client);
    }
}

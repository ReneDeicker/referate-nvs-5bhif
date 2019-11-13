package at.htl.socketchat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClient {
    public static void main(String[] args) throws IOException {
        try(Socket s = new Socket("127.0.0.1", 9001);
            BufferedReader socketIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter socketOut = new PrintWriter(s.getOutputStream());
            BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in))
        ){
            new Thread(() -> {
                    while(!s.isClosed()) {
                        try {
                            System.out.println(socketIn.readLine());
                        } catch (IOException e){
                        }
                    }
            }).start();

            String temp = "";
            while(!temp.equals("quit")){
                temp = userIn.readLine();
                socketOut.println(temp);
                socketOut.flush();
            }
        }
    }
}

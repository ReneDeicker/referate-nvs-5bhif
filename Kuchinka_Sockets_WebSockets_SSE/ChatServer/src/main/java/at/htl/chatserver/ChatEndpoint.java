package at.htl.chatserver;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.websocket.EncodeException;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ServerEndpoint(value = "/chat/{username}", encoders = ChatMessageEncoder.class, decoders = ChatMessageDecoder.class)
public class ChatEndpoint {

    @OnOpen
    public void OnOpen(final Session session, @PathParam("username") final String userName) {
        session.getUserProperties().put("userName", userName);
    }

    @OnMessage
    public void OnMessage(String message, Session client) throws IOException, EncodeException {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setMessage(message);
        chatMessage.setUserName(client.getUserProperties().get("userName").toString());
        chatMessage.setTime(LocalDateTime.now());
        for(Session peer : client.getOpenSessions()){
            peer.getBasicRemote().sendObject(chatMessage);
        }
    }

}

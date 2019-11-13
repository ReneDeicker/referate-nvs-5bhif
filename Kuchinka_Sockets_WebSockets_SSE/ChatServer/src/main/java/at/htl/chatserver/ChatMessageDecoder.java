package at.htl.chatserver;

import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import java.io.StringReader;
import java.time.LocalDateTime;

public class ChatMessageDecoder implements Decoder.Text<ChatMessage> {
    @Override
    public void init(final EndpointConfig config) {
    }

    @Override
    public void destroy() {
    }

    @Override
    public ChatMessage decode(final String textMessage) throws DecodeException {
        ChatMessage chatMessage = new ChatMessage();
        JsonObject obj = Json.createReader(new StringReader(textMessage))
                .readObject();
        chatMessage.setMessage(obj.getString("message"));
        chatMessage.setUserName(obj.getString("userName"));
        chatMessage.setTime(LocalDateTime.now());
        return chatMessage;
    }

    @Override
    public boolean willDecode(final String s) {
        return true;
    }
}

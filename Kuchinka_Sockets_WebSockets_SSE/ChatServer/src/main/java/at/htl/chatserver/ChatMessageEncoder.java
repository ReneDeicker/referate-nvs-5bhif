package at.htl.chatserver;

import javax.json.Json;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import java.time.format.DateTimeFormatter;

public class ChatMessageEncoder implements Encoder.Text<ChatMessage> {
    @Override
    public void init(final EndpointConfig config) {
    }

    @Override
    public void destroy() {
    }

    @Override
    public String encode(final ChatMessage chatMessage) throws EncodeException {
        return Json.createObjectBuilder()
                .add("message", chatMessage.getMessage())
                .add("userName", chatMessage.getUserName())
                .add("time", chatMessage.getTime().format(DateTimeFormatter.ofPattern("HH:mm"))).build()
                .toString();
    }
}

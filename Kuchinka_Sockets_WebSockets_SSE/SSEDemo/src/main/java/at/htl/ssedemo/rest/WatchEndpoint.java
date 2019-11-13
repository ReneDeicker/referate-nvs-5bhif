package at.htl.ssedemo.rest;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.sse.OutboundSseEvent;
import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseBroadcaster;
import javax.ws.rs.sse.SseEventSink;

@Path("/watch")
@ApplicationScoped
public class WatchEndpoint {
    @Context
    private Sse sse;
    private SseBroadcaster sseBroadcaster;

    @PostConstruct
    public void init() {
        this.sseBroadcaster = sse.newBroadcaster();
    }

    @GET
    @Path("/connect")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public void register(@Context SseEventSink eventSink) {
        eventSink.send(sse.newEvent("Welcome!"));
        sseBroadcaster.register(eventSink);
    }

    public void broadcastMessage(String message){
        OutboundSseEvent event = sse.newEventBuilder()
                .name("time")
                .mediaType(MediaType.TEXT_PLAIN_TYPE)
                .data(message)
                .build();
        sseBroadcaster.broadcast(event);
    }

}

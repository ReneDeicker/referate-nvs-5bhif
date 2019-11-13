package at.htl.ssedemo.business;

import at.htl.ssedemo.rest.WatchEndpoint;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Singleton
@Startup
public class WatchHandler {

    private ScheduledExecutorService executorService;

    @Inject
    WatchEndpoint watchEndpoint;

    @PostConstruct
    public void init(){
        executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleAtFixedRate(() -> broadcastTime(), 1, 1, TimeUnit.SECONDS);
    }

    public void broadcastTime(){
        watchEndpoint.broadcastMessage(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
    }
}

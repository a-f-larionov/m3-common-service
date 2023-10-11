package m3.common;

import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@Slf4j
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void readyEvent() {
        log.warn("Ready to survive ⭐!");
    }

    @EventListener(ApplicationFailedEvent.class)
    public void failedEvent() {
        log.warn("Failed to run  \uD83E\uDD2F");
    }

    @PreDestroy
    public void preDestroy(){
        log.warn("Pre destroy state ⚰");
    }
}
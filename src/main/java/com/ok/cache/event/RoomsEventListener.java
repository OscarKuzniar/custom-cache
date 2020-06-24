package com.ok.cache.event;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RoomsEventListener {

    @Async
    @EventListener
    public void onFetchingRoomsUpdateEvent(@NonNull RoomsUpdateEvent event) {
        log.info(event.toString());
    }
}

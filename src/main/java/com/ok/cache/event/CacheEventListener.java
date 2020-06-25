package com.ok.cache.event;

import com.ok.cache.core.RoomCache;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CacheEventListener {

    private final RoomCache cache;

    @Async
    @EventListener
    public void onWrite(@NonNull RoomsUpdateEvent event) {
        cache.refresh(event.getNotification());
    }
}

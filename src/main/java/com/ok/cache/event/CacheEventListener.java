package com.ok.cache.event;

import com.ok.cache.core.RoomCache;
import com.ok.cache.dto.RoomDeltaElement;
import com.ok.cache.dto.RoomDeltaResponse;
import com.ok.cache.service.DeltaService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CacheEventListener {

    private final DeltaService deltaService;
    private final RoomCache cache;

    @Async
    @EventListener
    public void onWrite(@NonNull RoomsUpdateEvent event) {
        cache.refresh(event.getNotification());
    }


    /** Not implemented, written to prepare for cron update **/
    @Async
    @EventListener
    public void onInterval(@NonNull RoomsIntervalUpdateEvent event) {
        List<RoomDeltaElement> roomDeltaElements = deltaService.getModifiedRooms();
    }
}

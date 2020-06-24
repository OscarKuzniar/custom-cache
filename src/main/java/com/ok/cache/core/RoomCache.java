package com.ok.cache.core;

import com.ok.cache.domain.Room;
import com.ok.cache.dto.RoomChangeValue;
import com.ok.cache.dto.RoomsChangeNotification;
import com.ok.cache.repository.RoomDataSource;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Slf4j
@ToString(of = "cache")
public class RoomCache {

    private Map<RequestKey, Set<Room>> cache;

    private final RoomDataSource dataSource;

    public RoomCache(RoomDataSource dataSource) {
        this.dataSource = dataSource;
        invoke();
    }

    private void invoke() {
        this.cache = new HashMap<>();
        this.cache.put(null, new HashSet<>());
        dataSource.findAll().forEach(
                e -> this.cache.get(null).add(e)
        );
    }

    public void refresh(RoomsChangeNotification notification) {
        Map<ActionKey, List<Room>> modificationMap = new HashMap<>();
        for (RoomChangeValue roomChange : notification.getValue()) {
            RefreshContext context = new RefreshContext(this.cache, modificationMap, roomChange, this.dataSource);
            context.refresh();
        }
        log.info("Cache state after notification was processed: " + this.cache);
    }
}

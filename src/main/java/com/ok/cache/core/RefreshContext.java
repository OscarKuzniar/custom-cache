package com.ok.cache.core;

import com.ok.cache.domain.Room;
import com.ok.cache.dto.RoomChangeValue;
import com.ok.cache.repository.RoomDataSource;

import java.util.List;
import java.util.Map;
import java.util.Set;

class RefreshContext {

    private final RefreshStrategy strategy;

    RefreshContext (Map<RequestKey, Set<Room>> cache,
                    Map<ActionKey, List<Room>> changeMap,
                    RoomChangeValue roomChange,
                    RoomDataSource dataSource) {
        if (roomChange.getChangeType().equalsIgnoreCase("CREATED")) {
            this.strategy = new CreateStrategy(cache, changeMap, roomChange, dataSource);
        } else if (roomChange.getChangeType().equalsIgnoreCase("UPDATED")) {
            this.strategy = new UpdateStrategy(cache, changeMap, roomChange, dataSource);
        } else {
            this.strategy = new DeleteStrategy(cache, changeMap, roomChange);
        }
    }

    void refresh() {
        strategy.refresh();
    }
}

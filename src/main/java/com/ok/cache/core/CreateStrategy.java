package com.ok.cache.core;

import com.ok.cache.domain.Room;
import com.ok.cache.dto.RoomChangeValue;
import com.ok.cache.repository.RoomDataSource;

import java.util.*;

class CreateStrategy implements RefreshStrategy {

    private final Map<RequestKey, Set<Room>> cachedValues;

    private final Map<ActionKey, List<Room>> changeMap;

    private final RoomChangeValue roomChange;

    private final RoomDataSource dataSource;

    public CreateStrategy(Map<RequestKey, Set<Room>> cache, Map<ActionKey, List<Room>> changeMap, RoomChangeValue roomChange, RoomDataSource dataSource) {
        this.cachedValues = cache;
        this.changeMap = changeMap;
        this.roomChange = roomChange;
        this.dataSource = dataSource;
    }

    @Override
    public void refresh() {
        Room room = dataSource.find(roomChange.getResourceData().getId()).orElseThrow(IllegalArgumentException::new);
        this.cachedValues.get(null).add(room);
        this.changeMap.putIfAbsent(ActionKey.CREATED, new ArrayList<>());
        this.changeMap.get(ActionKey.CREATED).add(room);
    }
}

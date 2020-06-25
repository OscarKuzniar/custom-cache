package com.ok.cache.core;

import com.ok.cache.domain.Room;
import com.ok.cache.dto.RoomChangeValue;
import com.ok.cache.repository.RoomDataSource;

import java.util.*;
import java.util.stream.Collectors;

class UpdateStrategy implements RefreshStrategy {

    private final Map<RequestKey, Set<Room>> cachedValues;

    private final Map<ActionKey, List<Room>> changeMap;

    private final RoomChangeValue roomChange;

    private final RoomDataSource dataSource;

    public UpdateStrategy(Map<RequestKey, Set<Room>> cache, Map<ActionKey, List<Room>> changeMap, RoomChangeValue roomChange, RoomDataSource dataSource) {
        this.cachedValues = cache;
        this.changeMap = changeMap;
        this.roomChange = roomChange;
        this.dataSource = dataSource;
    }
    @Override
    public void refresh() {
        var keysToUpdate = cachedValues.entrySet().stream()
                .filter(e -> e.getValue().contains(Room.builder().id(roomChange.getResourceData().getId()).build()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        keysToUpdate.forEach(
                e -> {
                    Room room = dataSource.find(roomChange.getResourceData().getId()).orElseThrow(IllegalArgumentException::new);
                    cachedValues.get(e).remove(Room.builder().id(roomChange.getResourceData().getId()).build());
                    cachedValues.get(e).add(room);
                    this.changeMap.putIfAbsent(ActionKey.UPDATED, new ArrayList<>());
                    this.changeMap.get(ActionKey.UPDATED).add(room);
                }
        );
    }
}

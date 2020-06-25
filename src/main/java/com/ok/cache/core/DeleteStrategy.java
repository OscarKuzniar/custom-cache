package com.ok.cache.core;

import com.ok.cache.domain.Room;
import com.ok.cache.dto.RoomChangeValue;

import java.util.*;
import java.util.stream.Collectors;

class DeleteStrategy implements RefreshStrategy {

    private final Map<RequestKey, Set<Room>> cachedValues;

    private final Map<ActionKey, List<Room>> changeMap;

    private final RoomChangeValue roomChange;


    public DeleteStrategy(Map<RequestKey, Set<Room>> cache, Map<ActionKey, List<Room>> changeMap, RoomChangeValue roomChange) {
        this.cachedValues = cache;
        this.changeMap = changeMap;
        this.roomChange = roomChange;
    }

    @Override
    public void refresh() {
        var keysToUpdate = cachedValues.entrySet().stream()
                .filter(e -> e.getValue().contains(Room.builder().id(roomChange.getResourceData().getId()).build()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        keysToUpdate.forEach(
                e -> {
                    cachedValues.get(e).remove(Room.builder().id(roomChange.getResourceData().getId()).build());
                    this.changeMap.putIfAbsent(ActionKey.DELETED, new ArrayList<>());
                    this.changeMap.get(ActionKey.DELETED).add(Room.builder().id(roomChange.getResourceData().getId()).build());
                }
        );
    }
}

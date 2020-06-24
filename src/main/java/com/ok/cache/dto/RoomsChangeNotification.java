package com.ok.cache.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;

@Builder
@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RoomsChangeNotification {

    @Builder.Default
    List<RoomChangeValue> value = new ArrayList<>();
}

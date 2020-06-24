package com.ok.cache.dto;

import com.ok.cache.core.ActionKey;
import com.ok.cache.domain.Room;
import lombok.Builder;
import lombok.ToString;
import lombok.Value;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ToString
@Builder
@Value
public class CachingResponse {

    @Builder.Default
    Map<ActionKey, List<Room>> updatedCachedRooms = new HashMap<>();
}

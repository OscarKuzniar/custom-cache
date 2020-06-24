package com.ok.cache.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;

@Builder
@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RoomDeltaResponse {

    @JsonProperty("@odata.context")
    String context;

    @JsonProperty("@odata.deltaLink")
    String deltaLink;

    @Builder.Default
    @JsonProperty("value")
    List<RoomDeltaElement> values = new ArrayList<>();
}

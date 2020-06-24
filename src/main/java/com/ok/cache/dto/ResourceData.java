package com.ok.cache.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ResourceData {

    String id;

    @JsonProperty("@odata.type")
    String dataType;

    @JsonProperty("@odata.id")
    String dataId;

    @JsonProperty("@odata.etag")
    String dataEtag;
}

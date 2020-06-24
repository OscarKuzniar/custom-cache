package com.ok.cache.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.time.Instant;

@Builder
@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RoomChangeValue {

    String id;
    int sequenceNumber;
    String subscriptionId;
    Instant subscriptionExpirationDateTime;
    String clientState;
    String changeType;
    String resource;
    String tenantId;
    ResourceData resourceData;
}

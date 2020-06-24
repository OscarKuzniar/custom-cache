package com.ok.cache.event;

import com.ok.cache.dto.RoomsChangeNotification;
import lombok.ToString;
import lombok.Value;

@Value
@ToString
public class RoomsUpdateEvent {

    RoomsChangeNotification notification;
}

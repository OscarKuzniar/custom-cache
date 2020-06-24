package com.ok.cache.repository;

import com.ok.cache.domain.Room;
import com.ok.cache.dto.RoomDeltaResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public interface RoomDataSource {

    /**
     * Mocked data
     **/
    public List<Room> rooms = fill();

    RoomDeltaResponse getDeltaRooms(String delta);

    /**
     * MOCK
     **/
    default Optional<Room> find(String id) {
        Optional<Room> room = rooms.stream()
                .filter(e -> e.getId().equalsIgnoreCase(id))
                .findFirst();

        //Adds dumb data as in our context, room with given id should always be in data source, because we were notified
        //about this
        if (room.isEmpty()) {
            Room dumbRoom = Room.builder()
                    .id(id)
                    .capacity(Math.random())
                    .location(String.valueOf(Math.random()))
                    .isFree(LocalDateTime.now().getSecond() % 2 == 0)
                    .build();
            rooms.add(dumbRoom);
            return Optional.of(dumbRoom);
        }
        return room;
    }

    default List<Room> findAll() {
        return new ArrayList<>(rooms);
    }

    private static List<Room> fill() {
        List<Room> rooms = new ArrayList<>();
        rooms.add(
                Room.builder()
                        .id("1111")
                        .capacity(10.0)
                        .location("Warsaw")
                        .isFree(true)
                        .build()
        );
        rooms.add(
                Room.builder()
                        .id("2222")
                        .capacity(20.0)
                        .location("Wroclaw")
                        .isFree(false)
                        .build()
        );
        rooms.add(
                Room.builder()
                        .id("3333")
                        .capacity(30.0)
                        .location("Rzeszow")
                        .isFree(true)
                        .build()
        );
        return new ArrayList<>(rooms);
    }
}

package com.ok.cache.service;

import com.ok.cache.dto.RoomDeltaElement;
import com.ok.cache.dto.RoomDeltaResponse;
import com.ok.cache.repository.RoomDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DeltaService {

    private final RoomDataSource roomDataSource;

    private String deltaUrl = "";

    public List<RoomDeltaElement> getModifiedRooms() {
        RoomDeltaResponse response = roomDataSource.getDeltaRooms(this.deltaUrl);
        this.deltaUrl = response.getDeltaLink();
        return response.getValues();
    }
}

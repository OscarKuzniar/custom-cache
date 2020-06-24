package com.ok.cache.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ok.cache.dto.RoomDeltaResponse;
import com.ok.cache.event.ApplicationRoomEventInterceptor;
import com.ok.cache.repository.RoomDataSource;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class Config implements WebMvcConfigurer {

    private final ApplicationEventPublisher publisher;

    private static final List<String> ROOM_EVENT_TRACKED_URLS = List.of("/notifications");

    @Bean
    ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        return mapper;
    }

    @Bean
    ApplicationRoomEventInterceptor roomInterceptor() {
        return new ApplicationRoomEventInterceptor(publisher, objectMapper());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(roomInterceptor()).addPathPatterns(
                new ArrayList<>(ROOM_EVENT_TRACKED_URLS)
        );
    }

    @Bean
    RoomDataSource roomDataSource() {
        return delta -> RoomDeltaResponse.builder().build();
    }
}

package com.ok.cache.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ok.cache.dto.RoomsChangeNotification;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@AllArgsConstructor
public class RoomEventInterceptor extends HandlerInterceptorAdapter {

    ApplicationEventPublisher publisher;
    ObjectMapper objectMapper;

    private static final String POST = "POST";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getMethod().equalsIgnoreCase(POST)) {
            try {
                String requestBody = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
                RoomsChangeNotification notification = objectMapper.readValue(requestBody, RoomsChangeNotification.class);
                publisher.publishEvent(new RoomsUpdateEvent(notification));
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}

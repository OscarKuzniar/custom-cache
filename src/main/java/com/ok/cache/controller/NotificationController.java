package com.ok.cache.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("notifications")
public class NotificationController {

    @PostMapping
    public ResponseEntity<?> notifications() {
        return ResponseEntity.accepted().build();
    }
}

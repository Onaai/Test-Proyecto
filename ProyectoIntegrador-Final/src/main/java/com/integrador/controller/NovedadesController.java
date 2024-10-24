package com.integrador.controller;

import com.integrador.entity.User;
import com.integrador.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NovedadesController {

    @Autowired
    private UserService userService;

    @PostMapping("/novedades/seen")
    public ResponseEntity<Void> marcarNovedadesComoVistas(@AuthenticationPrincipal User user) {
        userService.markNovedadesAsSeen(user);
        return ResponseEntity.ok().build();
    }
}

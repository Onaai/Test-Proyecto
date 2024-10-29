package com.integrador.controller;

import com.integrador.entity.Notificacion;
import com.integrador.entity.User;
import com.integrador.service.NotificacionService;
import com.integrador.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class NotificacionController {

    @Autowired
    private NotificacionService notificacionService;

    @Autowired
    private UserService userService;  // Para obtener el usuario autenticado

    @GetMapping("/notificaciones/contar")
    public ResponseEntity<Map<String, Integer>> contarNotificaciones(@AuthenticationPrincipal User user) {
        int count = notificacionService.contarNotificacionesNoLeidas(user);
        Map<String, Integer> response = new HashMap<>();
        response.put("count", count);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/notificaciones/marcar-como-leidas")
    public ResponseEntity<Void> marcarComoLeidas(@AuthenticationPrincipal User user) {
        notificacionService.marcarNotificacionesComoLeidas(user);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/notificaciones")
    public ResponseEntity<List<Notificacion>> obtenerNotificacionesNoLeidas(@AuthenticationPrincipal User user) {
        List<Notificacion> notificaciones = notificacionService.obtenerNotificacionesNoLeidas(user);
        return ResponseEntity.ok(notificaciones);
    }
}

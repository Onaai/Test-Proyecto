package com.integrador.controller;

import com.integrador.service.NotificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
public class NotificacionController {

    @Autowired
    private NotificacionService notificacionService; // Asegúrate de tener el servicio correspondiente

    @GetMapping("/notificaciones/contar")
    public ResponseEntity<Map<String, Integer>> contarNotificaciones() {
        int count = notificacionService.contarNotificacionesNoLeidas(); // Implementar este método en el servicio
        Map<String, Integer> response = new HashMap<>();
        response.put("count", count);
        return ResponseEntity.ok(response);
    }
}

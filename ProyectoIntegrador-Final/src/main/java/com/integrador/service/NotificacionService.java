package com.integrador.service;

import com.integrador.entity.User;
import org.springframework.stereotype.Service;

@Service
public class NotificacionService {

    // Este método debe implementar la lógica de contar las notificaciones no leídas del usuario actual
    public int contarNotificacionesNoLeidas() {
        // Aquí deberías implementar la lógica para consultar cuántas notificaciones no ha visto el usuario
        // Ejemplo: return notificacionRepository.countByUsuarioAndLeidoFalse(user);
        return 5; // Valor de ejemplo
    }

}
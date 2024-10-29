package com.integrador.service;

import com.integrador.entity.Comentario;
import com.integrador.entity.Notificacion;
import com.integrador.entity.User;
import com.integrador.repository.NotificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificacionService {

    @Autowired
    private NotificacionRepository notificacionRepository;

    public void notificarLike(Comentario comentario, User quienLikeo) {
        String mensaje = quienLikeo.getUsername() + " dio me gusta a tu comentario '"
                + comentario.getTexto() + "' en el libro '"
                + comentario.getLibro().getTitulo() + "'";
        Notificacion notificacion = new Notificacion();
        notificacion.setMensaje(mensaje);
        notificacion.setReceptor(comentario.getUser());  // El receptor es el autor del comentario
        notificacion.setLeido(false);  // Inicialmente no leída
        notificacion.setFechaNotificacion(LocalDateTime.now());

        notificacionRepository.save(notificacion);
    }


    // Contar las notificaciones no leídas del usuario actual
    public int contarNotificacionesNoLeidas(User user) {
        return notificacionRepository.countByReceptorAndLeidoFalse(user);
    }

    // Marcar todas las notificaciones como leídas
    public void marcarNotificacionesComoLeidas(User user) {
        List<Notificacion> notificaciones = notificacionRepository.findByReceptorAndLeidoFalse(user);
        for (Notificacion notificacion : notificaciones) {
            notificacion.setLeido(true);
        }
        notificacionRepository.saveAll(notificaciones);
    }

    public List<Notificacion> obtenerNotificacionesNoLeidas(User user) {
        return notificacionRepository.findByReceptorAndLeidoFalse(user);
    }

}

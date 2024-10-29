package com.integrador.repository;

import com.integrador.entity.Notificacion;
import com.integrador.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {

    // Consulta las notificaciones no leídas de un usuario
    List<Notificacion> findByReceptorAndLeidoFalse(User receptor);

    // Cuenta las notificaciones no leídas de un usuario
    int countByReceptorAndLeidoFalse(User receptor);
}

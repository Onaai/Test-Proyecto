package com.integrador.service;

import com.integrador.entity.User;
import com.integrador.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Método para verificar si el usuario ha visto las novedades
    public boolean hasSeenNovedades(User user) {
        return user.isVistoNovedades();
    }

    // Método para marcar las novedades como vistas
    public void markNovedadesAsSeen(User user) {
        user.setVistoNovedades(true);
        userRepository.save(user);
    }
}

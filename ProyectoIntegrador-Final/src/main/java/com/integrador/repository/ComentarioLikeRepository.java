package com.integrador.repository;

import com.integrador.entity.Comentario;
import com.integrador.entity.ComentarioLike;
import com.integrador.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ComentarioLikeRepository extends JpaRepository<ComentarioLike, Long> {
    Optional<ComentarioLike> findByComentarioAndUser(Comentario comentario, User user);
    boolean existsByComentarioAndUser(Comentario comentario, User user);
}

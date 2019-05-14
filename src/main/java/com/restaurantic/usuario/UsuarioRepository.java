package com.restaurantic.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByUsername (String username);
    List<Usuario> findAll();
    void delete(Usuario usuario);
}

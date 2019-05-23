package com.restaurantic.usuario;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    private UsuarioRepository usuarioRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UsuarioController(UsuarioRepository usuarioRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/sign-up")
    public Usuario signUp(@RequestBody Usuario usuario) {
        usuario.setPassword(this.bCryptPasswordEncoder.encode(usuario.getPassword()));
        Usuario usuarioDb = usuarioRepository.save(usuario);
        if (usuarioDb != null) {
            usuarioDb.setPassword("$encrypted");
        }
        return usuarioDb;
    }

    @PostMapping("/sign-in")
    public String signIn(@RequestBody Usuario usuario) {
        Usuario usuarioDb = usuarioRepository.findByUsername(usuario.getUsername());
        boolean match = false;
        if (usuarioDb != null) {
            if (this.bCryptPasswordEncoder.matches(usuario.getPassword(), usuarioDb.getPassword())) {
                return "success";
            }
            return "failed";
        }
        return "failed";
    }

    @PutMapping("/{username}")
    public void updateOne(@PathVariable String username, @RequestBody Usuario usuario) {
        Usuario usuarioDb = usuarioRepository.findByUsername(username);
        if (usuarioDb != null) {
            if (usuario.getPassword() != null) {
                usuarioDb.setPassword(this.bCryptPasswordEncoder.encode(usuario.getPassword()));
            }
            usuarioDb.setRoles(usuario.getRoles());
            usuarioDb.setNombre(usuario.getNombre());
            usuarioDb.setTelefono(usuario.getTelefono());
            usuarioDb.setEmail(usuario.getEmail());
            usuarioRepository.save(usuarioDb);
        }
    }

    @GetMapping("/{username}")
    public Usuario getUsuario(@PathVariable String username) {
        Usuario usuario = usuarioRepository.findByUsername(username);
        if (usuario != null) {
            usuario.setPassword("$encrypted");
        }
        return usuario;
    }

    @GetMapping()
    public List<Usuario> getAll() {
        List<Usuario> usuarios = this.usuarioRepository.findAll();
        for (Usuario usuario: usuarios) {
            usuario.setPassword("$crypted");
        }

        return usuarios;
    }

    @DeleteMapping("/{username}")
    public void deleteOne(@PathVariable String username) {
        Usuario usuario = this.usuarioRepository.findByUsername(username);
        if (usuario != null) {
           this.usuarioRepository.delete(usuario);
        }
    }
}

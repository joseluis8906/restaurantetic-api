package com.restaurantic.usuario;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

  private UsuarioRepository usuarioRepository;
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  public UsuarioController (UsuarioRepository usuarioRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
    this.usuarioRepository = usuarioRepository;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }

  @PostMapping("/sign-up")
  public void signUp (@RequestBody Usuario usuario) {
    usuario.setPassword(this.bCryptPasswordEncoder.encode(usuario.getPassword()));
    usuarioRepository.save(usuario);
  }

  @PostMapping("/sign-in")
  public String signIn (@RequestBody Usuario usuario) {
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

  @GetMapping("/{username}")
  public Usuario getUsuario (@PathVariable String username) {
    Usuario usuario = usuarioRepository.findByUsername(username);
    if (usuario != null) {
      usuario.setPassword("$encrypted");
    }
    return usuario;
  }
}

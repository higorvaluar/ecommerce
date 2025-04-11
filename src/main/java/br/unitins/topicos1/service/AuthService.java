//package br.unitins.topicos1.service;
//
//import br.unitins.topicos1.model.Perfil;
//import br.unitins.topicos1.model.Usuario;
//import br.unitins.topicos1.repository.UsuarioRepository;
//import jakarta.enterprise.context.ApplicationScoped;
//import jakarta.inject.Inject;
//
//@ApplicationScoped
//public class AuthService {
//
//    @Inject
//    UsuarioRepository repository;
//
//    @Inject
//    TokenService tokenService;
//
//    public String login(String email, String senha) {
//        Usuario usuario = repository.findByEmailAndSenha(email, senha);
//        if (usuario == null || !usuario.getPerfil().equals(Perfil.ADMIN)) {
//            throw new RuntimeException("Login ou perfil inválido");
//        }
//        return tokenService.generateJwt(usuario);
//    }
//}
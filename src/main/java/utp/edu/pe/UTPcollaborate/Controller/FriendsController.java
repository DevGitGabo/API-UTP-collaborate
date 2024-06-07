package utp.edu.pe.UTPcollaborate.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.UTPcollaborate.Domain.Usuario.DTOInfoUsuario;
import utp.edu.pe.UTPcollaborate.Domain.Usuario.DTOListadoUsuarios;
import utp.edu.pe.UTPcollaborate.Domain.Usuario.UsuarioService;

import java.util.List;

@RestController
@RequestMapping("/friends")
public class FriendsController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/search")
    public ResponseEntity<List<DTOListadoUsuarios>> buscarUsuarios(@RequestParam String query) {
        List<DTOListadoUsuarios> usuarios = usuarioService.findBySearchTerm(query);
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTOInfoUsuario> getUsuarioById(@PathVariable Long id) {
        DTOInfoUsuario dtoInfoUsuario = usuarioService.getUsuarioDTOById(id);
        return ResponseEntity.ok(dtoInfoUsuario);
    }

    @GetMapping("/recomended")
    public ResponseEntity<List<DTOListadoUsuarios>> usuariosRecomendados(@RequestParam Long userId) {
        List<DTOListadoUsuarios> recomendados = usuarioService.getRecomendedUsuarios(userId);
        return ResponseEntity.ok(recomendados);
    }
}

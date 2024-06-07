package utp.edu.pe.UTPcollaborate.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.UTPcollaborate.Domain.Usuario.DTOEditarPerfil;
import utp.edu.pe.UTPcollaborate.Domain.Usuario.DTOPerfil;
import utp.edu.pe.UTPcollaborate.Domain.Usuario.UsuarioService;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/{id}")
    public ResponseEntity<DTOPerfil> getPerfilUserById(@PathVariable Long id){
        DTOPerfil dtoPerfil = usuarioService.getPerfilUserById(id);
        return ResponseEntity.ok(dtoPerfil);
    }

    @PutMapping
    public ResponseEntity<?> updateDtoPerfil(@RequestBody DTOEditarPerfil datos){
        usuarioService.updatePerfil(datos.idUsuario(),datos);
        return ResponseEntity.ok("Perfil actualizado correctamente");
    }

}

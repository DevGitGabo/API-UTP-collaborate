package utp.edu.pe.UTPcollaborate.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utp.edu.pe.UTPcollaborate.Domain.Usuario.DTOEditarPerfil;
import utp.edu.pe.UTPcollaborate.Domain.Usuario.DTOLogin;
import utp.edu.pe.UTPcollaborate.Domain.Usuario.Usuario;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/login")
public class AuthenticationController {
    @PostMapping
    public ResponseEntity<?> autenticarUsuario(@RequestBody DTOLogin datosLogin){
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}

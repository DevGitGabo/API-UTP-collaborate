package utp.edu.pe.UTPcollaborate.Domain.Usuario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Table(name = "usuario")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_usuario;
    private String nombre;
    private String apellido;
    private String email;
    private String contrasena;
    private String etiquetas;
    private String intereses;
    private String descripccion;
    private boolean perfilCompletado;
    private String codigoUsuario;
    private String imagen_perfil;
    private String carrera;
    private Long num_publicaciones;
    private Long seguidores;
    private Long seguidos;
    private Long num_mvp;
    private boolean visible_utpet;
}

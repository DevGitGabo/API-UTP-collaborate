package utp.edu.pe.UTPcollaborate.Domain.Publicacion;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import utp.edu.pe.UTPcollaborate.Domain.Usuario.Usuario;

import java.time.LocalDateTime;
import java.util.Date;

@Table(name = "publicacion")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Publicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_publicacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
    private String descripcion;
    private String imagen;
    private Long num_interacciones;
    private LocalDateTime fecha_publicacion;
}


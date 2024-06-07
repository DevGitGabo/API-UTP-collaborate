package utp.edu.pe.UTPcollaborate.Domain.MiembroGrupo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import utp.edu.pe.UTPcollaborate.Domain.Grupo.Grupo;
import utp.edu.pe.UTPcollaborate.Domain.Usuario.Usuario;

import java.time.LocalDateTime;
import java.util.Date;

@Table(name = "miembrogrupo")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MiembroGrupo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_miembro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_grupo")
    private Grupo grupo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    private String rol;
    private Date fecha_union;

    // Agregar a la base de datos
    private Boolean status;
}

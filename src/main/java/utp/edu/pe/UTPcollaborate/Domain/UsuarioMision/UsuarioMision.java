package utp.edu.pe.UTPcollaborate.Domain.UsuarioMision;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import utp.edu.pe.UTPcollaborate.Domain.Mision.Mision;
import utp.edu.pe.UTPcollaborate.Domain.Usuario.Usuario;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuariomision")
public class UsuarioMision {
    @EmbeddedId
    private UsuarioMisionId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", insertable = false, updatable = false)
    @MapsId("usuario")
    private Usuario user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_mision", insertable = false, updatable = false)
    @MapsId("mision")
    private Mision mision;
    private Date fecha_completado;
}

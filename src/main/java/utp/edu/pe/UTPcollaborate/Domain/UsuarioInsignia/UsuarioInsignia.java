package utp.edu.pe.UTPcollaborate.Domain.UsuarioInsignia;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import utp.edu.pe.UTPcollaborate.Domain.Insignia.Insignia;
import utp.edu.pe.UTPcollaborate.Domain.Usuario.Usuario;
import utp.edu.pe.UTPcollaborate.Domain.UsuarioMision.UsuarioMisionId;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuarioinsignia")
public class UsuarioInsignia {
    @EmbeddedId
    private UsuarioInsigniaId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", insertable = false, updatable = false)
    @MapsId("usuario")
    private Usuario user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_insignia", insertable = false, updatable = false)
    @MapsId("insignia")
    private Insignia insignia;

    private Date fecha_obtencion;
}

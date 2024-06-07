package utp.edu.pe.UTPcollaborate.Domain.UsuarioInsignia;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class UsuarioInsigniaId implements Serializable {
    private Long usuario;
    private Long insignia;
}

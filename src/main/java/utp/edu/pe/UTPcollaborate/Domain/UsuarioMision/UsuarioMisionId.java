package utp.edu.pe.UTPcollaborate.Domain.UsuarioMision;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class UsuarioMisionId implements Serializable {
    private Long usuario;
    private Long mision;
}

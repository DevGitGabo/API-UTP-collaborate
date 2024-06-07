package utp.edu.pe.UTPcollaborate.Domain.DetalleCurso;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import utp.edu.pe.UTPcollaborate.Domain.Curso.Curso;
import utp.edu.pe.UTPcollaborate.Domain.Usuario.Usuario;

@Table(name = "detallecurso")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetalleCurso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_detalle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_curso")
    private Curso curso;
    private String estado;
}

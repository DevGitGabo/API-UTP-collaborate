package utp.edu.pe.UTPcollaborate.Domain.Grupo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import utp.edu.pe.UTPcollaborate.Domain.Curso.Curso;

import java.time.LocalDateTime;
import java.util.Date;

@Table(name = "grupo")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Grupo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_grupo;

    @ManyToOne
    @JoinColumn(name = "id_curso")
    private Curso id_curso;
    private String nombre_grupo;
    private String descripcion;
    private Date fecha_creacion;
    private String tarea;
    private String etiquetas;
}

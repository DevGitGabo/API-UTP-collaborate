package utp.edu.pe.UTPcollaborate.Domain.Mision;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "mision")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mision {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_mision;
    private String nombre;
    private String descripcion;
    private String recompensa;
}

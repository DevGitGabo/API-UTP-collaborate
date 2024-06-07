package utp.edu.pe.UTPcollaborate.Domain.Insignia;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "insignia")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Insignia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_insignia;
    private String nombre;
    private String descripcion;
    private String imagen_url;
}

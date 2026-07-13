package com.example.tienda.model;

import java.sql.Date;
import jakarta.persistence.*;
import lombok.*;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "tienda")
public class Tienda extends RepresentationModel<Tienda> {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_tienda;

    @Column(name = "nombre_tienda", nullable = false)
    @NotEmpty
    @NotNull
    @Size(min = 2, max = 100, message = "Error nombre de tienda no valido")
    private String nombre_tienda;

    @Column(name = "ubicacion", nullable = false)
    @NotEmpty
    @NotNull
    @Size(min = 2, max = 100, message = "Error ubicacion no valida")
    private String ubicacion;

    private Date horario_apertura;

    @NotEmpty
    @NotNull
    @Size(min = 2, max = 100, message = "Error politicas no validas")
    private String politicas;

    //Tienda tambien es un caso especial ya que
}

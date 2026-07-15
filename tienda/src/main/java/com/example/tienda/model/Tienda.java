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
    private int id_tienda;

    @Column(name = "nombre_tienda", nullable = false)
    private String nombre_tienda;

    @Column(name = "ubicacion", nullable = false)
    private String ubicacion;

    @Column(name = "horario_apertura")
    private Date horario_apertura;

    @Column(name = "politicas")
    private String politicas;

    //Tienda tambien es un caso especial ya que
}

package com.example.proveedor.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "proveedor")
public class Proveedor extends RepresentationModel<Proveedor> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_proveedor;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "contacto", nullable = false)
    private String contacto;

    //Es un caso especial porque
}

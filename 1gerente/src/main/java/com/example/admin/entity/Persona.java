package com.ejemplo.persona.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * En microservicios NO usamos @ManyToOne entre entidades de servicios distintos.
 * Cada microservicio tiene su propia base de datos.
 *
 * En lugar de una FK de JPA, guardamos solo el generoId (Long).
 * La relación real se resuelve en tiempo de ejecución llamando a ms-genero via Feign.
 */
@Entity
@Table(name = "persona")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 10, max = 12)
    @Column(nullable = false, unique = true, length = 12)
    private String rut;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String nombre;

    @Min(0) @Max(120)
    @Column(nullable = false)
    private int edad;

    /**
     * Solo guardamos el ID del género.
     * No existe anotación @ManyToOne porque Genero vive en otro microservicio.
     */
    @Column(name = "genero_id", nullable = false)
    private Long generoId;
}

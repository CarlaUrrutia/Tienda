package com.example.gerente.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Valid
@Entity
@Table(name = "tienda")
public class Tienda {
    @NotNull(message = "El id no debe ser nulo")
    @NotEmpty(message = "el id no debe ser nulo")
    @Min(value = 0)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_tienda;
    @Column(name = "nombre_tienda",nullable = false)
    @NotEmpty
    @NotNull
    @Size(min = 2, max = 100, message = "Error nombre de tienda no valido")
    private String nombre_tienda;
    @Column(name = "ubicacion",nullable = false)
    @NotEmpty
    @NotNull
    @Size(min = 2, max = 100, message = "Error ubicacion no valida")
    private String ubicacion;
    /* debarible, es una fecha o es una hora */
    private Date horario_aprertura;
    @NotEmpty
    @NotNull
    @Size(min = 2, max = 100, message = "Error politicas no validas")
    private String politicas;
    /*clave foranea aqui o eso creo */
    @OneToMany(mappedBy = "id_tienda")           // Relación inversa
    @Column(name = "id_empleado", nullable = false)
    private Empleado id_empleado;
}

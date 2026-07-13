package com.example.tienda.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
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

    @Column(name = "id_empleado", nullable = false)
    private Integer id_empleado;
}
package com.example.empleado.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Valid
@Data
@Entity
@Table(name = "empleado")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_empleado;

    @Column(name = "nombre", nullable = false)
    @NotEmpty
    @NotNull
    @Size(min = 2, max = 100, message = "Error nombre no valido")
    private String nombre;

    @Column(name = "apellido", nullable = false)
    @NotEmpty
    @NotNull
    @Size(min = 2, max = 100, message = "Error apellido no valido")
    private String apellido;

    @Column(name = "sueldo", nullable = false)
    @NotNull(message = "El sueldo no debe ser nulo")
    @Min(value = 0)
    private Integer sueldo;

    @Column(name = "id_rol", nullable = false)
    private Integer id_rol;

    @Column(name = "id_tienda", nullable = false)
    private Integer id_tienda;
}
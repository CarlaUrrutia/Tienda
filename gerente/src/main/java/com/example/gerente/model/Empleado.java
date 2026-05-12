package com.example.gerente.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
@Table(name = "empleado")
@Entity
public class Empleado {
    @NotNull(message = "El id no debe ser nulo")
    @NotEmpty(message = "el id no debe ser nulo")
    @Min(value = 0)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_empleado;
    @Column(name = "nombre",nullable = false)
    @NotEmpty
    @NotNull
    @Size(min = 2, max = 100, message = "Error nombre no valido")
    private String nombre;
    @Column(name = "apellido",nullable = false)
    @NotEmpty
    @NotNull
    @Size(min = 2, max = 100, message = "Error apellido no valido")
    private String apellido;
    @Column(name = "sueldo",nullable = false)
    @NotNull(message = "El sueldo no debe ser nulo")
    @NotEmpty(message = "el sueldo no  debe ser nulo")
    @Min(value = 0)
    private int sueldo;
    @ManyToOne
    @Column(name = "id_tienda", nullable = false)
    private Tienda id_tienda;
    
}

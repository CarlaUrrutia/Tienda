package com.example.gerente.model;

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
@Table(name = "proveedor")
public class Proveedor {
    @NotNull(message = "El id no debe ser nulo")
    @NotEmpty(message = "el id no debe ser nulo")
    @Min(value = 0)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_proveedor;
    @NotEmpty
    @NotNull
    @Size(min = 2, max = 100, message = "Error nombre no valido")
    @Column(name = "nombre",nullable = false)
    private String nombre;
    @NotEmpty
    @NotNull
    @Size(min = 2, max = 100, message = "Error contacto no valido")
    @Column(name = "contacto", nullable = false)
    private String contacto;
}

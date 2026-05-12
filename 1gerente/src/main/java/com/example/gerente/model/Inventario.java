package com.example.gerente.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Valid
@Data
@Entity
@Table(name = "inventario")
public class Inventario {

    @NotNull(message = "El id no debe ser nulo")
    @NotEmpty(message = "el id no debe ser nulo")
    @Min(value = 0)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull(message = "La Cantidad no debe ser nulo")
    @NotEmpty(message = "La Cantidad no debe ser nulo")
    @Min(value = 0)
    @Column(name = "cantidad",nullable = false)
    private int cantidad;
    /*claves foranias */
    @ManyToOne
    @Column(name = "id_producto", nullable = false)
    private Producto id_producto;
    /*clavesh */
    @ManyToOne
    @Column(name = "id_tienda",nullable = false)
    private Tienda id_tienda;
}

package com.example.gerente.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
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
@Table(name = "boleta")
public class Boleta {
    @NotNull(message = "El id no debe ser nulo")
    @NotEmpty(message = "el id no debe ser nulo")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_boleta;
    
    /*que coincidan las claves foraneas */
    @ManyToOne
    @Column(name = "id_venta",nullable = false)
    private Venta id_venta;
}

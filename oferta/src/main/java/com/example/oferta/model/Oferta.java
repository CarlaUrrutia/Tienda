package com.example.oferta.model;

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
@Table(name = "oferta")
public class Oferta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "descripcion", nullable = false)
    @NotEmpty
    @NotNull
    @Size(min = 2, max = 100, message = "Error descripcion no valida")
    private String descripcion;

    @Column(name = "descuento", nullable = false)
    @NotNull(message = "El descuento no debe ser nulo")
    @Min(value = 0)
    private Integer descuento;

    @Column(name = "id_producto", nullable = false)
    private Integer id_producto;
}
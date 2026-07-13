package com.example.cupon.model;

import java.sql.Date;

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

@NoArgsConstructor
@AllArgsConstructor
@Valid
@Data
@Entity
@Table(name = "cupon")
public class Cupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_cupon;

    @Column(name = "codigo", nullable = false)
    @NotEmpty
    @NotNull
    @Size(min = 2, max = 50, message = "Error codigo no valido")
    private String codigo;

    @Column(name = "descuento", nullable = false)
    @NotNull(message = "El descuento no debe ser nulo")
    @Min(value = 0)
    private Integer descuento;

    @Column(name = "fecha_expiracion")
    private Date fecha_expiracion;

    @Column(name = "id_cliente", nullable = false)
    private Integer id_cliente;
}
package com.example.cupon.model;

import java.sql.Date;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "cupon")
public class Cupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_cupon;

    @Column(name = "codigo", nullable = false)
    private String codigo;

    @Column(name = "descuento", nullable = false)
    private int descuento;

    @Column(name = "fecha_expiracion")
    private Date fecha_expiracion;

    @Column(name = "id_cliente", nullable = false)
    private int id_cliente;
}

package com.example.venta.model;

import java.sql.Date;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "venta")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_venta;

    @Column(name = "fecha_venta", nullable = false)
    private Date fecha_venta;

    @Column(name = "id_cliente", nullable = false)
    private int id_cliente;

    @Column(name = "id_empleado", nullable = false)
    private int id_empleado;
}

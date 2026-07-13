
-- Estructura de tabla para la tabla `detalle_venta`

CREATE TABLE IF NOT EXISTS detalle_venta (
    id_detalle int(11) NOT NULL,
    cantidad int(11) NOT NULL,
    precio_unitario_venta int(11) DEFAULT NULL,
    id_venta int(11) NOT NULL,
    id_producto int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

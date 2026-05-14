
-- Estructura de tabla para la tabla `cupon`

CREATE TABLE IF NOT EXISTS cupon (
    id_cupon int(11) NOT NULL,
    codigo varchar(50) NOT NULL,
    descuento int(11) NOT NULL,
    fecha_expiracion date DEFAULT NULL,
    id_cliente int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

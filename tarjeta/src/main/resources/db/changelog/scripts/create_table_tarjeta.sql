
-- Estructura de tabla para la tabla `tarjeta`

CREATE TABLE IF NOT EXISTS tarjeta  (
    id_tarjeta  int(11) NOT NULL,
    tipo char(1) NOT NULL,
    id_cliente  int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

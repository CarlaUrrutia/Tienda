
-- Estructura de tabla para la tabla `boleta`

CREATE TABLE IF NOT EXISTS boleta (
    id_boleta int(11) NOT NULL,
    id_cliente int(11) NOT NULL,
    id_venta int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- Estructura de tabla para la tabla `oferta`

CREATE TABLE IF NOT EXISTS oferta  (
    id_oferta  int(11) NOT NULL,
    descripcion  varchar(100) NOT NULL,
    descuento  int(11) NOT NULL,
    id_producto  int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

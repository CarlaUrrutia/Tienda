
-- Estructura de tabla para la tabla `tienda`

CREATE TABLE IF NOT EXISTS tienda  (
    id_tienda  int(11) NOT NULL,
    nombre_tienda  varchar(100) NOT NULL,
    ubicacion  varchar(100) NOT NULL,
    horario_apertura  date DEFAULT NULL,
    politicas  varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


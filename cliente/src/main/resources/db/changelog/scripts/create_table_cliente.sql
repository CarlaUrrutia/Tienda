
-- Estructura de tabla para la tabla `cliente`

CREATE TABLE IF NOT EXISTS cliente (
    id_cliente int(11) NOT NULL,
    nombre varchar(100) NOT NULL,
    apellido varchar(100) NOT NULL,
    email varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

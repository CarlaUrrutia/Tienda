
-- Estructura de tabla para la tabla `empleado`

CREATE TABLE IF NOT EXISTS empleado (
    id_empleado int(11) NOT NULL,
    nombre varchar(100) NOT NULL,
    apellido varchar(100) NOT NULL,
    sueldo int(11) NOT NULL,
    id_tienda int(11) NOT NULL,
    id_rol int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


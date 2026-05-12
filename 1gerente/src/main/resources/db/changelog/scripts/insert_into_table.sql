-- Volcado de datos para la tabla `boleta`

INSERT INTO `boleta` (`id_boleta`, `id_cliente`, `id_venta`) VALUES
(1, 1, 1),
(2, 2, 2),
(3, 3, 3),
(4, 4, 4),
(5, 5, 5),
(6, 6, 6),
(7, 7, 7),
(8, 8, 8),
(9, 9, 9),
(10, 10, 10),
(11, 11, 11),
(12, 12, 12),
(13, 13, 13),
(14, 14, 14),
(15, 15, 15);

-- Volcado de datos para la tabla `ciudad`

INSERT INTO `ciudad` (`id_ciudad`, `nombre`, `id_region`) VALUES
(1, 'Santiago', 1),
(2, 'Viña del Mar', 2),
(3, 'Concepción', 3),
(4, 'Temuco', 4),
(5, 'Talca', 5),
(6, 'Rancagua', 6),
(7, 'Puerto Montt', 7),
(8, 'La Serena', 8),
(9, 'Antofagasta', 9),
(10, 'Copiapó', 10),
(11, 'Iquique', 11),
(12, 'Arica', 12),
(13, 'Valdivia', 13),
(14, 'Coyhaique', 14),
(15, 'Punta Arenas', 15);

-- Volcado de datos para la tabla `cliente`

INSERT INTO `cliente` (`id_cliente`, `nombre`, `apellido`, `email`) VALUES
(1, 'Roberto', 'Alvarez', 'roberto@gmail.com'),
(2, 'Carla', 'Muñoz', 'carla@gmail.com'),
(3, 'Sebastián', 'Castro', 'sebastian@gmail.com'),
(4, 'Fernanda', 'Vargas', 'fernanda@gmail.com'),
(5, 'Nicolás', 'Romero', 'nicolas@gmail.com'),
(6, 'Paulina', 'Silva', 'paulina@gmail.com'),
(7, 'Tomás', 'Contreras', 'tomas@gmail.com'),
(8, 'Isabel', 'Medina', 'isabel@gmail.com'),
(9, 'Cristóbal', 'Fuentes', 'cristobal@gmail.com'),
(10, 'Bárbara', 'Espinoza', 'barbara@gmail.com'),
(11, 'Ignacio', 'Vega', 'ignacio@gmail.com'),
(12, 'Constanza', 'Ramos', 'constanza@gmail.com'),
(13, 'Alejandro', 'Pizarro', 'alejandro@gmail.com'),
(14, 'Marcela', 'Navarro', 'marcela@gmail.com'),
(15, 'Francisco', 'Ibáñez', 'francisco@gmail.com');

-- Volcado de datos para la tabla `cupon`

INSERT INTO `cupon` (`id_cupon`, `codigo`, `descuento`, `fecha_expiracion`, `id_cliente`) VALUES
(1, 'CUP001', 10, '2026-06-01', 1),
(2, 'CUP002', 15, '2026-07-01', 2),
(3, 'CUP003', 20, '2026-08-01', 3),
(4, 'CUP004', 5, '2026-05-15', 4),
(5, 'CUP005', 25, '2026-09-01', 5),
(6, 'CUP006', 10, '2026-06-15', 6),
(7, 'CUP007', 30, '2026-10-01', 7),
(8, 'CUP008', 15, '2026-07-15', 8),
(9, 'CUP009', 20, '2026-08-15', 9),
(10, 'CUP010', 5, '2026-05-30', 10),
(11, 'CUP011', 10, '2026-06-30', 11),
(12, 'CUP012', 25, '2026-09-15', 12),
(13, 'CUP013', 15, '2026-07-30', 13),
(14, 'CUP014', 20, '2026-08-30', 14),
(15, 'CUP015', 30, '2026-10-15', 15);

-- Volcado de datos para la tabla `detalle_venta`

INSERT INTO `detalle_venta` (`id_detalle`, `cantidad`, `precio_unitario_venta`, `id_venta`, `id_producto`) VALUES
(1, 2, 3500, 1, 1),
(2, 3, 4200, 2, 2),
(3, 1, 6800, 3, 3),
(4, 4, 2900, 4, 4),
(5, 2, 12000, 5, 5),
(6, 1, 8500, 6, 6),
(7, 3, 5500, 7, 7),
(8, 2, 7200, 8, 8),
(9, 1, 18000, 9, 9),
(10, 2, 25000, 10, 10),
(11, 3, 9500, 11, 11),
(12, 5, 3200, 12, 12),
(13, 1, 15000, 13, 13),
(14, 4, 2800, 14, 14),
(15, 2, 4900, 15, 15);

-- Volcado de datos para la tabla `devolucion`

INSERT INTO `devolucion` (`id_devolucion`, `fecha_devolucion`, `motivo`, `monto_reembolso`, `cantidad_devuelta`, `id_empleado`, `id_cliente`, `id_tarjeta`, `id_venta`, `id_producto`) VALUES
(1, '2026-01-10', 'Producto defectuoso', 3500, 1, 1, 1, 1, 1, 1),
(2, '2026-01-15', 'No era lo esperado', 4200, 1, 2, 2, 2, 2, 2),
(3, '2026-01-20', 'Producto dañado', 6800, 1, 3, 3, 3, 3, 3),
(4, '2026-01-25', 'Alergia al producto', 2900, 2, 4, 4, 4, 4, 4),
(5, '2026-02-05', 'Falla en material', 12000, 1, 5, 5, 5, 5, 5),
(6, '2026-02-12', 'Color incorrecto', 8500, 1, 6, 6, 6, 6, 6),
(7, '2026-02-18', 'Producto incompleto', 5500, 1, 7, 7, 7, 7, 7),
(8, '2026-02-22', 'No funciona', 7200, 1, 8, 8, 8, 8, 8),
(9, '2026-03-05', 'Defecto de fábrica', 18000, 1, 9, 9, 9, 9, 9),
(10, '2026-03-12', 'Producto equivocado', 25000, 1, 10, 10, 10, 10, 10),
(11, '2026-03-18', 'No compatible', 9500, 1, 11, 11, 11, 11, 11),
(12, '2026-03-22', 'Envase roto', 3200, 2, 12, 12, 12, 12, 12),
(13, '2026-04-03', 'Mal estado', 15000, 1, 13, 13, 13, 13, 13),
(14, '2026-04-12', 'No era lo pedido', 2800, 2, 14, 14, 14, 14, 14),
(15, '2026-04-18', 'Producto vencido', 4900, 1, 15, 15, 15, 15, 15);o

-- Volcado de datos para la tabla `empleado`

INSERT INTO `empleado` (`id_empleado`, `nombre`, `apellido`, `sueldo`, `id_tienda`) VALUES
(1, 'Carlos', 'González', 550000, 1),
(2, 'María', 'Pérez', 480000, 2),
(3, 'Juan', 'Rodríguez', 620000, 3),
(4, 'Ana', 'López', 510000, 4),
(5, 'Pedro', 'Martínez', 590000, 5),
(6, 'Sofía', 'García', 470000, 6),
(7, 'Luis', 'Hernández', 630000, 7),
(8, 'Valentina', 'Díaz', 500000, 8),
(9, 'Diego', 'Sánchez', 560000, 9),
(10, 'Camila', 'Torres', 490000, 10),
(11, 'Andrés', 'Flores', 610000, 11),
(12, 'Javiera', 'Reyes', 520000, 12),
(13, 'Matías', 'Morales', 580000, 13),
(14, 'Daniela', 'Jiménez', 475000, 14),
(15, 'Felipe', 'Rojas', 640000, 15);

-- Volcado de datos para la tabla `envio`

INSERT INTO `envio` (`id_envio`, `fecha_envio`, `fecha_estimada_entrega`, `estado`, `direccion_destino`, `id_venta`, `id_cliente`, `id_empleado`, `id_ciudad`) VALUES
(1, '2026-01-05', '2026-01-08', 'Entregado', 'Av. Libertador 101, Santiago', 1, 1, 1, 1),
(2, '2026-01-10', '2026-01-13', 'Entregado', 'Av. Marina 201, Viña del Mar', 2, 2, 2, 2),
(3, '2026-01-15', '2026-01-18', 'Entregado', 'Calle Barros Arana 301, Concepción', 3, 3, 3, 3),
(4, '2026-01-20', '2026-01-23', 'Entregado', 'Av. Alemania 401, Temuco', 4, 4, 4, 4),
(5, '2026-02-01', '2026-02-04', 'Entregado', 'Calle 1 Sur 501, Talca', 5, 5, 5, 5),
(6, '2026-02-10', '2026-02-13', 'Entregado', 'Av. O´Higgins 601, Rancagua', 6, 6, 6, 6),
(7, '2026-02-15', '2026-02-18', 'En tránsito', 'Av. Ibáñez 701, Puerto Montt', 7, 7, 7, 7),
(8, '2026-02-20', '2026-02-23', 'En tránsito', 'Av. Aguirre 801, La Serena', 8, 8, 8, 8),
(9, '2026-03-01', '2026-03-04', 'En tránsito', 'Av. Balmaceda 901, Antofagasta', 9, 9, 9, 9),
(10, '2026-03-10', '2026-03-13', 'Procesando', 'Av. Arturo Prat 1001, Iquique', 10, 10, 10, 11),
(11, '2026-03-15', '2026-03-18', 'Procesando', 'Av. San Martín 1101, Arica', 11, 11, 11, 12),
(12, '2026-03-20', '2026-03-23', 'Procesando', 'Calle Arauco 1201, Valdivia', 12, 12, 12, 13),
(13, '2026-04-01', '2026-04-04', 'Pendiente', 'Av. Baquedano 1301, Coyhaique', 13, 13, 13, 14),
(14, '2026-04-10', '2026-04-13', 'Pendiente', 'Av. Colón 1401, Punta Arenas', 14, 14, 14, 15),
(15, '2026-04-15', '2026-04-18', 'Pendiente', 'Av. Copayapu 1501, Copiapó', 15, 15, 15, 10);

-- Volcado de datos para la tabla `factura`

INSERT INTO `factura` (`id_factura`, `fecha`, `total`, `id_venta`, `id_cliente`) VALUES
(1, '2026-01-05', 7000, 1, 1),
(2, '2026-01-10', 12600, 2, 2),
(3, '2026-01-15', 6800, 3, 3),
(4, '2026-01-20', 11600, 4, 4),
(5, '2026-02-01', 24000, 5, 5),
(6, '2026-02-10', 8500, 6, 6),
(7, '2026-02-15', 16500, 7, 7),
(8, '2026-02-20', 14400, 8, 8),
(9, '2026-03-01', 18000, 9, 9),
(10, '2026-03-10', 50000, 10, 10),
(11, '2026-03-15', 28500, 11, 11),
(12, '2026-03-20', 16000, 12, 12),
(13, '2026-04-01', 15000, 13, 13),
(14, '2026-04-10', 11200, 14, 14),
(15, '2026-04-15', 9800, 15, 15);

-- Volcado de datos para la tabla `inventario`

INSERT INTO `inventario` (`id_inventario`, `cantidad`, `id_producto`, `id_tienda`) VALUES
(1, 50, 1, 1),
(2, 30, 2, 2),
(3, 45, 3, 3),
(4, 60, 4, 4),
(5, 25, 5, 5),
(6, 40, 6, 6),
(7, 55, 7, 7),
(8, 70, 8, 8),
(9, 35, 9, 9),
(10, 20, 10, 10),
(11, 65, 11, 11),
(12, 48, 12, 12),
(13, 38, 13, 13),
(14, 80, 14, 14),
(15, 42, 15, 15);

-- Volcado de datos para la tabla `oferta`

INSERT INTO `oferta` (`id_oferta`, `descripcion`, `descuento`, `id_producto`) VALUES
(1, 'Oferta de verano', 10, 1),
(2, 'Liquidación de invierno', 20, 2),
(3, 'Promo fin de semana', 15, 3),
(4, 'Descuento especial', 25, 4),
(5, 'Oferta relámpago', 30, 5),
(6, 'Promo navidad', 20, 6),
(7, 'Descuento aniversario', 15, 7),
(8, 'Oferta exclusiva web', 10, 8),
(9, 'Promo Black Friday', 40, 9),
(10, 'Descuento por temporada', 20, 10),
(11, 'Oferta nocturna', 15, 11),
(12, 'Promo de lanzamiento', 25, 12),
(13, 'Descuento por volumen', 10, 13),
(14, 'Oferta especial cliente', 20, 14),
(15, 'Promo fin de mes', 15, 15);

-- Volcado de datos para la tabla `producto`

INSERT INTO `producto` (`id_producto`, `nombre`, `precio_venta`, `id_proveedor`) VALUES
(1, 'Bolsa Reutilizable', 3500, 1),
(2, 'Jabón Orgánico', 4200, 2),
(3, 'Shampoo Natural', 6800, 3),
(4, 'Cepillo Bambú', 2900, 4),
(5, 'Botella Acero Inox', 12000, 5),
(6, 'Crema Hidratante Eco', 8500, 6),
(7, 'Tote Bag Algodón', 5500, 7),
(8, 'Vela Soja Aromática', 7200, 8),
(9, 'Kit Compostaje', 18000, 9),
(10, 'Filtro Agua', 25000, 10),
(11, 'Aceite Esencial', 9500, 11),
(12, 'Semillas Orgánicas', 3200, 12),
(13, 'Difusor Aromas', 15000, 13),
(14, 'Papel Reciclado', 2800, 14),
(15, 'Limpiador Multiuso', 4900, 15);

-- Volcado de datos para la tabla `proveedor`

INSERT INTO `proveedor` (`id_proveedor`, `nombre`, `contacto`) VALUES
(1, 'Distribuidora Norte', 'contacto@norte.cl'),
(2, 'Proveedor Sur', 'info@sur.cl'),
(3, 'Comercial Central', 'ventas@central.cl'),
(4, 'Importadora Andina', 'andina@import.cl'),
(5, 'Distribuciones Pacífico', 'pacifico@dist.cl'),
(6, 'Proveedor Express', 'express@prov.cl'),
(7, 'Suministros Globales', 'global@sum.cl'),
(8, 'Abastecedora Chile', 'chile@abast.cl'),
(9, 'Comercial Austral', 'austral@com.cl'),
(10, 'Distribuidora Polar', 'polar@dist.cl'),
(11, 'Proveedores Unidos', 'unidos@prov.cl'),
(12, 'Importaciones del Sur', 'sur@import.cl'),
(13, 'Comercio Andino', 'andino@com.cl'),
(14, 'Suministros Rápidos', 'rapidos@sum.cl'),
(15, 'Distribuciones Elite', 'elite@dist.cl');

-- Volcado de datos para la tabla `region`

INSERT INTO `region` (`id_region`, `nombre`) VALUES
(1, 'Metropolitana'),
(2, 'Valparaíso'),
(3, 'Biobío'),
(4, 'Araucanía'),
(5, 'Maule'),
(6, 'O´Higgins'),
(7, 'Los Lagos'),
(8, 'Coquimbo'),
(9, 'Antofagasta'),
(10, 'Atacama'),
(11, 'Tarapacá'),
(12, 'Arica y Parinacota'),
(13, 'Los Ríos'),
(14, 'Aysén'),
(15, 'Magallanes');

-- Volcado de datos para la tabla `rol`

INSERT INTO `rol` (`id_rol`, `nombre_rol`, `id_empleado`) VALUES
(1, 'Gerente', 1),
(2, 'Cajero', 2),
(3, 'Vendedor', 3),
(4, 'Bodeguero', 4),
(5, 'Supervisor', 5),
(6, 'Cajero', 6),
(7, 'Gerente', 7),
(8, 'Vendedor', 8),
(9, 'Bodeguero', 9),
(10, 'Cajero', 10),
(11, 'Supervisor', 11),
(12, 'Vendedor', 12),
(13, 'Gerente', 13),
(14, 'Cajero', 14),
(15, 'Bodeguero', 15);

-- Volcado de datos para la tabla `tarjeta`

INSERT INTO `tarjeta` (`id_tarjeta`, `tipo`, `id_cliente`) VALUES
(1, 'D', 1),
(2, 'C', 2),
(3, 'D', 3),
(4, 'C', 4),
(5, 'D', 5),
(6, 'C', 6),
(7, 'D', 7),
(8, 'C', 8),
(9, 'D', 9),
(10, 'C', 10),
(11, 'D', 11),
(12, 'C', 12),
(13, 'D', 13),
(14, 'C', 14),
(15, 'D', 15);

-- Volcado de datos para la tabla `tienda`

INSERT INTO `tienda` (`id_tienda`, `nombre_tienda`, `ubicacion`, `horario_apertura`, `politicas`) VALUES
(1, 'Tienda Santiago Centro', 'Av. Libertador 100', '2020-01-15', 'No devoluciones sin boleta'),
(2, 'Tienda Viña del Mar', 'Av. Marina 200', '2019-06-01', 'Cambios en 15 días'),
(3, 'Tienda Concepción', 'Calle Barros Arana 300', '2021-03-10', 'Devolución en 30 días'),
(4, 'Tienda Temuco', 'Av. Alemania 400', '2018-11-20', 'Solo cambios con etiqueta'),
(5, 'Tienda Talca', 'Calle 1 Sur 500', '2022-05-05', 'No devoluciones en ofertas'),
(6, 'Tienda Rancagua', 'Av. O´Higgins 600', '2017-08-15', 'Cambios en 7 días'),
(7, 'Tienda Puerto Montt', 'Av. Presidente Ibáñez 700', '2020-09-01', 'Devolución con boleta'),
(8, 'Tienda La Serena', 'Av. Francisco de Aguirre 800', '2019-12-10', 'Cambios en 10 días'),
(9, 'Tienda Antofagasta', 'Av. Balmaceda 900', '2021-07-20', 'No devoluciones sin empaque'),
(10, 'Tienda Iquique', 'Av. Arturo Prat 1000', '2018-04-05', 'Cambios en 20 días'),
(11, 'Tienda Arica', 'Av. Comandante San Martín 1100', '2022-02-15', 'Devolución en 15 días'),
(12, 'Tienda Valdivia', 'Calle Arauco 1200', '2017-10-01', 'Solo cambios'),
(13, 'Tienda Coyhaique', 'Av. Baquedano 1300', '2020-06-20', 'Cambios en 5 días'),
(14, 'Tienda Punta Arenas', 'Av. Colón 1400', '2019-03-10', 'Devolución en 7 días'),
(15, 'Tienda Copiapó', 'Av. Copayapu 1500', '2021-11-05', 'No devoluciones en liquidación');

-- Volcado de datos para la tabla `venta`

INSERT INTO `venta` (`id_venta`, `fecha_venta`, `id_cliente`, `id_empleado`) VALUES
(1, '2026-01-05', 1, 1),
(2, '2026-01-10', 2, 2),
(3, '2026-01-15', 3, 3),
(4, '2026-01-20', 4, 4),
(5, '2026-02-01', 5, 5),
(6, '2026-02-10', 6, 6),
(7, '2026-02-15', 7, 7),
(8, '2026-02-20', 8, 8),
(9, '2026-03-01', 9, 9),
(10, '2026-03-10', 10, 10),
(11, '2026-03-15', 11, 11),
(12, '2026-03-20', 12, 12),
(13, '2026-04-01', 13, 13),
(14, '2026-04-10', 14, 14),
(15, '2026-04-15', 15, 15);

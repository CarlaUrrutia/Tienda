package com.example.devolucion.DTO;

public class DevolucionDTO {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {

        @NotBlank(message = "El id de la devolucion es obligatoria")
        @Min(value = 0)
        private int  id_devolucion;

        @NotBlank(message = "El motivo es obligatorio")
        @Pattern(regexp = "^[\\w\\sáéíóúÁÉÍÓÚñÑ]{2,}\\s+[\\w\\sáéíóúÁÉÍÓÚñÑ]{2,}.*$",
                 message = "El motivo de la devolución debe contener al menos 2 palabras")
        private String motivo;
        
        @NotBlank(message = "El monto del reembolso del es obligatorio")
        @Min(value = 0)
        private int  monto_reembolso;

        @NotBlank(message = "La cantidad devuelta de la devolucion es obligatoria")
        @Min(value = 0)
        private int  cantidad_devuelta;

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private int id_devolucion;
        private String motivo;
        private int monto_reembolso;
        private int cantidad_devuelta;
        private TiendaDTO tienda; 
        private EmpleadoDTO empleado;
        private ClienteDTO cliente;
        private TarjetaDTO tarjeta;
        private VentaDTO venta;
        private ProductoDTO producto;

    }
}

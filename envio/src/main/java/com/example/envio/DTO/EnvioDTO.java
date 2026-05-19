package com.example.envio.DTO;


public class EnvioDTO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {

        @Min(value = 0, message = "El id del envio no puede ser negativo")
        private int id_envio;

        @NotBlank(message = "El estado del envio es obligatorio")
        @Pattern(regexp = "^[\\w\\sáéíóúÁÉÍÓÚñÑ]{2,}\\s+[\\w\\sáéíóúÁÉÍÓÚñÑ]{2,}.*$",
                 message = "El estado del envio debe contener al menos 2 palabras")
        private String descripcion;

        @NotBlank(message = "La direccion del destino  es obligatoria")
        @Pattern(regexp = "^[\\w\\sáéíóúÁÉÍÓÚñÑ]{2,}\\s+[\\w\\sáéíóúÁÉÍÓÚñÑ]{2,}.*$",
                 message = "La direccion del destino debe contener al menos 2 palabras")
        private String direccion_destino;
        
    }

    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private int id_envio;
        private String descripcion;
        private String direccion_destino;
        private ClienteDTO cliente ; // clave foranea
        private VentaDTO venta; //clave foranea
        private EmpleadoDTO empleado;
        
    }
}

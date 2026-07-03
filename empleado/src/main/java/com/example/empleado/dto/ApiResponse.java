package com.example.empleado.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Estructura estándar de respuesta de la API")
public class ApiResponse<T> {

    @Schema(description = "Código HTTP de la respuesta", example = "200")
    private int status;

    @Schema(description = "Indica si la operación fue exitosa", example = "true")
    private boolean success;

    @Schema(description = "Mensaje descriptivo del resultado", example = "Empleado creado exitosamente")
    private String message;

    @Schema(description = "Datos retornados por la operación")
    private T data;

    @Builder.Default
    @Schema(description = "Fecha y hora de la respuesta", example = "2025-06-22T10:00:00")
    private String timestamp = LocalDateTime.now().toString();

    @Schema(description = "Links HATEOAS relacionados al recurso")
    private Map<String, String> links;

    @Schema(description = "Errores de validación por campo (solo en 400)")
    private Map<String, String> errors;

    public static <T> ApiResponse<T> notFound(String message) {
        return ApiResponse.<T>builder()
                .status(404).success(false)
                .message(message)
                .build();
    }

    public static <T> ApiResponse<T> badRequest(String message, Map<String, String> errors) {
        return ApiResponse.<T>builder()
                .status(400).success(false)
                .message(message).errors(errors)
                .build();
    }
}
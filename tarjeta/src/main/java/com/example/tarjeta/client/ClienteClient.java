package com.example.tarjeta.client;

import com.example.venta.dto.ClienteResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Feign Client que llama al microservicio cliente.
 * Cuando VentaService necesita los datos del cliente,
 * Feign hace automáticamente GET http://localhost:8082/api/clientes/{id}
 */
@FeignClient(name = "cliente", url = "${ms.cliente.url}")
public interface ClienteClient {

    @GetMapping("/api/clientes/{id}")
    ClienteResponse getClienteById(@PathVariable("id") Integer id);
}

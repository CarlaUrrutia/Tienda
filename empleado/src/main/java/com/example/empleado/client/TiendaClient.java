package com.example.empleado.client;

import com.example.empleado.DTO.TiendaResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "tienda", url = "${ms.tienda.url}")
public interface TiendaClient {
    @GetMapping("/api/tiendas/{id}")
    TiendaResponse getTiendaById(@PathVariable("id") Integer id);
}

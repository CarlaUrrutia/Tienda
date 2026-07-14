package com.example.inventario.client;

import com.example.inventario.DTO.TiendaResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "tienda", url = "${ms.tienda.url}")
public interface TiendaClient {
    @GetMapping("/api/tiendas/{id}")
    TiendaResponse getTiendaById(@PathVariable("id") Integer id);
}

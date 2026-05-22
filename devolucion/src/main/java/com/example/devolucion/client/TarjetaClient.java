package com.example.devolucion.client;

import com.example.devolucion.dto.TarjetaResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "tarjeta", url = "${ms.tarjeta.url}")
public interface TarjetaClient {
    @GetMapping("/api/tarjetas/{id}")
    TarjetaResponse getTarjetaById(@PathVariable("id") Integer id);
}

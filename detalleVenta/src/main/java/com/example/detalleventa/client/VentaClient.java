package com.example.detalleventa.client;

import com.example.detalleventa.dto.VentaResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "venta", url = "${ms.venta.url}")
public interface VentaClient {
    @GetMapping("/api/ventas/{id}")
    VentaResponse getVentaById(@PathVariable("id") Integer id);
}

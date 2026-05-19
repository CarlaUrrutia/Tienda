package com.example.devolucion.service;

import com.example.devolucion.model.Devolucion;
import java.util.List;

public interface DevolucionService {
    List<Devolucion> getAllDevoluciones();
    Devolucion getDevolucionById(Integer id);
    Devolucion save(Devolucion devolucion);
    Devolucion updateDevolucion(Integer id, Devolucion devolucion);
    void delete(Integer id);
}

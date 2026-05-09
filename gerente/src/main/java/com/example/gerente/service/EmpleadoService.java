package com.example.gerente.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gerente.model.Empleado;
import com.example.gerente.repository.EmpleadoRepository;

@Service
public class EmpleadoService {
    @Autowired EmpleadoRepository empleadoRepository;

    public List<Empleado> getAllEmpleados (){
        return empleadoRepository.findAll();
    }

    public Empleado getEmpleadoById (Integer id){
        List<Empleado> empleados = empleadoRepository.buscarPorId(id);
        if(!empleados.isEmpty()){
            return empleados.get(0);
        }
        return null;
        
    }

    public void delete (Integer id ){
        empleadoRepository.deleteEmpleadoById(id);
    }

    public Empleado save (Empleado empleado){
        return empleadoRepository.save(empleado);
                
        
    }

    public Empleado updateEmpleado (Integer id,Empleado e){
        Empleado emp = getEmpleadoById(id);
         if (emp!=null){
          emp.setId_empleado(e.getId_empleado());  
          emp.setNombre(e.getNombre());
          emp.setApellido(e.getApellido());
          emp.setSueldo(e.getSueldo());
          emp.setRol(e.getRol());

          return empleadoRepository.save(e);
          
         }
         return null;
    }

}

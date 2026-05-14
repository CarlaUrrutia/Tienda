package com.example.cupon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gerente.Repository.CuponRepository;
import com.example.gerente.model.Cupon;

//import com.example.Repository.CuponRepository;
//import com.example.model.Cupon;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CuponService {
    
    @Autowired
    private CuponRepository cuponRepo;

    public List<Cupon> getAllCupon(){
        return cuponRepo.findAll();
    }

    public Cupon getBoletayid_cupon(int id_cupon){
        List<Cupon> cu = cuponRepo.findByid_cupon(id_cupon);
        if (!cu.isEmpty()) {
            return cu.get(0);
        }
        return null;
    }

    
    public List<Cupon> getCuponRepo(String codigo){
        return cuponRepo.findBycodigo(codigo);
    }
    

    public Cupon createCupon(Cupon cup){
        return cuponRepo.save(cup);
    }

    public void deleteCupon(int id_cupon){
        cuponRepo.deleteById(id_cupon);
    }
    
    
    public Cupon updateCupon(int id_cupon){
        Cupon cupo= getClienteByid_cupon(id_cupon);
        if (c!=null) {
            cupo.setCodigo(cupo.getNombre());
            cupo.setDescuento(cupo.getApellido());
            return cuponRepo.save(c);   
        }
        return null;
    }
    
}

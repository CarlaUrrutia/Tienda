package com.example.ciudad.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gerente.Repository.CiudadRepository;
import com.example.gerente.model.Ciudad;

//import com.example.Repository.CiudadRepository;
//import com.example.model.Ciudad;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CiudadService {
    
    @Autowired
    private CiudadRepository ciudadRepo;

    public List<Ciudad> getAllCiudad(){
        return ciudadRepo.findAll();
    }

    public Ciudad getBoletayid_ciudad(int id_ciudad){
        List<Ciudad> ciu= ciudadRepo.findByid_ciudad(id_ciudad);
        if (!ciu.isEmpty()) {
            return ciu.get(0);
        }
        return null;
    }

    
    public List<Ciudad> getCiudadRepo(String nombre){
        return ciudadRepo.findByNombre(nombre);
    }
    

    public Ciudad createPerso(Ciudad ci){
        return ciudadRepo.save(ci);
    }

    public void deletePerso(int id_ciudad){
        ciudadRepo.deleteById(id_ciudad);
    }
    
    /*
    public Ciudad updatePerso(int id_ciudad){
        CIudad ciud = getPersoByRut(id_ciudad);
        if (ciud!=null) {
            ciud.setNombre(perso.getNombre());
            ciud.setEdad(perso.getEdad());
            ciud.setGenero(perso.getGenero());
            return ciudadRepo.save(per);   
        }
        return null;
    }
    */
    
}

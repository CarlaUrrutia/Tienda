package com.example.boleta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gerente.Repository.BoletaRepository;
import com.example.gerente.model.Boleta;

//import com.example.Repository.BoletaRepository;
//import com.example.model.Boleta;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BoletaService {
    
    @Autowired
    private BoletaRepository boletaRepo;

    public List<Boleta> getAllBoleta(){
        return boletaRepo.findAll();
    }

    public Boleta getBoletayid_boleta(int id_boleta){
        List<Boleta> boletas= boletaRepo.findByid_boleta(id_boleta);
        if (!boletas.isEmpty()) {
            return boletas.get(0);
        }
        return null;
    }

    /*
    public List<Boleta> getBoletaRepo(int id_boleta){
        return boletaRepo.findByNombre(id_boleta);
    }
    */

    public Boleta createBoleta(Boleta bol){
        return boletaRepo.save(bol);
    }

    public void deleteBoleta(int id_boleta){
        boletaRepo.deleteById(id_boleta);
    }
    
    //No se puede modificar la boleta
    /*public Boleta updatePerso(String rut, int id_boleta){
        Boleta bol= getPersoByRut(rut);
        if (per!=null) {
            bol.setNombre(perso.getNombre());
            bol.setEdad(perso.getEdad());
            bol.setGenero(perso.getGenero());
            return boletaRepo.save(per);   
        }
        return null;
    }
    */
}

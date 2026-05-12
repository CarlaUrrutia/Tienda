package com.example.gerente.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gerente.Repository.BoletaRepository;
import com.example.gerente.model.Boleta;

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

    public List<Boleta> getPersoRepo(String name){
        return boletaRepo.findByNombre(name);
    }

    public Boleta createPerso(Boleta perso){
        return boletaRepo.save(perso);
    }

    public void deletePerso(String rut){
        boletaRepo.deleteById(rut);
    }
    
    public Boleta updatePerso(String rut, Boleta perso){
        Boleta per= getPersoByRut(rut);
        if (per!=null) {
            per.setNombre(perso.getNombre());
            per.setEdad(perso.getEdad());
            per.setGenero(perso.getGenero());
            return persoRepo.save(per);   
        }
        return null;
    }

}

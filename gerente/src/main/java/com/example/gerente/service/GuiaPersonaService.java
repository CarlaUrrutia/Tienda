package com.example.gerente.service;

public class GuiaPersonaService {
    
}


/*
@Service
@Transactional
public class PersonaService {
    
    @Autowired
    private PersonaRepository persoRepo;

    public List<Persona> getAllPersonas(){
        return persoRepo.findAll();
    }

    public Persona getPersoByRut(String rut){
        List<Persona> personas= persoRepo.findByRut(rut);
        if (!personas.isEmpty()) {
            return personas.get(0);
        }
        return null;
    }

    public List<Persona> getPersoRepo(String name){
        return persoRepo.findByNombre(name);
    }

    public Persona createPerso(Persona perso){
        return persoRepo.save(perso);
    }

    public void deletePerso(String rut){
        persoRepo.deleteById(rut);
    }
    
    public Persona updatePerso(String rut, Persona perso){
        Persona per= getPersoByRut(rut);
        if (per!=null) {
            per.setNombre(perso.getNombre());
            per.setEdad(perso.getEdad());
            per.setGenero(perso.getGenero());
            return persoRepo.save(per);   
        }
        return null;
    }

}
*/
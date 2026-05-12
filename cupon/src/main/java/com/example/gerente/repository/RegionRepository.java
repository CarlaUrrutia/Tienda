package com.example.gerente.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.gerente.model.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {

    Optional<Region> findByid_region(int id_region);


    List<Region> findBynombre(Long nombre); 
}

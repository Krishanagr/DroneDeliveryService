package com.dda.drones.services;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Drone JPA for DB operations
 * 
 * @author Krishan AGRAWAL
 */

@Repository
public interface DroneRepository extends JpaRepository<Drone,Integer> {


}


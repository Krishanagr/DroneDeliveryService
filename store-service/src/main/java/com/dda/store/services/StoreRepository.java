package com.dda.store.services;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Store JPA for DB operations
 * 
 * @author Krishan AGRAWAL
 */

@Repository
public interface StoreRepository extends JpaRepository<Store,Integer> {

}


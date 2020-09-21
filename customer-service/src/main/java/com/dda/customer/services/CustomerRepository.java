package com.dda.customer.services;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Customer JPA for DB operations
 * 
 * @author Krishan AGRAWAL
 */

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {


}


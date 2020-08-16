package com.udemy.curso.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udemy.curso.domain.Payment;

@Repository
public interface PaymentDAO extends JpaRepository<Payment, Integer> {

}

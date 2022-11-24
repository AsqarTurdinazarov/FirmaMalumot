package com.example.firmamalumot.repository;

import com.example.firmamalumot.entity.Firma;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FirmaRepository extends JpaRepository<Firma,Integer> {
    boolean existsByFirmaNomi(String nomi);
}

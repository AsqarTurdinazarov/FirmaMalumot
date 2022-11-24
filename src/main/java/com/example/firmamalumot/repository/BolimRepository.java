package com.example.firmamalumot.repository;

import com.example.firmamalumot.entity.Bolim;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BolimRepository extends JpaRepository<Bolim,Integer> {
    boolean existsByNomiAndFirma_Id(String nomi,Integer firmaId);
}

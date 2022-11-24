package com.example.firmamalumot.repository;

import com.example.firmamalumot.entity.Ishchi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IshchiRepository extends JpaRepository<Ishchi,Integer> {
    boolean existsByTelRaqam(String telRaqam);
}

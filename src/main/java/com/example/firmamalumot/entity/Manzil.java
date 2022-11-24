package com.example.firmamalumot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Manzil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String viloyat;
    @Column(nullable = false)
    private String tuman;
    @Column(nullable = false)
    private String kocha;
    @Column(nullable = false)
    private String uyRaqami;
}
